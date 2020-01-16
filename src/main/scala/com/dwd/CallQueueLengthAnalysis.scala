package com.dwd

import scala.io.Source

/**
  * 描述：
  **
  *
  * @author duanweidong
  * @date 2019/11/6 下午6:16
  * @version 1.0
  */
object CallQueueLengthAnalysis {

  def main(args: Array[String]): Unit = {
//    import scala.math.Ordering._
    val lines =  Source.fromResource("callqueuelength7.dat").getLines();
    lines.map(_.split("\\s+"))
      .map(array => (array(0), array(3)))
      .map(  { case (nn : String, avg: String) =>
        if(avg.endsWith("K")) {
          (nn, (avg.substring(0, avg.length - 1).toDouble * 1000).toString)
        } else if (avg.endsWith("M")){
          (nn, (avg.substring(0, avg.length - 1).toDouble * 1000 * 1000).toString)
        } else {
          (nn, avg)
        }
      })
      .map({case (k : String, v:String) => (k, v.trim()) })
      .withFilter(kv => !"avg".equals(kv._2))
      .map(kv => (kv._1, kv._2.toDouble))
      .toList.sortBy(kv => kv._2)
      .grouped(36)
      .toList
      .tail
      .flatMap(list => list)
      .grouped(6)
      .foreach(println(_))
//    val sd = all._1;
//    val active = all._2;
//    println(sd)
//    println(active)
  }

}
