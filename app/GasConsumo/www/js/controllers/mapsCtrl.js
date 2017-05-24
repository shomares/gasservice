var MapsCtrl = function ($scope, $uibModal, ResultadoFactory) {

    var directionsService = new google.maps.DirectionsService();



    if (window.StatusBar) {
        StatusBar.hide();
    }

    $scope.locations = new Array();

    $scope.openBuscador = function () {
        var modal = $uibModal.open({
            keyboard: false,
            animation: true,
            templateUrl: "templates/buscador.html",
            controller: 'BuscadorCtrl',
            resolve: {

            }
        });

        modal.result.then(function (args) {
            var marker = new google.maps.Marker({
                position: args.location,
                map: map
            });
            map.setCenter(args.location);
            map.setZoom(11);
            $scope.locations.push(marker);
        });


    };
    //Tenemos el mapa
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 19.43194, lng: -99.13306},
        scrollwheel: false,
        zoom: 8
    });
    var directionsDisplay = new google.maps.DirectionsRenderer();
    directionsDisplay.setMap(map);



    $scope.localizar = function () {
        var panPoint = null;
        navigator.geolocation.getCurrentPosition(function (position) {
            panPoint = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
            map.setCenter(panPoint);
        }, function () {
            alert('No se ha podido localizar la posición');
            if ($scope.locations.length === 0) {
                panPoint = new google.maps.LatLng(19.43194, -99.13306);
                map.setCenter(panPoint);
            }
        });
    };


    $scope.clearMap = function () {
        for (var i = 0; i < $scope.locations.length; i++) {
            $scope.locations[i].setMap(null);
        }
        $scope.locations = new Array();
        directionsDisplay.setMap(null);

    };
    $scope.calcular = function () {
        directionsDisplay.setMap(map);



        if ($scope.locations.length > 1) {
            var waypoints = new Array();
            if ($scope.locations.length > 2) {
                for (var i = 1; i < $scope.locations.length - 1; i++)
                    waypoints.push({location: $scope.locations[i].position, stopover: true});

            }
            var request = {
                origin: $scope.locations[0].position,
                destination: $scope.locations[$scope.locations.length - 1].position,
                waypoints: waypoints,
                travelMode: 'DRIVING',
                drivingOptions: {
                    departureTime: new Date(Date.now()), // for the time N milliseconds from now.
                    trafficModel: 'optimistic'
                },
                provideRouteAlternatives: true

            };
            directionsService.route(request, function (response, status) {
                if (status === 'OK') {
                    directionsDisplay.setDirections(response);
                    //Mostramos los resultados en un popup
                    var modal = $uibModal.open({
                        keyboard: false,
                        animation: true,
                        templateUrl: "templates/resultados.html",
                        controller: 'ResultadoCtrl',
                        resolve: {
                            data: function () {
                                return response.routes;
                            }
                        }
                    });
                }
            });
        } else
            alert('Seleccione algún punto de partida y final');


    };



    google.maps.event.addListener(map, 'click', function (event) {
        var location = event.latLng;
        var marker = new google.maps.Marker({
            position: location,
            map: map
        });
        $scope.locations.push(marker);
    });

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

MapsCtrl.$inject = ['$scope', '$uibModal', 'ResultadoFactory'];
root.factory('ResultadoFactory', ResultadoFactory);
root.controller('ResultadoCtrl', resultadoCtrl);
root.controller('BuscadorCtrl', BuscadorCtrl);
root.controller('MapsCtrl', MapsCtrl);


