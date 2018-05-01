package example

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object VoyageToTheFuture extends App {
  val f1: Future[Int] = Future {
    Thread.sleep(5000) // 重い処理
    println("タスク１終了")
    1 // 重い処理の結果
  }

  val f2: Future[Int] = Future {
    Thread.sleep(1000) // 軽い処理
    println("タスク２終了")
    2 / 0 // ゼロ除算で例外！
  }

  val f: Future[(Int, Int)] = f1.zip(f2)
  f.onComplete {
    case Success(res) => println(res._1 + res._2)
    case Failure(ex) => println(ex.getMessage)
  }

  println("コード的には一番下だよ")
  /* 出力（ほぼこの順番になるが、保証はされない）
  "コード的には一番下だよ"
  "タスク２終了"
  "タスク１終了"
  3
   */
}
