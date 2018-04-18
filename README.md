# NettyGateway
* English：NettyGateway is a HTTP proxy server with flexible routing rules.
* Chinese：基于Netty编写的轻量级HTTP代理转发服务器，可以根据请求的内容关键字进行定制化的规则路由。相比于Nginx（OpenResty）通过lua脚本配置方式，进行的规则路由而言，配置更加简单。

----------

## NettyGateway配置说明
* NettyGateway默认启动端口8999，你可以通过[netty-gateway.xml](https://github.com/tang-jie/NettyGateway/blob/master/src/main/resources/netty-gateway.xml)的gatewayPort属性进行配置。
* 配置文件[netty-gateway.properties](https://github.com/tang-jie/NettyGateway/blob/master/src/main/resources/netty-gateway.properties)主要用来定义Http post请求中url的路径（path）部分，没有匹配成功的时候，默认转发的URL地址。
* 配置文件[netty-route.properties](https://github.com/tang-jie/NettyGateway/blob/master/src/main/resources/netty-route.properties)主要用来定义具体的url路径（path）跟关键字匹配成功的时候，转发的URL地址。

----------

## NettyGateway代理转发场景描述
* NettyGateway部署在10.1.1.76主机，URL中的路径fcgi-bin/BSSP_SFC
* 如果请求报文中出现HelloWorldNettyGateway关键字的时候，转发到http://10.46.158.20:8089/fcgi-bin/BSSP_SFC
* 否则转发到http://10.46.158.10:8089/fcgi-bin/BSSP_SFC

----------

## NettyGateway代理转发场景配置说明
* 配置文件netty-gateway.properties新增如下属性：
~~~~~~~~~~java
#配置说明参考：
#netty-gateway.config2.serverPath ==> URL路径关键字。
#netty-gateway.config2.defaultAddr ==> 请求报文中的关键字没有匹配成功时，默认转发的URL地址。
netty-gateway.config2.serverPath=fcgi-bin/BSSP_SFC
netty-gateway.config2.defaultAddr=http://10.46.158.10:8089/fcgi-bin/BSSP_SFC
#注意config中的数字顺序递增即可。
~~~~~~~~~~

* 配置文件netty-route.properties新增如下属性：
~~~~~~~~~~java
#配置说明参考：
#netty-gateway.config3.serverPath ==> URL路径关键字。
#netty-gateway.config3.keyWord ==> 请求报文匹配关键字。支持1~N个关键字，多个关键字用逗号分割，关键字之间是逻辑与的关系。
#netty-gateway.config3.matchAddr ==> 请求报文关键字匹配成功时，转发的ULR地址。
netty-gateway.config3.serverPath=fcgi-bin/BSSP_SFC
netty-gateway.config3.keyWord=HelloWorldNettyGateway
netty-gateway.config3.matchAddr=http://10.46.158.20:8089/fcgi-bin/BSSP_SFC
注意config中的数字顺序递增即可。
~~~~~~~~~~

## NettyGateway代理转发测试
* 启动NettyGateway服务器，控制台打印如下信息
![](https://github.com/tang-jie/NettyGateway/blob/master/docs/netty-gateway-1.jpg)
* 发送HelloWorldNettyGateway到NettyGateway，关键字匹配成功，路由到http://10.46.158.20:8089/fcgi-bin/BSSP_SFC
![](https://github.com/tang-jie/NettyGateway/blob/master/docs/netty-gateway-3.jpg)
* 发送Tangjie到NettyGateway，关键字匹配不成功，路由到默认的http://10.46.158.10:8089/fcgi-bin/BSSP_SFC
![](https://github.com/tang-jie/NettyGateway/blob/master/docs/netty-gateway-2.jpg)

