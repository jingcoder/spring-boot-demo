package com.example.demo;

import org.I0Itec.zkclient.ZkClient;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/17 23:37
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class RegisterCenterInvoker implements IRegisterCenterInvoker {

    @Override
    public void initProviderMap() {
           ZkClient zkClient = new ZkClient("fsmanager:2181");
           List<String> children = zkClient.getChildren("/brokers");
           for (String item : children){
               System.out.println(item);
           }
    }

  /*  @Override
    public Map<String, List<ProviderService>> getServiceMetaDataMap4Consume() {
        return null;
    }*/

    /*@Override
    public void registerInvoker(InvokerService invoker) {
        ZkClient
    }*/

}
