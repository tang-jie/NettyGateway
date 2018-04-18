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

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:HostInfo.java
 * @description:HostInfo功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2018/4/18
 */
public class HostInfo {
    private final String HOST_NAME;
    private final String HOST_ADDRESS;

    public HostInfo() {
        String hostName;
        String hostAddress;

        try {
            InetAddress localhost = InetAddress.getLocalHost();

            hostName = localhost.getHostName();
            hostAddress = localhost.getHostAddress();
        } catch (UnknownHostException e) {
            hostName = "localhost";
            hostAddress = "127.0.0.1";
        }

        HOST_NAME = hostName;
        HOST_ADDRESS = hostAddress;
    }

    public final String getName() {
        return HOST_NAME;
    }

    public final String getAddress() {
        return HOST_ADDRESS;
    }


    @Override
    public final String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Host Name : ").append(getName()).append("\n");
        buffer.append("Host Address : ").append(getAddress()).append("\n");
        return buffer.toString();
    }
}

