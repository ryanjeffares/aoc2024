package days

import scala.io.Source
import scala.util.Using

class Day2(inputPath: String) extends Day {
  override def part1(): Unit =
    utils.time(() =>Using(Source.fromFile(inputPath)) { source =>
      source.getLines().iterator.count(line => reportIsSafe(line.split(' ').map(_.toInt)))
    }.get)

  override def part2(): Unit =
    utils.time(() =>Using(Source.fromFile(inputPath)) { source =>
      source.getLines().iterator.count { line =>
        val nums = line.split(' ').map(_.toInt)
        reportIsSafe(nums) || nums.indices.iterator.exists { i =>
          reportIsSafe(nums.indices.filter(_ != i).map(nums(_)))
        }
      }
    }.get)

  private def reportIsSafe(report: IndexedSeq[Int]): Boolean =
    report.sliding(3).iterator.forall {
      case IndexedSeq(a, b, c) =>
        val diff1 = (a - b).abs
        val diff2 = (b - c).abs
        val dir1 = b > a
        val dir2 = c > b
        diff1 >= 1 && diff1 <= 3 && diff2 >= 1 && diff2 <= 3 && dir1 == dir2
    }
}
