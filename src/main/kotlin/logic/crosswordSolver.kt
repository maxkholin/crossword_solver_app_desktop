package logic

import data.LetterButtonModel
import java.io.File

fun getListOfWords(countOfLetters: Int): List<String> {
    return File("src/main/resources/word_lists/words${countOfLetters}Letter.txt").readLines()
}

fun searchByMask(
    words: List<String>,
    mask: String
): List<String> {
    val result = mutableListOf<String>()

    words.forEach { word ->
        var matches = true
        val currentWord = word.lowercase()
        for (i in mask.indices) {
            if (mask[i] != '*' && mask[i] != currentWord[i]) {
                matches = false
                break
            }
        }
        if (matches) {
            result.add(word)
        }
    }

    return result
}

//fun searchByMask(words: List<String>, mask: String): List<String> {
//    val regex = mask.replace("*", ".").toRegex()
//    return words.filter { word -> regex.matches(word) }
//}

fun searchBySet(
    words: List<String>,
    search: Set<LetterButtonModel>,
    lettersToExclude: Set<LetterButtonModel>
): List<String> {
    return words.filter { word ->
        val lowercaseWord = word.lowercase()
        search.all { it.letter in lowercaseWord } && lettersToExclude.none { it.letter in lowercaseWord }
    }
}