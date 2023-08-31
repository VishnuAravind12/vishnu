---
toc: false
comments: false
layout: post
title: Morse Code Translator
description: A simple use of JavaScript to translate text to Morse code and vice versa.
type: hacks
---

## Morse Code Translator

### Input:
<textarea id="inputText" rows="4" cols="50" placeholder="Enter text or Morse code..."></textarea><br>
<button id="translateButton">Translate</button>

### Output:
<textarea id="outputText" rows="4" cols="50" readonly></textarea>

<script>
document.addEventListener("DOMContentLoaded", function() {
    const morseCodeDict = {
        'A': '.-', 'B': '-...', 'C': '-.-.', 'D': '-..', 'E': '.', 'F': '..-.', 'G': '--.', 'H': '....', 'I': '..', 'J': '.---',
        'K': '-.-', 'L': '.-..', 'M': '--', 'N': '-.', 'O': '---', 'P': '.--.', 'Q': '--.-', 'R': '.-.', 'S': '...', 'T': '-',
        'U': '..-', 'V': '...-', 'W': '.--', 'X': '-..-', 'Y': '-.--', 'Z': '--..',
        '0': '-----', '1': '.----', '2': '..---', '3': '...--', '4': '....-', '5': '.....', '6': '-....', '7': '--...', '8': '---..', '9': '----.',
        '.': '.-.-.-', ',': '--..--', '?': '..--..', "'": '.----.', '!': '-.-.--', '/': '-..-.', '(': '-.--.', ')': '-.--.-', '&': '.-...', ':': '---...', ';': '-.-.-.', '=': '-...-', '+': '.-.-.', '-': '-....-', '_': '..--.-', '"': '.-..-.', '$': '...-..-', '@': '.--.-.', ' ': '/'
    };

    function translate() {
        const input = document.getElementById("inputText").value.toUpperCase();
        let output = "";

        // Check if input is Morse code or text
        if (input.includes('.') || input.includes('-')) {
            // Translate Morse to Text
            const morseWords = input.split('   ');  // Morse code words are separated by 3 spaces
            for (let word of morseWords) {
                const morseChars = word.split(' ');  // Morse code characters are separated by 1 space
                for (let char of morseChars) {
                    output += Object.keys(morseCodeDict).find(key => morseCodeDict[key] === char);
                }
                output += ' ';
            }
        } else {
            // Translate Text to Morse
            for (let char of input) {
                output += morseCodeDict[char] + ' ';
            }
        }

        document.getElementById("outputText").value = output.trim();
    }

    // Attach the translate function to the button's click event
    document.getElementById("translateButton").addEventListener("click", translate);
});
</script>
