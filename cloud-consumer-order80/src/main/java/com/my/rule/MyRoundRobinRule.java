package com.my.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 参照RoundRobinRule实现一个自己的 负载均衡 轮训规则
 */
@Slf4j
public class MyRoundRobinRule extends AbstractLoadBalancerRule {

    private AtomicInteger current;

    public MyRoundRobinRule() {
        this.current = new AtomicInteger(0);
    }

    public MyRoundRobinRule(ILoadBalancer lb) {
        setLoadBalancer(lb);
    }


    private Server chooseServer(ILoadBalancer loadBalancer){
        List<Server> reachableServers = loadBalancer.getReachableServers();
        List<Server> allServers = loadBalancer.getAllServers();
        Server server = null;
        int count = 0;
        while (server == null && count <= 10){
            if (reachableServers.size() == 0 || allServers.size() == 0){
                log.warn("no server can be use");
                return null;
            }
            int serverIndex = getServerIndex(allServers.size());
            server = allServers.get(serverIndex);

            if (server.isAlive() && server.isReadyToServe()){
                return server;
            }

            server = null;
            count++;

        }
        log.warn("No available alive servers after 10 tries from load balancer: "
                + loadBalancer);
        return null;

    }

    /**
     * 获取下次使用的机器的下标
     * @param serverCount
     * @return
     */
    private int getServerIndex(int serverCount){
        for (;;){
            int currentIndex = this.current.get();
            // 下次下标
            int next = (currentIndex + 1) % serverCount;
            if (this.current.compareAndSet(currentIndex,next)){
                return next;
            }
        }

    }


    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return chooseServer(getLoadBalancer());
    }
}
