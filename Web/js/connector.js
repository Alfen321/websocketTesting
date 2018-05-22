var ws = new WebSocket("ws://localhost:8084/wsTesting/cmd");

function connect() {
    ws = new WebSocket("ws://localhost:8084/wsTesting/cmd");
}
ws.onopen = function (message) {
    Output("Server Connected...");
};

ws.onclose = function (message) {
    Output("Server Disconnect...");
};

ws.onmessage = function (message) {
    Output(message.data);
};

function sendMessage(message) {
    ws.send(message);
}