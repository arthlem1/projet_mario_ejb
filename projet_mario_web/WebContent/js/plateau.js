var tour = false;
var de_lance = false;
var nb_pioche = 0;
var selectionJoueur;
var nb_don = 0;

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
							var divTmp = '<li><div onClick="tirerCarte()" class="tirable card back no-shadow">*</div></li>';

							pioche.append(divTmp);

						}
						var zone_selection = $(".zone_selection");
						jQuery
								.each(
										data.joueurs,
										function(index, item) {
											var j = $('<div onClick="selection(\''
													+ item.pseudo
													+ '\')" id="'
													+ item.id
													+ '" class="joueur"><img src="assets/img/backgrounds/champi.png" alt="champi" /><p class="pseudo">'
													+ item.pseudo
													+ '</p><div class="de">'
													+ item.nb_de
													+ ' <div class="mini_dice_w"></div></div></div>');
											zone_selection.append(j);
										});

						var ma_main = $("#ma_main");
						jQuery
								.each(
										data.mes_cartes,
										function(index, item) {

											$(
													'<li id="c'+item.id+'"><a onClick="jouerCarte('
															+ item.id
															+ ','
															+ item.interaction
															+ ')" class="jouable card" href="#"><span class="rank">'
															+ item.cout
															+ '</span><span class="suit"><img src="assets/img/backgrounds/mini-coin.png" width="15" height="15" alt="coin" /></span><div class="desc">'
															+ item.description
															+ '</div></a></li>')
													.appendTo(ma_main);

										});
						var mes_des = $("#mes_des");
						for (var i = 0; i < data.nb_des; i++) {
							var tmp = $('<div class="dice_w"></div>');
							tmp.appendTo(mes_des);
						}
						init_layout(data.nb_joueur);
						distribuer_carte(data.nb_joueur, data.nb_cartes_autres);

					}).fail(function(jqXHR, textStatus, errorThrown) {
				alert("error : " + textStatus);
			});
}

function selection(pseudo) {
	selectionJoueur = pseudo;
	console.log(pseudo);
}

