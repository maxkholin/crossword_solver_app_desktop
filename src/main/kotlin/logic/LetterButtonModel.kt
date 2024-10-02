package logic

data class LetterButtonModel(val letter: Char, var isPressed: Boolean, val type: HowCommonLetterType)

enum class HowCommonLetterType {
    MOST_COMMON,
    USUALLY,
    RARE,
}

fun createLetters(): List<LetterButtonModel> {
    return createLetterModels(createMostCommonLetters(), HowCommonLetterType.MOST_COMMON) +
            createLetterModels(createUsuallyLetters(), HowCommonLetterType.USUALLY) +
            createLetterModels(createRareLetters(), HowCommonLetterType.RARE)
}

private fun createLetterModels(letters: List<Char>, type: HowCommonLetterType): List<LetterButtonModel> {
    return letters.map { LetterButtonModel(it, isPressed = false, type) }
}

fun createMostCommonLetters(): List<Char> = listOf('о', 'а', 'и', 'е', 'т', 'н', 'р', 'с')

fun createUsuallyLetters(): List<Char> = listOf('к', 'л', 'в', 'ь', 'п', 'д', 'м', 'й', 'у', 'ы', 'з')

fun createRareLetters(): List<Char> = listOf('я', 'б', 'г', 'ч', 'ц', 'ш', 'ж', 'х', 'ф', 'щ', 'ю', 'э', 'ъ')
