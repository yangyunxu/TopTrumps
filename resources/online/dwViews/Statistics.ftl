<!-- <%@page import="java.sql.*"%> -->
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
    <script>
    vex.defaultOptions.className = 'vex-theme-os';
    </script>
    <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css" />
    <link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>

<body onload="initalize()" style="background-color:#999999">
    <!-- Call the initalize method when the page loads -->
    <div class="container" id="aaa">
        <div id="header" style="background-color:#404040;height: 40px;">
            <p class="header-title" style="color:white;line-height: 40px;margin-left: 10px;">Top Trumps Games</p>
        </div>
        <div id="function-left" style="background-color:#FAEBD7;margin-top:10px;">
            <p style="margin-left: 10px;height: 30px;line-height: 30px;">New Game</p>
        </div>
    </div>
    <script type="text/javascript">
    // Method that is called on page load
    function initalize() {

        getStatistics();

    }


    function createCORSRequest(method, url) {
        var xhr = new XMLHttpRequest();
        if ("withCredentials" in xhr) {

            xhr.open(method, url, true);

        } else if (typeof XDomainRequest != "undefined") {

            xhr = new XDomainRequest();
            xhr.open(method, url);

        } else {

            xhr = null;

        }
        return xhr;
    }
    </script>
    <!-- Here are examples of how to call REST API Methods -->
    <script type="text/javascript">
    function getStatistics() {
    	var statisticsBlock=' ';
        var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getStatistics");
        if (!xhr) {
            alert("CORS not supported");
        }
        xhr.onload = function(e) {
            var responseText = xhr.response; // the text of the response
            console.log(responseText);

            responseText = responseText.replace("[",'');
            responseText = responseText.replace("]", "");
            var datalist = responseText.split(",");

            statisticsBlock += '<div id="data" style="background-color:#FFFFFF;margin-top:-16px;"><p style="margin-left:10px">Number Of Games:' + datalist[0] + '</p ><p style="margin-left:10px"> Number Of Human Wins:' + datalist[1] + '</p ><p style="margin-left:10px">Number Of AI Wins:' + datalist[2] + '</p ><p style="margin-left:10px">Average Number Of Draws:' + datalist[3] + '</p ><p style="margin-left:10px">Longest Game:' + datalist[4] + '</p ></div>';
            var html = document.getElementById("aaa").innerHTML;
            document.getElementById("aaa").innerHTML = html + statisticsBlock;
        };

        // We have done everything we need to prepare the CORS request, so send it
        xhr.send();
    }


    </script>
</body>

</html>