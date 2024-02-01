const domainUrl = 'http://localhost:8080';

const fetchJson = url =>
    fetch(domainUrl + url).then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('error: ' + response.statusText);
        }
    });

const leaderboard = document.querySelector('#leaderboard');
const gamesList = document.getElementById("games-list");
const fetchedGameInfo = await fetchJson('/api/games'); //Top level await. No need to be in async function.


const briefGameInfo = (games) => {
    return games.map(game =>
        game['created'].toLocaleString() + ', ' +
        game['gamePlayers'].map(gamePlayer => gamePlayer['player']['email']).sort().join(', '));
}
briefGameInfo(fetchedGameInfo).forEach(createHtmlListOfGames);

const scoresOfPlayers = (games) => {
    const playerList = createPlayerListFromJson(games);
    return playerList.reduce((scoresOfPlayers, player) => {
        const playerResult = {
            'name': player,
            'total': getTotalScoreOfPlayer(player, games),
            'won': getTotalWinCountOfPlayer(player, games),
            'lost': getTotalLossCountOfPlayer(player, games),
            'tied': getTotalTieCountOfPlayer(player, games)
        }
        scoresOfPlayers.push(playerResult);
        return scoresOfPlayers;
    }, []).sort((firstPlayer, secondPlayer) => secondPlayer['total'] - firstPlayer['total']);
}
console.log('scoresOfPlayers', scoresOfPlayers(fetchedGameInfo));
scoresOfPlayers(fetchedGameInfo).forEach(createLeaderboardTable);


function createHtmlListOfGames(gameInfo) {
    const listItem = document.createElement('li');
    const listItemText = document.createTextNode(gameInfo);
    listItem.appendChild(listItemText);
    gamesList.appendChild(listItem);
}

function createLeaderboardTable(player) {
        const tableRow = document.createElement('tr');
        Object.keys(player).forEach(key => {
            const tableCell = document.createElement('td');
            const tableCellText = document.createTextNode(player[key]);
            tableCell.appendChild(tableCellText);
            tableRow.appendChild(tableCell);
        });
        leaderboard.appendChild(tableRow);
}

function createPlayerListFromJson(games) {
    return games.reduce((playerList, {gamePlayers}) => {
        gamePlayers.map(({player}) => player['email'])
            .forEach(email => {
                if (!playerList.includes(email)) {
                    playerList.push(email);
                }
            });
        return playerList;
    }, []);
}

function getTotalScoreOfPlayer(playerUsername, games) {
    return games.reduce((totalScore, {gamePlayers}) => {
        gamePlayers.forEach(gamePlayer => {
            if (gamePlayer['player']['email'] === playerUsername) {
                totalScore += gamePlayer['score'];
            }
        })
        return totalScore;
    }, 0);
}

function getTotalWinCountOfPlayer(playerUsername, games) {
    return resultCounter(playerUsername, games, 'win');
}

function getTotalTieCountOfPlayer(playerUsername, games) {
    return resultCounter(playerUsername, games, 'tie');
}

function getTotalLossCountOfPlayer(playerUsername, games) {
    return resultCounter(playerUsername, games, 'loss');
}

function resultCounter(playerUsername, games, resultType) {
    const score = resultType === 'win' ? 1.0 : resultType === 'tie' ? 0.5 : 0.0;
    return games.reduce((counter, {gamePlayers}) => {
        gamePlayers.forEach(gamePlayer => {
            if (gamePlayer['player']['email'] === playerUsername && gamePlayer['score'] === score) {
                counter += 1;
            }
        });
        return counter;
    }, 0);
}