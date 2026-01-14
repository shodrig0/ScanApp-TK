package com.rodrigov.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.rodrigov.myapplication.ui.navigation.AppNavGraph
import com.rodrigov.myapplication.ui.theme.ScanAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScanAppTheme {
                AppNavGraph()
            }
        }
    }
}
