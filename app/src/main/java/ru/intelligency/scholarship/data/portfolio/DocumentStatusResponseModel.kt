package ru.intelligency.scholarship.data.portfolio

import com.google.gson.annotations.SerializedName

data class DocumentStatusResponseModel(
    @SerializedName("id")
    val documentId: Int,
    @SerializedName("status")
    val status: Int
)
