
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

    			if(data.success != 1){
    				$.notiny({text: data.message, theme: 'error'});
    			}else{
    				 window.location.href = 'menu.html';
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

    			if(data.success != 1){
    				$.notiny({text: data.message, theme: 'error'});
    			}else{
    				 window.location.href = 'menu.html';
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
    
    $(".show_sub_menu").click(function(){
    	show_sub_menu($(this).attr("id"));
    });
    
    $(".close").click(function(){
    	hide_and_show_menu($(this));
    	
    });
    
    var hide_and_show_menu = function(div){
    	div.parent().parent().parent().parent().slideToggle("fast");
    	$("#menu").slideToggle("slow");
    };
    
    $("#create_game").click(function(){
    	var nom = $("#form-game-name").val();
    	
    	if(nom){
    		$.ajax({
    			url:"Create",
    			data:{"nom":nom}
    		}).done(function(data){
    			if(data.success == 1){
    				window.location.href = "attente.html";
    			}else{
    				$.notiny({text: data.message, theme: 'error'});
    				$("#create").slideToggle("fast");
    		    	$("#menu").slideToggle("slow");
    			}
    		}).fail(function(jqXHR, textStatus, errorThrown){
    			alert(errorThrown);
    		});
    	}else{
    		$.notiny({text: "Veuillez entrer un nom pour la partie", theme: 'error'});
    	}
    });
    
    var show_sub_menu = function(id){
    	
    	$("#menu").slideToggle("fast");
    	switch(id){
    	case "create-btn":
    		$("#create").slideToggle("slow");
    		break;
    	case "join-btn":
    		$("#join").slideToggle("slow");
    		break;
    	case "list-btn":
    		$("#list").slideToggle("slow");
    		$.ajax({
    			url:"ListerPartiesJouees"
    		}).done(function(data){
    			var liste = $("#parties-results");
    			liste.empty();
    			jQuery.each(data, function(index, item){
    				console.log(item.nom);
    				liste.append("<p>"+item.nom+"</p>");
    			});
    		}).fail(function(jqXHR, textStatus, errorThrown){
    			alert(errorThrown);
    		});
    		break;
    	}
    	
    };
    
    $("#deconnexion").click(function(){
    	$.ajax({
    		url:"Deconnexion"
    	}).done(function(){
    		window.location.href = "index.html";
    	}).fail(function(jqXHR, textStatus, errorThrown){
			alert(errorThrown);
		});
    });
    
    
    
    /*
    var x = 0;
    setInterval(function(){
        x-=1;
        $('body').css('background-position', x + 'px');
    }, 100);
    */
    
    
});
