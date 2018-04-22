package PandaSolver

import java.util.*

/**
 * Undirected, weightless PandaSolver class
 */
class Graph(private val numberOfNodes: Int) {
    private val adjacentNodes: Array<ArrayList<Any?>?> = arrayOfNulls(numberOfNodes)
    /**
     * Constructor
     */
    init {
        for (i in 0 until numberOfNodes) {
            adjacentNodes[i] = ArrayList( )
        }
    }
    /**
     * Returns true if PandaSolver is Hamiltonian, or false otherwise
     */
    val isHamiltonian: Boolean
        get() {
            if (numberOfNodes < 3) {
                return false
            }
            var i = 0
            val tempNodes = adjacentNodes.filterNotNull()
            while (i < tempNodes.size) {
                if (tempNodes[i].size < numberOfNodes / 2) {
                    return false
                }
                ++i
            }
            return true
        }
    /**
     * Adds and edge between the node with the index of "nodeIndexOne" and "nodeIndexTwo"
     */
    fun addEdge(nodeIndexOne: Int, nodeIndexTwo: Int) {
        if (adjacentNodes[nodeIndexOne] != null && adjacentNodes[nodeIndexTwo] != null) {
            adjacentNodes[nodeIndexOne]?.add(nodeIndexTwo)
            adjacentNodes[nodeIndexTwo]?.add(nodeIndexOne)
        }
    }
    override fun toString(): String {
        return "Graph(numberOfNodes=$numberOfNodes, adjacentNodes=${Arrays.toString(adjacentNodes)})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Graph

        if (numberOfNodes != other.numberOfNodes) return false
        if (!Arrays.equals(adjacentNodes, other.adjacentNodes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = numberOfNodes
        result = 31 * result + Arrays.hashCode(adjacentNodes)
        return result
    }
}

