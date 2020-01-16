package com.dwd

/**
  * 描述：
  **
@author duanweidong
  * @date 2019/10/30 下午2:54
  * @version 1.0
  */
object Ns {
  var num = 0;
  var count = 0;
  def getNum():String ={
    val pre = if(num==0)num else num+1
    if(count <17){
      num = num + 71;
    }else {
      num = num+70;
    }
    count=count+1;
     return s"${pre}-${num}"
  }
  def main(args: Array[String]): Unit = {


    val nss = "rz-nn03,rz-nn05,rz-nn07,rz-nn11,rz-nn13,rz-nn15,rz-nn19,rz-nn21,rz-nn23,rz-nn27,gh-nn01,gh-nn03,gh-nn07,gh-nn09,gha-nn01,gha-nn03,zw01-nn01,zw01-nn03,zw01-nn05,zw01-nn07,zw01-nn09,zw01-nn11,zw01-nn13,zw02-nn43,zw02-nn45,zw02-nn49,zw02-nn51,zw02-nn53,zw02-nn55";
    val res = nss.split(",")
      .map(ns=>{
        val a = getNum()
        s"${ns}:${a}"
      }).mkString("#")
    println(res)
    val count = nss.split(",").count(_ => true)
    println(count)
  }
}
