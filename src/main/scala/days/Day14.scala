package days

import scala.io.Source
import scala.util.Using

class Day14(inputPath: String) extends Day {
  private val width = 101
  private val height = 103

  override def part1(): Unit =
    utils.time(() => Using(Source.fromFile(inputPath)) { source =>
      val (left, right) = source.getLines
        .map { line =>
          val split = line.split(' ')
          val Array(x, y) = split(0).substring(2).split(',').map(_.toInt)
          val Array(velX, velY) = split(1).substring(2).split(',').map(_.toInt)
          val (destX, destY) = ((x + velX * 100) % width, (y + velY * 100) % height)
          ((destX + width) % width, (destY + height) % height)
        }
        .filter((x, y) => x != width / 2 && y != height / 2)
        .partition((x, y) => x < width / 2)
      val (topLeft, bottomLeft) = left.partition((x, y) => y < height / 2)
      val (topRight, bottomRight) = right.partition((x, y) => y < height / 2)
      topLeft.size * topRight.size * bottomRight.size * bottomLeft.size
    }.get)

  override def part2(): Unit =
    Using(Source.fromFile(inputPath)) { source =>
      val robots = source.getLines
        .map { line =>
          val split = line.split(' ')
          val Array(x, y) = split(0).substring(2).split(',').map(_.toInt)
          val Array(velX, velY) = split(1).substring(2).split(',').map(_.toInt)
          ((x, y), (velX, velY))
        }
        .toArray

      for i <- 0 until 100 {
        
      }
    }
}
