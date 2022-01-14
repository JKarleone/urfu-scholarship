package ru.intelligency.scholarship.data.portfolio

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface DocumentsApi {

    @GET("/api/sert/getStatus")
    suspend fun getDocumentsStatuses(): Response<List<DocumentStatusResponseModel>>

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

    @Multipart
    @POST("/api/sert/add/img")
    suspend fun postDocumentImage(
        @Part file: MultipartBody.Part
    )
}
