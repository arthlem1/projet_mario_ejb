/**
 * 
 */

jQuery(document).ready(function() {

	setInterval(function() {
		 refreshPlayersList();
	}, 5000);
	
	function refreshPlayersList(){
		$.ajax({
			url:"WaitingPlayerList"
		}).done(function(data){
			console.log(data)
		}).fail(function(jqXHR, textStatus, errorThrown){
			alert(errorThrown);
		});
	}
	
});