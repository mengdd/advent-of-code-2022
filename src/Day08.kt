fun main() {
    fun findTopMax(treeHeightMap: Array<IntArray>, row: Int, column: Int): Int {
        var maxValue = treeHeightMap[row - 1][column]
        for (i in row - 2 downTo 0) {
            maxValue = maxOf(maxValue, treeHeightMap[i][column])
        }
        return maxValue
    }

    fun findBottomMax(treeHeightMap: Array<IntArray>, row: Int, column: Int): Int {
        var maxValue = treeHeightMap[row + 1][column]
        for (i in row + 2 until treeHeightMap.size) {
            maxValue = maxOf(maxValue, treeHeightMap[i][column])
        }
        return maxValue
    }

    fun findRightMax(treeHeightMap: Array<IntArray>, row: Int, column: Int): Int {
        var maxValue = treeHeightMap[row][column + 1]
        for (i in column + 2 until treeHeightMap[0].size) {
            maxValue = maxOf(maxValue, treeHeightMap[row][i])
        }
        return maxValue
    }

    fun findLeftMax(treeHeightMap: Array<IntArray>, row: Int, column: Int): Int {
        var maxValue = treeHeightMap[row][column - 1]
        for (i in column - 2 downTo 0) {
            maxValue = maxOf(maxValue, treeHeightMap[row][i])
        }
        return maxValue
    }

    fun isVisible(
        treeHeightMap: Array<IntArray>,
        row: Int,
        column: Int
    ): Int {

        if (row == 0 || row == treeHeightMap.size - 1 || column == 0 || column == treeHeightMap[0].size - 1) {
            return 1
        }
        val topHeight = findTopMax(treeHeightMap, row, column)
        val rightHeight = findRightMax(treeHeightMap, row, column)
        val bottomHeight = findBottomMax(treeHeightMap, row, column)
        val leftHeight = findLeftMax(treeHeightMap, row, column)
        val targetHeight = treeHeightMap[row][column]

        if (targetHeight > topHeight) {
            return 1
        }
        if (targetHeight > rightHeight) {
            return 1
        }
        if (targetHeight > bottomHeight) {
            return 1
        }
        if (targetHeight > leftHeight) {
            return 1
        }
        return 0
    }

    fun findTopCount(treeHeightMap: Array<IntArray>, row: Int, column: Int): Int {
        if (row < 1) {
            return 1
        }
        var height = treeHeightMap[row - 1][column]
        var count = 1
        for (i in row - 2 downTo 0) {
            if (treeHeightMap[i][column] >= height) {
                height = treeHeightMap[i][column]
                count++
            }
        }
        return count
    }

    fun findBottomCount(treeHeightMap: Array<IntArray>, row: Int, column: Int): Int {
        if (row >= treeHeightMap.size - 1) {
            return 1
        }
        var height = treeHeightMap[row + 1][column]
        var count = 1
        for (i in row + 2 until treeHeightMap.size) {
            if (treeHeightMap[i][column] >= height) {
                height = treeHeightMap[i][column]
                count++
            }
        }
        return count
    }

    fun findRightCount(treeHeightMap: Array<IntArray>, row: Int, column: Int): Int {
        if (column >= treeHeightMap[0].size - 1) {
            return 1
        }
        var height = treeHeightMap[row][column + 1]
        var count = 1
        for (i in column + 2 until treeHeightMap[0].size) {
            if (treeHeightMap[row][i] >= height) {
                height = treeHeightMap[row][i]
                count++
            }
        }
        return count
    }

    fun findLeftCount(treeHeightMap: Array<IntArray>, row: Int, column: Int): Int {
        if (column < 1) {
            return 1
        }
        var height = treeHeightMap[row][column - 1]
        var count = 1
        for (i in column - 2 downTo 0) {
            if (treeHeightMap[row][i] >= height) {
                height = treeHeightMap[row][i]
                count++
            }
        }
        return count
    }

    fun getScenicScore(
        treeHeightMap: Array<IntArray>,
        row: Int,
        column: Int
    ): Int {
        val topCount = findTopCount(treeHeightMap, row, column)
        val rightCount = findRightCount(treeHeightMap, row, column)
        val bottomCount = findBottomCount(treeHeightMap, row, column)
        val leftCount = findLeftCount(treeHeightMap, row, column)

        return topCount * rightCount * bottomCount * leftCount
    }

    fun part1(input: List<String>): Int {
        val trees = Array(input.size) { IntArray(input[0].length) }
        val visibleMap = Array(input.size) { IntArray(input[0].length) }
        input.forEachIndexed { index, string ->
            trees[index] = string.map { (it - '0') }.toIntArray()
        }

        for (i in trees.indices) {
            for (j in trees[0].indices) {
                visibleMap[i][j] = isVisible(trees, i, j)
            }
        }

        // 1835
        return visibleMap.sumOf { it.sum() }
    }

    fun part2(input: List<String>): Int {
        val trees = Array(input.size) { IntArray(input[0].length) }
        val scoreMap = Array(input.size) { IntArray(input[0].length) }
        input.forEachIndexed { index, string ->
            trees[index] = string.map { (it - '0') }.toIntArray()
        }

        for (i in trees.indices) {
            for (j in trees[0].indices) {
                scoreMap[i][j] = getScenicScore(trees, i, j)
            }
        }

        // 20592 too low
        return scoreMap.maxOf { it.max() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part2(testInput) == 12)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
