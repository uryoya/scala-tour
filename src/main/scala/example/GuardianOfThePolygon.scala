package example

abstract class Polygon(edges: List[Int]) {
  val n = edges.length // n角形
  val area: Double // 面積
}

class Triangle(edges: List[Int]) extends Polygon(edges) {
  // 与えられた辺から三角形が成立すると勝手に仮定
  val a = edges(0)
  val b = edges(1)
  val c = edges(2)

  val area = {
    // Heron's formula
    val s = (a + b + c) / 2.0
    math.sqrt(s * (s - a) * (s - b) * (s - c))
  }
}

class Square(edges: List[Int]) extends Polygon(edges) {
  // 与えられた辺から三角形が成立すると勝手に仮定
  val a = edges(0)
  val b = edges(1)
  val c = edges(2)
  val d = edges(3)

  val area = {
    // Brahmagupta's formula
    // 対角の和が180度と勝手に仮定
    val s = (a + b + c) / 2.0
    math.sqrt(s * (s - a) * (s - b) * (s - c) * (s - d))
  }
}

object GuardianOfThePolygon {
  def main(args: Array[String]): Unit = {
    val edges = List(3, 4, 5)
    val triangle = new Triangle(edges)
    println(triangle.area)
  }
}

