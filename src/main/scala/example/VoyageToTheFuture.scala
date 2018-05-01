package example

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object VoyageToTheFuture extends App {
  val f1: Future[Int] = Future {
    Thread.sleep(5000) // 重い処理
    println("タスク１終了")
    1 // 重い処理の結果
  }

  val f2: Future[Int] = Future {
    Thread.sleep(1000) // 軽い処理
    println("タスク２終了")
    2 // 軽い処理の結果
  }

  for {
    res1 <- f1
    res2 <- f2
  } {
    println(res1 + res2)
  }

  println("コード的には一番下だよ")
  /* 出力（ほぼこの順番になるが、保証はされない）
  "コード的には一番下だよ"
  "タスク２終了"
  "タスク１終了"
  3
   */
}
