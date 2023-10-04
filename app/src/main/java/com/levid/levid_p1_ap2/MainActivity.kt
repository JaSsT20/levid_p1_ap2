package com.levid.levid_p1_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.levid.levid_p1_ap2.ui.counter.CounterScreen
import com.levid.levid_p1_ap2.ui.counter.CounterViewModel
import com.levid.levid_p1_ap2.ui.theme.Levid_p1_ap2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Levid_p1_ap2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: CounterViewModel =  hiltViewModel()
                    val counter by viewModel.counter.collectAsState(0)
                    CounterScreen(
                        counter = counter,
                        onIncrement = viewModel::increment
                    )
                }
            }
        }
    }
}