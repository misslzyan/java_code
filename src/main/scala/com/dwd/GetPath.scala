package com.dwd

import scala.io.Source

/**
  * 描述：
  **
  *
  * @author duanweidong
  * @date 2019/10/29 下午2:50
  * @version 1.0
  */
object GetPath {

  def main(args: Array[String]): Unit = {
    val lines = Source.fromResource("path.dat").getLines();
    lines.map(line => {
      val words = line.split("\\s+")
      words(words.length-1)
    }).toList.map(path => {
      val pathChild = path.split("/", -1)
      (pathChild(pathChild.length-1).hashCode & 2047, path, pathChild(pathChild.length-1))
    }).foreach(println(_))
  }
}
