
const params = new Proxy(new URLSearchParams(window.location.search), {
    get: (searchParams, prop) => searchParams.get(prop),
});
const gamePlayerId = params['gp'];
gamesCalls();

function gamesCalls() {
    fetch( `http://localhost:8080/api/game_view/${gamePlayerId}`, {
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
        let data = json;
        console.log(data)
    })
    .catch(function (error) {
        console.log("Request failed: " + error.message);
    });
}

// Import this function in game.html as a module
export function createEmptyGrid() {
    let tab = document.getElementById("game-info"); // Assuming "game-info" is the correct ID
    let grid = document.createElement("table");

    // Create the header row with numbers
    let headerRow = document.createElement("tr");
    headerRow.appendChild(document.createElement("th")); // Empty corner cell
    for (let i = 1; i <= 10; i++) {
        let headerCell = document.createElement("th");
        headerCell.textContent = i;
        headerRow.appendChild(headerCell);
    }
    grid.appendChild(headerRow);

    // Create rows with letters on the left side
    for (let i = 0; i < 10; i++) {
        let row = document.createElement("tr");
        let letterCell = document.createElement("td");
        letterCell.textContent = String.fromCharCode(65 + i); // ASCII code for 'A' is 65
        row.appendChild(letterCell);

        // Create empty cells in each row
        for (let j = 0; j < 10; j++) {
            let cell = document.createElement("td");
            row.appendChild(cell);
        }

        grid.appendChild(row);
    }
     grid.classList.add("game-grid");
    // Append the grid to the specified element
    tab.appendChild(grid);
}

// Call the function on page load
window.addEventListener('load', () => {
    createEmptyGrid();
});

