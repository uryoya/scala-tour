// 5章　多角形の守り人
package example

abstract class Polygon(edges: List[Int]) {
  val n = edges.length // n角形
  val area: Double // 面積
}

object Polygon {
  // 与えられる`edges` の辺に応じて
  // 適切な多角形を生成する静的なファクトリメソッド
  def fromEdges(edges: List[Int]): Polygon =
    edges.length match {
      case 3 =>
        new Triangle(edges)
      case 4 =>
        new Square(edges)
      case x =>
        ??? // 該当なし
    }
}

trait Color {
  val R: Int
  val G: Int
  val B: Int
}

trait Red extends Color {
  override val R: Int = 255
  override val G: Int = 0
  override val B: Int = 0
}

trait Green extends Color {
  override val R: Int = 0
  override val G: Int = 255
  override val B: Int = 0
}

class ColoredTriangle(edges: List[Int])
  extends Polygon(edges) with Red with Green {
  val a = edges(0)
  val b = edges(1)
  val c = edges(2)

  val area = {
    // Heron's formula
    val s = (a + b + c) / 2.0
    math.sqrt(s * (s - a) * (s - b) * (s - c))
  }
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
    val s = (a + b + c + d) / 2.0
    math.sqrt((s - a) * (s - b) * (s - c) * (s - d))
  }
}

object GuardianOfThePolygon {
  def main(args: Array[String]): Unit = {
    val edges3 = List(3, 4, 5)
    val edges4 = List(3, 13, 13, 14)
    val polygon3 = Polygon.fromEdges(edges3)
    val polygon4 = Polygon.fromEdges(edges4)
    println(s"辺の数: ${polygon3.n}, 面積: ${polygon3.area}")
    println(s"辺の数: ${polygon4.n}, 面積: ${polygon4.area}")

    val edges5 = List(3, 3, 3, 3, 3)
    //    val polygon5 = Polygon.fromEdges(edges5) // Error

    println()
    println("<<< ColoredTriangle >>>")
    // `Red` と `Green` が菱形継承問題を起こしている
    // 後勝ちなので `Green` による値が利用される
    val edges = List(3, 4, 5)
    val greenTriangle = new ColoredTriangle(edges)
    println(greenTriangle.R)
    println(greenTriangle.G)
    println(greenTriangle.B)
  }
}

