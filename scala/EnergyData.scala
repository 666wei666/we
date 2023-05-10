import scala.io.Source
import scala.collection.mutable.ListBuffer

class EnergyData(val solarFile: String, val windFile: String, val hydroFile: String) {

  // Define case class for energy data
  case class EnergyData(hourly: Double, daily: Double, weekly: Double, monthly: Double)

  // Read data from CSV file
  def readData(file: String): List[EnergyData] = {
    val bufferedSource = Source.fromFile(file)
    val dataList = new ListBuffer[EnergyData]()
    for (line <- bufferedSource.getLines.drop(1)) {
      val cols = line.split(",").map(_.trim.toDouble)
      val data = EnergyData(cols(0), cols(1), cols(2), cols(3))
      dataList += data
    }
    bufferedSource.close
    dataList.toList
  }

  // Load energy data from CSV files
  val solarData = readData(solarFile)
  val windData = readData(windFile)
  val hydroData = readData(hydroFile)

  // Filter energy data by time period
  def filterData(dataList: List[EnergyData], period: String): List[Double] = {
    period.toLowerCase match {
      case "hourly" => dataList.map(_.hourly)
      case "daily" => dataList.map(_.daily)
      case "weekly" => dataList.map(_.weekly)
      case "monthly" => dataList.map(_.monthly)
      case _ => List.empty[Double]
    }
  }

  // Sort energy data by value
  def sortData(dataList: List[Double]): List[Double] = {
    dataList.sorted
  }

  // Calculate mean of energy data
  def calculateMean(dataList: List[Double]): Double = {
    dataList.sum / dataList.length
  }

  // Calculate median of energy data
  def calculateMedian(dataList: List[Double]): Double = {
    val sortedList = dataList.sorted
    if (sortedList.length % 2 == 0) {
      val mid = sortedList.length / 2
      (sortedList(mid - 1) + sortedList(mid)) / 2
    } else {
      sortedList(sortedList.length / 2)
    }
  }

  // Calculate mode of energy data
  def calculateMode(dataList: List[Double]): Option[Double] = {
    val freqMap = dataList.groupBy(identity).mapValues(_.size)
    freqMap.maxByOption(_._2).map(_._1)
  }

  // Calculate range of energy data
  def calculateRange(dataList: List[Double]): Double = {
    val sortedList = dataList.sorted
    sortedList.last - sortedList.head
  }

  // Calculate midrange of energy data
  def calculateMidrange(dataList: List[Double]): Double = {
    val sortedList = dataList.sorted
    (sortedList.head + sortedList.last) / 2
  }

  // Search for specific value in energy data
  def searchData(dataList: List[Double], value: Double): Boolean = {
    dataList.contains(value)
  }

  // Display energy data as a table
  def displayTable(dataList: List[Double], dataType: String): Unit = {
    val header = s"Energy Data ($dataType)"
    val line = "-" * header.length
    println(header)
    println(line)
    dataList.foreach(println)
    println(s"\nNumber of data points: ${dataList.length}")
    println(s"Mean: ${calculateMean(dataList)}")
    println(s"Median: ${calculateMedian(dataList)}")
    println(s"Mode: ${calculateMode(dataList).getOrElse("N/A")}")
    println(s"Range: ${calculateRange(dataList)}")
    println(s"Midrange: ${calculateMidrange(dataList)}\n")
  }}