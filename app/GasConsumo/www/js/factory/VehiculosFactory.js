"use strict";
var VehiculosFactory = function ($http) {
    return new VehiculosService($http);
};
var VehiculosService = function ($http) {
    this.$http = $http;
    this.onloadMarcas = new Eventos("onloadMarcas");
    this.onloadSubMarcas = new Eventos("onloadSubMarcas");
    this.onloadAno = new Eventos("onloadAno");
    this.onloadPrederteminado = new Eventos("onloadPrederteminado");
    this.onloadVehiculos= new Eventos("onloadVehiculos");
    this.onerror = new Eventos();
};
VehiculosService.prototype = new ConEventos();
VehiculosService.prototype.loadMarcas = function () {
    var root = this;
    root.$http.get('https://gasservice-emejia.rhcloud.com/Catalogos/Marcas').then(function (args) {
        if (args.status === 200)
            root.onloadMarcas.onEvent(args.data);
        else
            root.onerror.onEvent('El servicio no esta disponible');
    }, function () {
        root.onerror.onEvent('El servicio no esta disponible');
    });
};
VehiculosService.prototype.loadAno = function (marca, subModelo) {
    var root = this;
    root.$http.get('https://gasservice-emejia.rhcloud.com/Catalogos/AnoModelo?marca=' + marca + '&subModelo=' + subModelo).then(function (args) {
        if (args.status === 200)
            root.onloadAno.onEvent(args.data);
        else
            root.onerror.onEvent('El servicio no esta disponible');
    }, function () {
        root.onerror.onEvent('El servicio no esta disponible');
    });
};
VehiculosService.prototype.loadSubMarcas = function (marca) {
    var root = this;
    root.$http.get('https://gasservice-emejia.rhcloud.com/Catalogos/SubModelos?marca=' + marca).then(function (args) {
        if (args.status === 200)
            root.onloadSubMarcas.onEvent(args.data);
        else
            root.onerror.onEvent('El servicio no esta disponible');
    }, function () {
        root.onerror.onEvent('El servicio no esta disponible');
    });
};

VehiculosService.prototype.loadVehiculos = function (marca, subModelo, ano) {
    var root = this;
    root.$http.get('http://gasservice-emejia.rhcloud.com/Catalogos/Vehiculos?marca=' + marca+ '&subModelo='  + subModelo + '&ano=' + ano).then(function (args) {
        if (args.status === 200)
            root.onloadVehiculos.onEvent(args.data);
        else
            root.onerror.onEvent('El servicio no esta disponible');
    }, function () {
        root.onerror.onEvent('El servicio no esta disponible');
    });

};

VehiculosService.prototype.save = function (vehiculo) {
    //Si funciona localStorage
    if (localStorage !== undefined) {
        var vehiculos = localStorage.getItem('vehiculos');
        if (vehiculos !== null && vehiculos !== undefined)
            vehiculos = JSON.parse(vehiculos);
        else
            vehiculos = new Array();
        vehiculos.push(vehiculo);
    }
};
VehiculosService.prototype.getVehiculoPredeterminado = function () {
    var root = this;
    //Si funciona localStorage
    if (localStorage !== undefined) {
        var vehiculos = localStorage.getItem('vehiculos');
        if (vehiculos !== null && vehiculos !== undefined) {
            vehiculos = JSON.parse(vehiculos);
            vehiculos.forEach(function (args) {
                if (args.prederteminado === true) {
                    root.onloadPrederteminado.onEvent(args);
                }
            });
        }
    }
};
VehiculosService.$inject = ['$http'];