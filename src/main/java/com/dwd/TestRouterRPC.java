package com.dwd;

import com.google.common.base.Joiner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsStatus;
import org.apache.hadoop.fs.Options;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.QuotaUsage;
import org.apache.hadoop.fs.permission.AclEntry;
import org.apache.hadoop.fs.permission.AclStatus;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.fs.viewfs.ViewFileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.hdfs.protocol.BlockStoragePolicy;
import org.apache.hadoop.ipc.RemoteException;
import org.apache.hadoop.security.AccessControlException;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class TestRouterRPC extends Configured implements Tool {

		private Configuration conf;
		private static final String WRITE_CONTENT = "router test!";
		private static final String WRITE_CONCAT_CONTENT = " concat-content ";
		private static final String ROOT = "/tmp/rt_rpc_test";
		private static final String D1 = ROOT + "/d1";
		private static final String F1 = ROOT + "/f1";
		private static final String F1_TO = ROOT + "/f1_To";
		private static final String F1_1 = ROOT + "/f11";
//		private static final String VIEWFS_ROOT = "viewfs://hadoop-meituan" + ROOT;
//		private static final String HDFS_ROOT = "hdfs://router-client/" + ROOT;
//		private static final String VF1 = VIEWFS_ROOT + "/f1";
//		private static final String VF1_TO = VIEWFS_ROOT + "/f1_To";

		private Path dirPath = new Path(D1);


		public int run(String args[]) throws Exception {
				new HdfsConfiguration();
				init(args);
				return process();
		}

		private void init(String args[]) {
				conf = getConf();
		}

		private int process() throws IOException {
				Path f1Path = new Path(F1);
				Path f1ToPath = new Path(F1_TO);
				testForPath(f1Path, f1ToPath);
				FileSystem fs = FileSystem.get(conf);
				Path root = new Path(ROOT);
				if (fs.exists(root)) {
						fs.delete(root, true);
				}
				return 0;
		}

		DistributedFileSystem getDFS(Path path) throws IOException {
				FileSystem fs = path.getFileSystem(conf);
				while (fs instanceof ViewFileSystem) {
						ViewFileSystem vfs = (ViewFileSystem) fs;
						fs = vfs.resolvePath(path).getFileSystem(conf);
				}
				if (!(fs instanceof DistributedFileSystem)) {
						throw new IllegalArgumentException("'" + path + "' is not an HDFS URI.");
				}
				return (DistributedFileSystem) fs;
		}

		public static void println(String msg) {
				System.out.println("=============" + msg + "================");
		}

		private int testForPath(Path src, Path dst)
										throws IOException {
				Path root = new Path(ROOT);
				FileSystem fs = FileSystem.get(conf);
				if (!fs.exists(root)) {
						fs.mkdirs(root);
				} else {
						println("root:" + ROOT + " is exist!");
						return 1;
				}

				// create
				OutputStream os = fs.create(src);
				os.write(WRITE_CONTENT.getBytes());
				os.close();
				println("create done");
				// open
				byte[] bytes = new byte[1000];
				InputStream in = fs.open(src);
				int count = in.read(bytes);
				in.close();
				String readData = new String(bytes, 0, count);
				if (readData.equals(WRITE_CONTENT)) {
						println("open done");
				} else {
						println("open error");
						return 1;
				}
				DistributedFileSystem dfs = getDFS(src);
				// contentSummary
				ContentSummary cs = dfs.getContentSummary(src);
				if (cs != null) {
						if (check(cs)) {
								println("contentSummary done");
						} else {
								println("contentSummary error");
								return 1;
						}
				} else {
						println("contentSummary error");
						return 1;
				}

				// append
				if (dfs.recoverLease(src)) {
						OutputStream out = fs.append(src);
						out.write(WRITE_CONTENT.getBytes());
						out.write(WRITE_CONTENT.getBytes());
						out.close();
						in = fs.open(src);
						count = in.read(bytes);
						readData = new String(bytes, 0, count);
						if (readData.equals(WRITE_CONTENT + WRITE_CONTENT + WRITE_CONTENT)) {
								println("append done");
						} else {
								println("append error");
								return 1;
						}
				} else {
						println("append error");
						return 1;
				}
				// mkdirs
				if (!fs.exists(dirPath)) {
						fs.mkdirs(dirPath);
						if (fs.exists(dirPath)) {
								println("mkdirs done");
						} else {
								println("mkdirs error");
								return 1;
						}
				} else {
						println("mkdirs error");
						return 1;
				}

				// rename
				if (fs.rename(src, dst)) {
						println("rename done");
				} else {
						println("rename error");
						return 1;
				}

				// rename (options=[NONE])
				Options.Rename renameOps = Options.Rename.NONE;
				dfs.rename(dst, src, renameOps);
				println("rename[options=NONE] done");

				dfs.recoverLease(src);
				println("recoverLease done");

				// getStoragePolicies
				BlockStoragePolicy[] policy = dfs.getStoragePolicies();
				if (policy != null && policy.length > 0) {
						println("getStoragePolicies done");
				} else {
						println("getStoragePolicies error");
				}

				// setStoragePolicy
				dfs.setStoragePolicy(src, "COLD");
				println("setStoragePolicy done");

				// concat
				Path toConcat = new Path(F1_1);
				if (!fs.exists(toConcat)) {
						OutputStream os1 = fs.create(toConcat);
						os1.write(WRITE_CONCAT_CONTENT.getBytes());
						os1.close();
				} else {
						println("concat error");
						return 1;
				}

				fs = FileSystem.get(conf);
				Path[] toConcats = new Path[]{toConcat};
				// isFileClosed
				if (dfs.isFileClosed(src)) {
						println("close done");
//						dfs.concat(src, toConcats);
						println("concat done");
				} else {
						println("close error");
						return 1;
				}

				// getStatus
				FsStatus fsStatus = fs.getStatus();
				if (fsStatus != null) {
						println("fsStatus done");
				} else {
						println("fsStatus error");
						return 1;
				}


				// listStatus
				FileStatus[] status = fs.listStatus(src);
				if (status != null) {
						println("listStatus done");
				} else {
						println("listStatus error");
						return 1;
				}

				// getFileInfo
				FileStatus fileInfo = fs.getFileStatus(src);
				if (fileInfo != null) {
						println("getFileInfo done");
				} else {
						println("getFileInfo error");
						return 1;
				}

				// quotaUsage
				try {
						QuotaUsage qs = dfs.getQuotaUsage(src);
						println("quotaUsage error");
				} catch (RemoteException e) {
						// expect to throw exception
						println("quotaUsage done");
				} catch (Exception e) {
						println("quotaUsage error");
				}

				// setPermission
				fs.setPermission(src, new FsPermission((short) 0775));
				fs.setPermission(src, new FsPermission((short) 0777));
				println("setPermission done");

				// setXAttr
				final String name1 = "user.a1";
				final byte[] value1 = new byte[]{0x31, 0x32, 0x33};
				byte[] getValue = new byte[1000];
        Map<String, byte[]> attrMap = fs.getXAttrs(src);
        if (attrMap.size() == 0) {
            println(src + " attr map is empty");
        }
        for (Map.Entry<String, byte[]> entry : attrMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().toString());
            fs.removeXAttr(src, entry.getKey());
        }
        fs.setXAttr(src, name1, value1);
				println("RPC: setXAttr SUCCESS");

				// getXAttr
				byte[] xattrs = fs.getXAttr(src, name1);
				if (xattrs != null) {
						println("RPC: getXAttr SUCCESS ");
				}
				Map<String, byte[]> attrMap1 = fs.getXAttrs(src);
				println("RPC: getXAttrs SUCCESS");

				// removeXAttr
				if (xattrs != null) {
						fs.removeXAttr(src, name1);
				}
				println("RPC: removeXAttr SUCCESS");

				// setAcl
				fs.setAcl(src, AclEntry.parseAclSpec(
												"user::rwx,user:foo:rw-,group::r--,other::---", true));
				println("RPC: setAcl SUCCESS");

				// getAclStatus
				AclStatus acl = fs.getAclStatus(src);
				println("RPC: getAclStatus SUCCESS!");

				// removeAcl
				fs.removeAcl(src);
				println("RPC: removeAcl SUCCESS");

				// setOwner
				fs.setOwner(src, "hadoop-hdp", "hadoop-user");
				println("RPC: setOwner SUCCESS");
				FileStatus status2 = fs.getFileStatus(src);
				println("RPC: getFileInfo SUCCESS");
				try {
						fs.setOwner(src, "hdfs", "supergroup");
				} catch (AccessControlException ace) {
						println("permission exception ignored");
				}
				println("RPC: setOwner SUCCESS");

//				 setReplication
				fs.setReplication(src, (short) 2);
				FileStatus status3 = fs.getFileStatus(src);
				println("RPC: getFileInfo SUCCESS ");
				fs.setReplication(src, (short) 3);
				println("RPC: setReplication SUCCESS");

				// setTimes
				fs.setTimes(src, 1570529624233L, 1570528987089L);
				println("RPC: setTimes SUCCESS");

				// getServerDefaults
				fs.getServerDefaults(src);
				println("RPC: getServerDefaults SUCCESS");
				if (fs.exists(root)) {
						fs.delete(root, true);
						println("delete done");
				}
				fs.close();
				dfs.close();
				println("all test pass!");
				return 0;
		}

		private boolean check(ContentSummary cs) {
				return cs.getDirectoryCount() == 0 &&
												cs.getFileCount() == 1 &&
												cs.getLength() == WRITE_CONTENT.getBytes().length;
		}

		public static void main(String[] args) throws IOException {
				int retCode;
				try {
						retCode = ToolRunner.run(new TestRouterRPC(), args);
				} catch (Exception e) {
						println("Run ReadWriteTest err");
						e.printStackTrace();
						retCode = 1;
				}
				System.exit(retCode);
		}
}
