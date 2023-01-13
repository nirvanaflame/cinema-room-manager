package indigo

fun main() {
  val deck = Indigo.Deck()
  while (true) {
    println("Choose an action (reset, shuffle, get, exit):")
    when (readln()) {
      "reset" -> deck.reset()
      "shuffle" -> deck.shuffle()
      "get" -> {
        println("Number of cards:")
        deck.get(readln().toInt())
      }

      "exit" -> {
        deck.exit()
        break
      }
    }
  }
}

class Indigo {
  enum class Rank(val symbol: String) {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    companion object {
      override fun toString(): String = values().joinToString(" ") { it.symbol }
    }
  }

  enum class Suit(val symbol: Char) {
    CLUBS('♣'),
    DIAMONDS('♦'),
    HEARTS('♥'),
    SPADES('♠');

    companion object {
      override fun toString(): String = values().joinToString(" ") { "${it.symbol}" }
    }
  }

  data class Card(val suit: Suit, val rank: Rank) {
    override fun toString() = "${rank.symbol}${suit.symbol}"

    companion object {
      val allCards = buildList {
        Suit.values().forEach { suit ->
          Rank.values().forEach { rank ->
            add(Card(suit, rank))
          }
        }
      }.toMutableList()
    }
  }

  class Deck() {
    private var listCards: MutableList<Card> = Card.allCards

    fun reset() {
      listCards.clear()
      listCards.addAll(Card.allCards)
      println(listCards.toString())
      println("Card deck is reset.")
    }

    fun shuffle() {
      listCards.shuffle()
      println("Card deck is shuffled.")
    }

    fun get(number: Int) {
      if (number !in 1..52) {
        println("Invalid number of cards.")
      } else if (number > listCards.size) {
        println("The remaining cards are insufficient to meet the request.")
      } else {
        repeat(number) {
          print("${listCards.removeFirst()} ")
        }
        println()
      }
    }

    fun exit() {
      println("Bye")
    }
  }
}