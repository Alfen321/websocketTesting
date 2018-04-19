var lineMarker = "&lambda;" + "&nbsp;"

function Init() {
    FocusOnInput()
    document.getElementById("userInput").addEventListener("keyUp", function (event) {
        if (event.key === "Enter") {
            CommandParser(this.value);
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
        "<span class=\"lineMarker\">" + lineMarker + "</span>" + text + "<br>" + "</div>"
}

function currentOutput(text){
    oldContent = document.getElementById("oldContent").lastChild.innerHTML +=
    "<br>" + text + "<br><br>";
}


function SetLineMarker(marker) {
    if (marker != "") {
        lineMarker = marker + "&nbsp;"
        currentMarkers = document.getElementsByClassName("lineMarker");
        for (var index = 0; index < currentMarkers.length; index++) {
            currentMarkers[index].innerHTML = lineMarker;

        }
    }

}

function HelpFunction() {
    text = "Commmands:<br>" +
        "cls, clear:<br>" +
        "clear the screen.<br>" +
        "help:<br>"+
        "show this text.<br>"
    currentOutput(text);
}

function CommandParser(input) {
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
                alert(cmdValue);
                break;
            case "marker":
                SetLineMarker(cmdValue);
                break;
            case "help":
                AddToBuffer(text);
                HelpFunction();
                break;
            default:
                AddToBuffer(text)
                break;
        }
        ClearInput();
    }

}