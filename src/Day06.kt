fun main() {
    fun findMarkerIndex(line: String, markerLength: Int): Int {
        var index = -1
        for (i in markerLength..line.length) {
            val substring = line.substring(i - markerLength, i)
            if (substring.toSet().size == substring.length) {
                index = i
                break
            }
        }
        return index
    }

    fun part1(input: List<String>): Int {
        assert(input.size == 1)
        // 1804
        return findMarkerIndex(input[0], 4)
    }

    fun part2(input: List<String>): Int {
        assert(input.size == 1)
        // 2508
        return findMarkerIndex(input[0], 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part2(testInput) == 29)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
