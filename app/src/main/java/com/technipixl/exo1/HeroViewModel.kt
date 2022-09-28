package com.technipixl.exo1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technipixl.exo1.models.Heroes
import com.technipixl.exo1.network.MarvelServices
import kotlinx.coroutines.launch
import java.util.*

class HeroViewModel : ViewModel() {
	private val publicKey = "1e8808e481b43dd7c6451da5ea93d0f8"
	private val privateKey = "5ded1e41137a351ab31ac651bfd3a5b00c869623"

	private val timestamp get() = Date().time
	private val hash get() = HashGenerator.generateHash(timestamp, privateKey, publicKey) ?: ""

	private val _heroList = MutableLiveData<List<Heroes.Data.Hero>>(listOf())
	val heroList get() = _heroList

	private val _comicsList = MutableLiveData<List<Heroes.Data.Hero.Comics.Comic>>(listOf())
	val comicsList get() = _comicsList

	private val _comicsDetails = MutableLiveData<String>("")
	val comicsDetails get() = _comicsDetails

	private val network = MarvelServices()

	fun getHeroList(limit: Int = 100) {
		viewModelScope.launch {
			try {
				val response = network.getHeroes(publicKey, timestamp, hash, limit)
				response.data?.results.let { _heroList.value = it }
			} catch (e: Exception) {
				_heroList.value = listOf()
			}
		}
	}

	fun getComicsList(id: String) {
		viewModelScope.launch {
			try {
				val response = network.getComics(publicKey, timestamp, hash, id)
				response.data?.results?.get(0)?.comics?.items.let { _comicsList.value = it }
			} catch (e: Exception) {
				_comicsList.value = listOf()
			}
		}
	}

	fun getComicsDetails(id: String) {
		viewModelScope.launch {
			try {
				_comicsDetails.value = network.getDetails(publicKey, timestamp, hash, id)
			} catch (e: Exception) {
				_comicsDetails.value = ""
			}
		}
	}
}