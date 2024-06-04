import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import leagueofscore.composeapp.generated.resources.Res
import leagueofscore.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun Home() {
    var offsetY by remember { mutableStateOf(0f) }
    val animatedOffsetY by animateDpAsState(
        targetValue = offsetY.dp,
        animationSpec = tween(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF000000),
                        Color(0xFF000000)
                    )
                )
            )
    )
        {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .offset(y = animatedOffsetY)
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onDragEnd = { offsetY = 0f },
                            onDragCancel = { offsetY = 0f },
                            onVerticalDrag = { _, dragAmount ->
                                offsetY += dragAmount
                            }
                        )
                    }
            ) {
                Image(
                    painter = painterResource(Res.drawable.logo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(alignment = Alignment.TopCenter)
                        .clip(RoundedCornerShape(24.dp))
                )
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.Black, shape = RoundedCornerShape(12.dp))
                        .padding(16.dp)
                        .height(IntrinsicSize.Min)
                        .scrollable(
                            orientation = Orientation.Vertical,
                            state = rememberScrollableState { delta ->
                                val newValue = delta + 1
                                if (newValue < 0) 0F else newValue
                            },
                            enabled = true
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Bem vindo ao               " +
                                "League of Scores,           " +
                                "o aplicativo da comunidade.",
                        color = Color(0xFFF5FFCC),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.Default
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .offset(y = animatedOffsetY)
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onDragStart = { /* Optionally handle drag start */ },
                        onDragEnd = { offsetY = 0f },
                        onDragCancel = { offsetY = 0f },
                        onVerticalDrag = { _, dragAmount ->
                            offsetY += dragAmount
                        }
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(Color.Black, shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
                    .height(IntrinsicSize.Min),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(16.dp)),
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("loginButton")
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Fazer Login",
                            color = Color(0xFF000000),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clip(RoundedCornerShape(16.dp)),
                )
                {
                    Button(
                        onClick = {
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("registerButton")
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Criar Conta",
                            color = Color(0xFF000000),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))
            }
        }
    }