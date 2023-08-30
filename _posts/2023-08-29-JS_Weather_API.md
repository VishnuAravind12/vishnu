---
title: Get the Weather
layout: default
description: With this
---


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather Dashboard</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        #weatherTable {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        #weatherTable th, #weatherTable td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        #weatherTable th {
            background-color: #f2f2f2;
        }
    </style>
</head>

<body>

<input type="text" id="cityName" placeholder="Enter city name">
<button onclick="fetchWeatherData()">Get Weather</button>
<label>
    <input type="checkbox" id="unitToggle"> Toggle to Fahrenheit
</label>
<p id="errorMessage" style="color: red;"></p>

<table id="weatherTable">
    <thead>
        <tr>
            <th>Parameter</th>
            <th>Value</th>
        </tr>
    </thead>
    <tbody></tbody>
</table>

<script>
function fetchWeatherData() {
    const cityName = document.getElementById("cityName").value;
    const apiKey = "83157bcd4c4f5e5fb18a3217be1284ee";
    const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${cityName}&appid=${apiKey}&units=metric`;

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to fetch weather data");
            }
            return response.json();
        })
        .then(data => {
            document.querySelector("#weatherTable tbody").innerHTML = "";
            for (const key in data.main) {
                const row = `<tr><td>${key}</td><td>${data.main[key]}</td></tr>`;
                document.querySelector("#weatherTable tbody").innerHTML += row;
            }

            // Add weather description and icon
            const weatherDescription = data.weather[0].description;
            const weatherIcon = data.weather[0].icon;
            const iconUrl = `http://openweathermap.org/img/w/${weatherIcon}.png`;
            const weatherRow = `<tr><td>Weather</td><td>${weatherDescription} <img src="${iconUrl}" alt="${weatherDescription}"></td></tr>`;
            document.querySelector("#weatherTable tbody").innerHTML += weatherRow;

            // Add wind speed
            const windSpeed = data.wind.speed;
            const windRow = `<tr><td>Wind Speed</td><td>${windSpeed} m/s</td></tr>`;
            document.querySelector("#weatherTable tbody").innerHTML += windRow;

        })
        .catch(error => {
            document.getElementById("errorMessage").textContent = "Error fetching weather data. Please ensure the city name is correct.";
            console.error("Error fetching weather data:", error);
        });
}

document.getElementById("unitToggle").addEventListener("change", function() {
    const rows = document.querySelectorAll("#weatherTable tbody tr");
    rows.forEach(row => {
        if (row.firstChild.textContent === "temp" || row.firstChild.textContent.includes("temp_")) {
            const tempValue = parseFloat(row.lastChild.textContent);
            if (this.checked) {
                row.lastChild.textContent = (tempValue * 9/5 + 32).toFixed(2);
            } else {
                row.lastChild.textContent = ((tempValue - 32) * 5/9).toFixed(2);
            }
        }
    });
});
</script>

</body>
</html>
