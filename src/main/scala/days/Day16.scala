package days

import scala.io.Source
import scala.util.Using

class Day16(inputPath: String) extends Day {
  override def part1(): Unit =
    utils.time(() => Using(Source.fromFile(inputPath)) { source =>
      val map = source.getLines.map(_.toCharArray).toArray
      val pos = utils.findElementInMap(map, 'S')
      findEnd(map, pos, Set.empty, 0)
    }.get)

  private def findEnd(map: Array[Array[Char]], pos: (Int, Int), visited: Set[(Int, Int)], score: Int): Int =
    val (x, y) = pos
    val offsets = Array((0, -1), (0, 1), (-1, 0), (1, 0))
    val scores = offsets.map { (offX, offY) =>
      val (nextX, nextY) = (x + offX, y + offY)
      if (!visited.contains((nextX, nextY))) {
        val newScore = score + offX match
          case 0 => 1
          case _ => 1001

        map(nextY)(nextX) match
          case 'E' => newScore
          case '.' => findEnd(map, (nextX, nextY), visited + pos, newScore)
          case '#' => 0
      } else {
        0
      }
    }.filter(s => s > 0)

    if (scores.isEmpty) {
      0
    } else {
      scores.min
    }

  override def part2(): Unit = ()
}
