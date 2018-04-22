package PandaSolver

import org.junit.Assert
import org.junit.Test


class PandaTest {

    val graph = Graph(5)

    @Test
    fun testIfHamiltonianGraphIsHamiltonian() {
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(3, 4)
        graph.addEdge(4, 0)
        Assert.assertEquals(true, graph.isHamiltonian)
    }

    @Test
    fun testIfNotHamiltonianGraphIsNotHamiltonian() {
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 3)
        graph.addEdge(3, 4)
        graph.addEdge(4, 1)
        Assert.assertEquals(false, graph.isHamiltonian)
    }
}
