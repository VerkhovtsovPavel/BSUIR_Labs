function statictic(verbose) {
    var timer = times.sum();
    var lines = clickDrag.filter(function(a){return a==false;}).length
    var min_x = clickX.min();
    var min_y = clickY.min();
    var max_x = clickX.max();
    var max_y = clickY.max();
    var horizontalLength = max_y - min_y;
    var verticalLength = max_x - min_x;
    var square = horizontalLength * verticalLength;
    var dists = distances(clickX, clickY, clickDrag);
    var totalLength = dists.sum();
    var velocities = dists.map(function(currentValue, index){ return currentValue / times[index];}).filter(function(x){return x != 0 && !isNaN(x);});
    var maxVelocity = velocities.max();
    var minVelocity = velocities.min();
    var durationX = getDuration(clickX, times);
    var durationY = getDuration(clickY, times);
    if(verbose){
        console.log(timer , lines,  square, horizontalLength, verticalLength, totalLength, maxVelocity, minVelocity, durationX, durationY);
    }
    updateChart(timer , lines, square, horizontalLength, verticalLength, totalLength, maxVelocity, minVelocity, durationX, durationY);
}

function distances(clickX, clickY, clickDrag){
    var dist = new Array();
    for (var i = 0; i < clickX.length; i++) {
        if (clickDrag[i]) {
            var deltaX = clickX[i-1] - clickX[i];
            var deltaY = clickY[i-1] - clickY[i];
            var distance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
            dist.push(distance);
        }
        else{
         dist.push(0)     
     }
 }
 return dist;
}

function getDuration(coordinates, times){
    var total = 0;
    for (var i = 1; i < coordinates.length - 1; i++) {
        if (coordinates[i-1] - coordinates[i] != 0) {
            total+=times[i];
        }
    }
    return total;
}