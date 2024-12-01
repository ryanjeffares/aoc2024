package utils

def readFileLines(path: String): Array[String] =
  val source = io.Source.fromFile(path)
  try source.getLines().toArray finally source.close()
  