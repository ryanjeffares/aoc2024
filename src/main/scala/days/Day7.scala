package days

import scala.io.Source
import scala.util.Using

class Day7(inputPath: String) extends Day {
  private enum Op {
    case Add, Multiply, Concat
  }

  override def part1(): Unit =
    utils.time(() =>solve(Array(Op.Add, Op.Multiply)))

  override def part2(): Unit =
    utils.time(() =>solve(Array(Op.Add, Op.Multiply, Op.Concat)))

  private def solve(ops: Array[Op]): Long =
    Using(Source.fromFile(inputPath)) { source =>
      source.getLines()
        .map { line =>
          val split = line.split(':')
          val target = split(0).toLong
          val operands = split(1).trim().split(' ').map(_.toLong)
          (target, operands)
        }
        .filter((target, operands) => anyCombinationMatches(target, operands.drop(1), Array(operands(0)), ops))
        .map(_._1)
        .sum
    }.get

  private def anyCombinationMatches(target: Long, operands: Array[Long], values: Array[Long], ops: Array[Op]): Boolean =
    if (operands.isEmpty) {
      values.contains(target)
    } else {
      values.exists { value =>
        value <= target && anyCombinationMatches(target, operands.drop(1), ops.map {
          case Op.Add => value + operands(0)
          case Op.Multiply => value * operands(0)
          case Op.Concat => (value.toString + operands(0).toString).toLong
        }, ops)
      }
    }
}
