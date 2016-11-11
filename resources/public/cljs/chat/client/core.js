// Compiled by ClojureScript 1.9.293 {}
goog.provide('cljs.chat.client.core');
goog.require('cljs.core');
goog.require('clojure.string');
NodeList.prototype.cljs$core$ISeqable$ = cljs.core.PROTOCOL_SENTINEL;

NodeList.prototype.cljs$core$ISeqable$_seq$arity$1 = (function (array){
var array__$1 = this;
return cljs.core.array_seq.call(null,array__$1,(0));
});
HTMLCollection.prototype.cljs$core$ISeqable$ = cljs.core.PROTOCOL_SENTINEL;

HTMLCollection.prototype.cljs$core$ISeqable$_seq$arity$1 = (function (array){
var array__$1 = this;
return cljs.core.array_seq.call(null,array__$1,(0));
});
cljs.chat.client.core.text = document.getElementById("text");
cljs.chat.client.core.output = document.getElementById("output");
cljs.chat.client.core.dialog = document.getElementById("dialog");
cljs.chat.client.core.loginReg = document.getElementById("loginReg");
cljs.chat.client.core.currentRoomTitle = document.getElementById("CurrentRoomName");
cljs.chat.client.core.addRoomDiv = document.getElementById("addRoom");
cljs.chat.client.core.searchDiv = document.getElementById("search");
cljs.chat.client.core.setStyleDiv = document.getElementById("setStyle");
cljs.chat.client.core.currentURL = document.URL;
cljs.chat.client.core.webSocket = (new WebSocket([cljs.core.str("ws://"),cljs.core.str(cljs.chat.client.core.currentURL.substring(cljs.chat.client.core.currentURL.indexOf("//"))),cljs.core.str("ws")].join('')));
cljs.chat.client.core.room = "global";
cljs.chat.client.core.fonts = new cljs.core.PersistentVector(null, 3, 5, cljs.core.PersistentVector.EMPTY_NODE, ["Times New Roman","Tahoma","Arial"], null);
cljs.chat.client.core.send = (function cljs$chat$client$core$send(clj_map){
return cljs.chat.client.core.webSocket.send(JSON.stringify(cljs.core.clj__GT_js.call(null,clj_map)));
});
cljs.chat.client.core.display = (function cljs$chat$client$core$display(message){
var p = document.createElement("p");
var new_text = document.createTextNode(message);
p.appendChild(new_text);

if(cljs.core.truth_(cljs.chat.client.core.output.firstChild)){
return cljs.chat.client.core.output.insertBefore(p,cljs.chat.client.core.output.firstChild);
} else {
return cljs.chat.client.core.output.appendChild(p);
}
});
cljs.chat.client.core.addStyle = (function cljs$chat$client$core$addStyle(styleSheet){
var style = document.createElement("style");
style.type = "text/css";

style.innerHTML = styleSheet;

return cljs.core.first.call(null,document.getElementsByTagName("head")).appendChild(style);
});
cljs.chat.client.core.builtRoomList = (function cljs$chat$client$core$builtRoomList(room_list){
var list = document.getElementById("rlist");
while(true){
if(cljs.core.truth_(list.firstChild)){
list.removeChild(list.firstChild);

continue;
} else {
}
break;
}

var seq__9779 = cljs.core.seq.call(null,room_list);
var chunk__9780 = null;
var count__9781 = (0);
var i__9782 = (0);
while(true){
if((i__9782 < count__9781)){
var l_room = cljs.core._nth.call(null,chunk__9780,i__9782);

var G__9783 = seq__9779;
var G__9784 = chunk__9780;
var G__9785 = count__9781;
var G__9786 = (i__9782 + (1));
seq__9779 = G__9783;
chunk__9780 = G__9784;
count__9781 = G__9785;
i__9782 = G__9786;
continue;
} else {
var temp__4657__auto__ = cljs.core.seq.call(null,seq__9779);
if(temp__4657__auto__){
var seq__9779__$1 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__9779__$1)){
var c__7357__auto__ = cljs.core.chunk_first.call(null,seq__9779__$1);
var G__9787 = cljs.core.chunk_rest.call(null,seq__9779__$1);
var G__9788 = c__7357__auto__;
var G__9789 = cljs.core.count.call(null,c__7357__auto__);
var G__9790 = (0);
seq__9779 = G__9787;
chunk__9780 = G__9788;
count__9781 = G__9789;
i__9782 = G__9790;
continue;
} else {
var l_room = cljs.core.first.call(null,seq__9779__$1);

var G__9791 = cljs.core.next.call(null,seq__9779__$1);
var G__9792 = null;
var G__9793 = (0);
var G__9794 = (0);
seq__9779 = G__9791;
chunk__9780 = G__9792;
count__9781 = G__9793;
i__9782 = G__9794;
continue;
}
} else {
return null;
}
}
break;
}
});
cljs.chat.client.core.webSocket.onopen = (function (){
cljs.chat.client.core.display.call(null,"Connection opened...");

return cljs.chat.client.core.currentRoomTitle.innerHTML = cljs.chat.client.core.room;
});
cljs.chat.client.core.webSocket.onclose = (function (){
return cljs.chat.client.core.display.call(null,"Connection closed...");
});
cljs.chat.client.core.webSocket.onmessage = (function (message){
var msg = cljs.core.js__GT_clj.call(null,JSON.parse(message.data));
if(cljs.core._EQ_.call(null,msg.call(null,"result"),undefined)){
cljs.chat.client.core.display.call(null,msg.call(null,"text"));
} else {
var G__9795_9800 = msg.call(null,"method");
switch (G__9795_9800) {
case "roomList":
cljs.chat.client.core.builtRoomList.call(null,msg.call(null,"result"));

break;
case "login":
if(cljs.core._EQ_.call(null,msg.call(null,"result"),"Success")){
cljs.chat.client.core.loginReg.style.visibility = "hidden";

cljs.chat.client.core.dialog.style.visibility = "visible";

cljs.core.first.call(null,document.getElementsByClassName("left-sidebar")).style.visibility = "visible";

cljs.core.first.call(null,document.getElementsByClassName("right-sidebar")).style.visibility = "visible";

cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"method","method",55703592),"roomList"], null));

cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"method","method",55703592),"roomEnter",new cljs.core.Keyword(null,"room","room",536484922),cljs.chat.client.core.room], null));
} else {
alert(msg.call(null,"result"));
}

