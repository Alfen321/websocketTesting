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
        "cls, clear:<br>" +
        "clear the screen.<br>" +
        "alert [text]"
    "help:<br>" +
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
        switch (cmd) {
            case "clear":
            case "cls":
                ClearConsole();
                break;
            case "alert":
                AddToBuffer(text);
                alert(cmdValue);
                break;
            case "prompt":
                SetPrompt(cmdValue);
                break;
            case "help":
                AddToBuffer(text);
                HelpFunction();
                break;
            case "disconnect":
                AddToBuffer(text);    
                ws.close();
                
            default:
                AddToBuffer(text);
                sendMessage(text);
                break;
        }
        cmdHistory.push(text);
        ClearInput();
    }

}

Init();