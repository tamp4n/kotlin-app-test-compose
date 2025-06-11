package com.example.apptestcompose.pages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.apptestcompose.pages.ui.theme.AppTestComposeTheme
import com.example.apptestcompose.R
import com.example.apptestcompose.model.PokemonModel
import com.example.apptestcompose.model.getData

class PokemonActivity : ComponentActivity() {
    /**
     * Explanation of the onCreate Function in PokemonActivity
     * The onCreate function in PokemonActivity is a lifecycle method that
     * initializes the activity when it's created.
     *
     * Here's a breakdown of what it does:
     * Method Signature:
     *      override fun onCreate(savedInstanceState: Bundle?) - Overrides the standard Android Activity lifecycle method
     *      savedInstanceState parameter contains the activity's previously saved state if available
     * Key Components:
     *      super.onCreate(savedInstanceState) - Calls the parent class implementation first
     *      enableEdgeToEdge() - Enables edge-to-edge display, extending content to draw behind system bars
     *      setContent { } - Defines the Jetpack Compose UI content for this activity
     * UI Structure:
     *      AppTestComposeTheme { } - Applies the app's theme to all composable content within
     *      Scaffold() - Creates a basic material design layout structure
     *      Modifier.fillMaxSize() - Makes the Scaffold fill the entire screen
     *      innerPadding - Padding values provided by Scaffold to avoid overlapping with system UI elements
     *      PokemonContent() - The main composable function that displays the list of Pokémon
     *
     * The function essentially sets up the activity to display a Jetpack Compose UI
     * showing a scrollable list of Pokémon using the PokemonContent composable,
     * all while applying proper theming and edge-to-edge display.
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTestComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokemonContent(
                        context = this,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

/**
 * The PokemonContent function is a Jetpack Compose @Composable function
 * that displays a scrollable list of Pokémon.
 *
 * Breaking down this function:
 *
 * Parameters:
 *      modifier: Modifier = Modifier - An optional Compose modifier that can be used
 *      to adjust the layout. It has a default empty value.
 * Data Retrieval:
 *      val pokemons: List<PokemonModel> = getData() - Fetches a list of Pokémon data models.
 * LazyColumn:
 *      Creates a vertically scrolling list (similar to RecyclerView in traditional Android)
 *      Applies 16dp padding on all sides (contentPadding)
 *      Passes through any additional modifiers from the function parameter
 * List Rendering:
 *      Uses the items() scope function to iterate through the Pokémon list
 *      For each Pokémon in the list, renders a PokemonItem composable, passing the current Pokémon model
 *
 * This function serves as a container that efficiently displays a scrollable list of Pokémon cards,
 * with each card being rendered by the PokemonItem composable function.
 */
@Composable
fun PokemonContent(
    context: Context,
    modifier: Modifier = Modifier
) {

    val pokemons: List<PokemonModel> = getData()
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        modifier = modifier
    ) {
        items(
            items = pokemons,
            itemContent = { pokemon ->
                PokemonItem(pokemon) {
                    context.startActivity(
                        Intent(
                            context, PokemonDetailActivity::class.java
                        ).putExtra("intent_pokemon", pokemon),
                        null
                    )
                }
            }
        )
    }
}

/**
 * The PokemonItem is a Jetpack Compose @Composable function that renders a card display for a Pokémon.
 *
 * The function:
 *
 * Takes a pokemon parameter of type PokemonModel as input
 * Creates a Card with:
 *      Padding around the edges
 *      Maximum width to fill available space
 *      Subtle elevation (2dp) for a shadow effect
 *      White content color
 *      Rounded corners (16dp)
 * Inside the card, it arranges content in a Row with:
 *      A PokemonImage component on the left that displays the Pokémon's image
 *      A Column on the right containing:
 *          The Pokémon's name in a headline style
 *          A "View Details" text in a smaller body style
 *
 * This function is used within a LazyColumn in the PokemonContent composable
 * to display a scrollable list of Pokémon items.
 *
 */
@Composable
fun PokemonItem(
    pokemon: PokemonModel,
    navigateToDetails: (PokemonModel) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.elevatedCardColors(contentColor = Color.White),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(
            modifier = Modifier.clickable {
                navigateToDetails(pokemon)
            }
        ) {
            PokemonImage(pokemon = pokemon)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = pokemon.name,
                    style = typography.headlineMedium,
                    color = Color.Black
                )
                Text(
                    text = "View Details",
                    style = typography.bodyMedium,
                    color = Color.Black
                )
            }
        }

    }
}

/**
 * Composable function to display an image for a Pokemon.
 *
 * @param pokemon The PokemonModel object containing information about the Pokemon.
 */
@Composable
fun PokemonImage(pokemon: PokemonModel) {
    Image(
//        painter = painterResource(id = R.drawable.pikachu),
        painter = painterResource(id = pokemon.image),

        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(84.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}