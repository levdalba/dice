package com.example.dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dice.ui.theme.DiceTheme

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            DiceTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    DiceRollerApp(
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//fun DiceWithButtonAndImage(
//    modifier: Modifier = Modifier) {
//    var result by remember { mutableStateOf(1) }
//    val imageResource = when(result){
//        1 -> R.drawable.dice_1
//        2 -> R.drawable.dice_2
//        3 -> R.drawable.dice_3
//        4 -> R.drawable.dice_4
//        5 -> R.drawable.dice_5
//        else -> R.drawable.dice_6
//
//    }
//    Column(
//        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//        ){
//        Image(
//            painter = painterResource(imageResource),
//            contentDescription = result.toString()
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            result = (1..6).random()
//        }) {
//            Text(stringResource(R.string.roll))
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DiceRollerApp(modifier: Modifier = Modifier) {
//        DiceWithButtonAndImage(modifier = modifier.fillMaxSize())
//}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Apply padding from the scaffold to avoid overlap
                .background(MaterialTheme.colorScheme.background),
        ) {
            LemonadeScreen()
        }
    }
}

@Composable
fun LemonadeScreen(modifier: Modifier = Modifier) {
    var curr by remember { mutableIntStateOf(0) }
    var squeeze by remember { mutableIntStateOf(0) }

    val imageResource = when (curr) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textResource = when (curr) {
        0 -> R.string.lemon_tree
        1 -> R.string.lemon_squeeze
        2 -> R.string.lemon_drink
        else -> R.string.lemon_empty_glass
    }

    val contentDescriptionResource = when (curr) {
        0 -> R.string.lemon_tree_content_description
        1 -> R.string.lemon_content_description
        2 -> R.string.lemonade_content_description
        else -> R.string.empty_glass_content_description
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFC3ECD2))
                .clickable {
                    when (curr) {
                        0 -> {
                            curr = 1
                            squeeze = (2..4).random()
                        }

                        1 -> {
                            squeeze--
                            if (squeeze == 0) {
                                curr = 2
                            }
                        }

                        2 -> curr = 3
                        3 -> curr = 0
                    }
                }
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(id = contentDescriptionResource)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    DiceTheme {
        LemonadeApp()
    }
}