break;
case "registration":
if(cljs.core._EQ_.call(null,msg.call(null,"result"),"Success")){
cljs.chat.client.core.loginReg.style.visibility = "hidden";

cljs.chat.client.core.dialog.style.visibility = "visible";

cljs.core.get.call(null,document.getElementsByClassName("left-sidebar"),(0)).style.visibility = "visible";

cljs.core.get.call(null,document.getElementsByClassName("right-sidebar"),(0)).style.visibility = "visible";

cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 1, [new cljs.core.Keyword(null,"method","method",55703592),"roomList"], null));

cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"method","method",55703592),"roomEnter",new cljs.core.Keyword(null,"room","room",536484922),cljs.chat.client.core.room], null));
} else {
alert(msg.call(null,"result"));
}

break;
case "roomStyle":
cljs.chat.client.core.addStyle.call(null,msg.call(null,"result"));

break;
case "roomEnter":
var messages_9802 = msg.call(null,"result");
var seq__9796_9803 = cljs.core.seq.call(null,messages_9802);
var chunk__9797_9804 = null;
var count__9798_9805 = (0);
var i__9799_9806 = (0);
while(true){
if((i__9799_9806 < count__9798_9805)){
var m_9807 = cljs.core._nth.call(null,chunk__9797_9804,i__9799_9806);
cljs.chat.client.core.display.call(null,m_9807);

var G__9808 = seq__9796_9803;
var G__9809 = chunk__9797_9804;
var G__9810 = count__9798_9805;
var G__9811 = (i__9799_9806 + (1));
seq__9796_9803 = G__9808;
chunk__9797_9804 = G__9809;
count__9798_9805 = G__9810;
i__9799_9806 = G__9811;
continue;
} else {
var temp__4657__auto___9812 = cljs.core.seq.call(null,seq__9796_9803);
if(temp__4657__auto___9812){
var seq__9796_9813__$1 = temp__4657__auto___9812;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__9796_9813__$1)){
var c__7357__auto___9814 = cljs.core.chunk_first.call(null,seq__9796_9813__$1);
var G__9815 = cljs.core.chunk_rest.call(null,seq__9796_9813__$1);
var G__9816 = c__7357__auto___9814;
var G__9817 = cljs.core.count.call(null,c__7357__auto___9814);
var G__9818 = (0);
seq__9796_9803 = G__9815;
chunk__9797_9804 = G__9816;
count__9798_9805 = G__9817;
i__9799_9806 = G__9818;
continue;
} else {
var m_9819 = cljs.core.first.call(null,seq__9796_9813__$1);
cljs.chat.client.core.display.call(null,m_9819);

var G__9820 = cljs.core.next.call(null,seq__9796_9813__$1);
var G__9821 = null;
var G__9822 = (0);
var G__9823 = (0);
seq__9796_9803 = G__9820;
chunk__9797_9804 = G__9821;
count__9798_9805 = G__9822;
i__9799_9806 = G__9823;
continue;
}
} else {
}
}
break;
}

