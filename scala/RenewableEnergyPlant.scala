import java.io.{BufferedWriter, File, FileWriter}
import scala.collection.immutable.ListMap

case class RenewableEnergyPlant(
                                 solarEnergy: Double = 0,
                                 windEnergy: Double = 0,
                                 hydroEnergy: Double = 0,
                                 collectedData: List[Double] = List()
                               ) {
  def getSolarEnergy(): Double = solarEnergy
  def getWindEnergy(): Double = windEnergy
  def getHydroEnergy(): Double = hydroEnergy

  def collectData(energyData: Double): RenewableEnergyPlant = {
    val newData = energyData :: collectedData
    RenewableEnergyPlant(solarEnergy, windEnergy, hydroEnergy, newData)
  }

  def filterData(filterType: String): List[Double] = {
    val hourlyData = collectedData.takeRight(24)
    val dailyData = collectedData.takeRight(24 * 7)
    val weeklyData = collectedData.takeRight(24 * 30)
    val monthlyData = collectedData

    filterType match {
      case "hourly" => hourlyData
      case "daily" => dailyData
      case "weekly" => weeklyData
      case "monthly" => monthlyData
      case _ => collectedData
    }
  }
  def sortData(sortType: String): List[Double] = {
    sortType match {
      case "ascending" => collectedData.sorted
      case "descending" => collectedData.sorted.reverse
      case _ => collectedData
    }
  }

  def searchData(keyword: String): List[Double] = {
    collectedData.filter(d => d.toString.contains(keyword))
  }

  def calculateMean(data: List[Double]): Double = {
    data.sum / data.length
  }

  def calculateMedian(data: List[Double]): Double = {
    val sortedData = data.sorted
    val n = sortedData.length
    if (n % 2 == 0) (sortedData(n / 2 - 1) + sortedData(n / 2)) / 2 else sortedData(n / 2)
  }

  def calculateMode(data: List[Double]): List[Double] = {
    val freqMap = data.groupBy(identity).mapValues(_.size)
    val maxFreq = freqMap.values.max
    freqMap.filter(_._2 == maxFreq).keys.toList
  }

  def calculateRange(data: List[Double]): Double = {
    data.max - data.min
  }

  def calculateMidRange(data: List[Double]): Double = {
    (data.max + data.min) / 2
  }

  def handleIssues(): Unit = {
    val solarEnergyLevel = getSolarEnergy()
    val windEnergyLevel = getWindEnergy()
    val hydroEnergyLevel = getHydroEnergy()

    if (solarEnergyLevel < 50) {
      // generate alert for low solar energy level
      println("WARNING: Low solar energy level detected!")
    }

    if (windEnergyLevel < 30) {
      // generate alert for low wind energy level
      println("WARNING: Low wind energy level detected!")
    }

    if (hydroEnergyLevel < 20) {
      // generate alert for low hydro energy level
      println("WARNING: Low hydro energy level detected!")
    }

    // check for other issues and generate alerts accordingly
  }


  def writeToFile(filename: String, data: List[Double], filterType: String = ""): Unit = {
    val filteredData = filterData(filterType)
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    filteredData.foreach(d => bw.write(d.toString + "\n"))
    bw.close()
  }

  def readFromFile(filename: String): List[Double] = {
    val file = new File(filename)
    if (file.exists()) {
      val lines = io.Source.fromFile(file).getLines.toList
      lines.map(_.toDouble)
    } else {
      List()
    }
  }

  def calculateTotalEnergy(data: List[Double]): Double = {
    data.sum
  }

  def calculateEnergyDistribution(data: List[Double]): Map[String, Double] = {
    val energyMap = Map("solar" -> solarEnergy, "wind" -> windEnergy, "hydro" -> hydroEnergy)
    val totalEnergy = energyMap.values.sum + data.sum
    val distributionMap = energyMap.mapValues(_ / totalEnergy)
    val collectedDataDistribution = "collected" -> (data.sum / totalEnergy)
    ListMap((distributionMap + collectedDataDistribution).toSeq.sortWith(_._2 > _._2):_*)
  }

}
