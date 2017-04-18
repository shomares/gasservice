"use strict";
var FavoritosFactory = function ($http) {
    return new FavoritosService($http);
};
var FavoritosService = function () {
    this.oneliminar = new Eventos("oneliminar");
    this.onchangeprederteminado = new Eventos("onchangeprederteminado");
    this.oneror = new Eventos("oneror");



};
FavoritosService.prototype = new ConEventos();

FavoritosService.prototype.getDatabase = function () {
    var vehiculos = null;
    if (localStorage !== undefined) {
        vehiculos = localStorage.getItem('vehiculos');
        if (vehiculos !== null && vehiculos !== undefined)
            vehiculos = JSON.parse(vehiculos);
        else
            vehiculos = new Array();
    }
    return vehiculos;
};
FavoritosService.prototype.save = function (array) {
    if (localStorage !== undefined) {
        localStorage.setItem("vehiculos", JSON.stringify(array));
    }
};
FavoritosService.prototype.load = function () {
    return this.getDatabase();
}
FavoritosService.prototype.eliminar = function (elementos) {
    var root = this;
    var database = root.getDatabase();
    if (database !== null) {
        for (var i = 0; i < elementos.length; i++) {
            database.splice(elementos[i], 1);
        }
    }
    root.save(database);
    root.oneliminar.onEvent('Se ha eliminado corretamente', database);
};
FavoritosService.prototype.changePrederteminado = function (index, elemento) {
    var root = this;
    var error = false;
    var database = root.getDatabase();
    if (database !== null) {

        if (elemento.predefinido)
        {
            for (var i = 0; i < database.length; i++) {
                if (database[i].predefinido) {
                    root.oneror.onEvent("No se puede haber mas de un predefinido");
                    elemento.predefinido = false;
                    error = true;
                }
            }
        }
        database[index] = elemento;
    }
    root.save(database);
    if (!error)
        root.onchangeprederteminado.onEvent('Se ha establecido correctamente', database);
};

