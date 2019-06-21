# integralShop-projec
##  1:项目技术方向
<ol>
<li> 积分商城</li>
<li> spring-boot项目</li>
<li> ssm+redis+spring security+fastDFS+Swagger2+druid</li>
<li> pc端 用于供应商登陆上传商品,商品编辑等操作</li>
<li> 终端 用于商品查询,积分兑换,平台币兑换积分,兑换商品等操作</li>
<li> Swagger2登陆地址http://localhost:8089/swagger-ui.html</li>
<li> druid登陆地址 账户 a 密码 123 地址:http://localhost:8089/druid/login.html</li>
</ol>

##  2:项目结构 
	        com.integral
  		|
  		|-com.integral.config    配置类
  		|
  		|-com.integral.constant  常量和枚举
  		|
		|-com.integral.controller
  		|             |
 	        |             |-com.integral.controller.pcController   pc端入口
            |             |
  	        |             |-com.integral.controller.terminalController 终端入口
 	        |
  		|-com.integral.exception  自定义异常
            |
  		|-com.integral.interceptior  拦截器
      	|
  		|-com.integral.mapper   mybatis的mapper
  		|
  		|-com.integral.model    实体
  		|
  		|-com.integral.security  security登陆认证和jwt签发和解密jwt
 	 	|
  		|-com.integral.service   逻辑service
  		|              
  		|              |-com.integral.service.base  基本类业务
  		|              |           |
	  	|              |           |-com.integral.service.base.impl
 	        |              |
  		|              |-com.integral.service.impl   业务层
 	        |
            |-com.integral.util  工具
  		|
  		|-IntegralApplication.java   启动类


