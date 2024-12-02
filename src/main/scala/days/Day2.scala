package days

import scala.io.Source
import scala.util.Using

class Day2(inputPath: String) extends Day {
  override def part1(): Unit =
    println(Using(Source.fromFile(inputPath)) { source =>
      source.getLines().iterator.count { line =>
        line.split(' ').map(_.toInt).sliding(3).iterator.forall {
          case Array(a, b, c) =>
            val diff1 = (a - b).abs
            val diff2 = (b - c).abs
            val dir1 = b > a
            val dir2 = c > b
            diff1 >= 1 && diff1 <= 3 && diff2 >= 1 && diff2 <= 3 && dir1 == dir2
        }
      }
    }.get)

  override def part2(): Unit =
    println(Using(Source.fromFile(inputPath)) { source =>
      source.getLines().iterator.count { line =>
        
      }
    }.get)
}
