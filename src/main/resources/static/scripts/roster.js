document.addEventListener("DOMContentLoaded", function () {
    const playerList = document.getElementById("player-list");
    const addPlayerButton = document.getElementById("add-player");
    const newPlayerInput = document.getElementById("new-player");

    // Function to fetch and display players
    function fetchPlayers() {
        fetch("http://localhost:8080/rest/players")
            .then((response) => response.json())
            .then((data) => {
                playerList.innerHTML = ""; // Clear existing list
                data._embedded.players.forEach((player) => {
                    const li = document.createElement("li");
                    li.textContent = player.email;
                    playerList.appendChild(li);
                });
            })
            .catch((error) => {
                console.error("Error fetching players:", error);
            });
    }

    // Event listener for Add button
    addPlayerButton.addEventListener("click", function () {
        const newPlayerName = newPlayerInput.value;
        if (newPlayerName) {
            // POST new player to /players
            fetch("http://localhost:8080/rest/players", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ email: newPlayerName }),
            })
                .then(() => {
                    // After successful POST, fetch and display players again
                    fetchPlayers();
                })
                .catch((error) => {
                    console.error("Error adding player:", error);
                });
        }
    });

    // Initial fetch and display of players
    fetchPlayers();
});
