package ui.screens

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState

suspend fun showErrorMessage(snackbarHostState: SnackbarHostState, message: String) {
    snackbarHostState.showSnackbar(
        message = message,
        duration = SnackbarDuration.Long,
        actionLabel = "X"
    )
}