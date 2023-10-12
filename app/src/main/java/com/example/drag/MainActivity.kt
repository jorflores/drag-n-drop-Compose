package com.example.drag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.Px
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.Drag
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.drag.ui.theme.DragTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Drag()
                }
            }
        }
    }
}


@Preview(showBackground = true, device = "id:Nexus 10")
@Composable
private fun Drag() {
    Box(modifier = Modifier.fillMaxSize()) {
        var boxOffsetX by remember { mutableStateOf(1000f) }
        var boxOffsetY by remember { mutableStateOf(1000f) }



        var imageOffsetX by remember { mutableStateOf(0f) }
        var imageOffsetY by remember { mutableStateOf(0f) }


        //val anchorX = 200.dp // Adjust this value as needed

        Column {
            Box(
                Modifier
                    .offset { IntOffset(boxOffsetX.roundToInt(), boxOffsetY.roundToInt()) }
                    .background(Color.Blue)
                    .size(50.dp)
                    .clickable{

                    }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            boxOffsetX += dragAmount.x
                            boxOffsetY += dragAmount.y

                            // Check if the box has reached the anchor point
                           /* if (boxOffsetX >= anchorX.toPx()) {
                                boxOffsetX = anchorX.toPx()
                            }*/
                        }
                    }
            )

            Image(
                painter = painterResource(id = R.drawable.user_avatar),
                contentDescription = null,
                modifier = Modifier
                    .offset { IntOffset(imageOffsetX.roundToInt(), imageOffsetY.roundToInt()) }
                    .size(300.dp)
                    .padding(16.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            imageOffsetX += dragAmount.x
                            imageOffsetY += dragAmount.y
                        }
                    }
            )
        }
    }
}

