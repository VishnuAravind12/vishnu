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
$(document).ready(function() {
    $("#fetchButton").click(function() {
        const playerName = $("#playerName").val().split(" ");
        const firstName = playerName[0];
        const lastName = playerName[1];

        $.getJSON(`https://www.balldontlie.io/api/v1/players?search=${firstName}%20${lastName}`, function(data) {
            const player = data.data[0];
            displayGeneralInfo(player);

            // Fetch detailed stats using player's ID for the 2022 season
            $.getJSON(`https://www.balldontlie.io/api/v1/season_averages?season=2022&player_ids[]=${player.id}`, function(statsData) {
                if (statsData.data && statsData.data.length > 0) {
                    displayDetailedStats(statsData.data[0]);
                    const fantasyRating = computeFantasyRating(statsData.data[0]);
                    $("#fantasyScore").html(`Fantasy Rating: ${fantasyRating}/10`);
                } else {
                    alert("Detailed stats not available for this player.");
                }
            });
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

function displayDetailedStats(stats) {
    const statsTable = $("#statsTable tbody");
    statsTable.empty(); // Clear previous data

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

    for (const key in statNames) {
        if (stats[key] !== undefined) {
            const row = `<tr><td>${statNames[key]}</td><td>${stats[key]}</td></tr>`;
            statsTable.append(row);
        }
    }
}

function computeFantasyRating(stats) {
    // Weights for each stat
    const weights = {
        pts: 0.2,
        reb: 0.15,
        ast: 0.15,
        stl: 0.1,
        blk: 0.1,
        fg_pct: 0.1,
        ft_pct: 0.1,
        fg3_pct: 0.05,
        turnover: -0.05  // Negative weight since turnovers are bad
    };

    // Normalize each stat to a scale of 0 to 1
    // For simplicity, we'll use some arbitrary max values for normalization
    const normalizedStats = {
        pts: stats.pts / 30,
        reb: stats.reb / 15,
        ast: stats.ast / 10,
        stl: stats.stl / 5,
        blk: stats.blk / 5,
        fg_pct: stats.fg_pct,
        ft_pct: stats.ft_pct,
        fg3_pct: stats.fg3_pct,
        turnover: stats.turnover / 5
    };

    // Compute the weighted sum
    let rawScore = 0;
    for (const key in weights) {
        rawScore += normalizedStats[key] * weights[key];
    }

    // Scale the raw score to a rating out of 10
    const rating = Math.min(Math.max(rawScore * 10, 0), 10);

    return rating.toFixed(1);  // Return the rating rounded to one decimal place
}

</script>

</body>
</html>
