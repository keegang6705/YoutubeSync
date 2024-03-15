package com.colstu.youtubesync


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.colstu.youtubesync.ui.theme.YoutubeSync
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoutubeSync {
                Screen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showNewItemPage by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight()
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Column( //profile
                        modifier = Modifier
                            .height(180.dp)
                            .fillMaxWidth()
                    ) {
                        Box( //padding
                            modifier = Modifier
                                .height(30.dp)
                                .align(alignment = Alignment.CenterHorizontally)
                                .fillMaxWidth()
                        )
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Account",
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight(0.5f)
                        )
                        Text(
                            text = "Profile",
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            modifier = Modifier
                                .align(alignment = Alignment.CenterHorizontally)
                                .fillMaxWidth()
                        )
                    }
                    Box( //line
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth(0.8f)
                            .align(alignment = Alignment.CenterHorizontally)
                            .border(2.dp, Color.White, RoundedCornerShape(0.dp))
                    )
                    Box( //padding
                        modifier = Modifier
                            .height(10.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                            .fillMaxWidth()
                    )
                    Button(
                        onClick = { /*TODO: Add action*/ },
                        content = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Settings,
                                    contentDescription = "Settings",
                                    modifier = Modifier.padding(end = 10.dp),
                                    tint = Color.White
                                )
                                Text(
                                    text = "Settings",
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            }
                        },
                        shape = RoundedCornerShape(0.dp),
                        colors = ButtonDefaults.buttonColors(Color.Transparent),
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()

                    )

                }

            }
        },
    ) {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "YoutubeSync") },
                    colors = topAppBarColors(containerColor = Color(0xFF2196F3)),
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "menu"
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                if (!showNewItemPage) {
                FloatingActionButton(
                    onClick = { showNewItemPage = true },
                    containerColor = Color(0xFF2196F3),
                    modifier = Modifier.padding(bottom = 30.dp, end = 10.dp)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Add new item"
                    )

                }
                } else {NewItemPage{showNewItemPage = false}}
            },
        ) {
            Column(
//body
                modifier = Modifier
                    .padding(it)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
            ) {
                ItemConstructor()


            }
        }
    }

}

@Composable
fun NewItemPage(onClose: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var link by remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .offset(16.dp, 16.dp)
            .background(Color.Black.copy(alpha = 0.5f))

    ) {

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.9f)
                .absoluteOffset(0.dp)
                .border(2.dp, Color.White, RoundedCornerShape(10.dp))
                .align(alignment = Alignment.Center)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black)


        ) {
            Column {
                Box(
                    modifier = Modifier.height(30.dp)
                )
                Text(
                    text = "Add New Item",
                    color = Color.White,
                    fontSize = 20.sp
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                OutlinedTextField(
                    value = link,
                    onValueChange = { link = it },
                    label = { Text("Url") }
                )
            }
            Box(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { onClose() },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF389FF1)),
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 15.dp, 15.dp)
                        .border(2.dp, Color.White, RoundedCornerShape(10.dp))
                        .height(50.dp)
                        .align(alignment = Alignment.Center)
                ) {
                    Text(text = "Add", color = Color.White)
                }
                Button(
                    onClick = { onClose() },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFEC3636)),
                    contentPadding =  PaddingValues(0.dp),
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 15.dp, 15.dp)
                        .border(2.dp, Color.White, RoundedCornerShape(10.dp))
                        .height(50.dp)
                        .width(50.dp)
                        .align(alignment = Alignment.BottomEnd)
                ) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Close",
                        tint = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun ItemConstructor() {
 ItemBox()
}

@Composable
fun ItemBox(
    name: String = "Name",
    link: String = "https://keegang.000.pe/home",
    id: String = "0",
    active: Boolean = true,
    onActiveChange: (Boolean) -> Unit = {},
) {
    var switchState by remember { mutableStateOf(active) }
    Box(
        modifier = Modifier
            .padding(10.dp, 10.dp, 10.dp, 0.dp)
            .height(100.dp)
            .border(2.dp, Color.White, RoundedCornerShape(10.dp))
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
        ) {

            Column(
                modifier = Modifier.align(alignment = Alignment.TopStart),
            ) {
                Text(text = name)
                Text(text = "id:$id", color = Color.Gray)
                HyperlinkText(
                    text = link,
                    hyperLinks = mutableMapOf(
                        link to link,
                    ),
                    color = Color.Cyan,
                )
            }


        }

        Switch(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(0.dp, 10.dp, 15.dp, 0.dp),
            checked = switchState,
            onCheckedChange = { newState ->
                switchState = newState
                onActiveChange(newState)
            }
        )

        Row(
            modifier = Modifier.align(alignment = Alignment.BottomEnd)
        ) {
            IconButton(onClick = { /* TODO:Edit action */ }) {
                Icon(imageVector = Icons.Filled.Create, contentDescription = "edit")
            }
            IconButton(onClick = { /* TODO:Delete action */ }) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete")
            }
        }


    }
}

@Composable
fun HyperlinkText(
    text: String,
    hyperLinks: Map<String, String>,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textDecoration: TextDecoration? = null,
    style: TextStyle = LocalTextStyle.current
) {

    val annotatedString = buildAnnotatedString {
        append(text)

        for ((key, value) in hyperLinks) {

            val startIndex = text.indexOf(key)
            val endIndex = startIndex + key.length
            addStyle(
                style = SpanStyle(
                    color = color,
                    fontSize = fontSize,
                    fontWeight = fontWeight,
                    textDecoration = textDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = value,
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = text.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = style,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    try {
                        uriHandler.openUri(stringAnnotation.item)
                    } catch (e: Exception) {
                        // do nothing
                    }
                }
        }
    )
}