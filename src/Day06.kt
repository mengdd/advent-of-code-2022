fun main() {
    fun part1(input: List<String>): Int {
        assert(input.size == 1)

        val line = input[0]
        var index = -1
        for (i in 4..line.length) {
            val substring = line.substring(i - 4, i)
            if (substring.toSet().size == substring.length) {
                index = i
                break
            }
        }
        // 1804
        return index
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 10)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
