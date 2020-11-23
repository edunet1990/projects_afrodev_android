package com.eduardo.pokedex.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eduardo.pokedex.R
import com.eduardo.pokedex.response.Pokemon
import com.eduardo.pokedex.utils.Utility

class PokemonAdapter(private val context: Context, private var pokemonList: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.pokemon_item ,parent,false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position], context)
        var list = pokemonList[position]
        val user = pokemonList.get(position)
        var power: ImageView? = null

        //Deixa a primeira letra em maiusculo
        val name = Utility.capitalize(user.name)
        holder.name?.text = name

        //pega apenas os números de uma string
        var getIdFromUrl = user.url.filter { it.isDigit() }
        //remove o primeiro caracter
        var removerFirstNumber = getIdFromUrl.drop(1)
        var getId = removerFirstNumber.toInt()

        //Insere as # na frente do Id
        var idPokemon = Utility.idMask(getId)
        holder.url?.text = user.type

        //Envia a imagem
       Glide.with(holder.image?.getContext()).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${removerFirstNumber}.png").into(holder.image)
        power?.setImageDrawable(Utility.getImageByString(context, user.type))

    }

    class PokemonViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var id  = 0
        var name: TextView? = null
        var url: TextView? = null
        var image: ImageView? = null


        fun bind(pokemons: Pokemon, context: Context) {
            name = view.findViewById(R.id.name_pokemon)
            url = view.findViewById(R.id.id_pokemon)
            image = view.findViewById(R.id.img_pokemon)

        }







    }

}