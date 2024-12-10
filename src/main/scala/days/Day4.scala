package days

import scala.annotation.tailrec
import scala.io.Source
import scala.util.Using

class Day4(inputPath: String) extends Day {
  override def part1(): Unit =
    utils.time(() =>Using(Source.fromFile(inputPath)) { source =>
      val graph = source.getLines.map(_.toCharArray).toArray
      (for {
        i <- graph.indices
        j <- graph(i).indices
        if graph(i)(j) == 'X'
      } yield findXmas(graph, j, i)).sum
    }.get)

  override def part2(): Unit =
    utils.time(() =>Using(Source.fromFile(inputPath)) { source =>
      val graph = source.getLines.map(_.toCharArray).toArray
      (for {
        i <- graph.indices
        j <- graph(i).indices
        if graph(i)(j) == 'A'
      } yield findMasMas(graph, j, i)).count(identity)
    }.get)

  private def findXmas(graph: Array[Array[Char]], x: Int, y: Int): Int =
    Array((-1, -1), (0, -1), (1, -1), (-1, 0), (1, 0), (-1, 1), (0, 1), (1, 1)).count { dir =>
      val x1 = x + dir._1
      val y1 = y + dir._2
      (x1 >= 0 && y1 >= 0 && x1 < graph(0).length && y1 < graph.length) && findNextChar(graph, x1, y1, 1, dir)
    }

  @tailrec
  private def findNextChar(graph: Array[Array[Char]], x: Int, y: Int, level: Int, direction: (Int, Int)): Boolean =
    val toFind = "XMAS".charAt(level)
    (x >= 0 && y >= 0 && x < graph(0).length && y < graph.length)
      && (graph(y)(x) == toFind && (toFind == 'S' || findNextChar(graph, x + direction._1, y + direction._2, level + 1, direction)))

  private def findMasMas(graph: Array[Array[Char]], x: Int, y: Int): Boolean =
    (x > 0 && y > 0 && x < graph(0).length - 1 && y < graph.length - 1)
      && ((graph(y - 1)(x - 1) == 'S' && graph(y + 1)(x + 1) == 'M') || (graph(y - 1)(x - 1) == 'M' && graph(y + 1)(x + 1) == 'S'))
      && ((graph(y - 1)(x + 1) == 'S' && graph(y + 1)(x - 1) == 'M') || (graph(y - 1)(x + 1) == 'M' && graph(y + 1)(x - 1) == 'S'))
}
