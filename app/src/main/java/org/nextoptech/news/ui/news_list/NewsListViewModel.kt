package org.nextoptech.news.ui.news_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.nextoptech.news.common.AppConstant
import org.nextoptech.news.common.Resource
import org.nextoptech.news.data.data_source.local.NewsEntity
import org.nextoptech.news.domain.usecase.AddNewsIntoDbUseCase
import org.nextoptech.news.domain.usecase.DeleteNewsFromDatabaseUseCase
import org.nextoptech.news.domain.usecase.GetNewsFromRemoteDataSourceUseCase
import org.nextoptech.news.domain.usecase.GetNewsListFromLocalDataSourceUseCase
import org.nextoptech.news.utils.QueryParams
import org.nextoptech.news.utils.UiUtils
import org.nextoptech.news.utils.sortBy
import javax.inject.Inject


@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsFromRemoteDataSourceUseCase: GetNewsFromRemoteDataSourceUseCase,
    private val getNewsListFromLocalDataSourceUseCase: GetNewsListFromLocalDataSourceUseCase,
    private val addNewsIntoDbUseCase: AddNewsIntoDbUseCase,
    private val deleteNewsFromDatabaseUseCase: DeleteNewsFromDatabaseUseCase,
    private val uiUtils: UiUtils
) : ViewModel() {


    var newsListState: MutableState<List<NewsEntity>> = mutableStateOf(listOf())
    val errorState = mutableStateOf("")
    val loading = mutableStateOf(false)
    val page = mutableIntStateOf(1)
    private var newsListScrollPosition = 0



    fun loadData() {
        viewModelScope.launch {
            loading.value = true
            resetState()
            val response = getNewsListFromRemoteDataSource()
            newsListState.value = response
            deleteNewsFromDb()
            addNewsIntoDb(newsListState.value)
            loading.value = false
        }
    }


    private fun resetState() {
        newsListState.value = emptyList()
        page.intValue = 1
        onChangeScrollPosition(0)
    }


    fun nextPage() {
        viewModelScope.launch {
            if ((newsListScrollPosition + 1) >= (page.intValue * AppConstant.PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                delay(1000)
                if (page.intValue > 1) {
                    val response = getNewsListFromRemoteDataSource()
                    appendNews(response)
                    addNewsIntoDb(response)
                }
                loading.value = false
            }
        }
    }

    private fun appendNews(articles: List<NewsEntity?>?) {
        val current = ArrayList(newsListState.value)
        articles?.let { current.addAll(it) }
        this.newsListState.value = current
    }


    private fun incrementPage() {
        page.intValue += 1
    }


    fun onChangeScrollPosition(position: Int) {
        newsListScrollPosition = position
    }


    private fun addNewsIntoDb(newsEntity: List<NewsEntity>) = viewModelScope.launch {
        addNewsIntoDbUseCase(newsEntity)
    }


    private fun deleteNewsFromDb() = viewModelScope.launch {
        deleteNewsFromDatabaseUseCase()
    }


    private suspend fun getNewsListFromRemoteDataSource(): List<NewsEntity> {
        val newsJobLost = mutableListOf<Job>()
        val newsListResponse = mutableListOf<NewsEntity>()
        var sortedNewsListResponse: List<NewsEntity> = emptyList()
        coroutineScope {
            QueryParams.entries.forEach { queryParam ->
                val job = launch {
                    val response =
                        getNewsFromRemoteDataSourceUseCase(
                            queryParam, uiUtils.getDaysAgo(1),
                            uiUtils.getTodayDate(), sortBy = sortBy, page = page.intValue
                        )

                    response.collect { result ->
                        when (result) {
                            is Resource.Loading -> {
                                loading.value = true
                            }

                            is Resource.Success -> {
                                newsListResponse.addAll(result.data ?: emptyList())
                            }

                            is Resource.Error -> {
                                errorState.value = result.message ?: "An unexpected error occured."
                            }
                        }
                    }
                }
                newsJobLost.add(job)
            }
            newsJobLost.joinAll()
            sortedNewsListResponse = newsListResponse.sortedBy { it.queryName?.priority }
        }
        return sortedNewsListResponse
    }


    fun getNewsListFromLocalDataSource() {
        viewModelScope.launch {
            val newsListFromLocalDataSource = getNewsListFromLocalDataSourceUseCase()
            newsListFromLocalDataSource.collect { localNewsList ->
                when (localNewsList) {
                    is Resource.Loading -> {
                        loading.value = true
                    }

                    is Resource.Success -> {
                        loading.value = false
                        if (localNewsList.data != null)
                            newsListState.value = localNewsList.data
                        else
                            newsListState.value = emptyList()
                    }

                    is Resource.Error -> {
                        loading.value = false
                    }
                }

            }

        }
    }


}