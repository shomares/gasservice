"use strict";
var VehiculosCtrl = function ($scope, VehiculosFactory) {

    $scope.displayResultado = {
        display: 'none'
    };

    $scope.displayBusqueda = {
        display: 'block'
    };



    $scope.marca = null;
    $scope.loading = {
        display: 'block'
    };
    //Eventos-------------------------------------------------------------
    VehiculosFactory.addManejadorEventos("onloadMarcas", function (args) {
        $scope.marcas = args;
        $scope.loading = {
            display: 'none'
        };

    });
    VehiculosFactory.addManejadorEventos("onloadSubMarcas", function (args) {
        $scope.submarcas = args;
        $scope.loading = {
            display: 'none'
        };
    });
    VehiculosFactory.addManejadorEventos("onloadAno", function (args) {
        $scope.anos = args;
        $scope.loading = {
            display: 'none'
        };
    });

    VehiculosFactory.addManejadorEventos("onloadVehiculos", function (args) {
        $scope.vehiculos = args;
        $scope.loading = {
            display: 'none'
        };
        $scope.displayResultado = {
            display: 'block'
        };
        $scope.displayBusqueda = {
            display: 'none'
        };


    });
    //--------------------------------------------------------------------

    $scope.loadAno = function (submarca) {
        $scope.submarca = submarca;
        $scope.loading = {
            display: 'block'
        };
        VehiculosFactory.loadAno($scope.marca, $scope.submarca);
    };
    $scope.loadSubMarca = function (marca) {
        $scope.marca = marca;
        $scope.loading = {
            display: 'block'
        };
        VehiculosFactory.loadSubMarcas($scope.marca);
    };
    $scope.changeAno = function (ano) {
        $scope.ano = ano;
    };

    $scope.findVehiculo = function () {
        $scope.loading = {
            display: 'block'
        };
        VehiculosFactory.loadVehiculos($scope.marca, $scope.submarca, $scope.ano);
    };

    $scope.regresar = function () {
        $scope.displayResultado = {
            display: 'none'
        };

        $scope.displayBusqueda = {
            display: 'block'
        };


    };

    VehiculosFactory.loadMarcas();
};
VehiculosCtrl.$inject = ['$scope', 'VehiculosFactory'];
root.factory('VehiculosFactory', VehiculosFactory);
root.controller('VehiculosCtrl', VehiculosCtrl);


      