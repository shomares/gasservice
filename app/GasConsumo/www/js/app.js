// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
var root = angular.module('starter', ['ionic', 'ngRoute', 'ui.bootstrap']);
root.run(function ($ionicPlatform) {
    $ionicPlatform.ready(function () {
        // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
        // for form inputs)
        if (window.cordova && window.cordova.plugins.Keyboard) {
            cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
            cordova.plugins.Keyboard.disableScroll(true);

        }

        $ionicPlatform.registerBackButtonAction(function (event) {
            if (true) { // your check here
                
                var r = confirm("¿Esta seguro que desea salir?");
                if(r===true){
                        ionic.Platform.exitApp();
                }
            }
        }, 100);
        if (window.StatusBar) {
            // org.apache.cordova.statusbar required
            StatusBar.backgroundColorByHexString('#26a69a');
            StatusBar.hide();
        }
    });
});
root.config(function ($routeProvider) {
    $routeProvider
            .when("/Mapa", {
                templateUrl: "templates/mapa.html",
                controller: "MapsCtrl"
            })
            .when("/Vehiculos", {
                templateUrl: "templates/vehiculos.html",
                controller: "VehiculosCtrl"
            })
            .when("/Favoritos", {
                templateUrl: "templates/favoritos.html",
                controller: "FavoritosCtrl"
            });

    if (localStorage.getItem('vehiculos') === "" || localStorage.getItem('vehiculos') === null) {
        $routeProvider.when("/", {
            templateUrl: "templates/vehiculos.html",
            controller: "VehiculosCtrl"
        });

    } else {

        var array = JSON.parse(localStorage.getItem('vehiculos'));
        if (array.length === 0) {
            $routeProvider.when("/", {
                templateUrl: "templates/vehiculos.html",
                controller: "VehiculosCtrl"
            });
        } else {
            $routeProvider.when("/", {
                templateUrl: "templates/mapa.html",
                controller: "MapsCtrl"
            });

        }
    }

});