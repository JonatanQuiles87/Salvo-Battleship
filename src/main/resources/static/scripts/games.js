const domainUrl = 'http://localhost:8080';

const fetchJson = url =>
    fetch(domainUrl + url).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('error: ' + response.statusText);
        }
    });
const gamesList = document.getElementById("games-list");

fetchJson('/api/games').then(games => {
    console.log('games: ', games);
    createGameInfo(games);
    const playerList = changeJsonStructureForLeaderboard(games);
    console.log('playerList', playerList);
});

function createGameInfo(games) {
    const gamesInfo = games.map(game =>
        game['created'].toLocaleString() + ', ' +
        game['gamePlayers'].map(gamePlayer => gamePlayer['player']['email']).sort().join(', '));
    gamesInfo.forEach(createListOfGames);
}

const createListOfGames = (gameInfo) => {
    const listItem = document.createElement('li');
    const listItemText = document.createTextNode(gameInfo);
    listItem.appendChild(listItemText);
    gamesList.appendChild(listItem);
}

function changeJsonStructureForLeaderboard(games) {
    return games.reduce((playerList, {gamePlayers}) => {
        const playerUsernamesGamePlayer = gamePlayers.map(({player}) => player['email']);
        // playerList = playerList.concat(playerUsernamesGamePlayer); To be able to use includes method we used foreach. Otherwise, duplicated values are also added to array.
        playerUsernamesGamePlayer.forEach(email => {
            if (!playerList.includes(email)) {
                playerList.push(email);
            }
        });
        return playerList;
    }, []);
}