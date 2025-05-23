package utils

def time[A](f: () => A): Unit =
  val t1 = System.nanoTime
  val answer = f()
  val t2 = System.nanoTime
  println("Found answer " + answer + " in " + (t2 - t1) / 1e6d + " ms")

def findElementInMap[A](map: Array[Array[A]], element: A): (Int, Int) =
  map.zipWithIndex.flatMap { (row, y) =>
    map(y).zipWithIndex.collectFirst {
      case (a, x) if a == element => (x, y)
    }
  }.head