package ru.intelligency.scholarship.data.portfolio

import com.google.gson.annotations.SerializedName

data class PostDocumentRequestBody(
    @SerializedName("id")
    val id: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("event_type")
    val eventType: String,
    @SerializedName("event_status")
    val eventStatus: String,
    @SerializedName("date_of_receipt")
    val dateOfReceipt: String,
    @SerializedName("event_place")
    val eventPlace: String,
    @SerializedName("filename")
    val fileName: String,
)
