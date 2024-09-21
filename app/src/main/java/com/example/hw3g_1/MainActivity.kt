package com.example.hw3g_1

import android.os.Bundle
import android.widget.CheckBox
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.hw3g_1.ui.theme.Hw3g1Theme
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hw3g1Theme {
                    ListedTasks()
                }
            }
        }
    }


@Composable
fun ListedTasks() {
    var taskDescription by remember{ mutableStateOf("")}
    var taskList by remember { mutableStateOf(listOf<Task>())}

    Column(
        modifier = Modifier.fillMaxSize().padding(14.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

      TaskInputField(
          taskDescription = taskDescription,
          onTaskDescriptionChange = {taskDescription = it}
        )

        Spacer(modifier = Modifier.height(10.dp))

        AddTaskButton(
            onClick = {
                if (taskDescription.isNotEmpty()) {
                    taskList = taskList + Task(taskDescription, false)
                    taskDescription = ""
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TaskList(taskList) { updatedTask ->
            taskList = taskList.map { task ->
                if (task.description == updatedTask.description) updatedTask else task
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ClearCompletedButton(
            onClick = {
                taskList = taskList.filter { !it.isComplete }
            }
        )
    }
}

data class Task(val description: String, var isComplete: Boolean)


@Composable
fun TaskInputField(taskDescription: String, onTaskDescriptionChange: (String) -> Unit) {
    TextField(
        value = taskDescription,
        onValueChange = onTaskDescriptionChange,
        label = { Text(text = "Enter Task Description") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AddTaskButton(onClick: () -> Unit) {
    Button(onClick = onClick){
        Text(text = "Add Task")
    }
}

@Composable
fun ClearCompletedButton(onClick: () -> Unit) {
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)){
        Text(text = "Clear Completed", color = Color.White)
    }
}

@Composable
fun TaskList(
    taskList: List<Task>,
    onTaskToggle: (Task) -> Unit
){
    LazyColumn{
            items(taskList.size){
                index ->
                TaskRow(
                    task = taskList[index],
                    onTaskToggle = onTaskToggle
                )
            }
            }
        }


@Composable
fun TaskRow(
    task: Task,
    onTaskToggle: (Task) -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,

    ){
        Checkbox(
            checked = task.isComplete,
            onCheckedChange = { isChecked ->
                onTaskToggle(task.copy(isComplete = isChecked))
            }
        )

        val textDecoration = if(task.isComplete) TextDecoration.LineThrough else TextDecoration.None
        val textColor = if(task.isComplete) Color.Gray else Color.Black

        Text(
            text = task.description,
            color = textColor,
            textDecoration = textDecoration,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hw3g1Theme {
        ListedTasks()
    }
}
