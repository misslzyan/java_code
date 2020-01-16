package com.dwd

/**
  * 描述：
  **
@author duanweidong
  * @date 2019/11/8 上午11:50
  * @version 1.0
  */
object Bucket {

  def main(args: Array[String]): Unit = {
    val str = ".hive-staging_hive_2019-11-08_11-20-44_234_2134200789933780428-1";
    println(str.hashCode & 2047)
  }
}
