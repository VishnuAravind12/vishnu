---
title: NBA Player Stats
layout: default
description: Fetch and visualize NBA player stats
---

Get your favorite player's stats!

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NBA Player Stats</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        table th {
            background-color: #f2f2f2;
        }

        #fantasyScore {
            font-size: 24px;
            margin-top: 20px;
            font-weight: bold;
        }
    </style>
</head>

<body>

<input type="text" id="playerName" placeholder="Enter player name">
<button id="fetchButton">Get Player Stats</button>
<div id="fantasyScore"></div>
<h3>General Info</h3>
<table id="playerTable">
    <thead>
        <tr>
            <th>Parameter</th>
            <th>Value</th>
        </tr>
    </thead>
    <tbody></tbody>
</table>

<h3>Last 20 Games Stats</h3>
<table id="statsLast20Games">
    <thead>
        <tr>
            <th>Game</th>
            <th>Points</th>
            <th>Rebounds</th>
            <th>Assists</th>
            <th>Field Goals</th>
        </tr>
    </thead>
    <tbody></tbody>
</table>

<script>
$(document).ready(function() {
    $("#fetchButton").click(function() {
        const playerName = $("#playerName").val().split(" ");
        const firstName = playerName[0];
        const lastName = playerName[1];

        $.getJSON(`https://www.balldontlie.io/api/v1/players?search=${firstName}%20${lastName}`, function(data) {
            const player = data.data[0];
            displayGeneralInfo(player);
            
            fetchGameStats(player.id);
        }).fail(function() {
            console.error("Error fetching player data.");
        });
    });
});

function displayGeneralInfo(player) {
    const playerTable = $("#playerTable tbody");
    playerTable.html(`
        <tr><td>Name</td><td>${player.first_name} ${player.last_name}</td></tr>
        <tr><td>Position</td><td>${player.position}</td></tr>
        <tr><td>Height</td><td>${player.height_feet} ft ${player.height_inches} in</td></tr>
        <tr><td>Weight</td><td>${player.weight_pounds} lbs</td></tr>
        <tr><td>Team</td><td>${player.team.full_name}</td></tr>
    `);
}

function fetchGameStats(playerId) {
    $.getJSON(`https://www.balldontlie.io/api/v1/stats?player_ids[]=${playerId}&per_page=20`, function(gamesData) {
        if (gamesData.data && gamesData.data.length > 0) {
            const statsMap = {
                points: [],
                rebounds: [],
                assists: [],
                fieldGoals: []
            };

            gamesData.data.forEach(game => {
                statsMap.points.push(game.pts);
                statsMap.rebounds.push(game.reb);
                statsMap.assists.push(game.ast);
                statsMap.fieldGoals.push(game.fgm); // Assuming you want field goals made
            });

            displayLast20GameStats(statsMap);
        } else {
            alert("Game stats not available for this player.");
        }
    }).fail(function() {
        console.error("Error fetching game stats.");
    });
}

function displayLast20GameStats(statsMap) {
    const statsTable = $("#statsLast20Games tbody");
    statsTable.empty(); // Clear previous data

    for (let i = 0; i < 20; i++) {
        const row = `
            <tr>
                <td>Game ${i + 1}</td>
                <td>${statsMap.points[i] ?? 'N/A'}</td>
                <td>${statsMap.rebounds[i] ?? 'N/A'}</td>
                <td>${statsMap.assists[i] ?? 'N/A'}</td>
                <td>${statsMap.fieldGoals[i] ?? 'N/A'}</td>
            </tr>
        `;
        statsTable.append(row);
    }
}
</script>

</body>
</html>
