/*
@author hqs
version v0.5
*/

var banner_panel ; 
var width
var height ; 
var banner_right ; 
var banner_center ; 


var banner =  function(n){
	 
	var banner_container = document.getElementById('banner_container') ; 
    banner_panel = document.getElementById('banner_panel') ; 
    banner_left = document.getElementById('banner_left') ; 
	banner_right = document.getElementById('banner_right') ;
    banner_center = document.getElementById('banner_center') ; 
	
    var banner_centerDivs = banner_center.getElementsByTagName('div') ; 
    var banner_contoller = document.getElementById('banner_contoller') ;
	width = banner_container.clientWidth ; 
	height = banner_container.clientHeight ; 
    var secs = banner_panel.getElementsByTagName('div') ; 
	const len = secs.length ; 
	
	var count = 0 ; 
	for(var i = 0 ; i < len ; i++){
		var div = document.createElement('div') ; 
		div.innerHTML = i+1+'' ; 
		div.onclick = function(e){
			var index_ = Number.parseInt(e.target.innerHTML) ; 
		    var x_ = index_-(index+1) ; 
			var x_1 = Math.abs(x_) ; 
			if(x_>0){
				rightMove(x_1 , x*x_1) ; 
			}else if(x_<0){
				leftMove(x_1 , x*x_1) ; 
			}
	    }
		banner_center.appendChild(div) ; 
	}
	
	banner_contoller.style.top = (height-22)+'px' ; 
	banner_contoller.style.left = (width-banner_contoller.clientWidth)/2+'px' ;   
    banner_panel.style.width = (len * width) +'px';  
	banner_centerDivs[0].style.backgroundColor = 'blue' ; 
	var index = 0 ; 
	var left  = 0 ; 
	var left_n = 0 ; 
	var current = 0 ; 
	var n_ = 0 ; 
	var x = 15 ; 
    var run = null ; 
	var rightMove = function(n , x){
	  if(run == null){
			console.log(run) ; 
			
		  if(index>=len-1){
			index = 0 ; 
			left = 0 ; 
			n_ = 0 ; 
			banner_centerDivs[index].style.backgroundColor = 'blue' ; 
			banner_centerDivs[len-1].style.backgroundColor = 'aquamarine' ; 
			banner_panel.style.left = 0+'px' ; 
		  }else{
			left_n = n ;  
			run = setInterval(function(){
				 current += x ;  
				 left -= x ;  
				 
				 if(Math.abs(current) >= width*Math.abs(left_n)){
					 banner_panel.style.left = n_ - width*Math.abs(left_n)+'px' ; 
					 n_ -= width*Math.abs(left_n);  
					 left = n_ ;  
					 current = 0 ; 
					 banner_centerDivs[index].style.backgroundColor = 'aquamarine' ; 
					 index += n ;  
					 if(index>=len){
						index = len-1 ; 
					 }
					 banner_centerDivs[index].style.backgroundColor = 'blue' ; 
					 clearInterval(run) ; 
					 run = null ; 
				 
				 }else{
					 banner_panel.style.left = left+'px' ; 
				 }
			}, 1) ; 
		   }
		 count = 0  ; 
	   }
//	 
	}
	 
	var leftMove = function(n,x){
	  if(run==null){
			console.log(run) ; 
			if(index>0){
			  left_n = n ;
			 
			  run = setInterval(function(){
					 current += x ; 
					 left += x ;  
					 if(Math.abs(current) >= width*Math.abs(left_n)){
						 banner_panel.style.left = n_ + width*Math.abs(left_n)+'px' ; 
						 n_ += width*Math.abs(left_n);  
						 left = n_ ;  
						 current = 0 ; 
						 banner_centerDivs[index].style.backgroundColor = 'aquamarine' ; 
						 index -= n ;  
						 banner_centerDivs[index].style.backgroundColor = 'blue' ; 
						if(index<0){
							index = 0 ; 
						}
						 clearInterval(run) ; 
						 run = null ; 
					}else{
						 banner_panel.style.left = left+'px' ; 
					 }
				}, 1) ; 
			}else {
				index = len-1 ;
				left = -(width * (len-1)) ; 
				n_ = left ; 
				banner_centerDivs[len-1].style.backgroundColor = 'blue' ; 
				banner_centerDivs[0].style.backgroundColor = 'aquamarine' ; 
				banner_panel.style.left = left+'px' ; 
			}  
			
		 count = 0  ; 
	   }
	}
	
	banner_left.onclick = function(){
	   leftMove(1,x) ; 
    }
	
	banner_right.onclick = function(){
	   rightMove(1,x) ; 
    }
	
	for(var i = 0 ; i < len ; i++){
	  var sec = secs[i] ; 
	  sec.style.width = width + 'px' ; 
	  sec.style.height = height + 'px' ;  
	}
	
	if(n==true){ 
		 setInterval(function(){
			 count++ ; 
		     if(count>=2000){
		      rightMove(1 , x) ; 
			}
		 } , 2) ; 
	 }
}  ; 





