var prompt = "&lambda;" + "&nbsp;"
var cmdHistory = [];

function Init() {
    FocusOnInput()
    document.document.getElementById("userInput").addEventListener("keyPress", function (event) {
        event.preventDefault();
        if (event.key === "Enter") {
            CommandParser(this.value);
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
    messages = document.getElementById("messages").innerHTML = "";
}

function AddToBuffer(text) {
    //yea its ugly deal with it.
    messages = document.getElementById("messages");
    header = document.getElementById("headerLine").innerHTML;
    messages.innerHTML += "<div class=\"block\">" + header + "<br>" +
        "<span class=\"prompt\">" + prompt + "</span>" + text + "<br>" + "</div>"
}

function Output(text) {
    messages = document.getElementById("messages").lastChild.innerHTML +=
        "<br>" + text + "<br><br>";
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

function CommandParser(input) {
    inputField = document.getElementById("userInput");
    text = input.value;
    cmd = text.split(" ")[0];
    cmdValue = text.split(" ")[1];

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
            default:
                AddToBuffer(text);
                sendMessage(text);
                break;
        }
        cmdHistory.push(text);
        ClearInput();
    }

}