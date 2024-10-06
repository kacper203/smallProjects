import Login.Companion.login

fun main() {
    val player: Player = login()
    Game(player).startGame()
}