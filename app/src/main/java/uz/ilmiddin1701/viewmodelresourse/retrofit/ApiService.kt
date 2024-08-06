package uz.ilmiddin1701.viewmodelresourse.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.ilmiddin1701.viewmodelresourse.models.MyMoney

interface ApiService {
    @GET("json")
    fun getAllMoney(): Call<List<MyMoney>>
}