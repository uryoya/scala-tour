package example

object FirstFizzBuzz {
  def fizzBuzz(n: Int): Unit = for { i <- 1 to n } {
    if (i % 15 == 0) {
      println("FizzBuzz")
    } else if (i % 3 == 0) {
      println("Fizz")
    } else if (i % 5 == 0) {
      println("Buzz")
    } else {
      println(i)

    }
  }

  def main(args: Array[String]): Unit = {
    fizzBuzz(100)
  }
}
