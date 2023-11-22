// game.js

// Function to create an empty grid
function createEmptyGrid(rows, cols) {
    const grid = document.createElement('table');
    grid.classList.add('game-grid'); // Add a class for styling purposes

    // Create the header row with numbers
    const headerRow = document.createElement('tr');
    headerRow.appendChild(document.createElement('th')); // Empty corner cell
    for (let col = 1; col <= cols; col++) {
        const th = document.createElement('th');
        th.textContent = col;
        headerRow.appendChild(th);
    }
    grid.appendChild(headerRow);

    // Create rows with letters on the left and empty cells
    for (let row = 0; row < rows; row++) {
        const tr = document.createElement('tr');
        // Create the leftmost cell with letters
        const th = document.createElement('th');
        th.textContent = String.fromCharCode(65 + row); // A, B, C, ...
        tr.appendChild(th);
        // Create empty cells
        for (let col = 0; col < cols; col++) {
            const td = document.createElement('td');
            tr.appendChild(td);
        }
        grid.appendChild(tr);
    }

    return grid;
}

// Execute the function on page load
document.addEventListener('DOMContentLoaded', function () {
    // Specify the number of rows and columns for your grid
    const rows = 10; // Adjust as needed
    const cols = 10; // Adjust as needed

    // Call the function to create the empty grid and append it to the body
    document.body.appendChild(createEmptyGrid(rows, cols));
});
