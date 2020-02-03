<html>

<head>
    <!-- Web page title -->
    <title>Top Trumps</title>

    <!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    <script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    <!--<script type="text/javascript" src="/js/jquery-1.7.1.min.js"></script>-->
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

<body onload="initalize()" style="background-color:#999999"> <!-- Call the initalize method when the page loads -->

<div class="container">

    <div id="header" style="background-color:#404040;height: 40px;">
        <p class="header-title" style="color:white;line-height: 40px;margin-left: 10px">Top Trumps Games</p>
    </div>

    <div style="background-color:#FFFFFF;width: 550px;float: left;margin-top: 10px;">
        <div id="function-left" style="background-color:#FAEBD7;">
            <p style="margin-left: 10px;height: 30px;line-height: 30px;">New Game</p>
        </div>
        <div>
            <!-- <div id ="button" style="margin-left: 10px;height: 40px;">
            <a  id="button-left" href= "javascript:"  style="color:black; " data-toggle="modal" data-target="#myModal" >Start a new Top Trumps Game</a>
            </div> -->


            <a href="javascript:" style="color:black;" data-toggle="modal" data-target="#myModal">
                <div id="button" style="margin-left: 10px;height: 40px;">
                    Start a new Top Trumps Game
                </div>
            </a>


        </div>
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true" style="opacity: 100">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">back
                        </button> -->
                        <h4 class="modal-title" id="myModalLabel" style="margin-right: : 50px;">
                            AI players
                        </h4>
                    </div>
                    <div class="modal-body">
                        <input style="text" placeholder="numbers" id="Inputnumbers">

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            close
                        </button>
                        <button type="button" class="btn btn-primary" onclick="Confirm()">
                            confirm
                        </button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>

    <div style="background-color:#FFFFFF;width: 550px;float: right;margin-top: 10px;">
        <div id="function-right" style="background-color:#FAEBD7;">
            <p style="margin-left: 10px;height: 30px;line-height: 30px;">Game Statistics</p>
        </div>

        <a id="button-left" style="color:black; " href="http://localhost:7777/toptrumps/stats">
            <div id="button" style="margin-left: 10px;height: 40px;">Get Statistics from past Games
            </div>
        </a>


    </div>
</div>


<script type="text/javascript">

    // Method that is called on page load
    function initalize() {


    }

    function createCORSRequest(method, url) {
        var xhr = new XMLHttpRequest();
        if ("withCredentials" in xhr) {

            xhr.open(method, url, true);

        } else if (typeof XDomainRequest != "undefined") {

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

    function Confirm() {
        var numbers = $('#Inputnumbers').val();
        if (numbers == "0") {
            alert("At least one AI player!")
            ;
        } else if (numbers >= "5") {
            alert("The maximum is 4!")
            ;
        } else if (numbers == "") {
            alert("NOT NULL!");
        } else {
            var getvalue = numbers;
            window.location.href = "http://localhost:7777/toptrumps/game?valus=" + getvalue;

        }

    }


</script>

</body>
</html>