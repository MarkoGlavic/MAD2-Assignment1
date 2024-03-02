package com.example.madtwoassignmentone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.madtwoassignmentone.navigation.ToDoNavHost
import com.example.madtwoassignmentone.ui.theme.MadTwoAssignmentOneTheme
import com.example.madtwoassignmentone.views.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myApplication = application as MyApplication
        val championRepository = myApplication.championRepository
        setContent {
            MadTwoAssignmentOneTheme {
                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoNavHost(navController = navController, championRepository = championRepository)

                }
            }
        }
    }
}