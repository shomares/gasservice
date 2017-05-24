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
    this.onloadVehiculos = new Eventos("onloadVehiculos");
    this.onerror = new Eventos("oneror");
    this.config = Setting.getInstance();
    this.onsave = new Eventos("onsave");
};
VehiculosService.prototype = new ConEventos();
VehiculosService.prototype.loadMarcas = function () {
    var root = this;
    root.$http.get(root.config.url + '/Catalogos/Marcas').then(function (args) {
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
    root.$http.get(root.config.url + '/Catalogos/AnoModelo?marca=' + marca + '&subModelo=' + subModelo).then(function (args) {
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
    root.$http.get(root.config.url + '/Catalogos/SubModelos?marca=' + marca).then(function (args) {
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
    root.$http.get(root.config.url + '/Catalogos/Vehiculos?marca=' + marca + '&subModelo=' + subModelo + '&ano=' + ano).then(function (args) {
        if (args.status === 200)
            root.onloadVehiculos.onEvent(args.data);
        else
            root.onerror.onEvent('El servicio no esta disponible');
    }, function () {
        root.onerror.onEvent('El servicio no esta disponible');
    });

};

VehiculosService.prototype.save = function (vehiculosA) {
    var root = this;
    var error = false;
    //Si funciona localStorage
    if (localStorage !== undefined) {
        var vehiculos = localStorage.getItem('vehiculos');
        if (vehiculos !== null && vehiculos !== undefined)
            vehiculos = JSON.parse(vehiculos);
        else
            vehiculos = new Array();
        for (var item in vehiculosA) {
            for (var jitem in vehiculos) {
                if (vehiculosA[item].nombre === vehiculos[jitem].nombre)
                {
                    root.onerror.onEvent('Ya se agrego a favoritos');
                    error = true;
                    break;
                }
            }
            if (!error){
                vehiculos.push(vehiculosA[item]);
            }else
                break;
        }
        if (!error) {
            localStorage.setItem("vehiculos", JSON.stringify(vehiculos));
            root.onsave.onEvent();
        }
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