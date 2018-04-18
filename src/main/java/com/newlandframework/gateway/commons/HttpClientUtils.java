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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import static com.newlandframework.gateway.commons.GatewayOptions.GATEWAY_OPTION_CHARSET;

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:HttpClientUtils.java
 * @description:HttpClientUtils功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2018/4/18
 */
public class HttpClientUtils {
    public static StringBuilder post(String serverUrl, String xml, int timeout) {
        StringBuilder responseBuilder = null;
        BufferedReader reader = null;
        OutputStreamWriter wr = null;
        URL url;

        try {
            url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(timeout);
            wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(xml);
            wr.flush();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), GATEWAY_OPTION_CHARSET.name()));
            responseBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
        } finally {
            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException e) {
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }

            if (responseBuilder != null) {
                return responseBuilder;
            } else {
                throw GatewayOptions.GATEWAY_OPTION_SERVICE_ACCESS_ERROR;
            }
        }
    }
}

