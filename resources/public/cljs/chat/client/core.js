// Compiled by ClojureScript 1.9.293 {}
goog.provide('cljs.chat.client.core');
goog.require('cljs.core');
NodeList.prototype.cljs$core$ISeqable$ = cljs.core.PROTOCOL_SENTINEL;

NodeList.prototype.cljs$core$ISeqable$_seq$arity$1 = (function (array){
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

return cljs.core.get.call(null,document.getElementsByTagName("head"),(0)).appendChild(style);
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

var seq__8188 = cljs.core.seq.call(null,room_list);
var chunk__8189 = null;
var count__8190 = (0);
var i__8191 = (0);
while(true){
if((i__8191 < count__8190)){
var l_room = cljs.core._nth.call(null,chunk__8189,i__8191);

var G__8192 = seq__8188;
var G__8193 = chunk__8189;
var G__8194 = count__8190;
var G__8195 = (i__8191 + (1));
seq__8188 = G__8192;
chunk__8189 = G__8193;
count__8190 = G__8194;
i__8191 = G__8195;
continue;
} else {
var temp__4657__auto__ = cljs.core.seq.call(null,seq__8188);
if(temp__4657__auto__){
var seq__8188__$1 = temp__4657__auto__;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__8188__$1)){
var c__7357__auto__ = cljs.core.chunk_first.call(null,seq__8188__$1);
var G__8196 = cljs.core.chunk_rest.call(null,seq__8188__$1);
var G__8197 = c__7357__auto__;
var G__8198 = cljs.core.count.call(null,c__7357__auto__);
var G__8199 = (0);
seq__8188 = G__8196;
chunk__8189 = G__8197;
count__8190 = G__8198;
i__8191 = G__8199;
continue;
} else {
var l_room = cljs.core.first.call(null,seq__8188__$1);

var G__8200 = cljs.core.next.call(null,seq__8188__$1);
var G__8201 = null;
var G__8202 = (0);
var G__8203 = (0);
seq__8188 = G__8200;
chunk__8189 = G__8201;
count__8190 = G__8202;
i__8191 = G__8203;
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
var G__8204_8209 = msg.call(null,"methods");
switch (G__8204_8209) {
case "roomList":
cljs.chat.client.core.builtRoomList.call(null,msg.call(null,"result"));

break;
case "login":
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
var messages_8211 = msg.call(null,"result");
var seq__8205_8212 = cljs.core.seq.call(null,messages_8211);
var chunk__8206_8213 = null;
var count__8207_8214 = (0);
var i__8208_8215 = (0);
while(true){
if((i__8208_8215 < count__8207_8214)){
var m_8216 = cljs.core._nth.call(null,chunk__8206_8213,i__8208_8215);
cljs.chat.client.core.display.call(null,m_8216);

var G__8217 = seq__8205_8212;
var G__8218 = chunk__8206_8213;
var G__8219 = count__8207_8214;
var G__8220 = (i__8208_8215 + (1));
seq__8205_8212 = G__8217;
chunk__8206_8213 = G__8218;
count__8207_8214 = G__8219;
i__8208_8215 = G__8220;
continue;
} else {
var temp__4657__auto___8221 = cljs.core.seq.call(null,seq__8205_8212);
if(temp__4657__auto___8221){
var seq__8205_8222__$1 = temp__4657__auto___8221;
if(cljs.core.chunked_seq_QMARK_.call(null,seq__8205_8222__$1)){
var c__7357__auto___8223 = cljs.core.chunk_first.call(null,seq__8205_8222__$1);
var G__8224 = cljs.core.chunk_rest.call(null,seq__8205_8222__$1);
var G__8225 = c__7357__auto___8223;
var G__8226 = cljs.core.count.call(null,c__7357__auto___8223);
var G__8227 = (0);
seq__8205_8212 = G__8224;
chunk__8206_8213 = G__8225;
count__8207_8214 = G__8226;
i__8208_8215 = G__8227;
continue;
} else {
var m_8228 = cljs.core.first.call(null,seq__8205_8222__$1);
cljs.chat.client.core.display.call(null,m_8228);

var G__8229 = cljs.core.next.call(null,seq__8205_8222__$1);
var G__8230 = null;
var G__8231 = (0);
var G__8232 = (0);
seq__8205_8212 = G__8229;
chunk__8206_8213 = G__8230;
count__8207_8214 = G__8231;
i__8208_8215 = G__8232;
continue;
}
} else {
}
}
break;
}

break;
case "newRoom":
var list_8233 = document.getElementById("rlist");
var listItem_8234 = document.createElement("li");
listItem_8234.id = msg.call(null,"result");

listItem_8234.onclick = ((function (list_8233,listItem_8234,G__8204_8209,msg){
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
});})(list_8233,listItem_8234,G__8204_8209,msg))
;

listItem_8234.appendChild(document.createTextNode(msg.call(null,"result")));

list_8233.appendChild(listItem_8234);

break;
default:
throw (new Error([cljs.core.str("No matching clause: "),cljs.core.str(msg.call(null,"methods"))].join('')));

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