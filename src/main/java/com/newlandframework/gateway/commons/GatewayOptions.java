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

import io.netty.util.Signal;

import java.nio.charset.Charset;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:GatewayOptions.java
 * @description:GatewayOptions功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2018/4/18
 */
public class GatewayOptions {
    public static final Charset GATEWAY_OPTION_CHARSET = Charset.forName("GBK");
    public static final Signal GATEWAY_OPTION_SERVICE_ACCESS_ERROR = Signal.valueOf("[NettyGateway]:Access gateway fail!");
    public static final Signal GATEWAY_OPTION_TASK_POST_ERROR = Signal.valueOf("[NettyGateway]:Http post fail!");
    public static final int GATEWAY_OPTION_PARALLEL = Math.max(2, Runtime.getRuntime().availableProcessors());
    public static final int GATEWAY_OPTION_HTTP_POST = 60 * 1000;
    public static final String GATEWAY_OPTION_GATEWAY_CONFIG_FILE = "netty-gateway.properties";
    public static final String GATEWAY_OPTION_ROUTE_CONFIG_FILE = "netty-route.properties";
    public static final String GATEWAY_OPTION_KEY_WORD_SPLIT = ",";
    public static final String GATEWAY_OPTION_SERVER_SPLIT = "@";
    public static final String GATEWAY_OPTION_LOCALHOST = "http://127.10.0.1:8080/";
    public static final String GATEWAY_PROPERTIES_PREFIX_KEY_WORD = ".keyWord";
    public static final String GATEWAY_PROPERTIES_PREFIX_MATCH_ADDR = ".matchAddr";
    public static final String GATEWAY_PROPERTIES_PREFIX_DEFAULT_ADDR = ".defaultAddr";
    public static final String GATEWAY_PROPERTIES_PREFIX_SERVER_PATH = ".serverPath";
    public static final String GATEWAY_PROPERTIES_KEY_WORD = GATEWAY_PROPERTIES_PREFIX_KEY_WORD.substring(1);
    public static final String GATEWAY_PROPERTIES_MATCH_ADDR = GATEWAY_PROPERTIES_PREFIX_MATCH_ADDR.substring(1);
    public static final String GATEWAY_PROPERTIES_SERVER_PATH = GATEWAY_PROPERTIES_PREFIX_SERVER_PATH.substring(1);
    public static final String GATEWAY_PROPERTIES_DEFAULT_ADDR = GATEWAY_PROPERTIES_PREFIX_DEFAULT_ADDR.substring(1);

    private int gatewayPort = 0;

    public int getGatewayPort() {
        return gatewayPort;
    }

    public void setGatewayPort(int gatewayPort) {
        this.gatewayPort = gatewayPort;
    }
}

