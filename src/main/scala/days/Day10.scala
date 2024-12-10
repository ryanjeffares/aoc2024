package days

import scala.io.Source
import scala.util.Using

class Day10(inputPath: String) extends Day {
  override def part1(): Unit =
    utils.time(() => Using(Source.fromFile(inputPath)) { source =>
      val map = source.getLines.map(s => s.toCharArray.map(_ - '0')).toArray
      map.indices.map(y => map(y).indices.map(x => findTrails(map, Array.empty, (x, y), 0).toSet.size).sum).sum
    }.get)

  override def part2(): Unit =
    utils.time(() => Using(Source.fromFile(inputPath)) { source =>
      val map = source.getLines.map(s => s.toCharArray.map(_ - '0')).toArray
      map.indices.map(y => map(y).indices.map(x => findTrails(map, Array.empty, (x, y), 0).length).sum).sum
    }.get)

  private def findTrails(map: Array[Array[Int]], nines: Array[(Int, Int)], pos: (Int, Int), level: Int): Array[(Int, Int)] =
    val (x, y) = pos
    if (x < 0 || y < 0 || x >= map(0).length || y >= map.length) {
      Array.empty
    } else {
      val current = map(y)(x)
      if (current == level) {
        if (level == 9) {
          nines.concat(Array(pos))
        } else {
          val coords = Array((0, -1), (1, 0), (0, 1), (-1, 0))
          coords.map { coord =>
            val (x1, y1) = (x + coord._1, y + coord._2)
            findTrails(map, nines, (x1, y1), level + 1)
          }.reduce(_ concat _)
        }
      } else {
        Array.empty
      }
    }
}
