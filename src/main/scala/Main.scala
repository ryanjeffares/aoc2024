import days.*

object Main {
  def main(args: Array[String]): Unit =
    if (args.isEmpty) {
      println("Provide day number (1 - 25)")
      return
    }

    val dayNumber = args(0).toInt - 1
    val days = new Day1("src/main/resources/day1.txt")
      :: new Day2("src/main/resources/day2.txt")
      :: new Day3("src/main/resources/day3.txt")
      :: new Day4("src/main/resources/day4.txt")
      :: Nil

    if (dayNumber >= days.length) {
      println("Given day has not been implemented yet")
      return
    }

    days(dayNumber).part1()
    days(dayNumber).part2()
}