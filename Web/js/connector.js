var ws = new WebSocket("ws://localhost:8084/wsTesting/cmd");

ws.onopen = function (message) {
    Output("Server Connected...");
};

ws.onclose = function (message) {
    wa.send("client disconnected...");
    Output("Server Disconnect...");
};

ws.onmessage = function (message) {
    Output(message.data);
};

function sendMessage(message) {
    ws.send(message.data);
    Output("Send to server -> " + message);
}