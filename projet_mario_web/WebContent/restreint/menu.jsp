<title>Menu principal</title>
</head>
<body>

	<button id="deconnexion" class="btn deco">Se déconnecter</button>

	<div id="menu" class="menu-container">

		<div class="form-box">
			<div class="form-top">
				<div class="form-top-left">
					<h3>Bienvenue, ${sessionScope.joueur.pseudo }</h3>
				</div>
			</div>
			<div class="form-bottom">

				<button id="create-btn" class="btn menu-btn show_sub_menu">Créer une partie</button>
				<br />
				<br />
				<button id="join-btn" class="btn menu-btn show_sub_menu">Rejoindre une partie</button>
				<br />
				<br />
				<button id="list-btn" class="btn menu-btn show_sub_menu">Historique des parties</button>
			</div>
		</div>

	</div>

	<div id="create" class="menu-container">

		<div class="form-box">
			<div class="form-top">
				<div class="form-top-left">
					<h3>Créer une partie</h3>
				</div>
				<div class="form-top-right">
	                 <i class="fa fa-times cursor close"></i>
	            </div>
			</div>
			<div class="form-bottom">

				<div class="form-group">
					<label class="sr-only" for="form-game-name">Nom de la partie</label> <input
						type="text" name="form-game-name" placeholder="Nom de la partie..."
						class="form-game-name form-control" id="form-game-name">
				</div>
				<button id="create_game" class="btn menu-btn">Créer une partie</button>

			</div>
		</div>

	</div>
	
	<div id="join" class="menu-container">

		<div class="form-box">
			<div class="form-top">
				<div class="form-top-left">
					<h3>Rejoindre une partie</h3>
				</div>
				<div class="form-top-right">
	                 <i class="fa fa-times cursor close"></i>
	            </div>
			</div>
			<div class="form-bottom">

				<h3>LISTE DES PARTIE PAS ENCORE COMMENCEES</h3>
				<button id="join_game" class="btn menu-btn">Rejoindre la partie</button>

			</div>
		</div>

	</div>
	
	<div id="list" class="menu-container">

		<div class="form-box">
			<div class="form-top">
				<div class="form-top-left">
					<h3>Historique des parties</h3>
				</div>
				<div class="form-top-right">
	                 <i class="fa fa-times cursor close"></i>
	            </div>
			</div>
			<div class="form-bottom">

				<div id="parties-results"></div>
				
			</div>
		</div>

	</div>
	
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/scripts.js"></script>
	<script src="assets/js/notiny.min.js"></script>

</body>
</html>