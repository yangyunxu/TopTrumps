
	var resetPage = () =>{	
		$(".goBack").hide();
		$("#selection").hide();
		$("#show").hide();
		$("#new").hide();
		$("#selected").hide();
		$(".cardsAI").hide();
	}
	var updateDataUser = () =>{
		var responseText = nextRound();
		console.log(responseText);
		data.userAlive = responseText.userAlive;
		if (data.userAlive) {
			var userObject = {};
			userObject["name"] = userName;
			userObject["cardName"] = responseText.CardName;
			userObject["cardNum"] = responseText.numberInDeck;
			userObject["card"] = {
				"speed":responseText.speed,
				"cargo":responseText.cargo,
				"size":responseText.size,
				"range":responseText.range,
				"firepower":responseText.firepower
			}
			data.round = Number(responseText.Round)+1;
			data.user = userObject;
			if ( responseText.activePlayer == userName ) {
				data.activePlayer = userName;
			}else{
				data.activePlayer = responseText.activePlayer;
			}
		}

		console.log(data.activePlayer);
	}
	var initializeUser = () =>{
		var cardsYou = "";
		updateDataUser();
		$(".status h1").html("Round "+data.round+" Players have drawn their cards");
		//initialize you in the first round
		if (data.userAlive) {
			cardsYou += "<div class='cardt' id='"+data.user.name+"'>"+
						"<span class='name'>"+data.user.name+"</span>"+
						"<div id='cardName'>"+data.user.cardName+" <span class='badge badge-primary'>"+data.user.cardNum+"</span></div>"+
						"<div id='cardImg'><img src='../assets/images/"+data.user.cardName+".jpg'></div>"+
						"<ul id='category'>"+
							"<li>Size, "+data.user.card.size+"</li>"+
							"<li>Speed, "+data.user.card.speed+"</li>"+
							"<li>Range, "+data.user.card.range+"</li>"+
							"<li>Firepower, "+data.user.card.firepower+"</li>"+
							"<li>Cargo, "+data.user.card.cargo+"</li>"+
						"</ul>"+
					"</div>";
		}
		$(".cardsYou").html(cardsYou);	
		if (data.activePlayer == userName) {
			$(".cardsYou").children().first().children().first().css("background-color","#ff7f08");
			$("#activePlayer").html("You");
		}else{
			$("#activePlayer").html(data.activePlayer);
		}
	}
	var updateDataAi = (objAi) =>{
		switch(objAi.Category){
			case 1 :
				data.selected = "Size";
				break;
			case 2:
				data.selected = "Speed";
				break;
			case 3:
				data.selected = "Range";
				break;
			case 4:
				data.selected = "Firepower";
				break;
			case 5:
				data.selected = "Cargo";
				break;
		}
		console.log(objAi);
		$.each(objAi,function(player,info){
			if (player != "Category" && player.split(",")[0] != userName && player.split(",")[0] != "") {
				var ai = {};
				ai["name"] = player.split(",")[0];
				ai["cardName"] = info.cardName;

				ai["cardNum"] = player.split(",")[1];
				ai["card"] = {
					"speed":info.speed,
					"cargo":info.cargo,
					"size":info.size,
					"range":info.range,
					"firepower":info.firepower
				}
				data.players.push(ai);
			}
		});

		data.players.sort(function(a, b){
			var playerNameA = a.name.split(" ")[2];
			var playerNameB = b.name.split(" ")[2];
			if (playerNameA < playerNameB) {return -1;}
			if (playerNameA > playerNameB) {return 1;}
			return 0;
		});
		console.log(data.players);

		// console.log(ai);
	}
	var initializeAi = (objAi) =>{
		var cardsAI = "";
		updateDataAi(objAi);
		for(var index=0;index<data.players.length;index++){
			var player = data.players[index].name.split(" ")[2];
			cardsAI += "<div class='cardt' id='"+player+"'>"+
							"<span class='name'>"+data.players[index].name+"</span>"+
							"<div id='cardName'>"+data.players[index].cardName+" <span class='badge badge-primary'>"+data.players[index].cardNum+"</span></div>"+
							"<div id='cardImg'><img src='../assets/images/"+data.players[index].cardName+".jpg'></div>"+
							"<ul id='category'>"+
								"<li>Size, "+data.players[index].card.size+"</li>"+
								"<li>Speed, "+data.players[index].card.speed+"</li>"+
								"<li>Range, "+data.players[index].card.range+"</li>"+
								"<li>Firepower, "+data.players[index].card.firepower+"</li>"+
								"<li>Cargo, "+data.players[index].card.cargo+"</li>"+
							"</ul>"+
						"</div>";	
		}
		$(".cardsAI").html(cardsAI);
		if (data.activePlayer == userName) {
			$(".cardsYou").children().first().children().first().css("background-color","#ff7f08");
			$("#activePlayer").html("You");
		}else{
			var playerindex = data.activePlayer.split(" ")[2];
			//find id = activeplayer
			$(".cardsAI #"+playerindex).children().first().css("background-color","#ff7f08");
			$("#activePlayer").html(data.activePlayer);
		}
	}


	
		resetPage();
	// set name and player number
		numberAiPlayers(playerNum);
		setUserName(userName);
		initializeUser();

