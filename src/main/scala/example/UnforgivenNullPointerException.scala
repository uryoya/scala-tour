// 7章　ぬるぽなんて、あたしが許さない
package example2

abstract class Polygon(edges: List[Int]) {
  val n = edges.length // n角形
  val area: Double // 面積
}

object Polygon {
  // 与えられる`edges` の辺に応じて
  // 適切な多角形を生成する静的なファクトリメソッド
  // 返り値を `Option[Polygon]` 型に変更
  def fromEdges(edges: List[Int]): Option[Polygon] =
    edges.length match {
      case 3 => Triangle.fromEdges(edges)
      case 4 => Square.fromEdges(edges)
      case x =>
        None
    }
}

// プライベートコンストラクタに変更することで、
// インスタンス生成を `Triangle.fromEdges` 経由に制限
class Triangle private (edges: List[Int]) extends Polygon(edges) {
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

object Triangle {
  // 辺の数だけでなく図形が成立するかどうかのチェックもするファクトリメソッド
  def fromEdges(edges: List[Int]): Option[Triangle] =
    if(edges.length == 3
        && edges(0) + edges(1) > edges(2)
        && edges(1) + edges(2) > edges(0)
        && edges(2) + edges(0) > edges(1))
      Some(new Triangle(edges))
    else None
}

// プライベートコンストラクタに変更することで、
// インスタンス生成を `Square.fromEdges` 経由に制限
class Square private (edges: List[Int]) extends Polygon(edges) {
  val a = edges(0)
  val b = edges(1)
  val c = edges(2)
  val d = edges(3)

  val area = {
    // Brahmagupta's formula
    // 対角の和が180度と勝手に仮定（この仮定は簡単のために維持する）
    val s = (a + b + c + d) / 2.0
    math.sqrt((s - a) * (s - b) * (s - c) * (s - d))
  }
}

object Square {
  // 辺の数だけでなく図形が成立するかどうかのチェックもするファクトリメソッド
  def fromEdges(edges: List[Int]): Option[Square] =
    if(edges.length == 4
        && edges(2) - edges(1) - edges(0) < edges(3)
        && edges(3) < edges(2) + edges(1) + edges(0))
      Some(new Square(edges))
    else None
}

object UnforgivenNullPointerException {
  def main(args: Array[String]): Unit = {
    val edges3 = List(3, 4, 5)
    val polygon3 = Polygon.fromEdges(edges3)
    // 面積を出力する
    polygon3 match {
      case Some(p) => println(p.area)
      case None =>
        println("不正な辺か図形が成立しない辺が与えられたため面積は出力できません")
    }

    val invalidEdges3 = List(3, 4, 100)
    val invalidPolygon3 = Polygon.fromEdges(invalidEdges3)
    // 面積を出力する
    invalidPolygon3 match {
      case Some(p) => println(p.area)
      case None =>
        println("不正な辺か図形が成立しない辺が与えられたため面積は出力できません")
    }

    val invalidEdge5 = List(3, 3, 3, 3, 3)
    val invalidPolygon5 = Polygon.fromEdges(invalidEdge5)
    // 面積を出力する
    invalidPolygon5 match {
      case Some(p) => println(p.area)
      case None =>
        println("不正な辺が与えられたため面積は出力できません")
    }
  }
}

