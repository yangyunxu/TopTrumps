<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script> 
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		
		<!-- new added file -->
    	<link rel="stylesheet" href="../assets/game.css">

	</head>

    <body> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">
    		<div class="row center-block text-light title">
    			<h1>Top Trumps Game</h1>
    		</div>
    		<div class="row center-block text-light status">
    			<h1>Round 1 : Players have drawn their cards</h1>
    		</div>
			<!-- Add your HTML Here -->
			<div class="row center-block gameArea">
				<div class="catSelect span3">
					<span id="active">The active player is <span id="activePlayer"></span></span>
					<div id="selected">
						<span>They selected Cargo</span>
					</div>
					<div class="btngroup">
						<span id="next">Next: Category Selection</span>
						<span id="show">Show Winner</span>
						<span id="new">Next Round</span>
					</div>
					<div class="selection" id="selection">
						<ul class="selection-menu" role="menu" aria-labelledby="dropdownMenu">
						  <li>Size</li>
						  <li>Speed</li>
						  <li>Range</li>
						  <li>Firepower</li>
						  <li>Cargo</li>
						</ul>
					</div>
				</div>
				<div class="cardsYou span4">
				</div>
				<div class="cardsAI span5">
				</div>
			</div>
			<div class="goBack">
				<a href="http://localhost:7777/toptrumps/">Go back to selection page </a>
				<a href="http://localhost:7777/toptrumps/stats">Go to statistics page</a>
			</div>	
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load

			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		var data = {	
			"round":"",
			"userAlive":true,
			"user":{},
			"players":[],
			"activePlayer":2,
			"winner":"",
			"selected":"Value",
			"continue":true,
			"drew":false
		}
		var userName = "imuser";
		var playerNum = location.search.split("=")[1];
		</script>
	
		<!-- Here are examples of how to call REST API Methods -->
		<script src="../assets/xhrRequest.js"></script>
		<script type="text/javascript">
			location.href.indexOf("refresh=1")
			launchGame();
		</script>
		<script src="../assets/game.js"></script>
		<script src="../assets/btnClick.js"></script>
		</body>
</html>