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

/**
 * @author tangjie<https://github.com/tang-jie>
 * @filename:JvmInfo.java
 * @description:JvmInfo功能模块
 * @blogs http://www.cnblogs.com/jietang/
 * @since 2018/4/18
 */
public class JvmInfo {
    private static String getSystemProperty(String name, boolean quiet) {
        try {
            return System.getProperty(name);
        } catch (SecurityException e) {
            if (!quiet) {
                System.err.println("Caught a SecurityException reading the system property '" + name + "'; the JvmInfo property value will default to null.");
            }
            return null;
        }
    }

    private final String JAVA_VM_NAME = getSystemProperty("java.vm.name", false);
    private final String JAVA_VM_VERSION = getSystemProperty("java.vm.version", false);
    private final String JAVA_VM_VENDOR = getSystemProperty("java.vm.vendor", false);
    private final String JAVA_VM_INFO = getSystemProperty("java.vm.info", false);

    public JvmInfo() {
    }

    public final String getName() {
        return JAVA_VM_NAME;
    }

    public final String getVersion() {
        return JAVA_VM_VERSION;
    }

    public final String getVendor() {
        return JAVA_VM_VENDOR;
    }

    public final String getInfo() {
        return JAVA_VM_INFO;
    }

    @Override
    public final String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("JavaVM Name : ").append(getName()).append("\n");
        buffer.append("JavaVM Version : ").append(getVersion()).append("\n");
        buffer.append("JavaVM Vendor : ").append(getVendor()).append("\n");
        buffer.append("JavaVM Info : ").append(getInfo()).append("\n");
        return buffer.toString();
    }
}
