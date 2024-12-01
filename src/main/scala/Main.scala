import days.*

import scala.collection.mutable.ArrayBuffer

object Main {
  def main(args: Array[String]): Unit =
    if (args.isEmpty) {
      println("Provide day number (1 - 25)")
      return
    }

    val dayNumber = args(0).toInt - 1

    val days = ArrayBuffer.empty[Day]
    days.append(new Day1)

    if (dayNumber >= days.length) {
      println("Given day has not been implemented yet")
      return
    }

    days(dayNumber).part1()
    days(dayNumber).part2()
}