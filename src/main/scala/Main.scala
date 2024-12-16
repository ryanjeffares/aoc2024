import days.*

object Main {
  def main(args: Array[String]): Unit =
    if (args.isEmpty) {
      println("Provide day number (1 - 25)")
      return
    }

    val dayNumber = args(0).toInt - 1
    val days = Day1("src/main/resources/day1.txt")
      :: Day2("src/main/resources/day2.txt")
      :: Day3("src/main/resources/day3.txt")
      :: Day4("src/main/resources/day4.txt")
      :: Day5("src/main/resources/day5_rules.txt", "src/main/resources/day5_updates.txt")
      :: Day6("src/main/resources/day6.txt")
      :: Day7("src/main/resources/day7.txt")
      :: Day8("src/main/resources/day8.txt")
      :: Day9("src/main/resources/day9.txt")
      :: Day10("src/main/resources/day10.txt")
      :: null
      :: null
      :: null
      :: Day14("src/main/resources/day14.txt")
      :: Nil

    if (dayNumber >= days.length) {
      println("Given day has not been implemented yet")
      return
    }

    days(dayNumber).part1()
    days(dayNumber).part2()
}