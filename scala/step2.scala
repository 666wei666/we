import java.io._
import java.io.BufferedReader
import java.io.FileReader
import scala.io.Source
import scala.collection.mutable.ListBuffer

object step2 {
  def main(args: Array[String]): Unit = {
    import scala.io.Source
    import java.io.PrintWriter
    import java.io.File

    // Define the input files
    val solarFile = "Solar energy.csv"
    val windFile = "Wind energy.csv"
    val hydroFile = "Hydro energy.csv"

    // Define the output file
    val outputFile = "energy_data.txt"

    // Open the output file for writing
    val writer = new PrintWriter(new File(outputFile))

    // Read data from each input file and write it to the output file
    def processData(file: String): Unit = {
      // Open the input file for reading
      val source = Source.fromFile(file)
      // Iterate over each line in the file
      for (line <- source.getLines()) {
        // Split the line into fields
        val fields = line.split(",")
        // Write the fields to the output file
        writer.write(fields.mkString("\t") + "\n")
      }
      // Close the input file
      source.close()
    }

    // Process each input file
    processData(solarFile)
    processData(windFile)
    processData(hydroFile)

    // Close the output file
    writer.close()


  }}