package hadoop.utils;

import lombok.Data;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.*;

@Data
public class JobInitModel {
    private String[] inPaths;//程序的输入目录
    private String outPath;//程序的输出目录
    private Configuration conf;//配置信息
    private Job job;//job相关设置,如分布式文件缓存共享等
    private String jobName;//该job的名称
    private Class<?> jarClass;//mr的驱动程序类
    private Class<? extends InputFormat> inputFormatClass;//输入格式化类
    private Class<? extends Mapper> mapper;//mapper的实现类
    private Class<?> mapOutKeyClass;//mapper输出的key类型
    private Class<?> mapOutValueClass;//mapper输出的value类型
    private Class<? extends Reducer> combinerClass;
    private Class<? extends Partitioner> partitionerClass;
    private Class<? extends Reducer> reducer;//reducer的实现类
    private Class<?> reduceOutKeyClass;//reduce输出的key类型
    private Class<?> reduceOutValueClass;//reduce输出的value类型

    public JobInitModel() {
    }

    public JobInitModel(String[] inPaths, String outPath, Configuration conf, Job job, String jobName
            , Class<?> jarClass, Class<? extends InputFormat> inputFormatClass, Class<? extends Mapper> mapper
            , Class<?> mapOutKeyClass, Class<?> mapOutValueClass, Class<? extends Partitioner> partitionerClass
            , Class<? extends Reducer> combiner, Class<? extends Reducer> reducer, Class<?> reduceOutKeyClass
            , Class<?> reduceOutValueClass) {
        this.outPath = outPath;
        this.inPaths = inPaths;
        this.conf = conf;
        this.job = job;
        this.jobName = jobName;
        this.jarClass = jarClass;
        this.inputFormatClass = inputFormatClass;
        this.mapper = mapper;
        this.mapOutKeyClass = mapOutKeyClass;
        this.mapOutValueClass = mapOutValueClass;
        this.partitionerClass = partitionerClass;
        this.combinerClass = combiner;
        this.reducer = reducer;
        this.reduceOutKeyClass = reduceOutKeyClass;
        this.reduceOutValueClass = reduceOutValueClass;
    }
}
