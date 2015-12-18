var test = "coucou";

function init_plateau(theurl) {
	emptyDiv();

	$
			.ajax({
				url : theurl
			})
			.done(
					function(data) {
						console.log(data);

						var pioche = $("#pioche");
						for (var i = 0; i < data.pioche; i++) {
							pioche
									.append('<li><div onClick="tirerCarte()" class="tirable card back no-shadow">*</div></li>');
						}
						var ma_main = $("#ma_main");
						jQuery
								.each(
										data.mes_cartes,
										function(index, item) {
											ma_main
													.append('<li><a  id="'
															+ item.id
															+ '" onClick="jouerCarte('+item.id+')" class="jouable card" href="#"><span class="rank">'
															+ item.cout
															+ '</span><span class="suit"><img src="assets/img/backgrounds/mini-coin.png" width="15" height="15" alt="coin" /></span><div class="desc">'
															+ item.codeEffet
															+ '</div></a></li>');
										});
						var mes_des = $("#mes_des");
						for (var i = 0; i < data.nb_des; i++) {
							mes_des
									.append('<div class="dice"><span class="face_pause"></span></div>');
						}
						init_layout(data.nb_joueur);
						distribuer_carte(data.nb_joueur, data.nb_cartes_autres);

					}).fail(function(jqXHR, textStatus, errorThrown) {
				alert("error : " + textStatus);
			});
}

function emptyDiv() {
	var left = $("#1");
	var left_top = $("#2");
	var top = $("#3");
	var right_top = $("#4");
	var right = $("#5");
	var pioche = $("#pioche");
	var mes_des = $("#mes_des");
	var ma_main = $("#ma_main");

	left.children().children().empty();
	left_top.children().children().empty();
	top.children().children().empty();
	right_top.children().children().empty();
	right.children().children().empty();
	pioche.empty();
	mes_des.empty();
	ma_main.empty();
	
	

}

function init_layout(nb) {
	var left = $("#1");
	var left_top = $("#2");
	var top = $("#3");
	var right_top = $("#4");
	var right = $("#5");

	switch (nb) {
	case 2:
		left.hide();
		left_top.hide();
		right_top.hide();
		right.hide();
		break;
	case 3:
		top.hide();
		left.hide();
		right.hide();
		break;
	case 4:
		left_top.hide();
		right_top.hide();
		break;
	case 5:
		right_top.hide();
		break;
	case 6:
		break;
	}
}

// Plutot moche
function distribuer_carte(nb, data) {
	var left = $("#1");
	var left_top = $("#2");
	var top = $("#3");
	var right_top = $("#4");
	var right = $("#5");
	switch (nb) {
	case 2:
		var ul = top.children().children();
		var nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul.append('<li><div class="card back">*</div></li>');
		}
		top
				.append('<div class="mini-dice"><span class="mini-face_pause"></span></div>');
		break;
	case 3:
		var ul = left_top.children().children();
		var nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul.append('<li><div class="card back">*</div></li>');
		}
		var ul2 = right_top.children().children();
		nb_cartes = data[1].nb_cartes;
		for (i = 0; i < nb_cartes; i++) {
			ul2.append('<li><div class="card back">*</div></li>');
		}
		break;
	case 4:
		var ul = left.children().children();
		var nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul.append('<li><div class="card back">*</div></li>');
		}
		var ul2 = top.children().children();
		nb_cartes = data[1].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul2.append('<li><div class="card back">*</div></li>');
		}
		var ul3 = right.children().children();
		nb_cartes = data[2].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul3.append('<li><div class="card back">*</div></li>');
		}
		break;
	case 5:
		var ul = left.children().children();
		var nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul.append('<li><div class="card back">*</div></li>');
		}
		var ul2 = left_top.children().children();
		nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul2.append('<li><div class="card back">*</div></li>');
		}
		var ul3 = top.children().children();
		nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul3.append('<li><div class="card back">*</div></li>');
		}
		var ul4 = right.children().children();
		nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul4.append('<li><div class="card back">*</div></li>');
		}
		break;
	case 6:
		var ul = left.children().children();
		var nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul.append('<li><div class="card back">*</div></li>');
		}
		var ul2 = left_top.children().children();
		nb_cartes = data[1].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul2.append('<li><div class="card back">*</div></li>');
		}
		var ul3 = top.children().children();
		nb_cartes = data[2].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul3.append('<li><div class="card back">*</div></li>');
		}
		var ul4 = right_top.children().children();
		nb_cartes = data[3].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul4.append('<li><div class="card back">*</div></li>');
		}
		var ul5 = right.children().children();
		nb_cartes = data[4].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul5.append('<li><div class="card back">*</div></li>');
		}
		break;
	}
}

function jouerCarte(id){
	
	console.log(id);
		
}

function tirerCarte() {
	$.ajax({
		url : "TirerCarte"
	}).done(function(data) {
		if (data.success == 1) {
			init_plateau("Init_partie");
		} else {
			console.log(data.message);
		}
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("error : " + errorThrown);
	});
}

function refresh() {

}

$(function() {
	init_plateau("Init_partie");
	
});