angular.module('starter.controllers', [])

        .controller('MapCtrl', function ($scope) {
            $scope.mapCreated = function (map) {
                $scope.map = map;
            };

            $scope.centerOnMe = function () {
                console.log("Centering");
                if (!$scope.map) {
                    return;
                }
                navigator.geolocation.getCurrentPosition(function (pos) {
                    console.log('Got pos', pos);
                    $scope.map.setCenter(new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude));
                    $scope.loading.hide();
                }, function (error) {
                    alert('Unable to get location: ' + error.message);
                });
            };
        })
        .controller('favoritosCtrl', function ($scope, FavoritosFactory) {
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
                    if ($scope.vehiculos[elemento].selected)
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

        })
        //********************************************************************
        .controller('vehiculosCtrl', function ($scope, VehiculosFactory) {
            $scope.marca = null;

            $scope.displayTabla = {
                display: 'none'
            };

            $scope.displayBusqueda = {
                display: 'block'
            };

            $scope.displayLoading = {
                display: 'block'
            };
            //Eventos-------------------------------------------------------------
            VehiculosFactory.addManejadorEventos("onloadMarcas", function (args) {
                $scope.marcas = args;
                $scope.displayLoading = {
                    display: 'none'
                };
            });
            VehiculosFactory.addManejadorEventos("onloadSubMarcas", function (args) {
                $scope.submarcas = args;
                $scope.displayLoading = {
                    display: 'none'
                };
            });
            VehiculosFactory.addManejadorEventos("onloadAno", function (args) {
                $scope.anos = args;
                $scope.displayLoading = {
                    display: 'none'
                };
            });
            VehiculosFactory.addManejadorEventos("onloadVehiculos", function (args) {
                $scope.vehiculos = args;
                $scope.displayLoading = {
                    display: 'none'
                };
                $scope.displayTabla = {
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
                $scope.displayTabla = {
                    display: 'none'
                };
                alert('Se ha añadido a favoritos');

            });
            VehiculosFactory.addManejadorEventos("oneror", function (args) {
                alert(args);
                $scope.displayLoading = {
                    display: 'none'
                };
            });
            //--------------------------------------------------------------------

            $scope.loadAno = function (submarca) {
                $scope.submarca = submarca;
                $scope.ano = null;
                $scope.enabledAno = true;
                $scope.enabledSubMarca = true;
                $scope.displayLoading = {
                    display: 'block'
                };
                VehiculosFactory.loadAno($scope.marca, $scope.submarca);
            };
            $scope.loadSubMarca = function (marca) {
                $scope.marca = marca;
                $scope.submarca = null;
                $scope.ano = null;
                $scope.anos = new Array();
                $scope.enabledAno = false;
                $scope.enabledSubMarca = true;
                $scope.displayLoading = {
                    display: 'block'
                };
                VehiculosFactory.loadSubMarcas($scope.marca);
            };
            $scope.changeAno = function (ano) {
                $scope.ano = ano;
            };
            $scope.findVehiculo = function () {
                $scope.displayLoading = {
                    display: 'block'
                };
                VehiculosFactory.loadVehiculos($scope.marca, $scope.submarca, $scope.ano);
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
        }).factory('VehiculosFactory', VehiculosFactory)
        .factory('FavoritosFactory', FavoritosFactory);