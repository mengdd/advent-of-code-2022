import kotlin.math.abs

// left bottom as (0, 0)
// x: horizontal axis, y: vertical axis
// (0, 1) for up
// (1, 0) for right
data class Position(val x: Int, val y: Int) {
    fun getDirectionTo(position: Position): Direction {
        return Direction(position.x - x, position.y - y)
    }

    fun move(direction: Direction): Position {
        return Position(x + direction.x, y + direction.y)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Position

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

}

data class Direction(val x: Int, val y: Int) {
    fun getDistance(): Int {
        return abs(x) + abs(y)
    }

    fun isSameLine(): Boolean {
        return x == 0 || y == 0
    }

    fun normalise(): Direction {
        val normalizedX = if (x == 0) {
            0
        } else {
            x / abs(x)
        }

        val normalizedY = if (y == 0) {
            0
        } else {
            y / abs(y)
        }
        return Direction(x = normalizedX, y = normalizedY)
    }


}


fun main() {
    fun moveHead(startPosition: Position, direction: String): Position {
        return when (direction) {
            "U" -> startPosition.copy(y = startPosition.y + 1)
            "R" -> startPosition.copy(x = startPosition.x + 1)
            "D" -> startPosition.copy(y = startPosition.y - 1)
            "L" -> startPosition.copy(x = startPosition.x - 1)
            else -> {
                throw IllegalArgumentException("Unknown direction $direction")
            }
        }
    }

    fun moveTail(headPosition: Position, tailPosition: Position): Position {
        var result = tailPosition
        val direction = tailPosition.getDirectionTo(headPosition)
        val threshold = if (direction.isSameLine()) 1 else 2
        if (direction.getDistance() > threshold) {
            result = tailPosition.move(direction.normalise())
        }
        return result
    }

    fun part1(input: List<String>): Int {
        val visited = mutableSetOf<Position>()
        var head = Position(0, 0)
        var tail = Position(0, 0)
        visited.add(tail)
        input.forEach {
            val command = it.split(" ")
            repeat(command[1].toInt()) {
                head = moveHead(startPosition = head, direction = command[0])
                tail = moveTail(headPosition = head, tailPosition = tail)
                visited.add(tail)
            }
        }
        // 6018
        return visited.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
