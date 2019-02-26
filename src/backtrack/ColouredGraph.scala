package backtrack

import graph.ds.AdjacentTable

import scala.collection.mutable.ArrayBuffer

object ColouredGraph {
  val inputGraph = new AdjacentTable(7)
  inputGraph.doubleConnect(0,1)
  inputGraph.doubleConnect(0,5)
  inputGraph.doubleConnect(0,6)
  inputGraph.doubleConnect(5,6)
  inputGraph.doubleConnect(6,1)
  inputGraph.doubleConnect(6,2)
  inputGraph.doubleConnect(1,2)
  inputGraph.doubleConnect(2,3)
  inputGraph.doubleConnect(3,4)
  inputGraph.doubleConnect(4,5)
  inputGraph.doubleConnect(5,3)
  val colourNum = 3

  def solution(graph : AdjacentTable, num : Int) : Array[Array[Int]] = {

    val processNode = 0 until graph.getTableSize
    val ret = new ArrayBuffer[Array[Int]]()
    for (i <- ret.indices) ret(i) = new Array[Int](processNode.length)
    var i = 0
    val solution = new Array[Int](processNode.length)
    val record : Array[Set[Int]] =
      new Array[Set[Int]](processNode.length)
    for (i <- processNode.indices)
      record.update(i, Set[Int]())
    while(true) {
      var flag = true
      while (i < processNode.length && flag) {
        val thisColourArr = getNextAvailableColour(graph, solution, record, i, num)
        if (thisColourArr.length == 0) flag = false // end this time
        else {
          val thisColour = thisColourArr.head
          solution.update(i, thisColour)
          record(i) += thisColour
          i += 1
        }
      }
      i -= 1
      if (i == processNode.length - 1) {
        ret += solution.clone()
      }
      //bt
      while (i > 0 && getNextAvailableColour(graph, solution, record, i, num).length == 0) {
        solution.update(i, 0)
        record.update(i, Set[Int]())
        i -= 1
      }
      if (record(i).contains(solution(i))) {
        val thisColourArr = getNextAvailableColour(graph, solution, record, i, num)
        if (thisColourArr.isEmpty) return ret.toArray
        solution.update(i, thisColourArr.head)
        record(i) += thisColourArr.head
        i += 1
      }
      //bt end
    }
    null
  }

  import scala.collection.JavaConversions._
  def getNextAvailableColour(graph: AdjacentTable,
                            solution : Array[Int], solutionSet : Array[Set[Int]],
                            index : Int, color : Int) : Array[Int] = {
    val arr = graph.get(index)
    val set = solutionSet(index)
    (1 to color)
      .filter(e => !arr.map(i => solution(i)).contains(e))
      .filter(i => !set.contains(i))
      .toArray
  }
  def main(args: Array[String]): Unit = {
    val ret = solution(inputGraph, colourNum)
    if (ret.length == 0) println("No")
    else {
      ret.foreach(i => {
        i.foreach(e => print(e + " "))
        println()
      })
    }
  }

}
