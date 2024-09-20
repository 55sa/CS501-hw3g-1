package com.example.hw3g_1

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hw3g_1.ui.theme.Hw3g1Theme
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hw3g1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    ListedTasks()
                }
            }
        }
    }
}

@Composable
fun ListedTasks() {
    var taskDescription by remember{ mutableStateOf(TextFieldValue("")) }
    var tasks by remember { mutableStateOf(mutableListOf<Task>())}

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

        ){
            Text("Add Task")
        }

        Spacer(modifier = Modifier.height(18.dp))

        TaskList(tasks = tasks, onTaskCheckedChange = { task, isChecked -> task.isCompleted = isChecked})

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = {
                tasks = tasks.filter { !it.isCompleted }.toMutableList()
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Clear Completed")
        }
    }
}

data class Task(
    val description: String,
    var isCompleted: Boolean = false
)
@Composable
fun TaskList(
    tasks: List<Task>,
    onTaskCheckedChange: (Task, Boolean) -> Unit
){
        Column(
            modifier = Modifier.fillMaxWidth()) {
            tasks.forEach { task ->
                TaskRow(task = task, onCheckedChange = { isChecked ->
                    onTaskCheckedChange(task, isChecked)
                })
            }
        }
    }


@Composable
fun TaskRow(task: Task,
            onCheckedChange: (Boolean) -> Unit  ){

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hw3g1Theme {
      ListedTasks()
    }
}