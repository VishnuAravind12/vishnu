---
title: NBA Player Stat Tracker
layout: default
description: Get detailed statistics and visualizations of NBA players.
---

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NBA Player Stat Tracker</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        #playerTable {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        #playerTable th, #playerTable td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #playerTable th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>

<input type="text" id="playerName" placeholder="Enter player name">
<button id="fetchButton">Get Player Stats</button>
<p id="errorMessage" style="color: red;"></p>

<table id="playerTable">
    <thead>
        <tr>
            <th>Parameter</th>
            <th>Value</th>
        </tr>
    </thead>
    <tbody></tbody>
</table>

<script>
document.getElementById("fetchButton").addEventListener("click", fetchPlayerData);

function fetchPlayerData() {
    const playerName = document.getElementById("playerName").value;
    const apiUrl = `https://www.balldontlie.io/api/v1/players?search=${playerName}`;

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch player data");
            }
            return response.json();
        })
        .then(data => {
            if (data.data.length > 0) {
                const player = data.data[0];
                fetchPlayerSeasonStats(player.id);
            } else {
                document.getElementById("errorMessage").textContent = "Player not found!";
            }
        })
        .catch(error => {
            console.error("Error fetching player data:", error);
        });
}

function fetchPlayerSeasonStats(playerId) {
    const seasonStatsUrl = `https://www.balldontlie.io/api/v1/season_averages?season=2022&player_ids[]=${playerId}`;

    fetch(seasonStatsUrl)
        .then(response => response.json())
        .then(data => {
            if (data.data.length > 0) {
                const stats = data.data[0];
                document.querySelector("#playerTable tbody").innerHTML = `
                    <tr><td>Points Per Game</td><td>${stats.pts}</td></tr>
                    <tr><td>Assists Per Game</td><td>${stats.ast}</td></tr>
                    <tr><td>Rebounds Per Game</td><td>${stats.reb}</td></tr>
                    <tr><td>Field Goal Percentage</td><td>${stats.fg_pct}</td></tr>
                    <tr><td>Free Throw Percentage</td><td>${stats.ft_pct}</td></tr>
                    <tr><td>Three Point Percentage</td><td>${stats.fg3_pct}</td></tr>
                `;
            }
        })
        .catch(error => {
            console.error("Error fetching player season stats:", error);
        });
}
</script>

</body>
</html>
