    var text = document.getElementById('text');
    var output = document.getElementById('output');
    var dialog = document.getElementById('dialog');
    var loginReg  = document.getElementById('loginReg');
    var currentRoomTitle = document.getElementById('CurrentRoomName');
    var addRoomDiv = document.getElementById('addRoom');
    var searchDiv = document.getElementById('search');
    var setStyleDiv = document.getElementById('setStyle');
    var currentURL = document.URL;
    var webSocket = new WebSocket("ws://"+currentURL.substring(currentURL.indexOf('//'))+"ws");

    var room = "global"

    var fonts = ['Times New Roman', 'Tahoma', 'Arial'] //TODO Increase count of fonts

    webSocket.onopen = function() {
      display("Connection opened...");
      currentRoomTitle.innerHTML=room;
    };

    webSocket.onclose = function() {display("Connection closed...")};

    webSocket.onmessage = function(message) {
      var msg = JSON.parse(message.data);
      if(msg.result == undefined)
        display(msg.text);

      switch(msg.method) {
      case "roomList":
        builtRoomList(msg.result)
        break
      case "login":
      case "registration":
         if(msg.result=="Success"){
            loginReg.style.visibility='hidden'
            dialog.style.visibility='visible'
            document.getElementsByClassName('left-sidebar')[0].style.visibility='visible';
            document.getElementsByClassName('right-sidebar')[0].style.visibility='visible';
            webSocket.send(JSON.stringify({method:"roomList"}));
            webSocket.send(JSON.stringify({method:"roomEnter", room:room}));
         }else{
            alert(msg.result)
         }
         break
      case "roomStyle":
        addStyle(msg.result);
        break
      case "roomEnter":
        var messages = msg.result
        for (var i = 0; i < messages.length; i++) {
             display(messages[i]);
        }
        break
      case "newRoom":
        var list = document.getElementById('rlist');
        var listItem = document.createElement("li")
        listItem.id = msg.result
        listItem.onclick = function(e) {
             webSocket.send(JSON.stringify({method:"roomLeave", room:room}));
             webSocket.send(JSON.stringify({method:"roomEnter", room:e.target.id}));
             room = e.target.id;
             currentRoomTitle.innerHTML = room;
             while (output.firstChild) {
                  output.removeChild(output.firstChild);
             }
        }
        listItem.appendChild(document.createTextNode(msg.result));
        list.appendChild(listItem);
      }

      text.value='';
    };

    function sendNewMessage(){
      webSocket.send(JSON.stringify({method:"message", text:text.value, room:room}));
    }

    function builtRoomList(room_list){
      var list = document.getElementById('rlist');
      while (list.firstChild) {
          list.removeChild(list.firstChild);
      }
      for (var i = 0; i < room_list.length; i++) {
        var listItem = document.createElement("li")
        listItem.id = room_list[i]
        listItem.onclick = function(e) {
          webSocket.send(JSON.stringify({method:"roomLeave", room:room}));
          webSocket.send(JSON.stringify({method:"roomEnter", room:e.target.id}));
          room = e.target.id;
          currentRoomTitle.innerHTML = room;
          while (output.firstChild) {
              output.removeChild(output.firstChild);
          }
        }
        listItem.appendChild(document.createTextNode(room_list[i]));
        list.appendChild(listItem);
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

    function addStyle(styleSheet) {
        var style = document.createElement('style');
        style.type = 'text/css';
        style.innerHTML = styleSheet;
        document.getElementsByTagName('head')[0].appendChild(style);
    }

    function createRoom(){
        var roomNameField = document.getElementById('roomName');
        var roomName = roomNameField.value;
        var roomParticipantsField = document.getElementById('roomPart');
        var roomPart = roomParticipantsField.value;
        webSocket.send(JSON.stringify({method:"newRoom", roomName:roomName, part:roomPart.split(';')}));
    }

    function saveStyle(){
         var bgrColor = document.getElementById('bgrColor');
         var bgrImage = document.getElementById('bgrImage');
         var msgFont  = document.getElementById('msgFont');

         var

    }


// --- handlers
    function showAddRoom(){
        searchDiv.style.visibility='hidden';
        setStyleDiv.style.visibility='hidden';
        addRoomDiv.style.visibility='visible';
    }

    function showSearch(){
         searchDiv.style.visibility='visible';
         setStyleDiv.style.visibility='hidden';
         addRoomDiv.style.visibility='hidden';
    }

    function showCustomizeRoom(){
         searchDiv.style.visibility='hidden';
         setStyleDiv.style.visibility='visible';
         addRoomDiv.style.visibility='hidden';
    }

    function registerOrLogin(){
          var userName = document.getElementById('userName')
          var password = document.getElementById('password')
          var userChoice = Array.prototype.slice.call(document.getElementsByName('logRegRadio')).filter(function (i) { return i.checked;})[0];
            if(userChoice.value=="reg"){
               webSocket.send(JSON.stringify({method:"registration", userName:userName.value, password:password.value}));
            }
            else{
               webSocket.send(JSON.stringify({method:"login", userName:userName.value, password:password.value}));
            }
        }