<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" type="text/css" href="normalize.css">
        <style type="text/css">
        	body{width: 100%;}
        	h1{text-align: center}
        	#container{width: 800px;margin: 0 auto;overflow: hidden;background: #96e555;border-radius: 30px;}
        	#top{text-align: center;width: 200px;}
        	#top p{font-weight:bold;width: 200px;text-align: center}
        	#wrapper{position: relative;overflow: hidden;}
        	.content{position: absolute;margin: auto;left: 0;top: 0;right: 0;bottom: 0}
        	.right-info{width: 200px;float:right;text-align: center}
        	.right-info p{height: 20px;text-align: center}
        	.right-info p span{color:red;}
        	.right-info button{display: inline-block;width: 100px;cursor: pointer}
        	.layout{position: fixed;width: 100%;height: 100%;top: 0;left: 0;background-color: lightgrey;z-index: 1;opacity: .9}
       		 .log{position: absolute;height: 350px;width: 300px;border: 1px solid black;top: 100px;z-index: 20;background: #fff;left: 0;top: 0;bottom: 0;right: 0;margin: auto;}
       		 .log_top{height: 50px;width: 300px;position: relative;color:#5fa6e5;}
       		 .log_top span{display: inline-block;width: 150px;box-sizing: border-box;text-align: center;height: 50px;line-height: 50px;cursor:pointer;border:1px solid #5fa6e5;font-size: 1.25em;}

       		 #form2,#form1{padding-top: 10px;width: 300px;}
       		 #form2 label,#form1 label{display: inline-block;width: 33%;height: 30px;text-align: right;line-height: 30px;}
       		 #form2 input,#form1 input{display: inline-block;width: 50%;height: 30px;line-height: 30px}
        	#form2 div p,#form1 div p{height: 16px;margin-left: 33%;display: inline-block}
       		 #form2 button, #form1 button{display: block;margin: 0 auto ;width: 100px;padding: 5px 0;cursor:pointer;text-align: center;background: #5fa6e5;border: none;border-radius: 2px;height: 35px;}
        	#form2 .to_register{display: inline-block;margin-top: 20px}
        	input{border:1px solid black}
        	.active{background-color: #5fa6e5;color:#fff;}
        	.element{position: absolute;box-sizing: border-box;border: 1px solid black}
        </style>
    </head>
    <body>
    <div id="container">
	    <h1>拼图小游戏</h1>
	    <div id="top">
	        <p>目标图像</p>
	        <img src="img/math.jpg" alt="目标图像" title="target" class="target" height="150" width="150" />
	    </div>
	    <div id="wrapper">
	        <div class="content">
	        	
	        </div>
	        <div class="right-info">
	            <p id="log_p"><a href="#" class="log_a">登录/注册</a></p>
	            <p id="username">用户:</p>
	            <p id="highest">简单模式历史最快：<span id="easy-grade">0</span>秒(须登录)</p>
	            <p id="highest">困难模式历史最快：<span id="diff-grade">0</span>秒(须登录)</p>
	            <p>模式:<span id="mode"></span></p>
	            <p>时间:&nbsp;<span id="time">0</span>秒</p>
	            <p>步数:&nbsp;<span id="trace">0</span></p>
	            <p><button class="begin">开始</button></p>
	            <p><button class="easy">简单模式</button></p>
	            <p><button class="difficult">困难模式</button></p>
	            <p><button class="reset">重新开始</button></p>
	        </div>
	    </div>
	</div>
	<div class="layout" style="display: none;">
			<div class="log" >
			    <div class="log_top">
			        <span class="active">登录</span><span>注册</span>
			    </div>
			    <div>
			    	<!--登录页面-->
			        <form name="form2" id="form2" style="display: block"  method="post">
			            <div><label>账号:</label><input type="text" name="user" id="login_user" placeholder="用户名"/><p></p></div>
			            <div><label>密码:</label><input type="password" name="password" placeholder="密码" id="login_pas"/><p></p></div>
			            <button id="login">登录</button><a href="#" class="to_register">忘记密码？/马上注册</a>
			        </form>
			        <!--注册页面-->
			        <form name="form1" id="form1" style="display: none"  method="post">
			           <div><label>账号:</label><input type="text" name="user" id="register_user" placeholder="用户名"/><p>请输入4-10个字符</p></div>
			            <div><label>密码:</label><input type="password" name="password" placeholder="密码" id="register_pas"/><p>请输入密码</p></div>
			            <div><label>请重复密码:</label><input type="password" placeholder="请重复密码" id="repas"/><p>请输入重复密码</p></div>
			            <button id="register">注册</button>
			        </form>
			        
			    </div>
			</div>
	</div>	
	<script type="text/javascript" src="jq.js"></script>
	<script type="text/javascript" src="render.js"></script>
	<script type="text/javascript" src="login.js"></script>
	<script type="text/javascript">
		$(function(){                           
			$('#login').click(function(){
				var username = 	$("#login_user").val();
				var password = $("#login_pas").val();  
				var data = JSON.stringify({'username':username,'password':password});
		         $.ajax({
		             type: "POST",
		             url: "UserServlet",
		             dataType: "json",
		             data: {
		             		"data":data,
		            	 	"method":"login"
		            	 },
		             //发送的数据格式，用户名和密码
		             //在后台验证
		             success: function(data){
		             			 /*
								返回data：{
									'username':xxx
									'grade':55
									"easy-grade":000
									"diff-grade":000
								}
			                  */
	             			 if(data['username']){
								$('#username').empty();   //清空resText里面的所有内容					  
								 $('#username').html('用户:'+data['username']);
								 $('.layout').css({
									display:'none'
								 })
								 console.log(data)
								 $('#easy-grade').html(data.easyGrade)
								 $('#diff-grade').html(data.diffGrade)
							 }else{
								alert('密码错误')
							 }
		                         
		                    },
		              error:function() {
		              	alert('出错了')
		              }
		        	
	    		})
		        return false 
	     	})
    		$('#register').click(function(){
    			if($("#register_pas").val()==$("#repas").val()){
    				var username = 	$("#register_user").val();
    				var password =  $("#register_pas").val()
    				var data = JSON.stringify({'username':username, 
          	 		  	'password':password});
    				$.ajax({
			             type: "POST",
			             url: "UserServlet",
			             data:  { 
			            	 	 "data":data,
		             			 "method":"register"
			            	    },
			             //发送的数据格式，用户名和密码
			             dataType: "json",
			             success: function(data){
			            	 console.log(data);
			                  /*
								返回的data:{
									username:'',
									password:'',
									grade:55
								}
			                  */
			                  //data=JSON.parse(data)如果是json数据就用这个
			                      
			                  	$('#username').empty();   //清空resText里面的所有内容
			                    $('#username').html(data.username);
								$('.layout').css({
									display:'none'
								 })
			                   	$('#easy-grade').html(data.easyGrade)
								$('#diff-grade').html(data.diffGrade)
							  
			        	},
			              error:function() {
			              	alert('出错了')
			              }
    				})
    			}else{
    				alert('密码输入不一致')
    			} 
				return false 
    			
    		}) 
		})
	</script>
    </body>
</html>