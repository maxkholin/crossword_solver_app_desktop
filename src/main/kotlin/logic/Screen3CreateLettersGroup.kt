package logic

fun createMostCommonLetters(): List<Char> = listOf('о', 'а', 'и', 'е', 'т', 'н', 'р', 'с')

fun createUsuallyLetters(): List<Char> = listOf('к', 'л', 'в', 'ь', 'п', 'д', 'м', 'й', 'у', 'ы', 'з')

fun createRareLetters(): List<Char> = listOf('я', 'б', 'г', 'ч', 'ц', 'ш', 'ж', 'х', 'ф', 'щ', 'ю', 'э', 'ъ')

fun createMutableMapOfLetters(): MutableMap<Char, Boolean> {
    return mutableMapOf(
        'а' to false, 'б' to false, 'в' to false, 'г' to false, 'д' to false,
        'е' to false, 'ж' to false, 'з' to false, 'и' to false, 'й' to false,
        'к' to false, 'л' to false, 'м' to false, 'н' to false, 'о' to false,
        'п' to false, 'р' to false, 'с' to false, 'т' to false, 'у' to false,
        'ф' to false, 'х' to false, 'ц' to false, 'ч' to false, 'ш' to false,
        'щ' to false, 'ъ' to false, 'ы' to false, 'ь' to false, 'э' to false,
        'ю' to false, 'я' to false
    )
}