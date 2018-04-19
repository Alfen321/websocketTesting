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


function SetLineMarker(marker) {
    document.getElementById("lineMarker").innerHTML = marker;
}


function CommandParser(input) {
    text = input.value;
    oldContent = document.getElementById("oldContent");
    header = document.getElementById("headerLine").innerHTML;
    if (event.key === 'Enter') {
        oldContent.innerHTML += header + "<br>"
            + text + "<br>"
    }

}