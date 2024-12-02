package days

import scala.io.Source
import scala.util.Using

class Day1(inputPath: String) extends Day {
  override def part1(): Unit =
    val tables = Using(Source.fromFile(inputPath)) { source =>
      val (left, right) = getTables(source)
      left.sorted.zip(right.sorted)
    }.get

    println(tables.map((l, r) => (l - r).abs).sum)

  override def part2(): Unit =
    val (left, right) = Using(Source.fromFile(inputPath)) { source =>
      val (left, right) = getTables(source)
      (left, right.groupMapReduce(identity)(_ => 1)(_ + _))
    }.get

    println(left.fold(0)((sum, n) => sum + n * right.getOrElse(n, 0)))

  private def getTables(source: Source): (Seq[Int], Seq[Int]) =
    source.getLines().toSeq.map { line =>
      val Array(l, r) = line.split(" {3}").map(_.toInt)
      (l, r)
    }.unzip
}