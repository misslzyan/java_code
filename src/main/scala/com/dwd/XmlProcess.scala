package com.dwd

import scala.io.Source

/**
  * 描述：
  **
 *
*@author duanweidong
  * @date 2019/11/6 下午3:04
  * @version 1.0
  */
object XmlProcess {

  def main(args: Array[String]): Unit = {
    val nss = "hadoop-meituan,rz-nn03,rz-nn05,rz-nn07,cosmos-hadoop,rz-nn09,rz-nn11,rz-nn13,rz-nn15,rz-nn17,rz-nn19,rz-nn21,rz-nn23,rz-nn25,rz-nn27,gh-nn01,gh-nn03,gh-nn05,gh-nn07,gh-nn09,gha-nn01,gha-nn03,zw01-nn01,zw01-nn03,zw01-nn05,zw01-nn07,zw01-nn09,zw01-nn11,zw01-nn13,zw02-nn43,zw02-nn45,zw02-nn47,zw02-nn49,zw02-nn51,zw02-nn53,zw02-nn55".split(",").toList;
    val lines = Source.fromResource("hdfs-site.xml").getLines().toList.zipWithIndex;
    val keys = lines.withFilter(kv => {
      nss.toStream.find(ns => kv._1.indexOf("dfs.namenode.rpc-address."+ns) >= 0).isDefined
    }).map(_._2+1)
    lines.withFilter(kv => keys.contains(kv._2)).map(_._1)
      .map(_.trim)
//      .map(_.substring(7,11))
//      .groupBy[String](zw=>zw)
//      .map(kv=>(kv._1, kv._2.size))
      .foreach(println(_))
  }
}
