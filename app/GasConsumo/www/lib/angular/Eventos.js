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


function ConEventos() { }
ConEventos.prototype.addManejadorEventos = function (nombre, funcion) {
    var props = Object.getOwnPropertyNames(this);
    for (var i = 0; i < props.length; i++) {
        if ((this[props[i]]) instanceof Eventos && this[props[i]].name === nombre) this[props[i]].addEvent(funcion);
    }
};
ConEventos.HEADERERRORCONST = "X-PERROR";

ConEventos.prototype.clear= function(){
     var props = Object.getOwnPropertyNames(this);
    for (var i = 0; i < props.length; i++) {
        if ((this[props[i]]) instanceof Eventos) 
            this[props[i]].splice(0,this[props[i]].length );
    }
};

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

// Conclusión
(function() {
  /**
   * Ajuste decimal de un número.
   *
   * @param {String}  tipo  El tipo de ajuste.
   * @param {Number}  valor El numero.
   * @param {Integer} exp   El exponente (el logaritmo 10 del ajuste base).
   * @returns {Number} El valor ajustado.
   */
  function decimalAdjust(type, value, exp) {
    // Si el exp no está definido o es cero...
    if (typeof exp === 'undefined' || +exp === 0) {
      return Math[type](value);
    }
    value = +value;
    exp = +exp;
    // Si el valor no es un número o el exp no es un entero...
    if (isNaN(value) || !(typeof exp === 'number' && exp % 1 === 0)) {
      return NaN;
    }
    // Shift
    value = value.toString().split('e');
    value = Math[type](+(value[0] + 'e' + (value[1] ? (+value[1] - exp) : -exp)));
    // Shift back
    value = value.toString().split('e');
    return +(value[0] + 'e' + (value[1] ? (+value[1] + exp) : exp));
  }

  // Decimal round
  if (!Math.round10) {
    Math.round10 = function(value, exp) {
      return decimalAdjust('round', value, exp);
    };
  }
  // Decimal floor
  if (!Math.floor10) {
    Math.floor10 = function(value, exp) {
      return decimalAdjust('floor', value, exp);
    };
  }
  // Decimal ceil
  if (!Math.ceil10) {
    Math.ceil10 = function(value, exp) {
      return decimalAdjust('ceil', value, exp);
    };
  }
})();



