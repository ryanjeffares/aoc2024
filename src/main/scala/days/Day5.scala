package days

import scala.io.Source
import scala.util.Using

class Day5(rulesPath: String, updatesPath: String) extends Day {
  override def part1(): Unit =
    val rules = getRules

    utils.time(() =>Using(Source.fromFile(updatesPath)) { source =>
      source.getLines()
        .map(_.split(',').map(_.toInt))
        .filter(update => isOrdered(update, rules))
        .map(update => update(update.length / 2))
        .sum
    }.get)

  override def part2(): Unit =
    val rules = getRules

    utils.time(() =>Using(Source.fromFile(updatesPath)) { source =>
      source.getLines()
        .map(_.split(',').map(_.toInt))
        .filter(update => !isOrdered(update, rules))
        .map { update =>
          update.indices.iterator.foreach { i =>
            update.indices.drop(i + 1).foreach { j =>
              rules.get(update(j)) match {
                case Some(s) if s.contains(update(i)) =>
                  val temp = update(i)
                  update.update(i, update(j))
                  update.update(j, temp)
                case Some(_) => ()
                case None => ()
              }
            }
          }

          update(update.length / 2)
        }
        .sum
    }.get)

  private def getRules: Map[Int, Set[Int]] =
    Using(Source.fromFile(rulesPath)) { source =>
      source.getLines()
        .map(_.split('|').map(_.toInt))
        .toArray
        .groupMapReduce(a => a(0))(a => Set(a(1)))((s1, s2) => s1.union(s2))
    }.get

  private def isOrdered(update: Array[Int], rules: Map[Int, Set[Int]]): Boolean =
    update.indices.iterator.forall { i =>
      update.indices.drop(i + 1).forall { j =>
        rules.get(update(j)) match {
          case Some(s) => !s.contains(update(i))
          case None => true
        }
      }
    }
}
