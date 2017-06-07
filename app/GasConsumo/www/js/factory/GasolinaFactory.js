"use strict";
var GasolinaFactory = function ($http) {
    return new GasolinaService($http);
};
var GasolinaService = function ($http) {
    this.$http = $http;
    this.oneror = new Eventos("oneror");
    this.onCargaGasolineras = new Eventos("onCargaGasolineras");
    this.config= Setting.getInstance();
    this.cache= new Array();

};
GasolinaService.prototype = new ConEventos();


GasolinaService.prototype.loadGasolineras = function (location) {
    var root = this;
    root.$http.get(root.config.url + '/Gasolinera/FindGasolineras?x=' + location.lng() + '&y=' + location.lat()).then(function (args) {
        root.cache= args.data;
        args.data.forEach(function (arg) {
            root.onCargaGasolineras.onEvent(arg);
        });
    });
};
GasolinaService.prototype.getInfo= function(id, callback){
     var root = this;
    root.$http.get(root.config.url + '/Gasolinera/FindPrecio?idGasolinera=' + id ).then(function (args) {
        var gas;
        for(var i=0; i<root.cache.length; i++){
            if(root.cache[i].id===parseInt(id)){
                gas= root.cache[i];
                break;
            }
        }
        callback(gas, args.data);
    });
    
};

GasolinaService.$inject = ['$http'];