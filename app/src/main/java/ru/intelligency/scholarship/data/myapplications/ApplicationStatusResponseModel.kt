package ru.intelligency.scholarship.data.myapplications

import com.google.gson.annotations.SerializedName

data class ApplicationStatusResponseModel(
    @SerializedName("id")
    val applicationId: Int,
    @SerializedName("status")
    val status: Int
)
