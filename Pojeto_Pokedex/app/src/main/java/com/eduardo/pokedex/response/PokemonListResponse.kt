package com.eduardo.pokedex.response

data class PokemonListResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Pokemon>
)