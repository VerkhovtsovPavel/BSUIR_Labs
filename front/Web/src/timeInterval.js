var TimeIntervals = function(){
    this.lastTime = new Date().getTime();

    this.currentInterval = function() {
        var newTime = new Date().getTime();
        var interval = newTime - this.lastTime;
        this.lastTime = newTime;
        return interval;
    }
}