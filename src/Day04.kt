fun main() {

    fun getRangePair(range: String): Pair<Int, Int> {
        val parts = range.split("-")
        return parts[0].toInt() to parts[1].toInt()
    }

    fun fullyContains(range1: String, range2: String): Boolean {
        val rangePair1 = getRangePair(range1)
        val rangePair2 = getRangePair(range2)
        if (rangePair1.first <= rangePair2.first && rangePair1.second >= rangePair2.second) {
            return true
        } else if (rangePair2.first <= rangePair1.first && rangePair2.second >= rangePair1.second) {
            return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        var count = 0
        input.forEach {
            val pair = it.split(",")
            if (fullyContains(pair[0], pair[1])) {
                count++
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    println(part1(input)) // 515
    println(part2(input))
}
