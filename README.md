# integralShop-projec
## create date20190619
<ol>
<li> spring-boot项目</li>
<li> ssm+redis+spring security+fastDFS+Swagger2+druid</li>
<li> 积分商城</li>
<li> pc端 用于供应商登陆上传商品,商品编辑等操作</li>
<li> 终端 用于商品查询,积分兑换,平台币兑换积分,兑换商品等操作</li>
<li> Swagger2登陆地址http://localhost:8089/swagger-ui.html</li>
<li> http://localhost:8089/druid/login.html</li>
</ol>

              com.integral
  		|
  		|-com.integral.config
  		|
  		|-com.integral.constant
  		|
		  |-com.integral.controller
  		|             |
 	     |             |-com.integral.controller.pcController 
          |             |
  	    |             |-com.integral.controller.terminalController 
 	     |
  		|-com.integral.exception/
          |
  		|-com.integral.interceptior 
      	|
  		|-com.integral.mapper
  		|
  		|-com.integral.model
  		|
  		|-com.integral.security
 	 	|
  		|-com.integral.service
  		|              
  		|              |-com.integral.service.base
  		|              |           |
	  	|              |           |-com.integral.service.base.impl
 	     |              |
  		|              |-com.integral.service.impl
 	     |
          |-com.integral.util
  		|
  		|-IntegralApplication.java

##项目结构后期往上补
