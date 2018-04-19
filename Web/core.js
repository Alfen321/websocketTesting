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

function SetLineMarker(marker) {
    document.getElementById("lineMarker").innerHTML = marker;
}


function CommandParser(input) {
    text = input.value;
    if (event.key === 'Enter') {
        if (text != "clear" && text != "cls") {
            AddToBuffer(text)
            ClearInput();
        } else {
            ClearConsole();
        }
    }

}

function AddToBuffer(text) {
    oldContent = document.getElementById("oldContent");
    header = document.getElementById("headerLine").innerHTML;
    oldContent.innerHTML += header + "<br>"
        + text + "<br>"
}