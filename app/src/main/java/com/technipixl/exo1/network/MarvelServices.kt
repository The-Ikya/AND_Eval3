package com.technipixl.exo1.network

import com.technipixl.exo1.models.Heroes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


class MarvelServices  {
	private val URL = "https://gateway.marvel.com/v1/public/"
	private val retrofit = Retrofit.Builder()
		.baseUrl(URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(MarvelRoutes::class.java)

	private interface MarvelRoutes {
		@GET("characters")
		suspend fun heroList(
			@Query("apikey") apiKey: String,
			@Query("ts") timestamp: Long,
			@Query("hash") hash: String,
			@Query("limit") limit: Int
		) : Heroes

		@GET("characters/{id}")
		suspend fun comicsList(
			@Query("apikey") apiKey: String,
			@Query("ts") timestamp: Long,
			@Query("hash") hash: String,
			@Path("id") id: String
		) : Heroes

		@GET("comics/{id}")
		suspend fun comicsDetails(
			@Query("apikey") apiKey: String,
			@Query("ts") timestamp: Long,
			@Query("hash") hash: String,
			@Path("id") id: String
		) : String
	}

	suspend fun getHeroes(
		apiKey: String,
		timestamp: Long,
		hash: String,
		limit: Int
	) = retrofit.heroList(apiKey, timestamp, hash, limit)

	suspend fun getComics(
		apiKey: String,
		timestamp: Long,
		hash: String,
		id: String
	) = retrofit.comicsList(apiKey, timestamp, hash, id)

	suspend fun getDetails(
		apiKey: String,
		timestamp: Long,
		hash: String,
		id: String
	) = retrofit.comicsDetails(apiKey, timestamp, hash, id)
}


