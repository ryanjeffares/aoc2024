package days

import scala.io.Source
import scala.util.Using

class Day6(inputPath: String) extends Day {
  private enum Direction {
    case Up, Down, Left, Right
  }

  override def part1(): Unit =
    println(Using(Source.fromFile(inputPath)) { source =>
      val map = source.getLines().map(_.toCharArray).toArray
      val height = map.length
      val width = map(0).length

      var direction = Direction.Up
      var (x, y) = map.zipWithIndex.flatMap { (row, y) =>
        map(y).zipWithIndex.collectFirst {
          case (char, x) if char == '^' => (x, y)
        }
      }.head

      var guardHasLeft = false
      while (!guardHasLeft) {
        val (nextX, nextY) = direction match {
          case Direction.Up => (x, y - 1)
          case Direction.Down => (x, y + 1)
          case Direction.Left => (x - 1, y)
          case Direction.Right => (x + 1, y)
        }

        if (nextX < 0 || nextX == width || nextY < 0 || nextY == height) {
          guardHasLeft = true
        } else if (map(nextY)(nextX) == '#') {
          direction = direction match {
            case Direction.Up => Direction.Right
            case Direction.Down => Direction.Left
            case Direction.Left => Direction.Up
            case Direction.Right => Direction.Down
          }
        } else {
          map(y)(x) = 'X'
          x = nextX
          y = nextY
        }
      }

      map.indices
        .iterator
        .map(y => map(y).indices.iterator.count(x => map(y)(x) == 'X'))
        .sum + 1
    }.get)

  override def part2(): Unit = ()
}
