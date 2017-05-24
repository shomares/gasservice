"use strict"; /** Clase evento. * @constructor * @returns {Eventos} * @argument {string} name Nombre del Evento*/

function Eventos(name) {
    this.name = name;
}
Eventos.prototype = new Array();
Eventos.prototype.constructor = Eventos;

Eventos.prototype.addEvent = function (args) {
    if (typeof (args) === 'function') this.push(args);
};
Eventos.prototype.addEventInit = function (args) {
    if (typeof (args) === 'function')
        this.unshift(args);
};
Eventos.prototype.onEvent = function () {
    var args = arguments;
    this.forEach(function (arg) {
        arg.apply(null, args);
    });
}; /** * @constructor * @returns {ConEventos} */
Eventos.prototype.clear= function(){
     var props = Object.getOwnPropertyNames(this);
    for (var i = 0; i < props.length; i++) {
        if ((this[props[i]]) instanceof Eventos) 
            this[props[i]].splice(0,this[props[i]].length );
    }
};

function ConEventos() { }
ConEventos.prototype.addManejadorEventos = function (nombre, funcion) {
    var props = Object.getOwnPropertyNames(this);
    for (var i = 0; i < props.length; i++) {
        if ((this[props[i]]) instanceof Eventos && this[props[i]].name === nombre) 
            this[props[i]].addEvent(funcion);
    }
};
ConEventos.HEADERERRORCONST = "X-PERROR";

ConEventos.prototype.addManejadorEventosInit = function (nombre, funcion) {
    var props = Object.getOwnPropertyNames(this);
    for (var i = 0; i < props.length; i++) {
        if ((this[props[i]]) instanceof Eventos && this[props[i]].name === nombre) this[props[i]].addEventInit(funcion);
    }
};
function getRootWebSitePath() {
    return Settings.getInstance().rootURL;
}
String.prototype.replaceAll = function (search, replacement) {
    var target = this;
    return target.split(search).join(replacement);
};

String.prototype.addslashes = function () {
    return this.replaceAll(/\\/g, '\\\\').
        replaceAll(/\u0008/g, '\\b').
        replaceAll(/\t/g, '\\t').
        replaceAll(/\n/g, '\\n').
        replaceAll(/\f/g, '\\f').
        replaceAll(/\r/g, '\\r').
        replaceAll(/'/g, '\\\'').
        replaceAll(/"/g, '\\"').
        replaceAll("(", "\\(").
        replaceAll(")", "\\)")
    ;
};



