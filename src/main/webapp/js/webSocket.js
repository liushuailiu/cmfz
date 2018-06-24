// 获取服务器最新消息类
var webSocket = null;
if(window.WebSocket){
    webSocket = new WebSocket("ws://127.0.0.1:8080/webSocket");
}
webSocket.onopen  = function () {
    console("连接成功");
};
webSocket.onmessage = function (event) {
    alert(JSON.stringify(event.data));
};


