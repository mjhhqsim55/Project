$(document).ready(function(){
	 
	 $('#context_box #buy').click(function(event){
		 var data = $('#context_box #data').serialize() ; 
		 event = event ; 
		 console.log(data)  ;  
		 
		  $.post('/user/shop/procd/1' , data , function(data,statu){
		 	  if(statu=='success'){
				 if(data==1){
			        alert("支付成功！^_^ !") ; 
				 }else if(data==0){
				    alert("没有此用户！") ; 
			     }else if(data==-1){
					alert("error") ;  
				 }else if(data==-10){
					alert("没有库存") ;  
				 }
				 
				 $.post('/user/procd/stack' , $('#context_box #data').serialize() , function(data,statu){
					 
					 if(statu=='success'){
						 $("#stackidtext").text(data) ; 
						 $("#stackidinput").attr("max" , data) ; 
					 }
					 
				  }) ; 
				 
			   }
			}) ;  
		 
		 }); 
		 
		 $('#context_box #shopCar').click(function(event){
			 var data = $('#context_box #data').serialize() ; 
			 event = event ; 
			 console.log(data)  ;  
             $.post('/user/shop/procd/0' , data , function(data,statu){
		 
			  if(statu=='success'){
				 if(data==1){
			        alert("添加成功！^_^ !") ; 
				 }else if(data==0){
				    alert("没有此用户！") ; 
			     }else if(data==-1){
					alert("error") ;  
				 }
			   }
			}) ; 
		 
		 }); 
	 
  
}) ; 