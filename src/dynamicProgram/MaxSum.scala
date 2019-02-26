package dynamicProgram

object MaxSum {
  def MaxSumBrutal(arr : Array[Int]) : Int = {
    var maxSum = Int.MinValue
    for (i <- arr.indices) {
      for (j <- i until arr.length) {
        var sumTemp = 0
        for (k <- i to j) {
          sumTemp += arr(k)
        }
        if (maxSum < sumTemp) maxSum = sumTemp
      }
    }
    maxSum
  }

  def divideAndConquer(arr : Array[Int], i : Int, j : Int) : Int = {
    if (i == j) return arr(i)
    val mid = (i + j) / 2
    var lowMax = 0
    var leftMax = Int.MinValue
    var highMax = 0
    var rightMax = Int.MinValue
    for (k <- i to mid) {
      val now = mid - k + i
      lowMax += arr(now)
      if (lowMax > leftMax) leftMax = lowMax
    }
    for (k <- mid + 1 to j) {
      highMax += arr(k)
      if (highMax > rightMax) rightMax = highMax
    }
    Math.max(Math.max(leftMax + rightMax, divideAndConquer(arr, i, mid))
      , divideAndConquer(arr, mid + 1, j)
    )
  }

  def DP(array: Array[Int]) : Int = {
    var sum = 0
    var b = 0
    for (i <- array.indices) {
      if (b > 0)
        b = b + array(i)
      else b = array(i)
      if (b > sum) {
        sum = b
      }
    }
    sum
  }

  def main(args: Array[String]): Unit = {
    val i = Array[Int](1,2,10,2,-100,7,4,2,6,8,-2,-8,-99,4,5,100)
    println(MaxSumBrutal(i))
    println(divideAndConquer(i, 0, i.length - 1))
    println(DP(i))
  }
}
