---
title: First Calculator
layout: default
description: This is my first HTML/CSS/JSS project.
---

## The Calculator

<style>
  .calculator-container {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 10px;
    max-width: 300px;
    margin: auto;
  }
  .calculator-output {
    grid-column: span 4;
    padding: 10px;
    background-color: #f5f5f5;
    border: 2px solid #333;
    text-align: right;
    font-size: 24px;
    color: #333
  }
  .calculator-button {
    padding: 10px;
    background-color: #aaa;
    border: 2px solid #333;
    font-size: 20px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  .calculator-button:hover {
    background-color: #ccc;
  }
  .calculator-operation {
    background-color: #e67e22;
    color: white;
  }
  .calculator-operation:hover {
    background-color: #f39c12;
  }
  .calculator-equals {
    background-color: #27ae60;
    color: white;
  }
  .calculator-equals:hover {
    background-color: #2ecc71;
  }
</style>

<div class="calculator-container">
    <div class="calculator-output" id="output">0</div>
    <div class="calculator-button calculator-number">1</div>
    <div class="calculator-button calculator-number">2</div>
    <div class="calculator-button calculator-number">3</div>
    <div class="calculator-button calculator-operation">+</div>
    <div class="calculator-button calculator-number">4</div>
    <div class="calculator-button calculator-number">5</div>
    <div class="calculator-button calculator-number">6</div>
    <div class="calculator-button calculator-operation">-</div>
    <div class="calculator-button calculator-number">7</div>
    <div class="calculator-button calculator-number">8</div>
    <div class="calculator-button calculator-number">9</div>
    <div class="calculator-button calculator-operation">*</div>
    <div class="calculator-button calculator-clear">AC</div>
    <div class="calculator-button calculator-number">0</div>
    <div class="calculator-button calculator-number">.</div>
    <div class="calculator-button calculator-equals">=</div>
</div>

<script>
  // Declare variables to hold the first number and the operator for calculations
  let firstNumber = null;
  let operator = null;

  // Flag to determine if the next number input should replace the current display or be appended to it
  let nextReady = true;

  // Get references to the calculator's display and various buttons
  const output = document.getElementById("output");
  const numbers = document.querySelectorAll(".calculator-number");
  const operations = document.querySelectorAll(".calculator-operation");
  const clear = document.querySelector(".calculator-clear");
  const equals = document.querySelector(".calculator-equals");

  // Add event listeners to each number button
  numbers.forEach(button => {
      button.addEventListener("click", function() {
          number(button.textContent);
      });
  });

  // Add event listeners to each operation button
  operations.forEach(button => {
      button.addEventListener("click", function() {
          operation(button.textContent);
      });
  });

  // Add event listeners to the clear and equals buttons
  clear.addEventListener("click", clearCalc);
  equals.addEventListener("click", equal);

  // Function to handle number button clicks
  function number(value) {
      if (nextReady) {
          output.innerHTML = value !== "0" ? value : "0";
          nextReady = false;
      } else {
          output.innerHTML += value;
      }
  }

  // Function to handle operation button clicks
  function operation(choice) {
      if (firstNumber === null) {
          firstNumber = parseFloat(output.innerHTML);
          operator = choice;
          nextReady = true;
      } else {
          firstNumber = calculate(firstNumber, parseFloat(output.innerHTML));
          operator = choice;
          output.innerHTML = firstNumber.toString();
          nextReady = true;
      }
  }

  // Function to perform the actual calculation based on the operator
  function calculate(first, second) {
      switch (operator) {
        case "+": return first + second;
        case "-": return first - second;
        case "*": return first * second;
        default: return first;
      }
  }

  // Function to handle the equals button click
  function equal() {
      if (firstNumber !== null) {
          output.innerHTML = calculate(firstNumber, parseFloat(output.innerHTML)).toString();
          firstNumber = null;
          nextReady = true;
      }
  }

  // Function to clear the calculator's display and reset variables
  function clearCalc() {
      firstNumber = null;
      output.innerHTML = "0";
      nextReady = true;
  }
</script>