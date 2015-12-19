/**
 * 
 */

jQuery(document).ready(function() {

	$.ajax({
		url : "PartieTerminee"
	}).done(function(data) {
		$('#result').append('<h1 style="text-align:center">'+data.vainqueur+'</h1>');
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("error : " + errorThrown);
	});

});