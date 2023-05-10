import scala.io.Source

object ReadCSVFile {
  def main(args: Array[String]) {
    val filename = "Solar energy.csv"
    val source = Source.fromFile(filename)
    val lines = source.getLines()
    for (line <- lines) {
      val cols = line.split(",").map(_.trim)
      println(s"${cols(0)}\t${cols(1)}\t${cols(2)}\t${cols(3)}")
    }
    source.close()
  }
}
