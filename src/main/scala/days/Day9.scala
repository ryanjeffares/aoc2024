package days

import scala.collection.mutable
import scala.io.Source
import scala.util.Using

class Day9(inputPath: String) extends Day {
  override def part1(): Unit =
    utils.time(() => Using(Source.fromFile(inputPath)) { source =>
      val diskMap = source.mkString.trim.map(c => c - '0').toArray
      val moved = mutable.ArrayBuffer.empty[Int]
      var start = 0
      var end = diskMap.length - 1

      while (start <= end) {
        // add entries for this file, move start to next empty space
        val firstLengthNeeded = diskMap(start)
        val firstId = start / 2
        for (i <- 0 until firstLengthNeeded) {
          moved.addOne(firstId)
        }
        start += 1

        // add as many entries as can fit
        while (start <= end && diskMap(start) > 0) {
          val secondLengthNeeded = diskMap(end)
          val freeSpace = diskMap(start)
          val secondId = end / 2
          val numUsed = secondLengthNeeded.min(freeSpace)
          diskMap(end) -= numUsed
          for (i <- 0 until numUsed) {
            moved.addOne(secondId)
          }

          if (secondLengthNeeded <= freeSpace) {
            // didn't use all the free space, or used exactly enough - update the amount of free space left, move end to next file
            diskMap(start) -= secondLengthNeeded
            end -= 2
          } else {
            // didn't have enough free space - update the amount of free space left
            diskMap(start) = 0
          }
        }

        start += 1
      }

      moved.zipWithIndex.map((i, value) => (i * value).toLong).sum
    }.get)

  override def part2(): Unit =
    utils.time(() =>Using(Source.fromFile(inputPath)) { source =>
      val diskMap = source.mkString.trim.map(c => c - '0').toArray
      val moved = mutable.ArrayBuffer.empty[Int]

      for (i <- diskMap.indices.reverse by -2) {
        println(i)
      }
    }.get)
}
