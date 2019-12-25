package com.example.demo;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/17 23:37
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public interface IRegisterCenterInvoker {

    /**
     * 由客户端启动首次从zookeeper中首次拉去信息
     */
    public void initProviderMap();

    /**
     * 消费端获取服务提供者信息
     * @return
     */
//    public Map<String,List<ProviderService>> getServiceMetaDataMap4Consume();

//    public void registerInvoker(final InvokerService invoker);

}
