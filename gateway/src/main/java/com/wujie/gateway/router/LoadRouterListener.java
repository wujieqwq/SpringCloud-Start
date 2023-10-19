package com.wujie.gateway.router;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class LoadRouterListener {
    private final NacosConfigManager configManager;
    private final RouteDefinitionWriter writer;
    private final Set<String> routeIds = new HashSet<>();
    public final static String DATA_ID = "shared-router.json";
    public final static String GROUP = "DEFAULT_GROUP";

    @PostConstruct
    public void initRouteConfigListener() throws NacosException, JsonProcessingException {
        // 1.注册监听器并⾸次拉取配置
        String configInfo = configManager.getConfigService()
                .getConfigAndSignListener(DATA_ID, GROUP, 1000, new Listener() {
                    @Override
                    public Executor getExecutor() {
                        return Executors.newSingleThreadExecutor();
                    }
                    @Override
                    public void receiveConfigInfo(String s) {
                    // 监听到配置更新, 更新路由表
                    }
                });
        // 2.⾸次启动时， 更新⼀次配置
        updateRouteDefinitonList(configInfo);

    }
    private void updateRouteDefinitonList(String configInfo) throws JsonProcessingException {

//        1.反序列化
        ObjectMapper objectMapper = new ObjectMapper();
        List<RouteDefinition> routeDefinitionList = objectMapper.readValue(configInfo, new TypeReference<List<RouteDefinition>>() {
        });

//        2.更新前清空旧路由
        for (String routeId : routeIds) {
            writer.delete(Mono.just(routeId)).subscribe();
        }
        routeIds.clear();
//        是否有路由要跟新
        if (CollectionUtils.isEmpty(routeDefinitionList)){
//            无则结束
            return;
        }
//        3.更新路由
        routeDefinitionList.forEach(routeDefinition -> {
//            3.1.更新路由
            writer.save(Mono.just(routeDefinition)).subscribe();
//            3.2.记录id
            routeIds.add(routeDefinition.getId());
        });
    }
}
