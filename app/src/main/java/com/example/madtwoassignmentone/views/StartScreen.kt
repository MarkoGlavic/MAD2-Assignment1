package com.example.madtwoassignmentone.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.madtwoassignmentone.R
import com.example.madtwoassignmentone.navigation.NavigationDestination

object StartDestination : NavigationDestination {
    override val route = "start"
    override val titleRes = R.string.app_name
}

@Composable
fun StartScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomStart
    ) {
        FullScreenImage()
        FloatingActionButton(
            onClick = navigateToHome,
            modifier = Modifier
                .background(Color.Black)
        ) {
            Icon(Icons.Default.ExitToApp, contentDescription = "Start")
        }
    }
}
@Composable
fun FullScreenImage(modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.leaguereal)
    Box(modifier = modifier.fillMaxWidth()) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()

        )
    }
}


