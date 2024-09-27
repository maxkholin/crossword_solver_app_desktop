package logic

import resources.MyStrings
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

fun parseLettersCount(length: String): Int {
    return try {
        val result = length.trim().toInt()
        if ( result in 3..18) {
            result
        } else {
            MyStrings.TOO_SHORT_OR_LONG_CODE.toInt()
        }
    } catch (e: Exception) {
        MyStrings.NOT_A_NUMBER_CODE.toInt()
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

fun searchBySet(words: List<String>, result: MutableList<String>) {
    val search: Set<Char> = readln().lowercase().toCharArray().toSet()

    for (word in words) {
        val currentWord = word.lowercase()
        if (search.all { it in currentWord }) {
            result.add(word)
        }
    }
}

fun exludeLetters(lettersToExlude: Set<Char>, words: MutableList<String>) {
    val iterator = words.iterator()
    while (iterator.hasNext()) {
        val word = iterator.next()
        if (lettersToExlude.any { it in word }) {
            iterator.remove()
        }
    }
}