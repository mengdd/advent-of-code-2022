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
        var groupSum = 0
        val groupSums = mutableListOf<Int>()
        for (line in input) {
            if (line.isBlank() || line.isEmpty()) {
                groupSums.add(groupSum)
                groupSum = 0
            } else {
                groupSum += line.toInt()
            }
        }
        groupSums.sortDescending()
        // 195292
        return groupSums[0] + groupSums[1] + groupSums[2]
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
