package ru.intelligency.scholarship.data.myapplications

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApplicationsApi {

    @POST("/api/application/add")
    suspend fun postApplication(@Body application: CreateApplicationRequestModel): Response<PostApplicationRequestBody>

    @GET("/api/application/getStatus")
    suspend fun getApplicationsStatuses(): Response<List<ApplicationStatusResponseModel>>

    @DELETE("/api/application/delete/{id}")
    suspend fun deleteApplication(@Path("id") applicationId: Int)
}
