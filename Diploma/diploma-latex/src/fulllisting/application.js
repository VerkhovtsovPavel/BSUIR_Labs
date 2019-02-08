var item = null
var message = null
var upload = false
function login(){
 var x = new XMLHttpRequest();
    var userName = document.getElementById('login_login').value
    var password = document.getElementById('login_password').value
    x.open("GET", "/login?login="+userName+"&password="+password, true);
    x.onload = function (){
        var message = x.responseText
        alert(message);
        if(message=="Access granted" || message=="Login successful"){
            document.getElementById('login').hidden=true
            document.getElementById('registartion').hidden=true
            document.getElementsByClassName('upload_block')[0].style.visibility="visible"
            document.getElementsByClassName('upload_block')[0].style.display="block"
            if(message=="Login successful"){
                document.getElementById('query_progress').hidden=true
                document.getElementById('queue_info').hidden=true
            }
        }
    }
    x.send(null);
}

function registration(){
    var x = new XMLHttpRequest();
    var userName = document.getElementById('registration_login').value
    var password = document.getElementById('registration_password').value
    var repassword = document.getElementById('registration_repassword').value
    if(password!=repassword){
        alert("Please enter password twice");
    }
    else
    {
        x.open("POST", "/registration", true);
        x.onload = function (){
            alert( x.responseText);
        }
        x.setRequestHeader("Content-type","application/json");
        x.send("{\"userName\": \"" + userName + "\", \"password\": \""+password+"\"}");
    }
}

function switchMode(){
    if(upload){
        document.getElementsByClassName('upload_block')[0].style.visibility="visible"
        document.getElementsByClassName('upload_block')[0].style.display="block"
        document.getElementById('imagesView').style.visibility="hidden"
        document.getElementById('imagesView').style.display="none"
        upload = !upload
        }
    else{
        document.getElementById('imagesView').style.visibility="visible"
        document.getElementById('imagesView').style.display="block"
        document.getElementsByClassName('upload_block')[0].style.visibility="hidden"
        document.getElementsByClassName('upload_block')[0].style.display="none"
        var y = new XMLHttpRequest();
        y.open("GET", "/images", true);
        y.onload = function (){
            message = JSON.parse(y.responseText)
            fileList = message.map(function(item) {return item.imageFormat});
            builtRoomList(fileList);
        }
        y.send(null)
        upload = !upload
    }
}

function changeLang(){
    var inject = document.getElementById('inject_button')
    var personal = document.getElementById('personal_button')
    var query_progress = document.getElementById('query_progress')
    var queue_info = document.getElementById('queue_info')
    var baseDropZone = document.getElementById('baseDropZone')
    var queueLength = document.getElementById('queueLength')
    var theQueue = document.getElementById('theQueue')
    var selectFiles = document.getElementById('selectFiles')
    var registration_signUp = document.getElementById('registration_signUp')
    var registration_repassword = document.getElementById('registration_repassword')
    var registration_password = document.getElementById('registration_password')
    var registration_login = document.getElementById('registration_login')
    var username = document.getElementById('username')
    var signUp = document.getElementById('signUp')
    var login_password = document.getElementById('login_password')
    var login_login = document.getElementById('login_login')
    var login_title = document.getElementById('login_title')
    var table_name = document.getElementById('table_name')
    var table_size = document.getElementById('table_size')
    var table_progress = document.getElementById('table_progress')
    var table_status = document.getElementById('table_status')
    var table_actions = document.getElementById('table_actions')
}


function addRoom (list, roomId) {
  var listItem = document.createElement("li")
  listItem.id = roomId
  listItem.onclick = function(e){
        var inject = document.getElementById('inject_button')
        var personal = document.getElementById('personal_button')
        inject.hidden = true
        personal.hidden = true
        var newRoom = e.target.id
        item = message.filter(function (i){return i.imageFormat==newRoom})[0]
        document.getElementById("ItemPreview").src = "data:image/png;base64,"+_arrayBufferToBase64(item.imageSource)
        if(item.handwriteFeatures){
            inject.hidden = false
         }
        if(item.natureDescription.length==0){
            personal.hidden = false
        }
  }
  listItem.appendChild(document.createTextNode(roomId))
  list.appendChild(listItem)
}

function removeRoom (list, roomId){
  var room = document.getElementById('roomId')
  list.removeChild(room)
}

function builtRoomList(room_list){
  var list = document.getElementById("rlist")
  removeChilds(list)
  room_list.forEach(function(room, i, q){addRoom(list, room)})
  }

function removeChilds (element){
  while (element.firstChild){
    element.removeChild(element.firstChild)
  }
}

function _arrayBufferToBase64( buffer ) {
    var binary = '';
    var bytes = new Uint8Array( buffer );
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode( bytes[ i ] );
    }
    return window.btoa( binary );
}

function injectParams(){
    var y = new XMLHttpRequest();
    y.open("GET", "/imageParams/"+item._id, true);
    y.onload = function (){
    var m = JSON.parse(y.responseText)
    document.getElementById("rlist").appendChild(document.createTextNode(JSON.stringify(m)))
    }
    y.send(null)
}

function personal(){
    var y = new XMLHttpRequest();
    y.open("GET", "/personalParams/"+item._id, true);
    y.onload = function (){
    var m = JSON.parse(y.responseText)
    document.getElementById("rlist").appendChild(document.createTextNode("\nPerson: "+m))
    }
    y.send(null)
}