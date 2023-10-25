var data;

gamesCalls();

function gamesCalls() {
    fetch("/api/games", {
        method: "GET",
        headers: {

        }
    })
    .then(function (response) {
        if (response.ok) {
            return response.json();
        }
    })
    .then(function (json) {
        data = json;
        tableGames();
    })
    .catch(function (error) {
        console.log("Request failed: " + error.message);
    });
}

function tableGames() {
    document.getElementById("Portal").innerHTML = "Salvo Games List";
    var tab = document.getElementById("gamesList");
    for (var i = 0; i < data.length; i++) {
        var list = document.createElement("li");
        var players = data[i].gamePlayers;
        var email = players.map(function(player) {
            return player.player.playerEmail;
        }).join(", ");
        list.innerHTML = data[i].created + " : " + email;
        tab.appendChild(list);
    }
}
