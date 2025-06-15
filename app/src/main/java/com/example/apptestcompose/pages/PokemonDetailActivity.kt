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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.apptestcompose.R

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
        PokemonDetailTitle(pokemon)

        //description
        PokemonDetailDesc(
            title = stringResource(R.string.description_title),
            content = stringResource(R.string.description_content)
        )

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

@Composable
private fun PokemonDetailTitle(
    pokemon: PokemonModel
) {
    Text(
        text = pokemon.name,
        style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
private fun PokemonDetailDesc(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        HorizontalDivider(modifier = Modifier.padding(bottom = 0.dp))

        Text(
            text = title,
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = content,
            style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )

    }
}
