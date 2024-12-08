package days

import scala.io.Source
import scala.util.Using

class Day8(inputPath: String) extends Day {
  private class Point(val x: Int, val y: Int)

  override def part1(): Unit =
    val (points, width, height) = getInput

    println(points.map { (char, points) =>
      points.indices.iterator.flatMap { i =>
        points.indices.iterator.drop(i + 1).flatMap { j =>
          findAntinodes(points, i, j, width, height, false)
        }
      }.toSet
    }.reduce(_ union _).size)

  override def part2(): Unit =
    val (points, width, height) = getInput

    println(points.map { (char, points) =>
      points.indices.iterator.flatMap { i =>
        points.indices.iterator.drop(i + 1).flatMap { j =>
          findAntinodes(points, i, j, width, height, true)
        }
      }.toSet
    }.reduce(_ union _).size)

  private def getInput: (Map[Char, Array[Point]], Int, Int) =
    val map = Using(Source.fromFile(inputPath))(_.getLines.map(_.toCharArray).toArray).get
    val points = map
      .zipWithIndex
      .flatMap((array, y) => array.zipWithIndex.filter(_._1 != '.').map((char, x) => (char, Point(x, y))))
      .groupMapReduce((char, p) => char)((char, p) => Array(p))((a1, a2) => a1.concat(a2))
    (points, map(0).length, map.length)


  private def findAntinodes(points: Array[Point], i: Int, j: Int, width: Int, height: Int, part2: Boolean): Iterator[(Int, Int)] =
    def inRange(point: (Int, Int)): Boolean =
      val (x, y) = point
      x >= 0 && y >= 0 && x < width && y < height

    val (p1, p2) = if (points(i).x <= points(j).x) {
      (points(i), points(j))
    } else {
      (points(j), points(i))
    }

    val xDiff = p2.x - p1.x
    val yDiff = p2.y - p1.y

    def findAntinodesRecursive(a1: (Int, Int), a2: (Int, Int), infinite: Boolean): Iterator[(Int, Int)] =
      val b1 = inRange(a1)
      val b2 = inRange(a2)

      if (!b1 && !b2) {
        Iterator.empty
      } else {
        val current = Seq(a1).filter(_ => b1) ++ Seq(a2).filter(_ => b2)
        if (infinite) {
          current.iterator ++ findAntinodesRecursive((a1._1 - xDiff, a1._2 - yDiff), (a2._1 + xDiff, a2._2 + yDiff), infinite)
        } else {
          current.iterator
        }
      }

    if (part2) {
      findAntinodesRecursive((p1.x, p1.y), (p2.x, p2.y), true)
    } else {
      findAntinodesRecursive((p1.x - xDiff, p1.y - yDiff), (p2.x + xDiff, p2.y + yDiff), false)
    }
}
