import days.*

import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]): Unit =
    if (args.isEmpty) {
      println("Provide day number (1 - 25)")
      return
    }

    val dayNumber = args(0).toInt - 1
    val days = new Day1("src/main/resources/day1.txt") :: Nil

    if (dayNumber >= days.length) {
      println("Given day has not been implemented yet")
      return
    }

    days(dayNumber).part1()
    days(dayNumber).part2()
}