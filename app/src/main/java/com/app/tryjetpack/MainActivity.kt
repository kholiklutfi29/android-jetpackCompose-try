package com.app.tryjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.expandVertically
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.tryjetpack.data.Answer
import com.app.tryjetpack.data.AnswerObject
import com.app.tryjetpack.ui.theme.OnBoarding
import com.app.tryjetpack.ui.theme.TryJetpackTheme
import com.app.tryjetpack.ui.theme.Typography

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TryJetpackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    MyApps(Modifier.fillMaxSize())
//                    Column (
//                        modifier = Modifier
//                            .height(300.dp)
//                            .width(500.dp)
//                            .background(Color.LightGray),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        CustomItem(weight = 3f, Color = MaterialTheme.colorScheme.secondary)
//                        CustomItem(weight = 1f, Color = MaterialTheme.colorScheme.primary)
//                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.CustomRow(weight: Float, Color: Color = MaterialTheme.colorScheme.primary){
    Surface (
        modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .weight(weight),
        color = Color
    ){}
}

@Composable
fun AnswerCard(answer: Answer){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .height(50.dp),
        shape = RoundedCornerShape(4.dp),
        shadowElevation = 5.dp,
    ) {
        Row (
            modifier = Modifier
                .padding(all = 8.dp)
        ){
            Image(
                painter = painterResource(id = answer.drawable),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RectangleShape)
                    .clip(RoundedCornerShape(size = 4.dp))
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = answer.text,
                modifier = Modifier.padding(all = 4.dp)
            )

            Spacer(modifier = Modifier.width(170.dp))

            RadioButton(
                selected = false,
                onClick = { /*TODO*/ }
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun AnswerCollection(ans: List<Answer>){
    LazyColumn{
        items(ans) {message ->
            AnswerCard(message)
        }
    }
}



@Composable
fun ColumnScope.CustomItem(weight: Float, Color: Color = MaterialTheme.colorScheme.primary){
    Surface (
        modifier = Modifier
            .height(50.dp)
            .width(50.dp)
            .weight(weight),
        color = Color
    ){}
}


@Preview
@Composable
fun PreviewAnswer(){
    TryJetpackTheme {
        AnswerCollection(AnswerObject.answerList)
    }
}

@Composable
fun GreetingPreview() {
    TryJetpackTheme {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomRow(weight = 3f, Color = MaterialTheme.colorScheme.secondary)
            CustomRow(weight = 1f, Color = MaterialTheme.colorScheme.primary)
        }
    }
}

// Try

@Composable
fun Greeting(name: String){
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val extraPadding = if (isExpanded) 48.dp else 0.dp

    Surface (
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Row (
            modifier = Modifier
                .padding(24.dp)
        ){
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            ElevatedButton(onClick = { isExpanded = !isExpanded }) {
                Text(text = if (isExpanded) "Show more" else "Show less")
            }
        }
    }
}

@Composable
fun Greetings(){
    val listName: List<String> = listOf("World", "Compose")
    Surface (
        modifier = Modifier
            .padding(vertical = 2.dp)
    ) {
        Column (modifier = Modifier.padding(vertical = 4.dp)) {
            for (name in listName){
                Greeting(name = name)
            }
        }
    }
}



@Composable
fun MyApps(modifier: Modifier = Modifier){
    var shouldShowOnBoarding by remember { mutableStateOf(true)}
    if (shouldShowOnBoarding){
        OnBoarding(onContinueClicked = { shouldShowOnBoarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun OnBoarding(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier
                .padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}














