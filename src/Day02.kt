val roundScoreMap = hashMapOf(
    "A X" to 3,
    "A Y" to 6,
    "A Z" to 0,
    "B X" to 0,
    "B Y" to 3,
    "B Z" to 6,
    "C X" to 6,
    "C Y" to 0,
    "C Z" to 3,
)

val shapeScoreMap = hashMapOf(
    "X" to 1,
    "Y" to 2,
    "Z" to 3
)
fun main() {
    fun part1(input: List<String>): Int {
        var score = 0
        input.forEach {
            val game = it.split(" ")
            score += roundScoreMap[it]!!
            score += shapeScoreMap[game[1]]!!
        }

        // 13809
        return score
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day02_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
