var clickX = new Array();
var clickY = new Array();
var clickDrag = new Array();
var times = new Array();

function addClick(x, y, dragging, time) {
	clickX.push(x);
	clickY.push(y);
	clickDrag.push(dragging);
	times.push(time);
	statictic();
}