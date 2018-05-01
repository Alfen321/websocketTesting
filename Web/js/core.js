var prompt = "&lambda;" + "&nbsp;"
var cmdHistory = [];

function Init() {
    FocusOnInput()
    document.addEventListener("keyPress", function (event) {
        if (event.key === "Enter") {
            CommandParser(document.getElementById("userInput").value);
        } else if (event.key === 38) {
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
    oldContent = document.getElementById("oldContent").innerHTML = "";
}

function AddToBuffer(text) {
    //yea its ugly deal with it.
    oldContent = document.getElementById("oldContent");
    header = document.getElementById("headerLine").innerHTML;
    oldContent.innerHTML += "<div class=\"block\">" + header + "<br>" +
        "<span class=\"prompt\">" + prompt + "</span>" + text + "<br>" + "</div>"
}

function Output(text){
    oldContent = document.getElementById("oldContent").lastChild.innerHTML +=
    "<br>" + text + "<br><br>";
}


function SetPrompt(marker) {
    if (marker != "") {
        if(marker.length > 1){
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
        "help:<br>"+
        "show this text.<br>"+
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
                AddToBuffer(text)
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
                AddToBuffer(text)
                break;
        }
        cmdHistory.push(text);
        ClearInput();
    } 

}