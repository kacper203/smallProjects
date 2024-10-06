class Login {
    companion object {
        fun login(): Player { 
            print("Login: ")
            val player = Player(readln())
            println("Hello ${player.login.uppercase()}!")
            return player
        }
    }
}