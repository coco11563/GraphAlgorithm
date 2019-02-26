package backtrack

object FulFillBT {
  val sampleInput: Array[Int] = Array[Int](90,80,40,30,20,12,10)
  val storage : (Int, Int) = (152, 130)


  def solution(pack : Array[Int], storage : (Int, Int)) : Array[Int] = {
    val ret = new Array[Int](pack.length)
    val packIndexed = pack.map(i => (i, pack.indexOf(i))).sortBy(i => i._1)(Ordering[Int].reverse)
    packIndexed.foreach(i => println(i._1 + "," + i._2))
    loading(packIndexed, ret, storage)._1
  }

  def loading(pack : Array[(Int, Int)], ret : Array[Int], storage : (Int, Int)) : (Array[Int], Array[Int]) = {
    var B = storage._1
    var best = storage._1
    var bestSolution = ret.clone()
    var i = 0
    val solution = ret.clone()
    while (true) {
      while (i < pack.length) {
        if (pack(i)._1 <= B) {
          B -= pack(i)._1
          solution.update(i, 1)
        } else {
          solution.update(i, 0)
        }
        i += 1
      }
      i -= 1
      if (B < best) {
        best = B
        bestSolution = solution.clone()
      }

      // bt
      while (i > 0 && solution(i) == 0) {
        i -= 1
      }
      if (solution(i) == 1) {
        solution.update(i, 0)
        B += pack(i)._1
        i += 1
      }
      //bt

      if (i == 0) {
        return (bestSolution, pack.map(_._2))
      }
    }
    null
  }


  def main(args: Array[String]): Unit = {
    solution(sampleInput, storage).foreach(println)
  }
}
