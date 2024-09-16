package com.example.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.logincompose.ui.theme.LoginComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "login") {
                composable("login") {
                    LoginForm(onLogin = {
                        navController.navigate("main")
                    })
                }
                composable("main") {
                    Main()
                }
            }
        }
    }
}

@Preview
@Composable
fun Main(){
    Screen {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Main Screen",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Composable
fun LoginForm(onLogin: () -> Unit) {
    Screen {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            var user by remember { mutableStateOf("")}
            var pass by remember { mutableStateOf("")}
            val buttonEnable = user.isNotEmpty() && pass.isNotEmpty()

            UserField(value = user, onValueChange = { user = it})
            PasswordField(value = pass, onValueChange = { pass = it})
            LoginButton(buttonEnable, onLogin)
        }
    }
}

@Composable
fun UserField(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "User") }
    )
}

@Composable
fun PasswordField(value: String, onValueChange: (String) -> Unit) {
    var passVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "Password") },
        visualTransformation = if(passVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(checked = passVisible, onCheckedChange = { passVisible = it }) {
                val icon = if(passVisible) Icons.Default.Lock else Icons.Default.Done
                Icon(imageVector = icon, contentDescription = null)
            }
        }
    )
}

@Composable
fun LoginButton(enable: Boolean, onLogin: () -> Unit) {
    Button(
        onClick = onLogin,
        enabled = enable
    ) {
        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
        Text(text = "Login")
    }
}

@Composable
fun Screen(content: @Composable () -> Unit) {
    LoginComposeTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 20.dp),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}







