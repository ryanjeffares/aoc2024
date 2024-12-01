package days

import utils.readFileLines

import scala.collection.mutable.ArrayBuffer

class Day1 extends Day {
  override def part1(): Unit =
    val lines = readFileLines("src/main/resources/day1.txt")
    val left = ArrayBuffer.empty[Int]
    val right = ArrayBuffer.empty[Int]
    for (line <- lines) {
      val numbers = line.split(" {3}")
      left.append(numbers(0).toInt)
      right.append(numbers(1).toInt)
    }
    left.sortInPlace()
    right.sortInPlace()

    var sum = 0
    for i <- left.indices do
      sum += (left(i) - right(i)).abs

    println(sum)

  override def part2(): Unit =
    val lines = readFileLines("src/main/resources/day1.txt")
    val left = ArrayBuffer.empty[Int]
    var right = Map.empty[Int, Int]
    for (line <- lines) {
      val numbers = line.split(" {3}")
      left.append(numbers(0).toInt)
      val n = numbers(1).toInt
      if (right.contains(n)) {
        right = right.updated(n, right(n) + 1)
      } else {
        right = right + (n -> 1)
      }
    }

    var similarity = 0
    for (n <- left) {
      similarity += n * right.getOrElse(n, 0)
    }

    println(similarity)
}