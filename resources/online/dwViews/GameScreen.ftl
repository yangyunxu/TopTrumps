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

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">

			<!-- Add your HTML Here -->
		
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {

			}

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
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">

			function numberAiPlayers(number) {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numberAi?number="+number); // Request type and URL+parameters
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.send();
			}

			function setUserName(name) {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/userName?name="+name); // Request type and URL+parameters
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.send();
			}

			function getCategory() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getCategory"); // Request type and URL
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					//if receive 0, winner is user and should choose category
					if(responseText==0){
						//display user category
					}
				};
				xhr.send();
			}

			function getUserCategory(number) {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getUserCategory?number="+number); // Request type and URL+parameters
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.send();
			}

			function showWinner() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showWinner"); // Request type and URL
				if (!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the respon

				}
				xhr.send();
			}

			function helloJSONList() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.send();		
			}

			function helloWord(word) {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				if (!xhr) {
  					alert("CORS not supported");
				}
				xhr.send();		
			}

		</script>
		
		</body>
</html>