package com.punksoft.practicea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.punksoft.practicea.ui.theme.PracticeATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticeATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InputScreen(modifier = Modifier.statusBarsPadding())
                }
            }
        }
    }
}

@Composable
fun InputScreen(
    modifier: Modifier = Modifier,
    vm: MyViewModel = viewModel()
) {
    val output by vm.input.collectAsState()
    var input by rememberSaveable { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxHeight().padding(15.dp)
    ) {
        Text(
            text = if (output.isBlank()) "No data" else output,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Spacer(Modifier.height(35.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = input,
            onValueChange = { value ->
                input = value
            },
            label = { Text("Enter value") }
        )

        Spacer(Modifier.height(15.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                vm.setInput(input)
            }
        ) {
            Text(
                text = "Save input",
                fontSize = 16.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticeATheme {
        InputScreen()
    }
}