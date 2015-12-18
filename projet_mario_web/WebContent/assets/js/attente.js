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
			console.log(data[0].etat);
						
			switch(data[0].etat){
			case "INITIAL":
				console.log("ici");
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
	
});