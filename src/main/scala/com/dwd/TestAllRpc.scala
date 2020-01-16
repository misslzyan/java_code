package com.dwd

/**
  * 描述：
  **
@author duanweidong
  * @date 2019/11/20 下午2:44
  * @version 1.0
  */
import java.io.IOException

import com.dwd.TestAllRpc._
import org.apache.hadoop.conf.{Configuration, Configured}
import org.apache.hadoop.fs.{FileSystem, Options, Path}
import org.apache.hadoop.fs.permission.{AclEntry, FsPermission}
import org.apache.hadoop.hdfs.{DistributedFileSystem, HdfsConfiguration}
import org.apache.hadoop.hdfs.protocol.BlockStoragePolicy
import org.apache.hadoop.security.AccessControlException
import org.apache.hadoop.util.{Tool, ToolRunner}


object TestAllRpc
{
  private val WRITE_CONTENT = "aaa bbb"
  private val WRITE_CONCAT_CONTENT = " concat-content "
  private val ROOT = "/tmp/dwd"
  private val D1 = ROOT + "/d1"
  private val F1 = ROOT + "/f1"
  private val F1_TO = ROOT + "/f1_To"
  private val F1_1 = ROOT + "/f11"
  private val VIEWFS_ROOT = "viewfs://hadoop-meituan" + ROOT
  private val HDFS_ROOT = "hdfs://router-client/" + ROOT
  private val VF1 = VIEWFS_ROOT + "/f1"
  private val VF1_TO = VIEWFS_ROOT + "/f1_To"

  @throws[IOException]
  def main(args: Array[String]): Unit = {
    try
      ToolRunner.run(new TestAllRpc, args)
    catch {
      case e: Exception =>
        System.out.println("Run ReadWriteTest err")
        e.printStackTrace()
    }
  }
}

class TestAllRpc extends Configured with Tool {
  private var conf: Configuration = null;
  private val dirPath = new Path(TestAllRpc.D1)

  @throws[Exception]
  def run(args: Array[String]): Int = {
    new HdfsConfiguration
    init(args)
    process
  }

  private def init(args: Array[String]): Unit = {
    conf = getConf
  }

  @throws[IOException]
  private def process: Int = {
    val f1Path = new Path(F1);
    val f1ToPath = new Path(F1_TO);
//    val f11Path = new Path(F1_1);
    testForPath(f1Path, f1ToPath, true);
    System.out.println("Test " + ROOT + " DONE");

//    val vf1Path = new Path(VF1);
//    val vf1ToPath = new Path(VF1_TO);
//    testForPath(vf1Path, vf1ToPath, false);
//    System.out.println("Test " + VIEWFS_ROOT + " DONE");

//    testForPath(f1Path, vf1ToPath, true);
//    testForPath(vf1Path, f1ToPath, false);
//    System.out.println("Test hdfs and viewfs RPC DONE");
    0
  }

  @throws[IOException]
  def getDFS(path: Path): DistributedFileSystem = {
    var fs = path.getFileSystem(conf)
    while (
      fs.isInstanceOf[DistributedFileSystem]
    ) {
      val vfs = fs.asInstanceOf[DistributedFileSystem]
      fs = vfs.resolvePath(path).getFileSystem(conf)
    }
    if (!fs.isInstanceOf[DistributedFileSystem]) throw new IllegalArgumentException("'" + path + "' is not an HDFS URI.")
    fs.asInstanceOf[DistributedFileSystem]
  }

