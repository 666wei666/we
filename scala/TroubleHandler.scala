import scala.io.Source

object TroubleHandler {
  def main(args: Array[String]): Unit = {
    val solarThreshold = 30.0 // 设置太阳能的阈值为30
    val windThreshold = 40.0 // 设置风能的阈值为40
    val hydroThreshold = 50.0 // 设置水力能的阈值为50

    // 解析太阳能文件
    val solarData = parseFile("Solar energy.csv")
    checkOutput(solarData, solarThreshold, "Solar")

    // 解析风能文件
    val windData = parseFile("Wind energy.csv")
    checkOutput(windData, windThreshold, "Wind")

    // 解析水力能文件
    val hydroData = parseFile("Hydro energy.csv")
    checkOutput(hydroData, hydroThreshold, "Hydro")
  }

  def parseFile(filename: String): Seq[Double] = {
    val file = Source.fromFile(filename)
    val lines = file.getLines.drop(1) // 跳过标题行
    val data = for {
      line <- lines
      values = line.split(",").map(_.trim.toDouble)
      output = values(1) // 取出输出值
    } yield output
    file.close()
    data.toSeq
  }

  def checkOutput(data: Seq[Double], threshold: Double, source: String): Unit = {
    val lowOutput = data.filter(_ < threshold)
    if (lowOutput.nonEmpty) {
      val message = s"Low ${source} output detected: ${lowOutput.mkString(", ")}"
      println(message)
    }
  }
}
