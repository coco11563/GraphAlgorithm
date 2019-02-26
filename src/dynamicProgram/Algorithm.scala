package dynamicProgram

object Algorithm {
  def MaximumSameSeq(a : String, b : String) : Unit = {
    var x = a.length
    var y = b.length
    val arr : Array[Array[Int]] = new Array[Array[Int]](x)
    for (i <- arr.indices) {
      arr(i) = new Array[Int](y)
    }
    MaximumSeqFound(a, b , arr)
    MaximumPrint(arr, a, b)
  }
  def MaximumSeqFound(a : String, b : String, arr : Array[Array[Int]]) : Unit = {
    val c = new Array[Array[Int]](a.length)
    for (i <- c.indices) {
      c(i) = new Array[Int](b.length)
    }
    for (i <- 0 until a.length) {
      for (j <- 0 until b.length) {
        if (a(i - 1) == b(j - 1)) {
          arr(i).update(j, 1)
          c(i).update(j, c(i - 1)(j - 1) + 1)
        }
        else if (c(i - 1)(j) >= c(i)(j - 1)) {
          arr(i).update(j, 2)
          c(i).update(j, c(i - 1)(j))
        }
        else {
          arr(i).update(j, 3)
          c(i).update(j, c(i)(j - 1))
        }
      }
    }
  }
  def MaximumPrint(arr : Array[Array[Int]], a : String, b : String): Unit = {
    var x = 1
    var y = 1
    while (x < a.length && y < b.length) {
      val z = arr(x)(y)
      z match {
        case 1 => print(a(z)) ; x -= 1; y -= 1
        case 2 => x -= 1;
        case 3 => y -= 1;
      }
    }
    println()
  }

  def main(args: Array[String]): Unit = {
    MaximumSameSeq("ABCBDAB", "BDCABA")
  }
}
