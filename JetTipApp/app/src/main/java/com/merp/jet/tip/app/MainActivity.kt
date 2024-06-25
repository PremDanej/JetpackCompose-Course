package com.merp.jet.tip.app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.merp.jet.tip.app.components.InputField
import com.merp.jet.tip.app.widgets.RoundIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                TopHeader()
            }

        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier, color = MaterialTheme.colorScheme.background) {
        content()
    }
}


@Preview()
@Composable
fun TopHeader(totalPerPerson: Double = 10.0) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(shape = CircleShape.copy(all = CornerSize(20.dp)))
            .background(color = Color.Blue),
        color = Color(0xFFE9D7F7)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            val total = "%.2f".format(totalPerPerson)
            Text(
                text = "Total Per Person",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "₹${total}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContent() {
    BillForm() { billAmount ->
        Log.d("AMT", "MainContent $billAmount")
    }

}

@Preview(showBackground = true)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    onValChange: (String) -> Unit = { }
) {

    val totalBillState = remember { mutableStateOf("") }
    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }
    val counter = remember {
        mutableStateOf(0)
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
        ) {
            InputField(
                valueState = totalBillState,
                label = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if (!validState) return@KeyboardActions
                    onValChange(totalBillState.value.trim())
                    keyboardController?.hide()
                })

            if (!validState) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Split",
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        RoundIconButton(
                            imageVector = Icons.Default.Remove,
                            modifier = Modifier.size(50.dp),
                            onClick = {
                                if(counter.value > 0){
                                    counter.value--
                                }
                                Log.d("ICON ", "Icon plus pressed")
                            })

                        Text(
                            text = "${counter.value}",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(horizontal = 10.dp)
                        )

                        RoundIconButton(
                            imageVector = Icons.Default.Add,
                            modifier = Modifier.size(50.dp),
                            onClick = {
                                counter.value++
                                Log.d("ICON ", "Icon minus pressed")
                            })
                    }
                }
            } else {
                Box {

                }
            }
        }
    }

}


@Composable
fun TextPreview() {
    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.titleSmall
        )

        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Hello Prem",
            style = MaterialTheme.typography.labelSmall
        )
    }
}