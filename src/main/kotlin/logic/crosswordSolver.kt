package logic

import data.LetterButtonModel
import java.io.File


//fun solve() {
//
//    val countOfLetters = parseLettersCount("5")
//
//    val words = getListOfWords(countOfLetters)
//
//    val result = mutableListOf<String>()
//
//    println(MyStrings.SELECT_MODE)
//
//    while (true) {
//        when (readln()) {
//            "1" -> {
//                println(MyStrings.SET_MODE)
//                searchBySet(words, result)
//                break
//            }
//
//            "2" -> {
//                println(MyStrings.MASK_MODE)
//                searchByMask(words, result, countOfLetters)
//                break
//            }
//
//            else -> {
//                println(MyStrings.INVALID_MODE)
//            }
//        }
//    }
//
//    println(MyStrings.EXLUDE_LETTERS_MODE)
//    val lettersToExlude = readln().lowercase().toCharArray().toSet()
//    if (lettersToExlude.isNotEmpty()) {
//        exludeLetters(lettersToExlude, result)
//    }
//
//    if (result.size == 0) {
//        println(MyStrings.NOT_FOUND)
//    } else {
//        result.forEach { println(it) }
//        println("Найдено ${result.size} слов")
//    }
//}


/**
 *  Изменил логику первого экрана
 *  теперь длина слова выбирается нажатием и пользователь не может ввести не валидные данные
 */
fun parseLettersCount(length: String): Int {
    return try {
        val result = length.trim().toInt()
        if (result in 3..18) {
            result
        } else {
            0
        }
    } catch (e: Exception) {
        -1
    }
}

fun getListOfWords(countOfLetters: Int): List<String> {
    return File("src/main/resources/word_lists/words${countOfLetters}Letter.txt").readLines()
}

fun searchByMask(words: List<String>, result: MutableList<String>, countOfLetters: Int) {
    var search: String

    while (true) {
        search = readln().lowercase()
        if (search.length == countOfLetters) {
            break
        } else {
            println("Вы ввели не $countOfLetters символов, попробуйте снова: ")
        }
    }

    for (word in words) {
        var matches = true
        val currentWord = word.lowercase()
        for (i in 0 until countOfLetters) {
            if (search[i] != '*' && search[i] != currentWord[i]) {
                matches = false
                break
            }
        }
        if (matches) {
            result.add(word)
        }
    }
}

//fun searchBySet(
//    words: List<String>,
//    search: Set<LetterButtonModel>,
//    lettersToExclude: Set<LetterButtonModel>
//): List<String> {
//    val tempResult = mutableListOf<String>()
//
//    tempResult.addAll(
//        words.filter { word ->
//            search.all { it.letter in word.lowercase() }
//        }
//    )
//
//    val result = excludeLetters(lettersToExclude, tempResult)
//    return result
//
//    return tempResult
//}
//
//fun excludeLetters(lettersToExclude: Set<LetterButtonModel>, result: MutableList<String>): List<String> {
//    return result.filterNot { word ->
//        lettersToExclude.any { it.letter in word }
//    }
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