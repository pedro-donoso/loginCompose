package com.example.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.logincompose.ui.theme.LoginComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginForm()
        }
    }
}

@Composable
fun Screen(content: @Composable () -> Unit) {
    LoginComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 20.dp),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@Preview
@Composable
fun LoginForm() {
    Screen {
        Text(text = "Login")
    }
}





