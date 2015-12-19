<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/plateau.js"></script>
<script src="assets/js/notiny.min.js"></script>
<link rel="stylesheet" href="assets/css/plateau.css">
<link rel="stylesheet" href="assets/css/cards.css">
<title>Plateau de jeu</title>
</head>
<body>

<div class="zone_selection">

</div>

<div class="menu_plateau">
<button id="quit" class="btn btn-danger">Quitter la partie</button>
<button id="lancer_des" class="lancer_des btn btn-primary">Lancer dés</button>
<button id="passer_tour" class="btn btn-success">Passer le tour</button>
</div>

	<div class="plateau">

		<div id="j1" class="container-joueur-left">
			<div class="playingCards ">
				<ul class="hand">
					
				</ul>
			</div>
		</div>

		<div id="j2" class="container-joueur-top-left">
			<div class="playingCards ">
				<ul class="hand">
					
				</ul>
			</div>
		</div>

		<div id="j3" class="container-joueur-top">
			<div class="playingCards ">
				<ul class="hand">
					
				</ul>
			</div>
		</div>

		<div id="j4" class="container-joueur-top-right">
			<div class="playingCards ">
				<ul class="hand">
					
				</ul>
			</div>
		</div>

		<div id="j5" class="container-joueur-right">
			<div class="playingCards ">
				<ul class="hand">
					
				</ul>
			</div>
		</div>

		<div class="mes-cartes">
			<div class="playingCards">
				<ul id="ma_main" class="hand front"></ul>
			</div>
		</div>

		<div id="mes_des" class="mes-des"></div>

		<div class="center-plateau">
			<div class="pioche">
				<div class="playingCards">
					<ul id="pioche" class="deck"></ul>
				</div>
			</div>

			<div id="result_de" class="result-de"></div>
		</div>
		
		

	</div>