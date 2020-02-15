function launchGame(){
    $.ajax({
        type:'GET',
        url:"http://localhost:7777/toptrumps/launchGame",
        dataType:'json',
        async:false,
        success:function(data){
            response = data;
        }
    });
}
function numberAiPlayers(number) {
	$.ajax({
        type:'GET',
        url:"http://localhost:7777/toptrumps/numberAi?number="+number,
        dataType:'json',
        async:false,
        success:function(data){
        	response = data;
        }
    });
}
function setUserName(name) {
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/userName?name="+name); // Request type and URL+parameters
	if (!xhr) {
		alert("CORS not supported");
	}
	xhr.send();
}
function getCategory() {
	var response;
	$.ajax({
        type:'GET',
        url:"http://localhost:7777/toptrumps/getCategory",
        dataType:'json',
        async:false,
        success:function(data){
        	response = data;
        }
    });

	return response;
}

function getUserCategory(number) {

	var response;
	$.ajax({
        type:'GET',
        url:"http://localhost:7777/toptrumps/getUserCategory?number="+number,
        dataType:'json',
        async:false,
        success:function(data){
        	response = data;
        }
    });

	return response;
}
function showWinner() {
	var response;
	$.ajax({
        type:'GET',
        url:"http://localhost:7777/toptrumps/showWinner",
        dataType:'json',
        async:false,
        success:function(data){
        	response = data;
        }
    });
    return response;
}

function nextRound() {
	var response;
	$.ajax({
        type:'GET',
        url:"http://localhost:7777/toptrumps/nextRound",
        dataType:'json',
        async:false,
        success:function(data){
        	response = data;
        }
    });

	    return response;
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