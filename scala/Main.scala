import scala.io.StdIn.readLine
import scala.util.Try
import scala.collection.mutable.ListBuffer

object Main {
  def main(args: Array[String]): Unit = {
    val powerPlant = new PowerPlant("Solar energy.csv", "Wind energy.csv", "Hydro energy.csv")
    var choice = ""
    while (choice != "q") {
      println("\nPlease select an option:")
      println("1. Show Energy Data")
      println("2. Increase Solar Output")
      println("3. Increase Wind Output")
      println("4. Increase Hydro Output")
      println("5. Analyse Energy Data")
      println("6. Decrease Solar Output")
      println("7. Decrease Wind Output")
      println("8. Decrease Hydro Output")
      println("q. Quit")
      choice = scala.io.StdIn.readLine().trim().toLowerCase()
      choice match {
        case "1" => powerPlant.showEnergyData()
        case "2" => {

          powerPlant.increaseSolarOutput()
        }
        case "3" => {
          powerPlant.increaseWindOutput()
        }
        case "4" => {
         powerPlant.increaseHydroOutput()
        }
        case "5" => {
         powerPlant.analyzeEnergy()
        }
        case "6" => {
          powerPlant.decreaseSolarOutput()
        }
        case "7" => {
          powerPlant.decreaseWindOutput()
        }
        case "8" => {
          powerPlant.decreaseHydroOutput()
        }

        case "q" => println("Goodbye!")
        case _ => println("Invalid choice. Please try again.")
      }
    }
  }
}