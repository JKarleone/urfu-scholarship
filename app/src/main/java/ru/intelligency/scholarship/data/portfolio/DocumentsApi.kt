package ru.intelligency.scholarship.data.portfolio

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface DocumentsApi {

    @POST("/api/sert/add")
    suspend fun postDocument(@Body document: CreateDocumentRequestModel)

    @PUT("/api/sert/update/{id}")
    suspend fun putDocument(
        @Path("id") id: Int,
        @Body document: DocumentRequestModel
    )

    @DELETE("/api/sert/delete/{id}")
    suspend fun deleteDocument(
        @Path("id") id: Int
    )

    @POST("")
    suspend fun postDocumentImage(
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part
    )
}
