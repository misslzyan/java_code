package com.dwd

/**
  * 描述：
  **
@author duanweidong
  * @date 2019/12/3 下午5:36
  * @version 1.0
  */
object ns2 {

//  def main(args: Array[String]): Unit = {
//    val nn = "rz-nn03,rz-nn05,rz-nn07,rz-nn11,rz-nn13,rz-nn15,rz-nn19,rz-nn21,rz-nn23,rz-nn27,gh-nn01,gh-nn03,gh-nn07,gh-nn09,gha-nn01,gha-nn03,zw01-nn01,zw01-nn03,zw01-nn05,zw01-nn07,zw01-nn09,zw01-nn11,zw01-nn13,zw02-nn43,zw02-nn45,zw02-nn49,zw02-nn51,zw02-nn53,zw02-nn55";
//    val c = nn.split(",").count(_=>true)
//    println(c)
//  }
    def main(args: Array[String]): Unit = {
      val monitor = """NumNameservices,NumNamenodes,ReceivedBytes8888,SentBytes8888,RpcQueueTimeNumOps8888,RpcProcessingTimeNumOps8888,RpcAuthenticationFailures8888,
  RpcAuthenticationSuccesses8888,RpcAuthorizationFailures8888,RpcAuthorizationSuccesses8888,RpcClientBackoff8888,RpcSlowCalls8888,RpcBigObjectCount8888,
  GetAuthorityTokenTotals8888,GetAuthorityTokenByContextTotals8888,GetAuthorityTokenByRpcTotals8888,NumDroppedConnections8888,
  ReceivedBytes8111,SentBytes8111,RpcQueueTimeNumOps8111,RpcProcessingTimeNumOps8111,RpcAuthenticationFailures8111,
  RpcAuthenticationSuccesses8111,RpcAuthorizationFailures8111,RpcAuthorizationSuccesses8111,RpcClientBackoff8111,
  RpcSlowCalls8111,RpcBigObjectCount8111,GetAuthorityTokenTotals8111,GetAuthorityTokenByContextTotals8111,
  GetAuthorityTokenByRpcTotals8111,NumDroppedConnections8111,ProxyOpFailureStandby,ProxyOpFailureCommunicate,
  ProxyOpFailureClientOverloaded,ProxyOpNotImplemented,ProxyOpRetries,ProxyOpNoNamenodes,RouterFailureStateStoreOps,
  RouterFailureSafemodeOps,RouterFailureReadOnlyOps,RouterFailureLockedOps,ProxyOps,ProcessingOps,ProxyOpPermitRejected""";
     val m = monitor.replaceAll("\\s+", "");
     val res = m.split(",")
    .map(mi=>{
      s"'${mi}'"
    }).mkString(",")
  println(res)
    }

}
