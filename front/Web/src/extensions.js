Array.prototype.max = function() {
    return Math.max.apply(null, this);
};

Array.prototype.last = function() {
    return this[this.length-1];
};

Array.prototype.min = function() {
    return Math.min.apply(null, this);
};

Array.prototype.sum = function() {
    return this.reduce(function(a, b) {return a + b;}, 0);
};