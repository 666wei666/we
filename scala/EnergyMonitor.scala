import scala.io.Source

object EnergyMonitor {
  def main(args: Array[String]): Unit = {
    val solarFile = "Solar energy.csv"
    val windFile = "Wind energy.csv"
    val hydroFile = "Hydro energy.csv"

    val solarData = readData(solarFile)
    val windData = readData(windFile)
    val hydroData = readData(hydroFile)

    val totalSolar = calculateTotal(solarData)
    val totalWind = calculateTotal(windData)
    val totalHydro = calculateTotal(hydroData)

    println(s"Total solar energy produced: $totalSolar kWh")
    println(s"Total wind energy produced: $totalWind kWh")
    println(s"Total hydro energy produced: $totalHydro kWh")
  }

  def readData(filename: String): List[List[Double]] = {
    val source = Source.fromFile(filename)
    val data = source.getLines().map(line => line.split("\t").map(_.toDouble).toList).toList
    source.close()
    data
  }

  def calculateTotal(data: List[List[Double]]): Double = {
    val hourlyData = data.map(_.head)
    val total = hourlyData.sum
    total
  }
}
