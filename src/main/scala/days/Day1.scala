package days

import scala.collection.mutable.ArrayBuffer

class Day1 extends Day {
  override def part1(): Unit =
    val left = ArrayBuffer.empty[Int]
    val right = ArrayBuffer.empty[Int]

    val input = io.Source.fromFile("src/main/resources/day1.txt")
    try input.getLines().foreach(line => {
      val numbers = line.split(" {3}")
      left.append(numbers(0).toInt)
      right.append(numbers(1).toInt)
    }) finally input.close()

    left.sortInPlace()
    right.sortInPlace()

    println(left.indices.fold(0)((sum, n) => sum + (left(n) - right(n)).abs))

  override def part2(): Unit =
    val left = ArrayBuffer.empty[Int]
    var right = Map.empty[Int, Int]

    val input = io.Source.fromFile("src/main/resources/day1.txt")
    try input.getLines().foreach(line => {
      val numbers = line.split(" {3}")
      left.append(numbers(0).toInt)
      val n = numbers(1).toInt
      if (right.contains(n)) {
        right = right.updated(n, right(n) + 1)
      } else {
        right = right + (n -> 1)
      }
    }) finally input.close

    println(left.fold(0)((sum, n) => sum + n * right.getOrElse(n, 0)))
}