package graph

class Solution {

}

object Solution {
  def findMinHeightTrees(n: Int, edges: Array[Array[Int]]): List[Int] = {
    val graph = new Graph(edges, n)
    graph.list_ret
  }

  class Graph(edges : Array[Array[Int]], n : Int) {
    var g = new Array[List[Int]](n)


    for (i <- g.indices) {
      g(i) = List[Int]()
    }


    for(pair <- edges) { // 2E
      val from = pair(0)
      val to = pair(1)
      var ini_f = g(from)
      var ini_t = g(to)
      ini_f +:= to
      ini_t +:= from
      g.update(from, ini_f)
      g.update(to, ini_t)
    }
    private var list_high: List[Int] = List[Int]()
    for (i <- 0 until n) {
      list_high :+= dijkstra(i)
    }
    var list_ret: List[Int] = List[Int]()
    private var highest: Int = Int.MaxValue
    private var i = 0
    for (j <- list_high) {
      if (j < highest) {
        highest = j
        list_ret = List[Int]()
        list_ret +:= i
      } else if (j == highest) {
        list_ret +:= i
      }
      i += 1
    }

    def dijkstra(i: Int) : Int = { // N
      assert(i < n)
      if (g(i).length == 1) Int.MaxValue
      val known_array = new Array[Int](n)
      var process_list : List[Int] = List[Int]() :+ i
      var len = 0
      while (known_array.contains(0)) {
        process_list.foreach(known_array.update(_, 1))
        process_list = {
          val ret = for (process <- process_list) yield {
            g(process).filter(known_array(_) != 1)
          }
          ret.flatten
        }
        len += 1
      }
      len
    }
  }

  def main(args: Array[String]): Unit = {
    println(findMinHeightTrees(4, Array[Array[Int]](Array(1,0),Array(1,2),Array(1,3))))
  }
}
