var stompClient = null;

/* 配置是否连接 */
function setConnected(connected) {
    // 判断是否成功连接
    $('#connect').prop('disabled', connected);
    $('#disconnect').prop('disabled', !connected)

    // 控制table是否显示
    if (connected) {
        $("#conversation").show();
    } else {
        $('#conversation').hide();
    }
    // tbody
    $('#greetings').html("");
}

/*连接Socket*/
function connect() {
    // 设置socket
    var socket = new SockJS('/messaging-stomp-websocket');

    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        // 开启连接
        setConnected(true);
        console.log("Connected: " + frame);
        // 订阅话题 topic
        stompClient.subscribe('/topic/greetings', function (greetings) {
            showGreetings(JSON.parse(greetings.body).content);
        })
    })

}

/*终止连接*/
function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log('Disconnected');
}

/*发送姓名*/
function sendName() {
    stompClient.send('/app/hello', {}, JSON.stringify({'name': $('#name').val()}));
}

/*显示内容*/
function showGreetings(message) {
    $('#greetings').append('<tr><td>' + message + '</td></tr>');
}


$(function () {
    $('form').on('submit',function (e) {
        // 阻止提交
        e.preventDefault();
    });

    // 绑定连接事件
    $('#connect').click(function () {
        connect();
    });

    // 绑定断开事件
    $('#disconnect').click(function () {
        disconnect();
    });

    // 绑定发送消息事件
    $('#send').click(function () {
        sendName();
    });
});
