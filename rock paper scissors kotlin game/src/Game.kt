import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

class Game(
    private val player: Player
){
    private fun saveScores(data: String) {
        val fileWriter = FileWriter("scores.txt", true)
        val bufferedWriter = BufferedWriter(fileWriter)

        bufferedWriter.use { bw ->
            bw.write(data)
            bw.newLine()
        }
    }

    private fun displayScores() {
        val fileReader = FileReader("scores.txt")
        val bufferedReader = BufferedReader(fileReader)

        println("Scores: ")
        bufferedReader.use { br ->
            var line: String?
            while (br.readLine().also {line = it} != null) {
                println(line)
            }
        }
    }

    fun startGame() {
            val listOfChoices: List<String> = listOf("Rock", "Paper", "Scissors")
            var choice: String?
            var compChoice: String?
            var roundNumber: Int? = null

            while (roundNumber == null) {
                try {
                    print("How many rounds?: ")
                    roundNumber = readln().toInt()
                } catch (e: NumberFormatException) {
                    println("Invalid input. Please enter a valid number.")
                }
            }

            for(i in 1..roundNumber)
            {
                do {
                    print("Your choice (r = Rock, p = Paper, s = Scissors): ")
                    choice = readln()

                    if (choice != "r" && choice != "p" && choice != "s") {
                        println("Invalid input. Please enter 'r', 'p' or 's'.")
                    }

                } while (choice != "r" && choice != "p" && choice != "s")

                when (choice) {
                    "s" -> choice = "Scissors"
                    "p" -> choice = "Paper"
                    "r" -> choice = "Rock"
                }

                compChoice = listOfChoices.random()

                if (choice == compChoice)
                    println("\nYou and Comp both chose $compChoice!\nYou gain 0 points.\n")
                else if (choice == "Scissors" && compChoice == "Paper" || choice == "Paper" && compChoice == "Rock" || choice == "Rock" && compChoice == "Scissors") {
                    player.playerPoints += 1
                    println("\nYour choice: $choice\nComp choice: $compChoice\nYou gain +1 point!\n")
                } else if (choice == "Scissors" && compChoice == "Rock" || choice == "Paper" && compChoice == "Scissors" || choice == "Rock" && compChoice == "Paper") {
                    player.playerPoints -= 1
                    println("\nYour choice: $choice\nComp choice: $compChoice\nYou lose -1 point!\n")
                }

                println("Your points: " + player.playerPoints + "\n")
            }
            if(player.playerPoints == 1 || player.playerPoints == -1)
            println("Your final score is: " + player.playerPoints + " point!\n")
            else println("Your final score is: " + player.playerPoints + " points!\n")

            saveScores(player.login + ": " + player.playerPoints.toString())
            displayScores()
        }
}


