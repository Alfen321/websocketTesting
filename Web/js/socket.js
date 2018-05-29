var ws = new WebSocket('ws://localhost:8084/wsTesting/cmd');

ws.onopen = function (message) { processOpen(message); };
ws.onclose = function (message) { processClose(message); };
ws.onmessage = function (message) { processMessage(message); };

function processOpen(message) {
}

function processClose(message) {
    ws.send('client disconnected...');
}

function sendMessage(message) {
    console.log(message);
    ws.send(message);
}

function processMessage(message) {
    cmdHistory.push(message);
}
