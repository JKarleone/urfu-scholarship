package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import ru.intelligency.scholarship.data.portfolio.ImageProvider
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.domain.portfolio.model.Document

class NewDocumentViewModel(
    private val interactor: PortfolioInteractor,
    private val imageProvider: ImageProvider
) : ViewModel() {

    var image: Bitmap? = null

    fun getDefaultEventTypes(): List<String> {
        return interactor.getDefaultEventTypes()
    }

    fun getDefaultEventStatuses(): List<String> {
        return interactor.getDefaultEventStatuses()
    }

    suspend fun createDocument(document: Document) {
        val newDocument = image?.let { img ->
            document.copy(fileName = imageProvider.createNewImage(img))
        } ?: document
        interactor.createDocument(newDocument)
        image = null
    }
}
