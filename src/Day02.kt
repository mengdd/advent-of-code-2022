// For Part 1 only
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

// For Part 2
enum class GameShape(val score: Int) {
    ROCK(score = 1) {
        override fun getWinShape(): GameShape {
            return PAPER
        }

        override fun getLoseShape(): GameShape {
            return SCISSORS
        }

    },
    PAPER(score = 2) {
        override fun getWinShape(): GameShape {
            return SCISSORS
        }

        override fun getLoseShape(): GameShape {
            return ROCK
        }

    },
    SCISSORS(score = 3) {
        override fun getWinShape(): GameShape {
            return ROCK
        }

        override fun getLoseShape(): GameShape {
            return PAPER
        }
    };

    abstract fun getWinShape(): GameShape
    abstract fun getLoseShape(): GameShape
    fun getDrawShape() = this

    companion object {
        fun fromSign(sign: String): GameShape {
            return when (sign) {
                "A" -> ROCK
                "B" -> PAPER
                "C" -> SCISSORS
                else -> {
                    throw IllegalArgumentException("Unknown sign $sign")
                }
            }
        }
    }
}

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

    fun calculateTheScore(sign: String, result: String): Int {
        var score = 0
        val firstShape = GameShape.fromSign(sign)
        when (result) {
            "X" -> {
                score = firstShape.getLoseShape().score
            }
            "Y" -> {
                score = 3 + firstShape.getDrawShape().score
            }
            "Z" -> {
                score = 6 + firstShape.getWinShape().score
            }
        }
        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        input.forEach {
            val game = it.split(" ")
            score += calculateTheScore(game[0], game[1])
        }
        return score
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input)) //12316
}
