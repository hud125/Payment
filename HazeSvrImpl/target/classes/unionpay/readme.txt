JAVA SDK rev 1.2.1

2012-11-23

==== 基本要求 ====

JDK 1.5以上版本

==== 使用说明 ====

0. 请详读《中国银联UPOP系统商户接入接口规范》和《中国银联在线支付业务机构（商户）技术改造指南》

1. sdk.properties 里面的参数一般商户不需要修改; 所有properties文件都需要以UTF-8编码来保存。

2. merchant.properties中主要包括一些商户不常修改的参数，商户可以根据自己的实际情况修改。
其中env指定联调环境，*_implClass指定商户业务逻辑的实现类。

3. Sample*.java范例代码都仅供参考，商户需要根据自己的业务要求实现FrontUPSupport，BackStageUPSupport接口，

4. 必要时商户可以自己实现FrontPayServLet，BackStageCase，PayResponseServLet等类

5. 其他properties文件，用于范例代码构造请求参数

6. bin目录下的ant脚本是为了提供测试的便利性，商户可以参考。

7. 测试环境无DNS服务，后台回调url中请使用 IP 取代域名进行测试

8. 连预上线环境和线上环境时，后台交易和交易查询接口会有https连接问题，
   需要先运行SSLConnetionUtils.java中的importCertificate()方法将证书导入TrustStore中，
   注意：upopHost对应相应的预上线环境和线上环境地址（若系统属性javax.net.sll.trustStore指定了TrustStore文件，
   则需要调用传参数的importCertificate方法，传入路径和密码）

9. 先在测试环境使用默认商户ID和密钥测试通过，然后联系我们在预上线环境配置好线上的商户ID和密钥，
   在预上线环境也测试通过后再切换至线上环境。

10. 如果出现问题，联系我们时请详细描述出现的问题，并附上请求参数。