package utils

def readFileLines(path: String): List[String] =
  val source = io.Source.fromFile(path)
  try source.getLines().toList finally source.close()
