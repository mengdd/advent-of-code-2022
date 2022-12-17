open class Node(val name: String, open val size: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class Folder(
    name: String,
    val parent: Folder? = null,
) : Node(name = name, size = 0) {
    val files = mutableSetOf<Node>()
    override val size: Int
        get() = files.sumOf { it.size }

    val isValidFolder
        get() = size <= MAX_SIZE

    companion object {
        private const val MAX_SIZE = 100000
    }
}

sealed class Command {
    data class CD(val path: String)
    object LS
}

fun main() {

    fun executeCDCommand(rootDirectory: Folder, currentFolder: Folder, command: Command.CD): Folder {
        if (command.path == "..") {
            return currentFolder.parent ?: currentFolder
        }
        if (command.path == "/") {
            return rootDirectory
        }
        val node = currentFolder.files.firstOrNull { it.name == command.path }
        if (node == null || node !is Folder) {
            throw IllegalArgumentException("can not find file or it is not a folder")
        }
        return node
    }

    fun handleListResult(directory: Folder, line: String, allFolders: MutableSet<Folder>) {
        val result = line.split(" ")
        val node: Node = if (result[0] == "dir") {
            Folder(name = result[1], parent = directory)
        } else {
            Node(name = result[1], size = result[0].toInt())
        }
        directory.files.add(node)
        if (node is Folder) {
            allFolders.add(node)
        }
    }

    fun part1(input: List<String>): Int {
        val root = Folder("/")
        var currentDirectory = Folder("/")
        val allFolders = mutableSetOf<Folder>()
        input.forEach {
            if (it.startsWith("$")) {
                // command: cd or ls
                if (it.startsWith("$ cd")) {
                    val command = Command.CD(path = it.split(" ").last())
                    currentDirectory = executeCDCommand(
                        rootDirectory = root,
                        currentFolder = currentDirectory,
                        command = command
                    )
                }
            } else {
                // ls result
                handleListResult(directory = currentDirectory, line = it, allFolders = allFolders)
            }
        }

        // 48044502, too high
        // 1181394 too low
        return allFolders.filter { it.isValidFolder }.sumOf { it.size }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
