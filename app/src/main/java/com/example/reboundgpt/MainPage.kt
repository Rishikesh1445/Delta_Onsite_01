package com.example.reboundgpt

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.reboundgpt.dataClass.NavigationItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainPage(viewModel: viewModel){

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val navControllerUser = rememberNavController()

    val navItems = listOf(
        NavigationItem(title = "Dummy1",route = "", selectedIcon = Icons.Filled.Person, unselectedIcon = Icons.Outlined.Person),
        NavigationItem(title = "Dummy2",route = "", selectedIcon = Icons.Filled.ExitToApp, unselectedIcon = Icons.Outlined.ExitToApp),
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                navItems.forEachIndexed{ index, item->
                    NavigationDrawerItem(
                        label = { Text(text = item.title, color = Color.White) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            navControllerUser.navigate(item.route)
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }},
                        icon = { Icon(
                            imageVector = if(index == selectedItemIndex){ item.selectedIcon }
                            else{item.unselectedIcon},
                            tint = Color.White,
                            contentDescription = item.title
                        )
                        },
                        badge = { item.badgeCount?.let { Text(text = it.toString()) }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Friendly Greeting", color = Color.White, fontFamily = FontFamily.SansSerif, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }
                        ) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = null, tint = Color.White)
                        }
                    })
            },

            ){
            var textField by remember{ mutableStateOf("") }
            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 16.dp, 16.dp, 75.dp)) {

                LazyColumn(contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)){
                    itemsIndexed(viewModel.toDisplay.value.texts){index, text->
                        if(text.isNotBlank()){
                            if(index == viewModel.toDisplay.value.texts.lastIndex){ TextCardRight(text = text, true, viewModel) }
                            else{ TextCardRight(text = text, false, viewModel) }
                            TextCardLeft(text = text)
                        }
                    }
                }

            }
            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)){
                OutlinedTextField(value = textField,
                    onValueChange ={ textField = it},
                    singleLine = true,
                    label = { Text(text = "Enter Prompt", color = Color.White) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedTextColor = Color.White,
                        focusedLeadingIconColor = Color.White,
                        focusedBorderColor = Color.White
                    ),
                    trailingIcon = { IconButton(onClick = { viewModel.submitButton(textField); textField="" }) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = "send")
                    }},
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}