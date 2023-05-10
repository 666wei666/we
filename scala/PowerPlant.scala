import java.io.{File, PrintWriter}
import java.time.{Duration, LocalDate}

case class Energy(hourly: Double, daily: Double, weekly: Double, monthly: Double)

class PowerPlant(solarFile: String, windFile: String, hydroFile: String) {




  var solarData = readEnergyData(solarFile)
  var windData = readEnergyData(windFile)
  var hydroData = readEnergyData(hydroFile)

  private def readEnergyData(file: String): List[Energy] = {
    io.Source.fromFile(file).getLines()
      .drop(1) // skip header
      .map(_.split(",").map(_.toDouble)) // split and convert to Double
      .map(arr => Energy(arr(0), arr(1), arr(2), arr(3))) // create case class
      .toList
  }

  def showEnergyData(): Unit = {
    println("Solar Energy:")
    solarData.foreach(println)
    println("Wind Energy:")
    windData.foreach(println)
    println("Hydro Energy:")
    hydroData.foreach(println)
  }

  def increaseSolarOutput(): Unit = {
    println("Enter the following values to increase Solar Energy:")
    print("Hourly: ")
    val hourly = scala.io.StdIn.readLine().trim().toDouble
    print("Daily: ")
    val daily = scala.io.StdIn.readLine().trim().toDouble
    print("Weekly: ")
    val weekly = scala.io.StdIn.readLine().trim().toDouble
    print("Monthly: ")
    val monthly = scala.io.StdIn.readLine().trim().toDouble

    val updatedData = solarData.map(e => Energy(e.hourly + hourly, e.daily + daily, e.weekly + weekly, e.monthly + monthly))
    solarData = updatedData
    writeEnergyData(solarFile, updatedData)
  }

  def increaseWindOutput(): Unit = {
    println("Enter the following values to increase Wind Energy:")
    print("Hourly: ")
    val hourly = scala.io.StdIn.readLine().trim().toDouble
    print("Daily: ")
    val daily = scala.io.StdIn.readLine().trim().toDouble
    print("Weekly: ")
    val weekly = scala.io.StdIn.readLine().trim().toDouble
    print("Monthly: ")
    val monthly = scala.io.StdIn.readLine().trim().toDouble

    val updatedData = windData.map(e => Energy(e.hourly + hourly, e.daily + daily, e.weekly + weekly, e.monthly + monthly))
    windData = updatedData
    writeEnergyData(windFile, updatedData)
  }

  def decreaseSolarOutput(): Unit = {
    println("Enter the following values to increase Solar Energy:")
    print("Hourly: ")
    val hourly = scala.io.StdIn.readLine().trim().toDouble
    print("Daily: ")
    val daily = scala.io.StdIn.readLine().trim().toDouble
    print("Weekly: ")
    val weekly = scala.io.StdIn.readLine().trim().toDouble
    print("Monthly: ")
    val monthly = scala.io.StdIn.readLine().trim().toDouble

    val updatedData = solarData.map(e => Energy(e.hourly - hourly, e.daily - daily, e.weekly - weekly, e.monthly - monthly))
    solarData = updatedData
    writeEnergyData(solarFile, updatedData)
  }
  def increaseHydroOutput(): Unit = {
    println("Enter the following values to increase Hydro Energy:")
    print("Hourly: ")
    val hourly = scala.io.StdIn.readLine().trim().toDouble
    print("Daily: ")
    val daily = scala.io.StdIn.readLine().trim().toDouble
    print("Weekly: ")
    val weekly = scala.io.StdIn.readLine().trim().toDouble
    print("Monthly: ")
    val monthly = scala.io.StdIn.readLine().trim().toDouble

    val updatedData = hydroData.map(e => Energy(e.hourly + hourly, e.daily + daily, e.weekly + weekly, e.monthly + monthly))
    hydroData = updatedData
    writeEnergyData(hydroFile, updatedData)
  }

