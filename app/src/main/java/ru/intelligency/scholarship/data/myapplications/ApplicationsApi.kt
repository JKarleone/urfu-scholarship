package ru.intelligency.scholarship.data.myapplications

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApplicationsApi {

    @POST("/api/application/add")
    suspend fun postApplication(@Body application: CreateApplicationRequestModel)

    @GET("/api/application/getStatus")
    suspend fun getApplicationsStatuses(): Response<List<ApplicationStatusResponseModel>>
}
