var user_panel = document.getElementById('user_panel') ; 
var user_message_1 = document.getElementById('user_message_1') ; 
if(user_message_1!=null){
	user_message_1.addEventListener('mousemove' , function(e){
		user_panel.style.top = 15+'px' ; 
	} , true) ; 
}

if(user_panel!=null){
	user_panel.addEventListener('mouseout' , function(e){
		user_panel.style.top = -9999999+'px' ; 
	} , true) ; 
}

