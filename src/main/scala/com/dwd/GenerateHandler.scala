package com.dwd

/**
  * 描述：
  **
@author duanweidong
  * @date 2019/11/26 下午7:06
  * @version 1.0
  */
object GenerateHandler {


  /**
    * <property>
    * <name>dfs.federation.router.fairness.handler.count.zw02-flat-lock-test</name>
    * <value>68</value>
    * </property>
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    var count = 0;
    var c = 0;
    val nss = "rz-nn25,rz-nn17,zw02-nn47,gh-nn05,rz-nn09,hadoop-meituan".split(",");
    val nsss="hadoop-meituan,rz-nn03,rz-nn05,rz-nn07,cosmos-hadoop,rz-nn09,rz-nn11,rz-nn13,rz-nn15,rz-nn17,rz-nn19,rz-nn21,rz-nn23,rz-nn25,rz-nn27,gh-nn01,gh-nn03,gh-nn05,gh-nn07,gh-nn09,gha-nn01,gha-nn03,zw01-nn01,zw01-nn03,zw01-nn05,zw01-nn07,zw01-nn09,zw01-nn11,zw01-nn13,zw02-nn43,zw02-nn45,zw02-nn47,zw02-nn49,zw02-nn51,zw02-nn53,zw02-nn55";
    nsss.split(",")
      .map(f=>{
        c=c+1
        f
      })
      .withFilter(!nss.contains(_))
      .map(f=>{
        count=count+1
        f
      })
    .foreach(ns=>{
      val res = s"""
                   |<property>
                   |  <name>dfs.federation.router.fairness.handler.count.${ns}</name>
                   |  <value>68</value>
                   |</property>""".stripMargin
      println(res)
    })
    println(count)
    println(c)
  }

}
