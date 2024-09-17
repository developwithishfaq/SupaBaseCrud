package com.supa.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supa.base.data.utils.MySupaClient
import com.supa.base.data.utils.Users
import com.supa.base.ui.theme.SupaBaseCrudTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var userList by remember {
                mutableStateOf<List<Users>>(listOf())
            }

            LaunchedEffect(key1 = Unit) {
                val client = MySupaClient()
                userList = client.getAllUsers()
            }

            LazyColumn {
                item {
                    Text(text = "Users List")
                }
                items(userList){
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 10.dp)
                    ){
                        Text(text = it.name)
                        Text(text = it.email)
                    }
                }
            }

        }
    }
}