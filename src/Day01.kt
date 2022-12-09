import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int {
        var maxSum = 0
        var groupSum = 0
        for (line in input) {
            if (line.isBlank() || line.isEmpty()) {
                maxSum = max(groupSum, maxSum)
                groupSum = 0
            } else {
                groupSum += line.toInt()
            }
        }
        // 66306
        return max(groupSum, maxSum)
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
