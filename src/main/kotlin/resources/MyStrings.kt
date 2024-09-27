package resources

object MyStrings {
    const val APP_TITLE = "Crossword Solver by Max Kholin"

    const val PARSE_LENGTH = "Введите длину искомого слова:"
    const val PARSE_LENGTH_SECOND = "Выберите количество букв в слове"

    const val LABEL_PARSE_LENGTH = "Число от 3 до 18"

    const val NEXT = "Далее"

    const val TOO_SHORT_OR_LONG = "Извините слова короче 3 или длиннее 18 букв мы не ищем.\n" +
            "Попробуйте снова"
    const val TOO_SHORT_OR_LONG_CODE = "0"

    const val NOT_A_NUMBER = "Вы ввели не число, попробуйте снова"
    const val NOT_A_NUMBER_CODE = "-1"

    const val CHOOSE_MODE = "Выберите режим поиска"
    const val CHOOSE_MODE_SET = " По множеству букв"
    const val CHOOSE_MODE_MASK = "По маске"

    const val MODE_SET = "Вы выбрали режим: поиск по множеству букв\n" +
            "Введите множество букв без пробелов и запятых:"

    const val MODE_MASK = "Вы выбрали режим: поиск по маске\n" +
            "Введите строку в формате \\\"_____\\\" вместо неизвестных букв указывая '*', " +
            "например \\\"а**уз\\\":"

    //const val INVALID_MODE = "Вы выбрали неверный режим, попробуйте снова\n${SELECT_MODE}"

    const val EXLUDE_LETTERS_MODE = "Если вы хотите исключить буквы из поиска введите их без пробелов и " +
            "запятых, или просто нажмите \"Enter\":"

    const val NOT_FOUND = "К сожалению ничего не найдено, попробуйте снова"

}
