package com.example.hw3g_1

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw3g_1.ui.theme.Hw3g1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw3g1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){

                }
            }
        }
    }
}

@Composable
fun ListedTasks() {
    var taskDescription by remember{ mutableStateOf("") }
    var tasks by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(14.dp),

    ){
        TextField(
            value = taskDescription,
            onValueChange = {taskDescription = it},
            label = {Text("Please enter your task's description")},
            modifier = Modifier.fillMaxWidth(),
            keyboardActions = KeyboardActions(onDone = {
                if(taskDescription.text.isNotBlank()){
                    tasks.add(Task(description = taskDescription.text))
                    taskDescription = TextFieldValue("")
                }
            }),
            singleLine = true

        )

        Button(
            onClick = {
                if (taskDescription.text.isNotBlank()) {
                    tasks.add(Task(description = taskDescription.text))
                    taskDescription = TextFieldValue("")
                }
            },
            modifier = Modifier
                .padding(vertical = 7.dp)
                .fillMaxSize()
        ){
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(16.dp))
        TaskList(tasks = tasks, onTaskCheckedChange = {task, isChecked -> task.isCompleted = isChecked})




    }
}

@Composable
fun TaskList(tasks: String, onTaskCheckedChange: Any) {

}

@Composable
fun Task(){

}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hw3g1Theme {
      ListedTasks()
    }
}