  private def writeEnergyData(file: String, data: List[Energy]): Unit = {
    val writer = new PrintWriter(new File(file))
    writer.write("Hourly,Daily,Weekly,Monthly\n")
    data.foreach(e => writer.write(s"${e.hourly},${e.daily},${e.weekly},${e.monthly}\n"))
    writer.close()
  }

  def analyzeEnergy(): Unit = {
    println("Energy Analysis:")
    println(s"Mean Solar Energy: ${calculateMean(solarData.map(_.daily))}")
    println(s"Median Solar Energy: ${calculateMedian(solarData.map(_.daily))}")
    println(s"Mode Solar Energy: ${calculateMode(solarData.map(_.daily))}")
    println(s"Range Solar Energy: ${calculateRange(solarData.map(_.daily))}")
    println(s"Midrange Solar Energy: ${calculateMidrange(solarData.map(_.daily))}")
    println(s"Mean Wind Energy: ${calculateMean(windData.map(_.daily))}")
    println(s"Median Wind Energy: ${calculateMedian(windData.map(_.daily))}")
    println(s"Mode Wind Energy: ${calculateMode(windData.map(_.daily))}")
    println(s"Range Wind Energy: ${calculateRange(windData.map(_.daily))}")
    println(s"Midrange Wind Energy: ${calculateMidrange(windData.map(_.daily))}")
    println(s"Mean Hydro Energy: ${calculateMean(hydroData.map(_.daily))}")
    println(s"Median Hydro Energy: ${calculateMedian(hydroData.map(_.daily))}")
    println(s"Mode Hydro Energy: ${calculateMode(hydroData.map(_.daily))}")
    println(s"Range Hydro Energy: ${calculateRange(hydroData.map(_.daily))}")
    println(s"Midrange Hydro Energy: ${calculateMidrange(hydroData.map(_.daily))}")
  }

  private def calculateMean(data: List[Double]): Double = {
    data.sum / data.length
  }

  private def calculateMedian(data: List[Double]): Double = {
    val sorted = data.sorted
    if (sorted.length % 2 == 0) {
      (sorted(sorted.length / 2 - 1) + sorted(sorted.length / 2)) / 2
    } else {
      sorted(sorted.length / 2)
    }
  }

  private def calculateMode(data: List[Double]): Option[Double] = {
    val freq = data.groupBy(identity).mapValues(_.size)
    val maxFreq = freq.values.max
    freq.filter(_._2 == maxFreq).keys.toList match {
      case Nil => None
      case x :: Nil => Some(x)
      case _ => None // multiple modes
    }
  }

  private def calculateRange(data: List[Double]): Double = {
    data.max - data.min
  }

  private def calculateMidrange(data: List[Double]): Double = {
    (data.max + data.min) / 2
  }

  def decreaseWindOutput(): Unit = {
    println("Enter the following values to increase Solar Energy:")
    print("Hourly: ")
    val hourly = scala.io.StdIn.readLine().trim().toDouble
    print("Daily: ")
    val daily = scala.io.StdIn.readLine().trim().toDouble
    print("Weekly: ")
    val weekly = scala.io.StdIn.readLine().trim().toDouble
    print("Monthly: ")
    val monthly = scala.io.StdIn.readLine().trim().toDouble

    val updatedData = windData.map(e => Energy(e.hourly - hourly, e.daily - daily, e.weekly - weekly, e.monthly - monthly))
    windData = updatedData
    writeEnergyData(windFile, updatedData)
  }

  def decreaseHydroOutput(): Unit = {
    println("Enter the following values to increase Solar Energy:")
    print("Hourly: ")
    val hourly = scala.io.StdIn.readLine().trim().toDouble
    print("Daily: ")
    val daily = scala.io.StdIn.readLine().trim().toDouble
    print("Weekly: ")
    val weekly = scala.io.StdIn.readLine().trim().toDouble
    print("Monthly: ")
    val monthly = scala.io.StdIn.readLine().trim().toDouble

    val updatedData = hydroData.map(e => Energy(e.hourly - hourly, e.daily - daily, e.weekly - weekly, e.monthly - monthly))
    hydroData = updatedData
    writeEnergyData(hydroFile, updatedData)
  }
  case class DecreaseSolarOutput()
}