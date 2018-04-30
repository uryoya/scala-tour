// 4章　ファーストフィズバス
package example

object FirstFizzBuzz {
  def fizzBuzz(n: Int): Unit = for { i <- 1 to n } {
    i match {
      case x if x % 15 == 0 =>
        println("FizzBuzz")
      case x if x % 3 == 0 =>
        println("Fizz")
      case x if x % 5 == 0 =>
        println("Buzz")
      case x =>
        println(x)
    }
  }

  def main(args: Array[String]): Unit = {
    fizzBuzz(100)
  }
}
