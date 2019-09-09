package com.dwd

import scala.io.Source

/**
  * 描述：
  **
 *
*@author duanweidong
  * @date 2019/9/6 下午4:32
  * @version 1.0
  */
object Analyse {

  def main(args: Array[String]): Unit = {
    val n = 1<<10;
    val lines = Source.fromResource("dir.tmp").getLines()
    val res = lines.map(l => {
      l.split("\\s+")
    }).withFilter(_.length>6)
      .map(_(7))
      .map(_.split("hive\\-scratch")(1))
      .map(_.substring(1))
      .map(_.hashCode)
      .map(c => { c ^ c >>> 16})
      .map(_ & (n - 1))
      .map((_,1))
      .toList
      .groupBy(_._1)
      .map(kv => (kv._1, {
        var sum = 0;
        kv._2.foreach(v => {
          sum = sum+ v._2
        })
        sum
      }))
      println(res.size)
      res.foreach(println)

//      .map(_ & 8192)

  }
}
