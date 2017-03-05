/*
* @Author: Administrator
* @Date:   2017-02-26 22:10:57
* @Last Modified by:   Administrator
* @Last Modified time: 2017-02-26 22:11:13
*/

;$(function(){
			$('.log_a').click(function(e){
				$('.layout').css({
					display:'block'
				})
				$('.log_top span').eq(0).addClass('active').siblings().removeClass('active');
				e.preventDefault()
			})
			$('.layout').bind('click',function(){
				$('.layout').css({
					display:'none'
				})
			})
			$('.log').bind('click',function(e){
				e.stopPropagation()
			})
			var arr=$('.log_top span');
			for(var i=0;i<arr.length;i++){
					arr.eq(i).bind('click',function(i){
						return function(){	
							arr.eq(i).addClass('active').siblings().removeClass('active');
							$('form').eq(i).css({
								display:'block'
							}).siblings().css({
								display:'none'
							})
						}
					}(i))
			}
			
		})