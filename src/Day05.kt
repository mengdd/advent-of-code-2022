import java.util.*

class Instruction(val count: Int, val from: Int, val to: Int) {
    companion object {
        private val pattern = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
        fun parseInstruction(text: String): Instruction {
            val matchResult = pattern.matchEntire(text) ?: throw IllegalStateException("Not Match! $text")
            return Instruction(
                count = matchResult.groupValues[1].toInt(),
                from = matchResult.groupValues[2].toInt() - 1,
                to = matchResult.groupValues[3].toInt() - 1
            )
        }
    }
}

fun main() {
    fun buildOriginalStacks(numberLine: String, stackState: List<String>): List<Stack<Char>> {
        val list = mutableListOf<Stack<Char>>()
        val numbers = numberLine.trim().split(" ")
        val first = numbers.first().toInt()
        val last = numbers.last().toInt()
        for (number in first..last) {
            val textIndex = numberLine.indexOf(number.toString())
            val stackInfo = Stack<Char>()
            for (i in stackState.size - 1 downTo 0) {
                val stateLine = stackState[i]
                if (stateLine.length > textIndex) {
                    val cargo = stateLine[textIndex]
                    if (cargo != ' ') {
                        stackInfo.push(cargo)
                    }
                }
            }
            list.add(stackInfo)
        }
        return list
    }

    fun part1(input: List<String>): String {
        val numberLineIndex = input.indexOfFirst { it.trim().startsWith("1") }
        val stacks = buildOriginalStacks(input[numberLineIndex], input.subList(0, numberLineIndex))

        input.subList(numberLineIndex + 2, input.size).forEach {
            val instruction = Instruction.parseInstruction(it)
            for (i in 1..instruction.count) {
                val toMove = stacks[instruction.from].peek()
                stacks[instruction.to].push(toMove)
                stacks[instruction.from].pop()
            }
        }
        val stringBuilder = StringBuilder()
        stacks.forEach { stringBuilder.append(it.peek()) }
        return stringBuilder.toString() // HNSNMTLHQ
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
