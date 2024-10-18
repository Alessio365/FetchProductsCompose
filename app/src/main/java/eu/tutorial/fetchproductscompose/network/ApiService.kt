package eu.tutorial.fetchproductscompose.network

import eu.tutorial.fetchproductscompose.data.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}

object RetrofitInstance {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}