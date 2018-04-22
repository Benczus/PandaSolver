package PandaSolver

import org.junit.Assert
import org.junit.Test
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.Charset


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

    @Test
    fun testiIfFileOperationsAreCorrect() {
        var data = ByteArray(10)
        try {
            val file = File("pandaFile.txt")
            val fileInputStream = FileInputStream(file)
            data = ByteArray(file.length().toInt())
            fileInputStream.read(data)
            fileInputStream.close()
        } catch (e: FileNotFoundException) {
            println("Expected file not found in project folder!\n" +
                    " Terminating..")
        } catch (e: IOException) {
            println("Unexpected IOException!\n" +
                    " Terminating..")
        }

        var pandaGraph: Graph?
        val fileString = String(data, Charset.defaultCharset())
        val lines: List<String> = fileString.split(System.getProperty("line.separator"))
        var lineCounter = 0
        val numberOfCases = Integer.parseInt(lines.elementAt(lineCounter++))
        var numberOfPandas = 0
        for (i in 1..numberOfCases) {
            numberOfPandas = Integer.parseInt(lines.elementAt(lineCounter++))
            Assert.assertEquals(numberOfPandas, 3)

            pandaGraph = Graph(numberOfPandas)
            var actualCounter = lineCounter
            for (j in 0 until numberOfPandas) {
                val pandaInfo = lines.elementAt(actualCounter++).split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                val numofPandasLiked = Integer.parseInt(pandaInfo[0])
                for (k in 1..numofPandasLiked) {
                    if (pandaInfo[k] != "") {
                        val indexofPanda = Integer.parseInt(pandaInfo[k])
                        pandaGraph.addEdge(j, indexofPanda - 1)
                    }
                }
            }
        }
        Assert.assertEquals(numberOfCases, 1)
        Assert.assertEquals(numberOfPandas, 3)


    }


}
