let data;

gamesCalls();

function gamesCalls() {
    fetch("http://localhost:8080/api/games", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    })
    .then(function (response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Network response was not ok');
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
    let tab = document.getElementById("gamesList");
    for (let i = 0; i < data.length; i++) {
        let list = document.createElement("li");
        let created = data[i].creationDate
        let players = data[i].gamePlayers;
        let email = players.map(function(player) {
            return player.player.email;
        }).join(", ");
        list.innerHTML = created + " : " + email;
        tab.appendChild(list);
    }
}
