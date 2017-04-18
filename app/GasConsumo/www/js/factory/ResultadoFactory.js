var ResultadoFactory = function () {
    return new ResultadoService();
};
var ResultadoService = function () {

};
ResultadoService.prototype.getFavoritos = function () {
    var salida = null;
    //Si funciona localStorage
    if (localStorage !== undefined) {
        var vehiculos = localStorage.getItem('vehiculos');
        if (vehiculos !== null && vehiculos !== undefined) {
            vehiculos = JSON.parse(vehiculos);
        }
        salida = vehiculos;
    }
    return salida;

};
ResultadoService.prototype.clean = function () {
    if (localStorage !== undefined) {
        var result = localStorage.getItem('resultados');
        if (result !== undefined)
            localStorage.setItem('resultados', undefined);
    }
};
ResultadoService.prototype.setResultados= function(res){
    if (localStorage !== undefined) {
        localStorage.setItem('resultados',JSON.stringify(res));
    }
};