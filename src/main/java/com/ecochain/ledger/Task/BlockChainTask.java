package com.ecochain.ledger.Task;

import com.alibaba.fastjson.JSONObject;
import com.ecochain.ledger.service.ShopOrderInfoService;
import com.ecochain.ledger.util.Base64;
import com.ecochain.ledger.util.HttpTool;
import com.ecochain.ledger.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

    private Logger logger = Logger.getLogger(BlockChainTask.class);

    @Scheduled(fixedDelay=10000)
    public void scheduler()throws  Exception {
        /**
         * 1.需要调用区块链接口查出当日增量的hash数据
         * 2.然后json解析出hash数据中的data字段
         * 3.取data字段中每个调用区块链接口存入的bussType 进行业务判断后，调用自身系统相对应的接口方法同步数据
         */
       logger.info(">>>>>>>>>>>>> Scheduled  Execute Interface ServiceName:   " +serviceName +" ServicePort:  " +servicePort);
        String getToDayBlockInfo = HttpTool.doPost("http://192.168.200.85:8332/GetDataList", "100");
        JSONObject toDayBlockInfo = JSONObject.parseObject(getToDayBlockInfo);
        for (Object resultMsg : toDayBlockInfo.getJSONArray("result")) {
            JSONObject resultInfo = (JSONObject) resultMsg;
            if (StringUtil.isNotEmpty(resultInfo.getString("data"))) {
                System.out.println(Base64.getFromBase64(resultInfo.getString("data")));
                break;
            }
        }
       /* for(int i =0;i<toDayBlockInfoList.size();i++){  //需循环list
            if(toDayBlockInfoList.get(i)){ //这里为处理Json话后的data逻辑
                shopOrderInfoService.queryOrderNum(""); //根据订单号查询当前系统是否处理此业务
                if("insertOrder".equals(true)){
                    HttpTool.doPost("http://localhost:"+servicePort+"/"+serviceName+"/api/rest/shopOrder/insertShopOrder", "insertOrder"); //insertOrder 此处值应为给区块链的data值
                    continue;
                } else if ("deliverGoods".equals(true)) {
                    HttpTool.doPost("http://localhost:"+servicePort+"/"+serviceName+"/deliverGoods?shop_order_no=170517112233972312999&goods_id=1120&logistics_no=111&logistics_name=22", "deliverGoods"); //deliverGoods 此处值应为给区块链的data值
                    continue;
                } else if ("insertOrder".equals(true)) {
                    HttpTool.doPost("http://localhost:"+servicePort+"/"+serviceName+"/api/rest/shopOrder/insertShopOrder", "insertOrder"); //insertOrder 此处值应为给区块链的data值
                    continue;
                }
            }
        }*/

    }

}