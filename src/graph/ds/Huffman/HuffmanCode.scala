package graph.ds.Huffman

object HuffmanCode {
  def codec(str : String) : Code = {
    codec(str.toCharArray)
  }

  def codec(array: Array[Char]) : Code = {
    val charOrder: List[TreeNode]
    = array
      .groupBy(a => a)
      .map(a => (a._2.head, a._2.length))
      .toList
      .sortBy(a => a._2)
      .map(a => new TreeNode(a._1, a._2))

    val c = charOrder
    new Code(array, treeMerge(c))
  }
  def encode(cs : Array[Char], e : TreeNode) : String = {
    val map = TreeNode.makeMap(e)
    cs.map(c => map.get(c)).reduce(_ + _)
  }

  def decode(cs : String, e : TreeNode) : Array[Char] = {
    var carr = cs.toCharArray
    var emp = List[Char]()
    while (carr.nonEmpty) {
      var temp = e
      while (temp.isTrunk) {
        val c = carr.head
        carr = carr.tail
        if (c equals '1') temp = temp.getRight
        else temp = temp.getLeft
      }
      emp = emp :+ temp.getNodeChar
    }
    emp.toArray
  }

  def treeMerge(charOrder : List[TreeNode]) : TreeNode = {
    if (charOrder.isEmpty) return null
    if (charOrder.length == 1) return charOrder.head
    if (charOrder.length == 2) return new TreeNode(charOrder.head, charOrder(1))
    var temp = charOrder
    val head = temp.head
    temp = temp.tail
    val head_2 = temp.head
    temp = temp.tail
    treeMerge((new TreeNode(head, head_2) :: temp).sortBy(t => t.getNum))
  }

  def main(args: Array[String]): Unit = {
    val s = "woainiqinaidebibi"
    val code = codec(s)
    println(code.getAfter)
    decode(code.getAfter, code.getSymbolTable)
      .foreach(print(_))
  }
}