function emptyDiv() {
	var left = $("#j1");
	var left_top = $("#j2");
	var top = $("#j3");
	var right_top = $("#j4");
	var right = $("#j5");
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
	var left = $("#j1");
	var left_top = $("#j2");
	var top = $("#j3");
	var right_top = $("#j4");
	var right = $("#j5");

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
	var left = $("#j1");
	var left_top = $("#j2");
	var top = $("#j3");
	var right_top = $("#j4");
	var right = $("#j5");
	switch (nb) {
	case 2:
		var ul = top.children().children();
		var nb_cartes = data[0].nb_cartes;
		for (var i = 0; i < nb_cartes; i++) {
			ul.append('<li><div class="card back">*</div></li>');
		}

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

function appelJouerCarte(id, cible) {
	$.ajax({
		url : "JouerCarte",
		data : {
			"id_carte" : id,
			"pseudo" : cible
		}
	}).done(function(data) {
		$(".zone_selection").removeClass("highlight");
		if (data.success == 1) {
			$("#c" + id).remove();
			
			refreshMain("Init_partie");
			selectionJoueur = undefined;
			tour = false;
			$("#passer_tour").addClass("disabled");
		} else {
			$.notiny({text: data.message, theme: 'error'});
		}
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("error : " + errorThrown);
	});
}

function waitForClick(id) {
	if (typeof selectionJoueur !== "undefined") {
		appelJouerCarte(id, selectionJoueur);
	} else {
		setTimeout(function() {
			waitForClick(id);
		}, 250);
	}
}

function jouerCarte(id, interaction) {
	if (tour) {
		if (de_lance) {
			if (nb_don == 0) {

					if (!interaction) {
						appelJouerCarte(id, "");
	
					} else {
						var zone = $(".zone_selection");
						zone.addClass("highlight");
	
						waitForClick(id);
	
					}
				
			} else {
				$.notiny({text: "Vous devez d'abord donner vos dés", theme: 'error'});
			}
		} else {
			$.notiny({text: "Vous n'avez pas lancé les dés", theme: 'error'});
		}

	} else {
		$.notiny({text: "Ce n'est pas votre tour", theme: 'error'});
	}

}

function refreshMain(theurl) {
	$
			.ajax({
				url : theurl
			})
			.done(
					function(data) {

						var ma_main = $("#ma_main");
						ma_main.empty();
						jQuery
								.each(
										data.mes_cartes,
										function(index, item) {

											$(
													'<li id="c'+item.id+'"><a onClick="jouerCarte('
															+ item.id
															+ ','
															+ item.interaction
															+ ')" class="jouable card" href="#"><span class="rank">'
															+ item.cout
															+ '</span><span class="suit"><img src="assets/img/backgrounds/mini-coin.png" width="15" height="15" alt="coin" /></span><div class="desc">'
															+ item.description
															+ '</div></a></li>')
													.appendTo(ma_main);

										});
						var mes_des = $("#mes_des");
						mes_des.empty();
						for (var i = 0; i < data.nb_des; i++) {
							var tmp = $('<div class="dice_w"></div>');
							tmp.appendTo(mes_des);
						}

						var left = $("#j1");
						var left_top = $("#j2");
						var top = $("#j3");
						var right_top = $("#j4");
						var right = $("#j5");

						left.children().children().empty();
						left_top.children().children().empty();
						top.children().children().empty();
						right_top.children().children().empty();
						right.children().children().empty();

						distribuer_carte(data.nb_joueur, data.nb_cartes_autres);

					}).fail(function(jqXHR, textStatus, errorThrown) {
				alert("error : " + textStatus);
			});
}

function tirerCarte() {
	if (tour) {
		if (nb_pioche > 0) {

			$
					.ajax({
						url : "TirerCarte"
					})
					.done(
							function(data) {
								if (data.success == 1) {
									
									var tmp = $(this).parent();
									tmp.remove();

									$("#ma_main").prepend('<li id="c'+data.carte.id+'"><a onClick="jouerCarte('
													+ data.carte.id
													+ ','
													+ data.carte.interaction
													+ ')" class="jouable card" href="#"><span class="rank">'
													+ data.carte.cout
													+ '</span><span class="suit"><img src="assets/img/backgrounds/mini-coin.png" width="15" height="15" alt="coin" /></span><div class="desc">'
													+ data.carte.description
													+ '</div></a></li>');

								} else {
									$.notiny({text: data.message, theme: 'error'});
								}
							}).fail(function(jqXHR, textStatus, errorThrown) {
						alert("error : " + errorThrown);
					});
			nb_pioche--;
		} else {
			$.notiny({text: "Vous ne pouvez plus piocher", theme: 'error'});
		}
	} else {
		$.notiny({text: "Ce n'est pas votre tour", theme: 'error'});
	}
}

function infos() {
	$.ajax({
		url : "InfosPartie"
	}).done(function(data) {
		
		if (data.etat == "FINI") {
			
			window.location.href = "partieterminee.html";
		}
		
		if (!tour) {
			if (data.ton_tour) {
				
				$("#lancer_des").removeClass("disabled");
				$("#passer_tour").removeClass("disabled");
				tour = true;
				de_lance = false;
				$.notiny({text: "C'est votre tour", theme: 'success'});
			} else {
				tour = false;
				$("#lancer_des").addClass("disabled");
				$("#passer_tour").addClass("disabled");
				refreshMain("Init_partie");
			}
		}
		
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("error : " + errorThrown);
	});
}

function don_de_de() {
	waitForSelection();
}

function appel_don_de(pseudo) {
	$.ajax({
		url : "DonnerDe",
		data : {
			"pseudo" : pseudo
		}
	}).done(function(data) {
		if (data.success == 1) {

			refreshMain("Init_partie");

		} else {
			$.notiny({text: data.message, theme: 'error'});
		}
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("error : " + errorThrown);
	});
}

function waitForSelection() {
	if (nb_don > 0) {
		if (typeof selectionJoueur !== "undefined") {
			appel_don_de(selectionJoueur);
			nb_don--;
			if(nb_don == 0){
				$(".zone_selection").removeClass("highlight");
			}
			selectionJoueur = undefined;
		} else {
			setTimeout(function() {
				waitForSelection();
			}, 450);
		}
	} 
}

$(function() {
	init_plateau("Init_partie");
	infos();

	$("#lancer_des").click(function() {
		if (tour) {
			if (!de_lance) {
				$(this).addClass("disabled");
				$.ajax({
					url : "Lancer_des"
				}).done(function(data) {
					var des_a_afficher;

					nb_pioche = data.nb_c;

					var container = $("#result_de");
					container.empty();
					for (var i = 0; i < data.nb_w; i++) {
						container.append('<div class="res dice_w"></div>');
					}
					for (var i = 0; i < data.nb_d; i++) {
						var de = $('<div class="res dice_d"></div>');
						container.append(de);
					}
					for (var i = 0; i < data.nb_c; i++) {
						container.append('<div class="res dice_c"></div>');
					}
					de_lance = true;

					if(data.nb_d > 0){
						$(".zone_selection").addClass("highlight");
						nb_don = data.nb_d;
						don_de_de();
					}

				}).fail(function(jqXHR, textStatus, errorThrown) {
					alert("error : " + errorThrown);
				});
			}
		}
	});

	setInterval(function() {
		infos();
	}, 5000);

	$("#quit").click(function() {
		$.ajax({
			url : "Quitter_partie"
		}).done(function() {
			window.location.href = "menu.html";
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("error : " + errorThrown);
		});
	});

	$("#passer_tour").click(function() {
		if (tour) {
			if(de_lance){
				if(nb_don == 0){
					
					$("#lancer_des").addClass("disabled");
					$("#passer_tour").addClass("disabled");
					$.ajax({
						url : "PasserTour"
					}).done(function() {
						$.notiny({text: "Vous avez passé votre tour", theme: 'success'});
						tour = false;
	
					}).fail(function(jqXHR, textStatus, errorThrown) {
						alert("error : " + errorThrown);
					});
				}else{
					$.notiny({text: "Il reste des dés à donner", theme: 'error'});
				}
			}else{
				$.notiny({text: "Vous n'avez pas lancé les dés", theme: 'error'});
			}
		}
	});
});