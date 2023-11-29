$(document).ready(function(){
	console.log('---------') ; 
	  $('#panel .item form').each(function(){
	   $(this).click(function(event){
		   var data = $(this).serialize() ; 
		   $.post('/user/manager/user/role' , data , function(data,statu){
			   console.log(data) ; 
			   console.log(statu) ; 
			  if(statu=='success'){
				 if(data==1){
				   $(event.target).prop("checked", true);
				   alert("enable") ; 
				 }else if(data==2){
				   $(event.target).prop("checked", false);
				   alert("disable") ; 
			     }else if(data==-1){
					 $(event.target).prop("checked", function( i, val ) {
						 i = i+1 ; 
						 return !val;
					  });
					alert("error") ;  
				 }else if(data==0){
				    $(event.target).prop("checked", true); 
				    alert("不许改变root") ; 
				 }
			   }
			}) ; 
	   })  ; 
	 }) ; 
}) ; 