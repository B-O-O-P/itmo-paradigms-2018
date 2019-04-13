"use strict";
/*var binaryOperator = function (f) {
    return function (a, b) {
        return function (x, y, z) {
            return f(a(x, y, z), b(x, y, z));
        }
    }
};*/

var BinaryOperator = function () {

    function BinaryOperator(a, b) {
        this.first = a;
        this.second = b;
    }

    BinaryOperator.evaluate = function (x) {
        var a = this.first.evaluate(x);
        var b = this.second.evaluate(x);
        return this.evaluateImpl(a, b);
    }
};


function binFactory(f) {
    var binoperator = function (a, b) {
        BinaryOperator.call(this, a, b);
    };
    binoperator.evaluateImpl = f;
    return binoperator;
}

var Add = binFactory(function (a, b) {
    return a + b;
});

function Const(x) {
    this.value = x;
    this.evaluate = function (x) {
        return this.value;
    }
}

function Variable(x) {
    this.value = x;
    this.evaluate = function (x) {
        return x;
    }
}

var expr = new Add(new Const(5), new Variable("x")); //new Add(new Const(5), new Variable("x"));
println(expr.evaluate(5));

/*var unaryOperator = function (f) {
    return function (a) {
        return function (x, y, z) {
            return f(a(x, y, z));
        }
    }
};

var add = binaryOperator(function (a, b) {
    return a + b;
});

var subtract = binaryOperator(function (a, b) {
    return a - b;
});
var multiply = binaryOperator(function (a, b) {
    return a * b;
});
var divide = binaryOperator(function (a, b) {
    return a / b;
});

var negate = unaryOperator(function (a) {
    return -a;
});

var cube = unaryOperator(function (a) {
    return Math.pow(a, 3);
});
var cuberoot = unaryOperator(function (a) {
    return Math.pow(a, 1 / 3);
});
var cnst = function (a) {
    return function () {
        return a;
    }
};

var variable = function (a) {
    return function (x, y, z) {
        if (a === 'x') return x;
        if (a === 'y') return y;
        if (a === 'z') return z;
    }
};

//var addd = makeOp(function (a, b) {
//return a + b;
//});*/