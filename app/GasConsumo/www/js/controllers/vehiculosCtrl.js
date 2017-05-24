"use strict";
var VehiculosCtrl = function ($scope, VehiculosFactory) {
    
    VehiculosFactory.clear();
    if (window.StatusBar) {
        StatusBar.show();
    }
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
    VehiculosFactory.addManejadorEventos("onsave", function () {
        $scope.displayBusqueda = {
            display: 'block'
        };

        $scope.displayLoading = {
            display: 'none'
        };
        $scope.displayResultado = {
            display: 'none'
        };
        alert('Se ha añadido a favoritos');

    });
    VehiculosFactory.addManejadorEventos("oneror", function (args) {
        $scope.displayLoading = {
            display: 'none'
        };
        $scope.displayBusqueda = {
            display: 'block'
        };
        $scope.displayResultado = {
            display: 'none'
        };
        alert(args);

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

    $scope.save = function () {

        var elementos = new Array();
        for (var elemento in $scope.vehiculos) {
            if ($scope.vehiculos[elemento].selected)
                elementos.push($scope.vehiculos[elemento]);
        }

        if (elementos.length > 0) {
            $scope.displayLoading = {
                display: 'block'
            };
            VehiculosFactory.save(elementos);
        }
    };


    VehiculosFactory.loadMarcas();
};
VehiculosCtrl.$inject = ['$scope', 'VehiculosFactory'];
root.factory('VehiculosFactory', VehiculosFactory);
root.controller('VehiculosCtrl', VehiculosCtrl);


      