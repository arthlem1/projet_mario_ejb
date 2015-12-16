
jQuery(document).ready(function() {

    /*
        Login form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"], .login-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
    $('.login-form').on('submit', function(e) {
    	
    	e.preventDefault();
    	
    	$(this).find('input[type="text"], input[type="password"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    	var pseudo = $('#form-username').val();
    	var mdp = $("#form-password").val();
    	
    	if(pseudo && mdp){
    		$.ajax({
    			url:"Login",
    			method:"POST",
    			
    			data:{"pseudo":pseudo,
    				"mdp":mdp}
    		}).done(function(data) {

    			if(data.success == 1){
    				$.notiny({text: data.message, theme: 'success'});
    				 window.location.href = 'attente.html'; 
    			}else{
    				$.notiny({text: data.message, theme: 'error'});
    			}
    
    		}).fail( function(jqXHR, textStatus, errorThrown) {
    	        alert("error : "+textStatus);
    	    });
    	}
    	
    	return false;
    	
    });
    
    /*
        Registration form validation
    */
    $('.registration-form input[type="text"], .registration-form textarea').on('focus', function() {
    	$(this).removeClass('input-error');
    });
    
 
    
    $('.registration-form').on('submit', function(e) {
    	
    	e.preventDefault();
    	
    	$(this).find('input[type="text"], textarea').each(function(){
    		if( $(this).val() == "" ) {
    			e.preventDefault();
    			$(this).addClass('input-error');
    		}
    		else {
    			$(this).removeClass('input-error');
    		}
    	});
    	
    	var prenom = $('#form-first-name').val();
    	var pseudo = $('#form-username-register').val();
    	var mdp = $("#form-password-register").val();
    	
    	if(prenom && pseudo && mdp){
    		$.ajax({
    			url:"Inscription",
    			method:"POST",
    			
    			data:{"prenom":prenom,
    				"pseudo":pseudo,
    				"mdp":mdp}
    		}).done(function(data) {

    			if(data.success == 1){
    				$.notiny({text: data.message, theme: 'success'});
    				//display_menu();
    			}else{
    				$.notiny({text: data.message, theme: 'error'});
    			}
    
    		}).fail( function(jqXHR, textStatus, errorThrown) {
    	        alert("error : "+textStatus);
    	    });
    	}
    	
    	return false;
    	
    });
    
    
    var display_menu = function(){
    	
    	$("#form1").slideToggle("slow");
    	$('#form2').slideToggle("slow");
    	$("#menu").slideToggle("slow");
    	
    };
    
    
    
    /*
    var x = 0;
    setInterval(function(){
        x-=1;
        $('body').css('background-position', x + 'px');
    }, 100);
    */
    
    
});
