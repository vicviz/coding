package tools.es;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EsClient implements Serializable {
    protected TransportClient client = null;
    /**
     * Instantiates a new Bulk client.
     *
     * @param gatewayIpPorts the gateway ip
     * @param clustName the clust name
     */
    public EsClient(String gatewayIpPorts, String clustName) {
        try {
            Settings settings = Settings.builder()
                    .put("cluster.name", clustName)
                    .put("client.transport.sniff", false)
                    .build();
            client = new PreBuiltTransportClient(settings);
            String[] oneInstance = gatewayIpPorts.split(",");
            for (String item : oneInstance) {
                String[] ipPort = item.split(":");
                client.addTransportAddresses(new TransportAddress(InetAddress.getByName(ipPort[0]), Integer.parseInt(ipPort[1])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用分词查询
     *
     * @param index          索引名称
     * @param type           类型名称,可传入多个type逗号分隔
     * @param startTime      开始时间
     * @param endTime        结束时间
     * @param size           文档大小限制
     * @param fields         需要显示的字段，逗号分隔（缺省为全部字段）
     * @param sortField      排序字段
     * @param matchStr       过滤条件（xxx=111,aaa=222）
     * @return
     */
    public List<PointBean> searchListData(String index, String type, String startTime, String endTime,
                                         Integer size, String fields, String sortField,
                                         String matchStr, int currentPage, int pageSize) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        if (StringUtils.isNotEmpty(type)) {
            searchRequestBuilder.setTypes(type.split(","));
        }
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        //ts时间范围
        boolQuery.must(QueryBuilders.rangeQuery("ts")
            .format("yyyy-MM-dd HH:mm:ss")
            .from(startTime)
            .to(endTime)
            .includeLower(true)
            .includeUpper(true));

        searchRequestBuilder.setFrom(currentPage).setSize(pageSize);

        //搜索的的字段
        if (StringUtils.isNotEmpty(matchStr)) {
            for (String s : matchStr.split(",")) {
                String[] ss = s.split("=");
                if (ss.length > 1) {
                    boolQuery.must(QueryBuilders.matchQuery(s.split("=")[0], s.split("=")[1]));
                }
            }
        }

        searchRequestBuilder.setQuery(boolQuery);

        if (StringUtils.isNotEmpty(fields)) {
            searchRequestBuilder.setFetchSource(fields.split(","), null);
        }
        searchRequestBuilder.setFetchSource(true);

        if (StringUtils.isNotEmpty(sortField)) {
            searchRequestBuilder.addSort(sortField, SortOrder.DESC);
        }

        if (size != null && size > 0) {
            searchRequestBuilder.setSize(size);
        }

        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        long totalHits = searchResponse.getHits().totalHits;
        long length = searchResponse.getHits().getHits().length;

        log.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

        if (searchResponse.status().getStatus() == 200) {
            // 解析对象
            List<PointBean> sourceList = setSearchResponse(searchResponse);
            return sourceList;
        }
        return null;
    }
    


    /**
     * 使用分词查询
     *
     * @param index    索引名称
     * @param type     类型名称,可传入多个type逗号分隔
     * @param fields   需要显示的字段，逗号分隔（缺省为全部字段）
     * @param matchStr 过滤条件（xxx=111,aaa=222）
     * @return
     */
    public List<PointBean> searchListData(String index, String type, String fields,
                                         String matchStr, String start, String end,
                                         int currentPage, int pageSize) {
        return searchListData(index, type, start, end, null, fields,
                null, matchStr, currentPage, pageSize);
    }

    /**
     * @param searchResponse
     */
    private static List<PointBean> setSearchResponse(SearchResponse searchResponse) {
        List<PointBean> sourceList = new ArrayList<PointBean>();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            String sourceAsString = searchHit.getSourceAsString();
            PointBean pointBean = JSON.parseObject(sourceAsString, PointBean.class);
            sourceList.add(pointBean);
        }
        return sourceList;
    }
}
