    var text = document.getElementById('text');
    var output = document.getElementById('output');
    var websocket = new WebSocket("ws://localhost:8080/ws");

    websocket.onopen = function() {display("Connection opened...")};
    websocket.onclose = function() {display("Connection closed...")};

    websocket.onmessage = function(message) {
      var msg = JSON.parse(message.data);
      if(msg.result == undefined)
        display(msg.text);
      text.value='';
    };

    function send(){
      websocket.send(JSON.stringify({method:"message", text:text.value, room:"global"}));
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