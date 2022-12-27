package cinema

class Room(rows: Int, rowSeats: Int) {
  val seats = Array(rows) { Array(rowSeats) { 'S' } }
  val totalIncome = totalIncome()

  var purchasedTickets = 0
  var currentIncome = 0

  private fun totalIncome(): Int {
    val rows = this.seats.size
    val columns = this.seats[0].size
    val totalSeats = rows * columns

    val totalIncome = if (totalSeats <= 60) totalSeats * 10 else {
      val frontHalf = rows / 2 * columns
      val endHalf = totalSeats - frontHalf
      frontHalf * 10 + endHalf * 8
    }

    return totalIncome
  }

  fun soldPercentage(): String {
    val allSeats = seats.size * seats[0].size
    val percent = purchasedTickets.toDouble() / allSeats * 100
    return "%.2f".format(percent)
  }
}

fun main() {
  println("Enter the number of rows:")
  val rows = readln().toInt()

  println("Enter the number of seats in each row:")
  val rowSeats = readln().toInt()

  val room = Room(rows, rowSeats)

  while (true) {
    printMenu()
    when (readln().toInt()) {
      1 -> printSeats(room)
      2 -> buyTicket(room)
      3 -> statistics(room)
      0 -> break
    }
  }
}

fun printMenu() {
  val menu = """
    1. Show the seats
    2. Buy a ticket
    3. Statistics
    0. Exit
  """.trimIndent()
  print(menu)
}

fun printSeats(room: Room) {
  val seats = room.seats
  val rowSeats = seats[0].size

  println()
  println("Cinema:")
  print(" ")
  for (seatNumber in 1..rowSeats) {
    print(" $seatNumber")
  }
  println()
  for (row in seats.indices) {
    print("${row + 1}")
    for (seat in seats[row]) {
      print(" $seat")
    }
    println()
  }
  println()
}

fun buyTicket(room: Room) {
  val seats = room.seats

  val column = seats[0]
  val totalSeats = seats.size * column.size
  val frontHalf = seats.size / 2

  var ticketPrice: Int
  while (true) {
    println("Enter a row number:")
    val rowNumber = readln().toInt()

    println("Enter a seat number in that row:")
    val seatNumber = readln().toInt()
    ticketPrice = if (totalSeats <= 60 || rowNumber <= frontHalf) 10 else 8

    try {
      val tryToBook = seats[rowNumber - 1][seatNumber - 1]
      if (tryToBook == 'S') {
        seats[rowNumber - 1][seatNumber - 1] = 'B'
        break
      } else {
        println("That ticket has already been purchased!")
      }
    } catch (e: ArrayIndexOutOfBoundsException) {
      println("Wrong input!")
    }
  }

  room.currentIncome += ticketPrice
  room.purchasedTickets++

  println("Ticket price: $$ticketPrice")
}

fun statistics(room: Room) {
  val statistics = """
    Number of purchased tickets: ${room.purchasedTickets}
    Percentage: ${room.soldPercentage()}%
    Current income: $${room.currentIncome}
    Total income: $${room.totalIncome}
  """.trimIndent()



  println(statistics)
}





