/**
 * Copyright (C) 2018 Newland Group Holding Limited
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.newlandframework.gateway.commons;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.*;

import static com.newlandframework.gateway.commons.GatewayOptions.*;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:RoutingLoader.java
 * @description:RoutingLoader功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2018/4/18
 */
public class RoutingLoader implements BeanDefinitionRegistryPostProcessor {
    public static final List<RouteAttribute> ROUTERS = new ArrayList<RouteAttribute>();
    public static final List<GatewayAttribute> GATEWAYS = new ArrayList<GatewayAttribute>();

    private static final List<String> KEY_ROUTERS = new ArrayList<String>();
    private static final List<String> KEY_GATEWAYS = new ArrayList<String>();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        initGatewayRule(registry);
        initRouteRule(registry);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        GATEWAYS.clear();
        ROUTERS.clear();

        for (String beanName : KEY_GATEWAYS) {
            GATEWAYS.add(beanFactory.getBean(beanName, GatewayAttribute.class));
        }

        for (String beanName : KEY_ROUTERS) {
            ROUTERS.add(beanFactory.getBean(beanName, RouteAttribute.class));
        }
    }

    private void initGatewayRule(BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        Resource resource = new ClassPathResource(GATEWAY_OPTION_GATEWAY_CONFIG_FILE);
        Properties p = new Properties();
        try {
            p.load(resource.getInputStream());

            String key = null;
            String keyPrefix = null;
            String defaultAddr = null;
            String serverPath = null;

            Map<String, String> valuesMap = null;
            MutablePropertyValues mpv = null;

            for (Object obj : p.keySet()) {
                key = obj.toString();
                if (key.endsWith(GATEWAY_PROPERTIES_PREFIX_SERVER_PATH)) {
                    keyPrefix = key.substring(0, key.indexOf(GATEWAY_PROPERTIES_PREFIX_SERVER_PATH));
                    serverPath = p.getProperty(keyPrefix + GATEWAY_PROPERTIES_PREFIX_SERVER_PATH).trim();
                    defaultAddr = p.getProperty(keyPrefix + GATEWAY_PROPERTIES_PREFIX_DEFAULT_ADDR).trim();

                    valuesMap = new LinkedHashMap<String, String>();
                    valuesMap.put(GATEWAY_PROPERTIES_DEFAULT_ADDR, defaultAddr);
                    valuesMap.put(GATEWAY_PROPERTIES_SERVER_PATH, serverPath);

                    mpv = new MutablePropertyValues(valuesMap);
                    beanDefinition = new GenericBeanDefinition();
                    beanDefinition.setBeanClass(GatewayAttribute.class);
                    beanDefinition.setPropertyValues(mpv);
                    registry.registerBeanDefinition(serverPath, beanDefinition);

                    KEY_GATEWAYS.add(serverPath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRouteRule(BeanDefinitionRegistry registry) {
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        Resource resource = new ClassPathResource(GATEWAY_OPTION_ROUTE_CONFIG_FILE);
        Properties p = new Properties();

        try {
            p.load(resource.getInputStream());

            String key = null;
            String keyPrefix = null;
            String keyWord = null;
            String matchAddr = null;
            String serverPath = null;

            Map<String, String> valuesMap = null;
            MutablePropertyValues mpv = null;

            for (Object obj : p.keySet()) {
                key = obj.toString();
                if (key.endsWith(GATEWAY_PROPERTIES_PREFIX_KEY_WORD)) {
                    keyPrefix = key.substring(0, key.indexOf(GATEWAY_PROPERTIES_PREFIX_KEY_WORD));
                    keyWord = p.getProperty(keyPrefix + GATEWAY_PROPERTIES_PREFIX_KEY_WORD).trim();
                    if (keyWord.isEmpty()) continue;
                    matchAddr = p.getProperty(keyPrefix + GATEWAY_PROPERTIES_PREFIX_MATCH_ADDR).trim();
                    serverPath = p.getProperty(keyPrefix + GATEWAY_PROPERTIES_PREFIX_SERVER_PATH).trim();

                    valuesMap = new LinkedHashMap<String, String>();
                    valuesMap.put(GATEWAY_PROPERTIES_KEY_WORD, keyWord);
                    valuesMap.put(GATEWAY_PROPERTIES_MATCH_ADDR, matchAddr);
                    valuesMap.put(GATEWAY_PROPERTIES_SERVER_PATH, serverPath);

                    mpv = new MutablePropertyValues(valuesMap);
                    beanDefinition = new GenericBeanDefinition();
                    beanDefinition.setBeanClass(RouteAttribute.class);
                    beanDefinition.setPropertyValues(mpv);
                    String beanName = serverPath + GATEWAY_OPTION_SERVER_SPLIT + keyWord;
                    registry.registerBeanDefinition(beanName, beanDefinition);

                    KEY_ROUTERS.add(beanName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

