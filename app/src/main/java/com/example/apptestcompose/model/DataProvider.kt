package com.example.apptestcompose.model

import com.example.apptestcompose.R

fun getData(): List<PokemonModel> {
    return arrayListOf(
        PokemonModel(
            "Pikachu",
            R.drawable.pikachu
        ),
        PokemonModel(
            "Pokeball",
            R.drawable.pokeball
        ),
        PokemonModel(
            "Bulbasaur",
            R.drawable.bullbasaur
        ),
        PokemonModel(
            "Charmander",
            R.drawable.charmander
        ),
        PokemonModel(
            "Squirtle",
            R.drawable.squirtle
        ),
        PokemonModel(
            "Pidgey",
            R.drawable.pidgey
        ),
        PokemonModel(
            "Eevee",
            R.drawable.eevee
        ),
        PokemonModel(
            "Snorlax",
            R.drawable.snorlax
        ),
        PokemonModel(
            "Rattata",
            R.drawable.rattata
        ),
        PokemonModel(
            "Zubat",
            R.drawable.zubat
        ),
    )
}
