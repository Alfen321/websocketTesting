var loc = window.location, new_uri;
if (loc.protocol === "https:") {
    new_uri = "wss:";
} else {
    new_uri = "ws:";
}
new_uri += "//" + loc.host;
new_uri += loc.pathname + "/cmd";

var ws = new WebSocket(new_uri);

function connect() {
    ws = new WebSocket(new_uri);
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
