<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="assets/js/attente.js"></script>
<title>Attente...</title>
</head>
<body>
	<img src="assets/img/backgrounds/block.png" alt="loading" width="425"
		height="300">

	<div class="menu-container2">

		<div class="form-box">
			<div class="form-top">
				<div class="form-top-left">
					<h3>Liste des joueurs dans la partie :
						${sessionScope.partie.nom }</h3>
				</div>
			</div>
			<div class="form-bottom">

				<div id="result">
					<ol id="liste_joueur"></ol>
				</div>

			</div>
		</div>

	</div>