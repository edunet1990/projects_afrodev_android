package com.eduardo.pokedex.api

import com.eduardo.pokedex.response.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("pokemon/")
    fun getPokemons(): Call<PokemonListResponse>

}

