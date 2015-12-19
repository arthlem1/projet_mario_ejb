/**
 * 
 */

jQuery(document).ready(function() {
	refreshPlayersList();
	
	setInterval(function() {
		 refreshPlayersList();
	}, 5000);
	
	function refreshPlayersList(){
		$.ajax({
			url:"WaitingPlayerList"
		}).done(function(data){
						
			switch(data[0].etat){
			case "INITIAL":
				var liste = $('#liste_joueur');
				liste.empty();
				jQuery.each(data[1], function(index, item){
					liste.append("<li>"+item.pseudo+"</li>");
				});
				break;
			case "EN COURS":
				window.location.href = "plateau.html";
				break;
			case "FINI":
				console.log("fini");
				window.location.href = "menu.html";
				break;
			}
			
			
		}).fail(function(jqXHR, textStatus, errorThrown){
			alert(errorThrown);
		});
	}
	
	$("#quitter").click(function(){
		$.ajax({
			url:"Quitter_salle_attente"
		}).done(function(data){
			window.location.href ="menu.html";
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("error : " + errorThrown);
		});
	});
	
});