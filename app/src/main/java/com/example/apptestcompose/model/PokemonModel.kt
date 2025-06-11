package com.example.apptestcompose.model

import java.io.Serializable

data class PokemonModel(
    val name: String,
    val image: Int
) : Serializable