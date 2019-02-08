var context

function initHandlers() {

    var canvas = document.getElementById('canvas');
    context = canvas.getContext("2d");
    var cleanButton = document.getElementById('cleanCanvas');
    var doneButton = document.getElementById('done');
    var timeIntervals;
    var paint;
 
    cleanButton.addEventListener("click", function(e) {
        clean();
    })

    doneButton.addEventListener("click", function(e) {
        statictic(true);
        clean();
    })

    canvas.addEventListener("mousedown", function(e) {
        var mouseX = e.pageX - this.offsetLeft;
        var mouseY = e.pageY - this.offsetTop;
        if (times.length == 0) {
            timeIntervals = new TimeIntervals();
        }
        var time = timeIntervals.currentInterval();
        paint = true;
        addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop, false, time);
        redraw();
    });

    canvas.addEventListener("mousemove", function(e) {
        if (paint) {
            var time = timeIntervals.currentInterval();
            addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop, true, time);
            redraw();
        }
    });

    canvas.addEventListener("mouseup", function(e) {
        paint = false;
    });

    canvas.addEventListener("mouseleave", function(e) {
        paint = false;
    });

}

function redraw() {
    context.clearRect(0, 0, context.canvas.width, context.canvas.height);

    context.strokeStyle = "#df4b26";
    context.lineJoin = "round";
    context.lineWidth = 5;

    for (var i = 0; i < clickX.length; i++) {
        context.beginPath();
        if (clickDrag[i] && i) {
            context.moveTo(clickX[i - 1], clickY[i - 1]);
        } else {
            context.moveTo(clickX[i] - 1, clickY[i]);
        }
        context.lineTo(clickX[i], clickY[i]);
        context.closePath();
        context.stroke();
    }
}

function clean(){
    context.clearRect(0, 0, context.canvas.width, context.canvas.height);
    clickX = new Array();
    clickY = new Array();
    clickDrag = new Array();
    times = new Array();
    statictic();
}