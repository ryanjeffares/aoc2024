package days

import scala.io.Source
import scala.util.Using

class Day5 extends Day {
  override def part1(): Unit =
    val rules = Using(Source.fromFile("src/main/resources/day5_rules.txt")) { source =>
      source.getLines()
        .map(_.split('|').map(_.toInt))
        .toArray
        .groupMapReduce(a => a(0))(a => List(a(1)))((l1, l2) => l1.concat(l2))
    }.get
    
    println(Using(Source.fromFile("src/main/resources/day5_updates.txt")) { source =>
      source.getLines()
    })


  override def part2(): Unit = ()
}
