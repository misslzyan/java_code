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
    val str = ".hive-staging_hive_2020-02-10_16-50-35_344_7661396419894593551-1";
    println(str.hashCode & 2047)
  }
}
