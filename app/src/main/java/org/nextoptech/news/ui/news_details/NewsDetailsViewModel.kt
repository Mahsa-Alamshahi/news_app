package org.nextoptech.news.ui.news_details

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.nextoptech.news.utils.UiUtils
import javax.inject.Inject


@HiltViewModel
class NewsDetailsViewModel @Inject constructor() : ViewModel() {


    @Inject
    lateinit var uiUtils: UiUtils


    fun parseHtml(responseFieldValue: String?): String = uiUtils.parseHtml(responseFieldValue)

}