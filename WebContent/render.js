/*
* @Author: Administrator
* @Date:   2017-02-26 22:10:00
* @Last Modified by:   Administrator
* @Last Modified time: 2017-03-02 08:51:56
*/

;$(function(){
	//以上为登录部分逻辑
			
			function Game(){
				this.data={
					x:3,
		            y:3,
		            imgWidth:300,
		            imgHeight:300,
		            mode:''
				};
				this.timer=null;
				this.src="img/math.jpg"
				this.content=$('.content');
				this.divArr=[];
				this.hasBegun=false;
				this.traceNum=0;//记录步数
				this.beforeBegin()
			}
			Game.prototype={
				beforeBegin:function(){
					var self=this
					var str="<h1 style='text-align: center;line-height: 300px;font-size: 16px;cursor: pointer;background: pink'>请点击开始</h1>"
					
					$('.content').css({
						height:self.data.imgWidth+self.data.y-1,
						width:self.data.imgWidth+self.data.x-1
					}).html(str);
					$('.easy').unbind('click')
					 $('.difficult').unbind('click')
					this.bindMode()
					$('.begin').unbind('click')
					$('.begin').bind('click',this.bindBegin.bind(this))
					$('.content h1').bind('click',this.bindBegin.bind(this))
					$('.reset').bind('click',function(){
						$('.content').html(str);
						self.traceNum=0;
						$('#trace').html(0);
						$('#time').html(0);
						clearInterval(self.timer)
						self.data.mode='';
						$('#mode').html('');
						$('.easy').unbind('click')
					    $('.difficult').unbind('click')
						self.bindMode();
						$('.begin').unbind('click')//去除绑定的事件
						$('.begin').bind('click',self.bindBegin.bind(self))
						$('.content h1').bind('click',self.bindBegin.bind(self))
						self.divArr=[]
					})
				},
				bindMode:function(){
					    var self=this

						$('.easy').bind('click',function(){
							self.data.mode='简单模式';
							self.data.x=3;
							self.data.y=3;
							$('#mode').html(self.data.mode)
						})
						$('.difficult').bind('click',function(){
							self.data.mode="困难模式";
							self.data.x=4;
							self.data.y=4;
							$('#mode').html(self.data.mode)
						})
				},
				bindBegin:function(){
					
					   var self=this
						if(!self.data.mode){
							alert("请选择模式")
							self.bindMode()
						}else{
							//点击开始后
							var num=0
							self.hasBegun=true;
							self.firstRender();
							$('.begin').unbind('click')
							$('.begin').bind('click',function(){
								alert('正在游戏中')
							})
							$('.easy').unbind('click')
							$('.difficult').unbind('click')
							self.timer=setInterval(function(){
								$('#time').html(++num)
							},1000)
						}
					
					
				},
				firstRender:function(){
					this.content.html('')
					var self=this
					var len=this.data.x*this.data.y;
					//前十五个节点
					for(var i=0;i<len-1;i++){
						var el=$('<div></div>')
						el.css({
							width:self.data.imgWidth/self.data.x,
							height:self.data.imgHeight/self.data.y
						}).attr({
							flag:1,
							tag:i,
							class:'element'
						})
						el.css({
							background:'url('+self.src+') no-repeat',
							backgroundPosition:'-'+(self.data.imgWidth/self.data.x)*(i%self.data.x)+'px -'+(self.data.imgHeight/self.data.y)*(Math.floor(i/self.data.x))+'px',
							backgroundSize:self.data.imgWidth+'px '+self.data.imgHeight+'px'
						})
						this.divArr.push(el)
					}
					this.divArr.sort(function () {
                		return Math.random()<.5?1:-1;
            		})
            		//最后一个节点
             		var el=$('<div></div>');
            		el.css({
            			width:self.data.imgWidth/self.data.x,
						height:self.data.imgHeight/self.data.y
            		}).attr({
            			flag:0,
            			tag:len-1,
            			class:'element'
            		})
            		this.divArr.push(el);
            		this.content.html('')
            		this.secondRender();
				},
				secondRender:function(){
					var self=this
					this.content.html('')
					for(var i=0;i<this.divArr.length;i++){
						this.divArr[i].css({
							left:(this.data.imgWidth/this.data.x+1)*(i%this.data.x),
							top:(this.data.imgHeight/this.data.y+1)*(Math.floor(i/self.data.x))+'px'
						})
		                    this.divArr[i].bind('click',function(i){
		                    	return function(){
		                    		self.move(i)
		                    	}
		                    }(i))
		                this.content.append(this.divArr[i]);
					}
				},
				move:function(num){
					var len=this.data.x*this.data.y;
					if(this.divArr[num].attr('flag')!=='0'){
		                if((num%this.data.x!==0)&&this.divArr[num-1].attr('flag')==='0'){
		                    this.swap(num,num-1);
		                }
		                if(((num+1)%this.data.x!==0)&&this.divArr[num+1].attr('flag')==='0'){
		                    this.swap(num,num+1);
		                }
		                if(((num+this.data.x)<len)&&this.divArr[num+this.data.x].attr('flag')==='0'){
		                    this.swap(num,num+this.data.x);
		                }
		                if(((num-this.data.x)>=0)&&this.divArr[num-this.data.x].attr('flag')==='0'){
		                    this.swap(num,num-this.data.x);
		                }
            		}
				},
				swap:function(m,n){
					var temp;
            		temp=this.divArr[m];
            		this.divArr[m]=this.divArr[n];
            		this.divArr[n]=temp;
            		++this.traceNum
            		$('#trace').html(this.traceNum);
            		this.secondRender();
            		this.check();
				},
				check:function(){

					var len=this.divArr.length
					var self=this
					var flag=this.divArr.every(function(item,index){
						return item.attr('tag')==index
					})  
					if(flag){
						this.divArr[len-1].css({
							background:'url('+self.src+') no-repeat',
							backgroundPosition:'-'+(this.data.imgWidth/this.data.x)*((len-1)%this.data.x)+'px -'+(this.data.imgHeight/this.data.y)*(Math.floor((len-1)/this.data.x))+'px',
							backgroundSize:this.data.imgWidth+'px '+this.data.imgHeight+'px'
						})
						setTimeout(function(){
							alert('游戏完成,用时:'+$('#time').html()+'秒,步数:'+self.traceNum);
						},50)
						console.log(this.data.mode)
						clearInterval(this.timer)
						var mode=this.data.mode=='简单模式'?'easy':'diff'
						var data=JSON.stringify({
		             			"username":$("#login_user").val(),
		             		});
						$.ajax({
							type:"POST",
							url: "UserServlet",
		             		dataType: "json",
		             		data:{"data":data,
		             			"grade":Number($('#time').html()),
		             			"mode":mode,
		             			"method":"updateGrade",
		             		},
		             		//完成后发往后台的格式,返回的秒数在后台比较后返回最小值
		             		success:function(data){
		             			//这里的data直接是对象
		             			/*{
		             				"username":...
		             				"highestGrade":00
		             			}*/
		             			/*
									实现完成游戏后的最高分功能
									在登入时：返回data：{
													'username':xxx
													'grade':55
													"easy-grade":000
													"diff-grade":000
												}
											传出的data：{
												'username':xxx,//这儿之前命名用的是'name'
												'password':xx
											}
									注册时：返回的data:{
										username:'',
										password:'',
										
									}，
									传出的data：{
										username:$("#register_user").val(), 
			            	 			password:$("#register_pas").val(),
			            	 			repas:$("#repas").val()
									}
									完成游戏后，返回的data:{
										"username":...
										"mode":...
		             					"highestGrade":00
									},
									传出的data:{
										"username":$("#login_user").val(),
		             					"grade":Number($('#time').html()),
		             					"mode":'easy'或'diff'
									}



		             			*/
		             			if(data.mode=='easy'){
		             				$('#easy-grade').html(data.highestGrade)
		             			}else if(data.mode=='difficult'){
		             				$('#diff-grade').html(data.highestGrade)
		             			}
		             			
		             		},
		             		error:function(){
		             			alert('出错了')
		             		}
						})
					}
					
				}
			}
			new Game()
			
})