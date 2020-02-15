$("#next").on("click",function(){
	var objectAi = getCategory();
	// when response is 0, wait for user's input
	if( objectAi == 0) data.activePlayer = userName;
	var userchoose = "";
	var winner = "";
	//if user is active
	if (data.activePlayer == userName) {
		$("#selection").show();
		$(".status h1").html("Player "+data.activePlayer+" selecting ");
		$("#selection li").off('click').on("click",function(){
			userchoose = $(this).text();
			// index of clicked li
			var number = $("#selection li").index(this)+1;
			data.selected = userchoose;
			$("#selection").hide();
		 	$(".cardsAI").show();
		 	$("#show").show();
			$("#selected span").html("You selected "+data.selected);
			$(".status h1").html("Player "+data.activePlayer+" selected "+data.selected);
			$("#selected").show();
			objectAi = getUserCategory(number);
			initializeAi(objectAi);
		})
	}else{
		initializeAi(objectAi);
		
		$(".cardsAI").show();
		$("#selected span").html("They selected "+data.selected);
		$("#selected").show();
		$("#show").show();
		$(".status h1").html("Player "+data.activePlayer+" selected "+data.selected);
	}
	$("#next").hide();
});
//show winner
$("#show").on("click",function(){

	$(".cardsYou").hide();
	$(".cardsAI").hide();
	$("#active").hide();
	$("#selected").hide();
	$("#show").hide();
	$("#new").show();
	//update winner
	var winner = showWinner();
	data.winner = winner[1];
	data.continue = winner[2];
	data.drew = winner[0];
	if (data.drew) {
		$(".status h1").html("Round "+data.round+" : This round is a draw ");
	}else{
		$(".status h1").html("Round "+data.round+" : Player "+data.winner+" won this round ");
	}
});
//next round
$("#new").on("click",function(){
	//set player = []
	data.players.splice(0,data.players.length);
	if (data.continue) {
		$("#new").hide();
		$(".cardsYou").show();
		$("#active").show();
		$("#next").show();
		initializeUser();
	}else{
		alert("game ended! "+data.winner+" has won the game!");
		$(".goBack").show();
	}
});
