var ws = new WebSocket("ws://localhost:8084/wsTesting/cmd");
var messages = document.getElementById("messages");

ws.onopen = function (message) {
    AddToBuffer("Server Connected... \n");
};

ws.onclose = function (message) {
    processClose(message);
    wa.send("client disconnected...");
    AddToBuffer("ServerDisconnect...");
};

ws.onmessage = function (message) {
    AddToBuffer(message.data)
};

function sendMessage(message) {
    ws.send(message);
    AddToBuffer("Send to server -> " + message);
}