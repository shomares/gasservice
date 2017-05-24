var FavoritosCtrl = function ($scope, FavoritosFactory) {
    if (window.StatusBar) {
        StatusBar.show();
    }
    FavoritosFactory.clear();
    FavoritosFactory.addManejadorEventos("oneliminar", function (args, p) {
        alert(args);
        $scope.vehiculos = p;
        $scope.displayLoading = {
            display: 'none'
        };
    });

    FavoritosFactory.addManejadorEventos("onchangeprederteminado", function (args) {
        alert(args);
        $scope.displayLoading = {
            display: 'none'
        };
    });
    FavoritosFactory.addManejadorEventos("oneror", function (args) {
        alert(args);
        $scope.displayLoading = {
            display: 'none'
        };

    });

    $scope.changePrederteminado = function (elemento) {
        var arreglo = new Array();
        for (var elementoH in $scope.vehiculos) {
            if ($scope.vehiculos[elementoH].nombre === elemento.nombre)
                arreglo.push({index: elementoH, valor: elemento});
        }

        if (arreglo.length === 1)
            FavoritosFactory.changePrederteminado(arreglo[0].index, arreglo[0].valor);
        else
            alert('Solo puede haber un predeterminado');

    };


    $scope.eliminar = function () {
        var i = 0;
        var elementos = new Array();
        for (var elemento in $scope.vehiculos) {
            if ($scope.vehiculos[elemento].selectedE)
                elementos.push(i);
            i++;
        }

        if (elementos.length > 0) {
            $scope.displayLoading = {
                display: 'block'
            };
            FavoritosFactory.eliminar(elementos);
        }

    };
    $scope.actualizar = function () {
        $scope.vehiculos = FavoritosFactory.load();

    };
    $scope.actualizar();

    if (localStorage.getItem('vehiculos') === "" || localStorage.getItem('vehiculos') === null) {
        alert('Seleccione al menos un vehiculo en favoritos');
        window.location.href = "#Vehiculos";
    } else {
        var array = JSON.parse(localStorage.getItem('vehiculos'));
        if (array.length === 0) {
            alert('Seleccione al menos un vehiculo en favoritos');
            window.location.href = "#Vehiculos";
        }
    }
};
FavoritosCtrl.$inject = ['$scope', 'FavoritosFactory'];
root.factory('FavoritosFactory', FavoritosFactory);
root.controller('FavoritosCtrl', FavoritosCtrl);
