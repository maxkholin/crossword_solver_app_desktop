package data

data class LetterButtonModel(
    val letter: Char,
    var isPressed: Boolean
)


fun createLetters(): List<LetterButtonModel> {
    return ('а'..'я').filter { it != 'ё' }.map { LetterButtonModel(it, isPressed = false) }
}