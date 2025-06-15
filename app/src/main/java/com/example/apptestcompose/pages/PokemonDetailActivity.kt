package com.example.apptestcompose.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.apptestcompose.model.PokemonModel
import com.example.apptestcompose.pages.ui.theme.AppTestComposeTheme
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class PokemonDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val pokemon: PokemonModel = intent.getSerializableExtra("intent_pokemon") as PokemonModel
        Log.d("PokemonDetailActivity", "intent_pokemon: ${pokemon.toString()}")
        setContent {
            AppTestComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokemonDetailContent(
                        modifier = Modifier.padding(innerPadding),
                        pokemon
                    )
                }
            }
        }
    }
}

@Composable
private fun PokemonDetailContent(
    modifier: Modifier = Modifier,
    pokemon: PokemonModel
) {
    Column(modifier = modifier) {
        //image
        PokemonDetailImage(pokemon)
        
        //text
        //description

    }

}

@Composable
private fun PokemonDetailImage(
    pokemon: PokemonModel
) {
    Image(
        painter = painterResource(id = pokemon.image),

        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
    )
}
