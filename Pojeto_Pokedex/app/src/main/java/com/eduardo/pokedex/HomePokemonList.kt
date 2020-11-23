package com.eduardo.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduardo.pokedex.adapters.PokemonAdapter
import com.eduardo.pokedex.api.ApiPokedex
import com.eduardo.pokedex.response.PokemonListResponse
import com.eduardo.pokedex.response.Pokemon
import com.eduardo.pokedex.utils.Utility.hideProgressBar
import com.eduardo.pokedex.utils.Utility.isInternetAvailable
import com.eduardo.pokedex.utils.Utility.showProgressBar
import kotlinx.android.synthetic.main.activity_home_pokemon_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePokemonList : AppCompatActivity() {

        private var adapter: PokemonAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_pokemon_list)
        supportActionBar?.hide()

        if (isInternetAvailable()) {
            getUsersData()
        }

    }

    private fun getUsersData() {

        showProgressBar()

        ApiPokedex.apiService.getPokemons().enqueue(object : Callback<PokemonListResponse> {
            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                hideProgressBar()
                Log.e("error", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                hideProgressBar()
                response.body()?.let {
                    setList(it.results)
                }

            }

        })
    }

    private fun setList(list: List<Pokemon>){

        recycler_view_main.layoutManager = LinearLayoutManager(this@HomePokemonList)
        adapter = PokemonAdapter(
            this,
            list
        )

        recycler_view_main.adapter = adapter
    }
}