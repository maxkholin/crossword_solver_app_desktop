package ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import resources.MyStrings
import ui.screens.*


@Composable
fun App() {
//    Screen1WordLength()
//    Screen2ChooseMode()
//    Screen3SetMode()
//    Screen4ExludeMode()
 Screen5Result()
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = MyStrings.APP_TITLE,
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        resizable = false
    ) {
        App()
    }
}