  @throws[IOException]
  private def testForPath(src: Path, dst: Path, isDFs: Boolean): Unit = {
    println(s"conf:${conf.toString}")
    var fs = FileSystem.get(conf)
    // create
    if (!fs.exists(src)) {
      val os = fs.create(src)
      os.write(TestAllRpc.WRITE_CONTENT.getBytes)
      os.close()
    } else {
      println(s"src is exists! : ${src.toString}")
    }
//    var dfs = getDFS(src)
//    // contentSummary
//    val cs = fs.getContentSummary(src)
//    System.out.println("RPC: getContentSummary SUCCESS, " + cs)
//    // append
//    if (dfs.recoverLease(src)) {
//      val out = fs.append(src)
//      out.write(TestAllRpc.WRITE_CONTENT.getBytes)
//      out.write(TestAllRpc.WRITE_CONTENT.getBytes)
//      out.close()
//      System.out.println("RPC: append SUCCESS")
//    }
//    // ignore delete
//    // mkdirs
//    if (!fs.exists(dirPath)) {
//      fs.mkdirs(dirPath)
//      System.out.println("RPC: mkdirs SUCCESS")
//    }
//    // rename
//    fs.rename(src, dst)
//    fs.rename(dst, src)
//    System.out.println("RPC: rename SUCCESS")
//    // rename (options=[NONE])
//    val renameOps = Options.Rename.NONE
//    dfs.rename(src, dst, renameOps)
//    dfs.rename(dst, src, renameOps)
//    System.out.println("RPC: rename (options=[NONE]) SUCCESS")
//    // recoverLease
//    dfs.recoverLease(src)
//    System.out.println("RPC: recoverLease SUCCESS")
//    // getStoragePolicies
//    val policy:Array[BlockStoragePolicy] = dfs.getStoragePolicies
//    System.out.println("RPC: getStoragePolicies SUCCESS, " + policy.mkString(","))
//    // setStoragePolicy
//    //        dfs.setStoragePolicy(src, "COLD");
//    System.out.println("RPC: getStoragePolicies SUCCESS")
//    // concat
//    if (isDFs) {
//      val toConcat = new Path(TestAllRpc.F1_1)
//      if (!fs.exists(toConcat)) {
//        val os = fs.create(toConcat)
//        os.write(TestAllRpc.WRITE_CONCAT_CONTENT.getBytes)
//        os.close()
//      }
//      dfs.close
//      fs.close
//      dfs = getDFS(src)
//      fs = FileSystem.get(conf)
//      val toConcats = Array[Path](toConcat)
//      // isFileClosed
//      if (dfs.isFileClosed(src)) {
//        System.out.println("RPC: isFileClosed SUCCESS")
//        dfs.concat(src, toConcats)
//      }
//      System.out.println("RPC: concat SUCCESS")
//    }
//    // createSymlink
//    // not supported
//    //        dfs.createSymlink(src, new Path(F1 + "-simlink"), false);
//    //        System.out.println("RPC: createSymlink SUCCESS");
//    // getStatus
//    val fsStatus = fs.getStatus
//    System.out.println("RPC: fsStatus SUCCESS, " + fsStatus.getCapacity)
//    // truncate
//    fs.truncate(src, 10)
//    System.out.println("RPC: truncate SUCCESS")
//    // listStatus
//    val status = fs.listStatus(src)
//    System.out.println(status)
//    System.out.println("RPC: listStatus SUCCESS, " + status.mkString(","))
//    // getFileInfo
//    val status1 = fs.getFileStatus(src)
//    System.out.println("RPC: getFileInfo SUCCESS, " + status1)
//    // open
//    val bytes = new Array[Byte](1000)
//    val in = fs.open(src)
//    in.read(bytes)
//    in.close()
//    System.out.println("RPC: open SUCCESS, " + new String(bytes))
//    // quotaUsage
//    val qs = dfs.getQuotaUsage(src)
//    System.out.println("RPC: quotaUsage SUCCESS," + qs)
//    // setPermission
//    fs.setPermission(src, new FsPermission(BigInt("0775",10).toShort))
//    fs.setPermission(src, new FsPermission(BigInt("0777",10).toShort))
//    System.out.println("RPC: setPermission SUCCESS")
//    // setXAttr
//    val name1 = "user.a1"
//    val value1 = Array[Byte](0x31, 0x32, 0x33)
//    val getValue = new Array[Byte](1000)
//    //        Map<String, byte[]> attrMap = fs.getXAttrs(src);
//    //        if (attrMap.size() == 0) {
//    //            System.out.println(src + " attr map is empty");
//    //        }
//    //        for (Map.Entry<String, byte[]> entry : attrMap.entrySet()) {
//    //            System.out.println(entry.getKey() + " " + entry.getValue().toString());
//    //            fs.removeXAttr(src, entry.getKey());
//    //        }
//    //        fs.setXAttr(src, name1, value1);
//    System.out.println("RPC: setXAttr SUCCESS")
//    // getXAttr
//    val xattrs = fs.getXAttr(src, name1)
//    if (xattrs != null) System.out.println("RPC: getXAttr SUCCESS, " + new String(xattrs))
//    val attrMap1 = fs.getXAttrs(src)
//    System.out.println("RPC: getXAttrs SUCCESS")
//    // removeXAttr
//    if (xattrs != null) fs.removeXAttr(src, name1)
//    System.out.println("RPC: removeXAttr SUCCESS")
//    // setAcl
//    fs.setAcl(src, AclEntry.parseAclSpec("user::rwx,user:foo:rw-,group::r--,other::---", true))
//    System.out.println("RPC: setAcl SUCCESS")
//    // getAclStatus
//    val acl = fs.getAclStatus(src)
//    System.out.println("RPC: getAclStatus SUCCESS, " + acl)
//    // removeAcl
//    fs.removeAcl(src)
//    System.out.println("RPC: removeAcl SUCCESS")
//    // setOwner
//    fs.setOwner(src, "hadoop-hdp", "hadoop-user")
//    System.out.println("RPC: setOwner SUCCESS")
//    val status2 = fs.getFileStatus(src)
//    System.out.println("RPC: getFileInfo SUCCESS, " + status2)
//    try
//      fs.setOwner(src, "hdfs", "supergroup")
//    catch {
//      case ace: AccessControlException =>
//        System.out.println("permission exception ignored")
//    }
//    System.out.println("RPC: setOwner SUCCESS")
//    // setReplication
//    fs.setReplication(src, 2.toShort)
//    val status3 = fs.getFileStatus(src)
//    System.out.println("RPC: getFileInfo SUCCESS, " + status3)
//    fs.setReplication(src, 3.toShort)
//    System.out.println("RPC: setReplication SUCCESS")
//    // setTimes
//    fs.setTimes(src, 1570529624233L, 1570528987089L)
//    System.out.println("RPC: setTimes SUCCESS")
//    // getServerDefaults
//    fs.getServerDefaults(src)
//    System.out.println("RPC: getServerDefaults SUCCESS")
    fs.close
//    dfs.close
  }
}
