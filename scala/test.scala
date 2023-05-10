import java.io._
import java.io.BufferedReader
import java.io.FileReader
import java.io.File
import scala.io.Source
object test {
  def main(args: Array[String]): Unit = {
    //step 3
    val solarFile = "Solar energy.csv"
    val windFile = "Wind energy.csv"
    val hydroFile = "Hydro energy.csv"

    val powerPlant = new PowerPlant(solarFile, windFile, hydroFile)

    powerPlant.showEnergyData()
    //
    val fileName = "Solar energy.csv"
    val file = new File(fileName)
    val writer = new BufferedWriter(new FileWriter(file))

    writer.write("Hourly,Daily,Weekly,Monthly\n")
    writer.write("2.5,60,420,1800\n")
    writer.write("1.6,38.4,268.8,1152\n")
    writer.write("1.9,45.6,319.2,1368\n")
    writer.write("2.2,52.8,369,1584\n")
    writer.write("1.7,40.8,285,1223\n")
    writer.write("1.3,31.2,218.4,936\n")
    writer.write("1.8,43.2,302,1296\n")
    writer.write("1.4,33.6,235,1008\n")
    writer.write("2.0,48,336,1440\n")
    writer.write("1.5,36,252,1080\n")

    writer.close()

    val fl = "Wind energy.csv"
    val fi = new File(fl)
    val bw = new BufferedWriter(new FileWriter(fi))
    bw.write("Hourly,Daily,Weekly,Monthly\n")
    bw.write("2.2,52.8,369.6,1584\n")
    bw.write("1.9,45.6,319.2,1368\n")
    bw.write("1.4,33.6,235.2,1008\n")
    bw.write("1.2,28.8,201.6,864\n")
    bw.write("1.5,36,252,1080\n")
    bw.write("2.3,55.2,386.4,1656\n")
    bw.write("1.6,38.4,268.8,1152\n")
    bw.write("1.8,43.2,302.4,1296\n")
    bw.write("1.3,31.2,218.5,936\n")
    bw.close()

    val fn = "Hydro energy.csv"
    val f = new File(fn)
    val bb = new BufferedWriter(new FileWriter(f))
    bb.write("Hourly,Daily,Weekly,Monthly\n")
    bb.write("2.2,52.8,369.6,1584\n")
    bb.write("1.9,45.6,319.2,1368\n")
    bb.write("1.4,33.6,235.2,1008\n")
    bb.write("1.2,28.8,201.6,864\n")
    bb.write("1.5,36,252,1080\n")
    bb.write("2.3,55.2,386.4,1656\n")
    bb.write("1.6,38.4,268.8,1152\n")
    bb.write("1.8,43.2,302.4,1296\n")
    bb.write("1.3,31.2,218.5,936\n")
    bb.close()


    def readData(fileName: String): List[List[Double]] = {
      val source = Source.fromFile(fileName)
      val lines = source.getLines().toList
      source.close()

      val data = for {
        line <- lines.tail // Skip first line
        values = line.split(",").map(_.toDouble).toList
      } yield values

      data
    }

    val solarData = readData("Solar energy.csv")
    val windData = readData("Wind energy.csv")
    val hydroData = readData("Hydro energy.csv")
    displayTable("Solar Energy", solarData)
    displayTable("Wind Energy", windData)
    displayTable("Hydro Energy", hydroData)
    def displayTable(title: String, data: List[List[Double]]): Unit = {
      println(s"\n$title")
      println("Hourly\tDaily\tWeekly\tMonthly")
      for (row <- data) {
        println(row.mkString("\t"))
      }
    }


  }}
