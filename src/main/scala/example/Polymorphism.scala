// 6章　再会のFizzBuzz
// Scalaの多相についての紹介
package example

object Polymorphism extends App {
  class Box[T](var element: T) {
    def get(): T = element
    def set(newElement: T): Unit = {
      element = newElement
    }
  }

  val intBox = new Box[Int](10) // `Box[Int]`
  println(intBox.get())
  intBox.set(0)
  println(intBox.get())

  sealed abstract class Animal(val cryJP: String, val cryEN: String)
  object Cat extends Animal("にゃー", "Meow")
  object Dog extends Animal("わんわん", "Bowwow")
  object Lion extends Animal("がおー", "Roar")

  val animalBox = new Box[Animal](Cat) // `Box[Animal]`
  println(animalBox.get())
  animalBox.set(Dog) // `Dog` は `Animal` を継承しているのでOK
  println(animalBox.get())

  // 型パラメータの省略
  val intBox2 = new Box(10) // `Box[Int]`
  val catBox = new Box(Cat) // `Box[Cat]`
//  catBox.set(Dog) // Error: `Box[Cat]` に `Dog` は入らない
}
