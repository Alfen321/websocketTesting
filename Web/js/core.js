var prompt = "&lambda;" + "&nbsp;"
var cmdHistory = [];
var messages = document.getElementById("messages");


function Init() {
    FocusOnInput()
    messages.innerHTML = "<div class=\"block\">"
    "<span class=\"prompt\">" + prompt + "</span>Welcome to the Webconsole!<br>" + 
    "</div>";

    document.getElementById("userInput").addEventListener("keypress", function (event) {
        if (event.key === "Enter") {
            CommandParser(this);
        } else if (event.key === "ArrowUp") {
            console.log(cmdHistory.peek());
            inputField.value = cmdHistory.peek();
        }
    });
}

function FocusOnInput() {
    document.getElementById("userInput").focus();
}

function ClearInput() {
    document.getElementById("userInput").value = "";
}

function ClearConsole() {
    document.getElementById("messages").innerHTML = "";
}

function AddToBuffer(text) {
    //yea its ugly deal with it.
    header = document.getElementById("headerLine").innerHTML;
    messages.innerHTML += "<div class=\"block\">" + header + "<br>" +
        "<span class=\"prompt\">" + prompt + "</span>" + text + "<br>" + "</div>"
}

function Output(text) {
    messages.lastChild.innerHTML += text+"<br>";
}

function addHeader(){
    header = document.getElementById("headerLine").innerHTML;
    messages.innerHTML += header;
}


function SetPrompt(marker) {
    if (marker != "") {
        if (marker.length > 1) {
            prompt = "&" + marker + ";&nbsp;"
        } else {
            prompt = marker + "&nbsp;"
        }
        currentMarkers = document.getElementsByClassName("prompt");
        for (var index = 0; index < currentMarkers.length; index++) {
            currentMarkers[index].innerHTML = prompt;
        }
    }
}

function HelpFunction() {
    text =
        "cls, clear: " +
        "clear the screen.<br>" +
        "!raw: No input sanitizing.<br>" +
        "!blacklist: Input sanitizing.<br>" +
        "!command: Command based input.<br>" +
        "alert [text]" +
    "help: " +
    "show this text.<br>" +
    Output(text);
}

// Takes a HTML element as input
function CommandParser(input) {
    inputField = document.getElementById("userInput");
    text = input.value;
    try {
        cmd = text.split(" ")[0];
        cmdValue = text.split(" ")[1];
    } catch (error) {
        cmd = text;
        cmdValue = "no value given";
    }

    if (event.key === 'Enter') {
        AddToBuffer(text);
        switch (cmd) {
            case "clear":
            case "cls":
                ClearConsole();
                break;
            case "alert":
                alert(cmdValue);
                break;
            case "prompt":
                SetPrompt(cmdValue);
                break;
            case "help":
                HelpFunction();
                break;
            case "disconnect":
                ws.close();
                break;
            case "connect":
                connect();
                break;
            default:
                sendMessage(text);
                break;
        }
        cmdHistory.push(text);
        ClearInput();
    }

}

Init();
AddToBuffer("Welcome to the webconsole!<br>")