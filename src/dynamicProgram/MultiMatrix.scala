package dynamicProgram

object MultiMatrix {
  def opt (array: Array[Int]) : Unit = {
    val seqMatrix : Array[Array[Int]] = new Array[Array[Int]](array.length)
    for (i <- seqMatrix.indices) {
      seqMatrix(i) = new Array[Int](array.length)
    } // init
    for (i <- 1 until array.length) {

    }
  }
}
