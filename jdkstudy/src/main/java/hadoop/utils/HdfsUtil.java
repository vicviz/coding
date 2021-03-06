package hadoop.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class HdfsUtil {
    private static Logger logger = LoggerFactory.getLogger(HdfsUtil.class);
    private static final String HDFS = "hdfs://localhost:9000/";
    private static final Configuration conf = new Configuration();

    /**
     * 创建文件夹
     *
     * @param folder 文件夹名
     */
    public static void mkdirs(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(HDFS), conf);
        if (!fs.exists(path)) {
            fs.mkdirs(path);
            System.out.println("Create: " + folder);
        }
        fs.close();
    }

    /**
     * 删除文件夹
     *
     * @param folder 文件夹名
     */
    public static void rmr(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(HDFS), conf);
        fs.deleteOnExit(path);
        System.out.println("Delete: " + folder);
        fs.close();
    }

    /**
     * 重命名文件
     *
     * @param src 源文件名
     * @param dst 目标文件名
     */
    public static void rename(String src, String dst) throws IOException {
        Path name1 = new Path(src);
        Path name2 = new Path(dst);
        FileSystem fs = FileSystem.get(URI.create(HDFS), conf);
        fs.rename(name1, name2);
        System.out.println("Rename: from " + src + " to " + dst);
        fs.close();
    }

    /**
     * 列出该路径的文件信息
     *
     * @param folder 文件夹名
     */
    public static void ls(String folder) throws IOException {
        Path path = new Path(folder);
        FileSystem fs = FileSystem.get(URI.create(HDFS), conf);
        FileStatus[] list = fs.listStatus(path);
        System.out.println("ls: " + folder);
        System.out.println("==========================================================");
        for (FileStatus f : list) {
            System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), f.isDirectory(), f.getLen());
        }
        System.out.println("==========================================================");
        fs.close();
    }

    /**
     * 创建文件
     *
     * @param file    文件名
     * @param content 文件内容
     */
    public static void createFile(String file, String content) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(HDFS), conf);
        byte[] buff = content.getBytes();
        FSDataOutputStream os = null;
        try {
            os = fs.create(new Path(file));
            os.write(buff, 0, buff.length);
            System.out.println("Create: " + file);
        } finally {
            if (os != null) {
                os.close();
            }
        }
        fs.close();
    }

    /**
     * 复制本地文件到hdfs
     *
     * @param local  本地文件路径
     * @param remote hdfs目标路径
     */
    public static void copyFile(String local, String remote) throws IOException {
        FileSystem fs = FileSystem.get(URI.create(HDFS), conf);
        fs.copyFromLocalFile(new Path(local), new Path(remote));
        System.out.println("copy from: " + local + " to " + remote);
        fs.close();
    }

    /**
     * 从hdfs下载文件到本地
     *
     * @param remote hdfs文件路径
     * @param local  本地目标路径
     */
    public static void download(String remote, String local) throws IOException {
        Path path = new Path(remote);
        FileSystem fs = FileSystem.get(URI.create(HDFS), conf);
        fs.copyToLocalFile(path, new Path(local));
        System.out.println("download: from" + remote + " to " + local);
        fs.close();
    }

    /**
     * 查看hdfs文件内容
     *
     * @param remoteFile hdfs文件路径
     */
    public static void cat(String remoteFile) throws IOException {
        Path path = new Path(remoteFile);
        FileSystem fs = FileSystem.get(URI.create(HDFS), conf);
        FSDataInputStream fsdis = null;
        System.out.println("cat: " + remoteFile);
        try {
            fsdis = fs.open(path);
            IOUtils.copyBytes(fsdis, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(fsdis);
            fs.close();
        }
    }

    public static void keyValueInfo(Configuration conf, String pathStr, int printLines) throws IOException {
        FileSystem fs = FileSystem.newInstance(conf);
        Path path = new Path(pathStr);
        System.out.println("path:" + path.getName());
        SequenceFile.Reader reader = null;
        try {
            reader = new SequenceFile.Reader(fs, path, conf);
            System.out.println("init reader" + reader.getMetadata());
            logger.info("key class:" + reader.getKeyClassName());
            System.out.println("key class:" + reader.getKeyClassName());
            logger.info("value class:" + reader.getValueClassName());
            System.out.println("value class:" + reader.getValueClassName());
            Writable key = (Writable)
                    ReflectionUtils.newInstance(reader.getKeyClass(), conf);
            Writable value = (Writable)
                    ReflectionUtils.newInstance(reader.getValueClass(), conf);

            @SuppressWarnings("unchecked")
            Class<Writable> writableClassK = (Class<Writable>) reader.getKeyClass();
            @SuppressWarnings("unchecked")
            Class<Writable> writableClassV = (Class<Writable>) reader.getValueClass();
            while (reader.next(key, value)) {
                logger.info(JSONObject.toJSONString(key) + "\t" + JSONObject.toJSONString(value));
            }
        } catch (Exception e) {
            System.out.println("get error:" + e.getLocalizedMessage());
            logger.error("keyvalue info get error:", e);
        }
    }

    /**
     * 一个读hdfs文件的例子
     * @param pathStr
     * @throws IOException
     */
    public static void readHdfsDemo(String pathStr) throws IOException {
        String url = pathStr;
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());

        FileSystem fs = FileSystem.get(URI.create(url), conf);
        org.apache.hadoop.fs.Path path = new org.apache.hadoop.fs.Path(url);

        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);
        Writable key = (Writable) ReflectionUtils. newInstance(
                reader.getKeyClass(), conf);
        Writable value = (Writable) ReflectionUtils.newInstance(
                reader.getValueClass(), conf);
        System.out.println("keyType:" + key.getClass().getSimpleName());
        System.out.println("valueType:" + value.getClass().getSimpleName());
        Scanner scanner = new Scanner(System.in);
        try {
            long position = reader.getPosition();
            while (reader.next(key, value)) {
                System.out.print(String.format("position:%s, key:%s, value:", position, key.toString()));
                byte[] bytes = ((BytesWritable) value).copyBytes();
                for (int i = 0; i < bytes.length;i++) {
                    System.out.print(String.format("%d ", bytes[i]));
                }
                System.out.println(", value.size:" + bytes.length);
                String s = scanner.next();
                if (s.equals("0")) {
                    break;
                }
                System.out.println("write key.size:" + (2 + key.toString().length()) + "M");
                System.out.println("write value.size:" + bytes.length + "M");
            }
        } finally {
            IOUtils.closeStream(reader);
        }
    }

    public static void writeDemo(String url) throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());

        FileSystem fs = FileSystem.get(URI.create(url), conf);
        org.apache.hadoop.fs.Path path = new org.apache.hadoop.fs.Path(url);

        Text key = new Text();
        BytesWritable value = new BytesWritable(new byte[]{1});

        SequenceFile.Writer writer = null;

        try {
            writer = SequenceFile.createWriter(fs, conf, path, key.getClass(), value.getClass());
            key.set(String.valueOf(0));
            writer.append(key, value);
            writer.hflush();
        } finally {
            IOUtils.closeStream(writer);
        }
    }
}
