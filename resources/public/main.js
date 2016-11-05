    var text = document.getElementById('text');
    var output = document.getElementById('output');
    var websocket = new WebSocket("ws://localhost:8080/ws");

    var room = "global"

    websocket.onopen = function() {
      display("Connection opened...");
      websocket.send(JSON.stringify({method:"roomList", text:text.value, room:room}));
      websocket.send(JSON.stringify({method:"roomEnter", room:room}));
    };

    websocket.onclose = function() {display("Connection closed...")};

    websocket.onmessage = function(message) {
      var msg = JSON.parse(message.data);
      if(msg.result == undefined)
        display(msg.text);
      else if (msg.method == "roomList")
        builtRoomList(msg.result)
      text.value='';
    };

    function sendNewMessage(){
      websocket.send(JSON.stringify({method:"message", text:text.value, room:room}));
    }

    function builtRoomList(room_list){
      var list = document.getElementById('rlist');
      for (var i = 0; i < room_list.length; i++) {
        var listItem = document.createElement("li")
        listItem.id = room[i]
        listItem.onclick = function(e) {
          websocket.send(JSON.stringify({method:"roomEnter", room:e.targer.id}))
        }
        var room_name = document.createTextNode(room[i]);
      }
    }

    function display(message){
      var p = document.createElement("p");
      var new_text = document.createTextNode(message);
      p.appendChild(new_text);

      if(output.firstChild)
        output.insertBefore(p, output.firstChild);
      else
        output.appendChild(p)
    }