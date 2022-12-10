fun main() {
    fun getCommonItem(firstPart: String, secondPart: String): Char {
        for (c in firstPart) {
            if (secondPart.contains(c)) {
                return c
            }
        }
        throw IllegalStateException("No Common Item Found in the two strings $firstPart $secondPart")
    }

    fun getPriority(char: Char): Int {
        if (char in 'A'..'Z') {
            return char - 'A' + 27
        } else if (char in 'a'..'z') {
            return char - 'a' + 1
        }
        throw IllegalStateException("Illegal char: $char")
    }

    fun findBadge(first: String, second: String, third: String): Char {
        for (c in first) {
            if (second.contains(c) && third.contains(c)) {
                return c
            }
        }
        throw IllegalStateException("No Common Item Found in the three strings $first $second $third")
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        input.forEach {
            val subStringLength = it.length / 2
            val commonItem = getCommonItem(it.substring(0, subStringLength), it.substring(subStringLength, it.length))
            val priority = getPriority(commonItem)
//            println("Common Item: $commonItem priority: $priority")
            sum += priority
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        var i = 0
        while (i < input.size - 2) {
            val badge = findBadge(input[i], input[i + 1], input[i + 2])
            val priority = getPriority(badge)
//            println("badge $badge priority $priority")
            sum += priority
            i += 3
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")

    val input = readInput("Day03")
    println(part1(input)) //7831
    println(part2(input)) //2683
}
