package com.ecochain.ledger.Task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecochain.ledger.model.PageData;
import com.ecochain.ledger.service.BlockDataHashService;
import com.ecochain.ledger.service.ShopOrderInfoService;
import com.ecochain.ledger.util.Base64;
import com.ecochain.ledger.util.HttpTool;
import com.ecochain.ledger.util.HttpUtil;
import com.ecochain.ledger.util.StringUtil;

/**
 * Created by Lisandro on 2017/5/17.
 */
@Component
@EnableScheduling
public class BlockChainTask {

    @Value("${server.port}")
    private String servicePort;

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private ShopOrderInfoService shopOrderInfoService;
    @Autowired
    private BlockDataHashService blockDataHashService;

    private Logger logger = Logger.getLogger(BlockChainTask.class);

    @Scheduled(fixedDelay=12000)
    public void scheduler()throws  Exception {
        /**
         * 1.需要调用区块链接口查出当日增量的hash数据
         * 2.然后json解析出hash数据中的data字段
         * 3.取data字段中每个调用区块链接口存入的bussType 进行业务判断后，调用自身系统相对应的接口方法同步数据
         */
       logger.info(">>>>>>>>>>>>> Scheduled  Execute Interface ServiceName:   " +serviceName +" ServicePort:  " +servicePort);
        String getToDayBlockInfo = HttpTool.doPost("http://192.168.200.85:8332/GetDataList", "100");
        JSONObject toDayBlockInfo = JSONObject.parseObject(getToDayBlockInfo);
          for (int i = toDayBlockInfo.getJSONArray("result").size()-1;i>=0;i--) {
            JSONObject resultInfo = (JSONObject) toDayBlockInfo.getJSONArray("result").get(i);
            if (StringUtil.isNotEmpty(resultInfo.getString("data"))) {
                System.out.println("data="+Base64.getFromBase64(resultInfo.getString("data")));
                JSONObject data = new JSONObject();
                try {
                    data = JSONObject.parseObject(Base64.getFromBase64(resultInfo.getString("data")));
                } catch (Exception e) {
                    System.out.println("不是一个json字符串");
//                    e.printStackTrace();
                }
                String hash = resultInfo.getString("hash");
                if(!blockDataHashService.isExistDataHash(hash)){
                    /*if("insertOrder".equals(data.getString("bussType"))){
                        HttpTool.doPost("http://localhost:"+servicePort+"/"+serviceName+"/api/rest/shopOrder/insertShopOrder", "insertOrder"); //insertOrder 此处值应为给区块链的data值
                    }else if("deliverGoods".equals(data.getString("bussType"))){
                        HttpTool.doPost("http://localhost:"+servicePort+"/"+serviceName+"/deliverGoods?shop_order_no=170517112233972312999&goods_id=1120&logistics_no=111&logistics_name=22", "deliverGoods"); //deliverGoods 此处值应为给区块链的data值
                    }else if("payNow".equals(data.getString("bussType"))){
                        HttpTool.doPost("http://localhost:"+servicePort+"/"+serviceName+"/api/rest/shopOrder/payNow", JSON.toJSONString(data)); //insertOrder 此处值应为给区块链的data值
                    }*/
                    
                    if("payNow".equals(data.getString("bussType"))){
//                        HttpTool.doPost("http://localhost:"+servicePort+"/"+serviceName+"/api/rest/shopOrder/payNow", JSON.toJSONString(data)); //insertOrder 此处值应为给区块链的data值
                        HttpUtil.postJson("http://localhost:"+servicePort+"/"+serviceName+"/api/rest/shopOrder/payNow", JSON.toJSONString(data));
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        /*PageData pd  = new PageData();
        pd.put("a", "sdf");
        String str = JSON.toJSONString(pd);
        System.out.println("str="+str);
        str = Base64.getBase64(str);
        System.out.println("getBase64="+str);
        str = Base64.getFromBase64(str);
        System.out.println("getFromBase64="+str);
        JSONObject data = JSONObject.parseObject(str);
        System.out.println("data="+data);*/
        PageData pd  = new PageData();
        pd.put("user_id", "123456");
        try {
            HttpTool.doPost("http://localhost:3333/logistics-service/api/rest/shopOrder/payNow", JSON.toJSONString(pd));
        } catch (Exception e) {
            e.printStackTrace();
        } 
////        HttpUtil.postJson("http://localhost:3333/logistics-service/api/rest/shopOrder/payNow", JSON.toJSONString(pd));
//        HttpUtil.postData("http://192.168.100.17:3333/logistics-service/api/rest/shopOrder/payNow", JSON.toJSONString(pd), "application/json");
    }

}
