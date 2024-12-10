package days

import scala.io.Source
import scala.util.Using
import scala.util.matching.Regex

class Day3(inputPath: String) extends Day {
  override def part1(): Unit =
    utils.time(() =>Using(Source.fromFile(inputPath)) { source =>
      "mul[(][0-9]+,[0-9]+[)]".r
        .findAllIn(source.getLines.mkString)
        .map("[0-9]+".r.findAllIn(_).map(_.toInt).product)
        .sum
    }.get)

  override def part2(): Unit =
    utils.time(() =>Using(Source.fromFile(inputPath)) { source =>
      val text = source.getLines.mkString
      val instructionPattern = "mul[(][0-9]+,[0-9]+[)]".r
      val matches = "do[(][)]|don't[(][)]".r.findAllMatchIn(text)

      def findInstructions(text: CharSequence): Int =
        instructionPattern
          .findAllIn(text)
          .map("[0-9]+".r.findAllIn(_).map(_.toInt).product)
          .sum

      val first = findInstructions(text.subSequence(0, matches.next().start))
      val rest = matches.sliding(2).map {
        case Seq(a, b) if a.toString == "do()" => findInstructions(text.subSequence(a.end, b.start))
        case _ => 0
      }.sum

      first + rest
    }.get)
}
