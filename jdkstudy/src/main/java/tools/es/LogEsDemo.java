package tools.es;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class LogEsDemo {
    private static final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

    private static final String GATEWAY_IP_POST = "39.106.65.120:9300";
    private static final String SSD_USR_ID = "ssd_usr_id";
    private static final String SSD_SSDEVICE_ID = "ssd_ssdevice_id";
    private static final String SSD_TYPE = "ssd_type";
    private static final String SSD_SLICE_ID = "ssd_slice_id";



    /** mservice 使用类似的方法就可以了 */
//    private static final String SSD_MSERVICE_ID = "ssd_mservice_id";
//    private static final String TS = "ts";

    /**
     * 1)获得usr_id、ssdevice_id和type：open再加时间段，就是“工端的文件打开数”
     * @return
     */
    private static List<PointBean> getSFileOpenNum(EsClient esClient, String index,
                                        String ssd_usr_id, String ssd_ssdevice_id, String ssd_type,
                                        String start, String end,
                                        int currentPage, int pageSize) {
        StringBuilder sb = new StringBuilder();
        sb.append(SSD_USR_ID).append("=").append(ssd_usr_id).append(",");
        sb.append(SSD_SSDEVICE_ID).append("=").append(ssd_ssdevice_id).append(",");
        sb.append(SSD_TYPE).append("=").append(ssd_type);
        List<PointBean> list = esClient.searchListData(index, "", "",
                sb.toString(), start, end, currentPage, pageSize);
        return list;
    }

    /**
     * 2)
     * @return
     */
    private static List<PointBean> getRWNum(EsClient esClient, String index, String ssd_usr_id, String ssd_ssdevice_id,
                                        String ssd_slice_id, String ssd_type,
                                        String start, String end,
                                        int currentPage, int pageSize) {
        StringBuilder sb = new StringBuilder();
        sb.append(SSD_USR_ID).append("=").append(ssd_usr_id).append(",");
        sb.append(SSD_SSDEVICE_ID).append("=").append(ssd_ssdevice_id).append(",");
        sb.append(SSD_SLICE_ID).append("=").append(ssd_slice_id).append(",");
        sb.append(SSD_TYPE).append("=").append(ssd_type);
        List<PointBean> list = esClient.searchListData(index, "", "",
                sb.toString(), start, end, currentPage, pageSize);
        return list;
    }

    /**
     * 3)获取当前的操作比例
     * @return
     */
    private static List<Float> rwRate(EsClient esClient, String index,
                                 String ssd_usr_id, String ssd_ssdevice_id,
                                 String ssd_slice_id, String ssd_type,
                                 String start, String end, int pageSize) {
        List<PointBean> list = null;
        int currentPage = 0;

        List<Long> totalSize = new ArrayList<Long>();
        List<String> start2End = new ArrayList<String>();
        while ((list == null) || list.size() == pageSize) {
            list = getRWNum(esClient, index,
                    ssd_usr_id, ssd_ssdevice_id, ssd_slice_id,
                    ssd_type, start, end,
                    currentPage++, pageSize);
            for (PointBean pointBean: list) {
                if (pointBean.getSsd_total_size() != null &&
                        pointBean.getSsd_offset() != null &&
                        pointBean.getSsd_slice_id() != null &&
                        pointBean.getSsd_size() != null) {
                        totalSize.add(Long.parseLong(pointBean.getSsd_total_size()));
                    start2End.add(pointBean.getSsd_offset() + "_" + pointBean.getSsd_size());
                }
            }
        }
        return computeUsedRate(start2End, totalSize);
    }

    /**
     * 记录每一条记录到来的时候的所有的已用的位置和当前的总大小，再一条条的去计算
     * 两个参数的长度应该是一样的
     * @param start2Ends
     * @param totalNums
     * @return
     */
    private static List<Float> computeUsedRate(List<String> start2Ends, List<Long> totalNums) {
        //这个是结果
        List<Float> rates = new ArrayList<Float>();

        //按起点进行一次排序,这样保证所有的段是从左到右排的
        //也就是最左边不会进行再移动了
        Collections.sort(start2Ends);
        long left = -1;
        long right = -1;
        long nowNum = 0;
        for (int i = 0; i < start2Ends.size(); i++) {
            String[] startAndEnd = start2Ends.get(i).split("_");
            long start = Long.parseLong(startAndEnd[0]);
            long end = start +  Long.parseLong(startAndEnd[1]);
            long totalNow = totalNums.get(i);
            if (totalNow == -1) {
                continue;
            }
            if (left == -1) {
                //初始化
                left = start;
                right = end;
                nowNum = (end - start);
                float rate = (float)nowNum / totalNow;
                rates.add(rate >= 1 ? 1f: rate);
            } else {
                //如果不是第一段，则有两种情况
                //1.  起点 <= right
                if (start <= right) {
                    if (end <= right) {
                        float rate = (float)nowNum / totalNow;
                        rates.add(rate >= 1 ? 1f: rate);
                    } else {
                        nowNum += (end - right);
                        float rate = (float)nowNum / totalNow;
                        rates.add(rate >= 1 ? 1f: rate);
                        right = end;
                    }
                } else {
                    // 2. right < 起点
                    nowNum += (end - start);
                    float rate = (float)nowNum / totalNow;
                    rates.add(rate >= 1 ? 1f: rate);
                    right = end;
                }
            }
        }
        return rates;
    }


    public static void main(String[] args) {
        int currentPage = 0;
        int pageSize = 30;
        EsClient bulkClient = new EsClient(GATEWAY_IP_POST, "elasticsearch");
        //测试例子1
        List<PointBean> list = getSFileOpenNum(bulkClient, "index-2019.07.11",
                "2", "200001", "open",
                "2019-07-11 08:33:34", "2019-07-11 08:34:36",
                currentPage, pageSize);
        System.out.println(JSONObject.toJSONString(list));

        //测试例子2
        list = getRWNum(bulkClient, "index-2019.07.11",
                "2", "200001", "fabce2af-b3b1-4cbd-8c29-9c6baf70d1dc",
                "read", "2019-07-11 08:33:34", "2019-07-11 08:34:36",
                currentPage, pageSize);
        System.out.println(JSONObject.toJSONString(list));


        //测试例子3
        List<Float> list2 = rwRate(bulkClient, "index-2019.07.11",
                "2", "200001", "fabce2af-b3b1-4cbd-8c29-9c6baf70d1dc",
                "read", "2019-07-11 08:33:34", "2019-07-12 08:34:36", pageSize);
        System.out.println(JSONObject.toJSONString(list2));
    }
}
