package PandaSolver

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.charset.Charset

/**
 * Main function of PandaGraph
 * It reads from the pandaFile.txt, and decides if a Hamilton path exists within the PandaSolver
 */
fun  main(args: Array<String>) {
    val pandaGraph: Graph?
    var data=ByteArray(10)
    try {
        val file= File("pandaFile.txt")
        val fileInputStream= FileInputStream(file)
        data= ByteArray(file.length().toInt())
        fileInputStream.read(data)
        fileInputStream.close()
    } catch (e: FileNotFoundException) {
        println("Expected file not found in project folder!\n" +
                " Terminating..")
    } catch (e: IOException) {
        println("Unexpected IOException!\n" +
                " Terminating..")
    }
    pandaGraph= createGraphfromFile(data)

    if (pandaGraph?.isHamiltonian!=null ) {
        if (pandaGraph.isHamiltonian)
            println("POSSIBLE")
        else
            println("IMPOSSIBLE")
    }
}
/**
 * Function to populate the PandaSolver from the file given by the user in the "fileName" value
 */
fun createGraphfromFile(data:ByteArray): Graph?{
    var pandaGraph: Graph? = null
    val fileString= String(data, Charset.defaultCharset())
    val lines: List<String> = fileString.split(System.getProperty("line.separator"))
    var lineCounter=0
    val numberOfCases = Integer.parseInt(lines.elementAt(lineCounter++))
    for (i in 1..numberOfCases) {
        val numberOfPandas = Integer.parseInt(lines.elementAt(lineCounter++))
        println("Number of Pandas:$numberOfPandas")
        pandaGraph = Graph(numberOfPandas)
        populateGraph(lines, pandaGraph, numberOfPandas, lineCounter)
    }
    return pandaGraph
}
/**
 * Populates the PandaSolver with elements from the read file.
 */
fun populateGraph(lines:List<String>, pandaGraph: Graph, numberOfPandas:Int, lineCounter:Int){
    var actualCounter=lineCounter
    for (j in 0 until numberOfPandas) {
        val pandaInfo = lines.elementAt(actualCounter++).split(" ".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
        val numofPandasLiked = Integer.parseInt(pandaInfo[0])
        for (k in 1..numofPandasLiked) {
            if (pandaInfo[k] != "") {
                val indexofPanda = Integer.parseInt(pandaInfo[k])
                println("Index of Panda liked by + " + (j + 1) + ". Panda: " + indexofPanda)
                pandaGraph.addEdge(j, indexofPanda - 1)
            }
        }
    }
}




