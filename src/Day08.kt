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
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
