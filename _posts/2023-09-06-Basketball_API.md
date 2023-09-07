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
    </style>
</head>

<body>

<input type="text" id="playerName" placeholder="Enter player name">
<button onclick="fetchPlayerData()">Get Player Stats</button>
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

<h3>Detailed Stats</h3>
<table id="statsTable">
    <thead>
        <tr>
            <th>Stat</th>
            <th>Value</th>
        </tr>
    </thead>
    <tbody></tbody>
</table>

<script>
function fetchPlayerData() {
    const playerName = document.getElementById("playerName").value.split(" ");
    const firstName = playerName[0];
    const lastName = playerName[1];

    fetch(`https://www.balldontlie.io/api/v1/players?search=${firstName}%20${lastName}`)
        .then(response => response.json())
        .then(data => {
            const player = data.data[0];
            displayGeneralInfo(player);

            // Fetch detailed stats using player's ID for the 2022 season
            fetch(`https://www.balldontlie.io/api/v1/season_averages?season=2022&player_ids[]=${player.id}`)
                .then(response => response.json())
                .then(statsData => {
                    if (statsData.data && statsData.data.length > 0) {
                        displayDetailedStats(statsData.data[0]);
                    } else {
                        alert("Detailed stats not available for this player.");
                    }
                });
        })
        .catch(error => {
            console.error("Error fetching player data:", error);
        });
}

function displayGeneralInfo(player) {
    const playerTable = document.querySelector("#playerTable tbody");
    playerTable.innerHTML = `
        <tr><td>Name</td><td>${player.first_name} ${player.last_name}</td></tr>
        <tr><td>Position</td><td>${player.position}</td></tr>
        <tr><td>Height</td><td>${player.height_feet} ft ${player.height_inches} in</td></tr>
        <tr><td>Weight</td><td>${player.weight_pounds} lbs</td></tr>
        <tr><td>Team</td><td>${player.team.full_name}</td></tr>
    `;
}

function displayDetailedStats(stats) {
    const statsTable = document.querySelector("#statsTable tbody");
    statsTable.innerHTML = ""; // Clear previous data

    const statNames = {
        "games_played": "Games Played",
        "player_id": "Player ID",
        "season": "Season",
        "min": "Minutes Played",
        "fgm": "Field Goals Made",
        "fga": "Field Goals Attempted",
        "fg3m": "Three-Point Field Goals Made",
        "fg3a": "Three-Point Field Goals Attempted",
        "ftm": "Free Throws Made",
        "fta": "Free Throws Attempted",
        "oreb": "Offensive Rebounds",
        "dreb": "Defensive Rebounds",
        "reb": "Total Rebounds",
        "ast": "Assists",
        "stl": "Steals",
        "blk": "Blocks",
        "turnover": "Turnovers",
        "pf": "Personal Fouls",
        "pts": "Points",
        "fg_pct": "Field Goal Percentage",
        "fg3_pct": "Three-Point Field Goal Percentage",
        "ft_pct": "Free Throw Percentage"
    };

    for (const key in stats) {
        if (statNames[key]) {
            const row = `<tr><td>${statNames[key]}</td><td>${stats[key]}</td></tr>`;
            statsTable.innerHTML += row;
        }
    }
}

</script>



</body>
</html>