break;
case "newRoom":
var list_9824 = document.getElementById("rlist");
var listItem_9825 = document.createElement("li");
listItem_9825.id = msg.call(null,"result");

listItem_9825.onclick = ((function (list_9824,listItem_9825,G__9795_9800,msg){
return (function (e){
var newRoom = e.target.id;
cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"method","method",55703592),"roomLeave",new cljs.core.Keyword(null,"room","room",536484922),cljs.chat.client.core.room], null));

cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 2, [new cljs.core.Keyword(null,"method","method",55703592),"roomEnter",new cljs.core.Keyword(null,"room","room",536484922),newRoom], null));

cljs.chat.client.core.room = newRoom;

cljs.chat.client.core.currentRoomTitle.innerHTML = cljs.chat.client.core.room;

while(true){
if(cljs.core.truth_(cljs.chat.client.core.output.firstChild)){
cljs.chat.client.core.output.removeChild(cljs.chat.client.core.output.firstChild);

continue;
} else {
return null;
}
break;
}
});})(list_9824,listItem_9825,G__9795_9800,msg))
;

listItem_9825.appendChild(document.createTextNode(msg.call(null,"result")));

list_9824.appendChild(listItem_9825);

break;
default:
msg.call(null,"result");

}
}

return cljs.chat.client.core.text.value = "";
});
cljs.chat.client.core.sendNewMessage = (function cljs$chat$client$core$sendNewMessage(){
return cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"method","method",55703592),"message",new cljs.core.Keyword(null,"text","text",-1790561697),cljs.chat.client.core.text.value,new cljs.core.Keyword(null,"room","room",536484922),cljs.chat.client.core.room], null));
});
cljs.chat.client.core.createRoom = (function cljs$chat$client$core$createRoom(){
var roomNameField = document.getElementById("roomName");
var roomName = roomNameField.value;
var roomParticipantsField = document.getElementById("roomPart");
var roomPart = roomParticipantsField.value;
return cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"method","method",55703592),"newRoom",new cljs.core.Keyword(null,"roomName","roomName",1701900822),roomName,new cljs.core.Keyword(null,"part","part",77757738),clojure.string.split.call(null,roomPart,";")], null));
});
cljs.chat.client.core.saveStyle = (function cljs$chat$client$core$saveStyle(){
var bgrColor = document.getElementById("bgrColor");
var bgrImage = document.getElementById("bgrImage");
var msgFont = document.getElementById("msgFont");
return null;
});
cljs.chat.client.core.showAddRoom = (function cljs$chat$client$core$showAddRoom(){
cljs.chat.client.core.searchDiv.style.visibility = "hidden";

cljs.chat.client.core.setStyleDiv.style.visibility = "hidden";

return cljs.chat.client.core.addRoomDiv.style.visibility = "visible";
});
cljs.chat.client.core.showSearch = (function cljs$chat$client$core$showSearch(){
cljs.chat.client.core.searchDiv.style.visibility = "visible";

cljs.chat.client.core.setStyleDiv.style.visibility = "hidden";

return cljs.chat.client.core.addRoomDiv.style.visibility = "hidden";
});
cljs.chat.client.core.showCustomizeRoom = (function cljs$chat$client$core$showCustomizeRoom(){
cljs.chat.client.core.searchDiv.style.visibility = "hidden";

cljs.chat.client.core.setStyleDiv.style.visibility = "visible";

return cljs.chat.client.core.addRoomDiv.style.visibility = "hidden";
});
cljs.chat.client.core.registerOrLogin = (function cljs$chat$client$core$registerOrLogin(){
var userName = document.getElementById("userName");
var password = document.getElementById("password");
var userChoice = cljs.core.first.call(null,cljs.core.filter.call(null,((function (userName,password){
return (function (i){
return i.checked;
});})(userName,password))
,document.getElementsByName("logRegRadio")));
if(cljs.core._EQ_.call(null,userChoice.value,"reg")){
return cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"method","method",55703592),"registration",new cljs.core.Keyword(null,"userName","userName",1867040765),userName.value,new cljs.core.Keyword(null,"password","password",417022471),password.value], null));
} else {
return cljs.chat.client.core.send.call(null,new cljs.core.PersistentArrayMap(null, 3, [new cljs.core.Keyword(null,"method","method",55703592),"login",new cljs.core.Keyword(null,"userName","userName",1867040765),userName.value,new cljs.core.Keyword(null,"password","password",417022471),password.value], null));
}
});

//# sourceMappingURL=core.js.map