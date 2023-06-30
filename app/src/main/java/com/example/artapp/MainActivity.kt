package com.example.artapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artapp.ui.theme.ArtAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtAppLayout()
                }
            }
        }
    }
}

@Composable
fun ArtAppLayout(modifier: Modifier = Modifier) {

    var artCount by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        when (artCount) {

            1 -> {
                PicWithText(
                    author = R.string.first_author,
                    description = R.string.first_description,
                    pic = R.drawable.ic_android_24,
                )
            }

            2 -> {
                PicWithText(
                    author = R.string.second_author,
                    description = R.string.second_description,
                    pic = R.drawable.ic_castle_24,
                )
            }

            3 -> {
                PicWithText(
                    author = R.string.third_author,
                    description = R.string.third_description,
                    pic = R.drawable.ic_forest_24,
                )
            }
        }

        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonArt(
                onClick = {
                    artCount = if (artCount == 1) 3 else artCount - 1
                },
                idString = R.string.btn_prev
            )
            ButtonArt(
                onClick = {
                    artCount = if (artCount == 3) 1 else artCount + 1
                },
                idString = R.string.btn_next
            )
        }
    }
}

@Composable
fun PicWithText(
    @StringRes author: Int,
    @StringRes description: Int,
    @DrawableRes pic: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = pic),
            contentDescription = stringResource(id = description),
            modifier = Modifier
                .padding(32.dp)
                .shadow(
                    shape = RoundedCornerShape(8.dp),
                    elevation = 10.dp
                )
                .background(color = Color.White)
                .size(150.dp)

        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.teal_700))
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = author),
                fontWeight = FontWeight.Bold,
                modifier = modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = description)
            )
        }

    }
}

@Composable
fun ButtonArt(
    onClick: () -> Unit,
    @StringRes idString: Int
) {

    Button(onClick = onClick) {
        Text(text = stringResource(id = idString))
    }

}

@Preview(showBackground = true)
@Composable
fun ArtAppLayoutPreview() {
    ArtAppTheme {
        ArtAppLayout()
    }
}