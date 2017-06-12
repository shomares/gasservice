var resultadoCtrl = function ($scope, data, ResultadoFactory) {
    var legs = data[0].legs;
    var sumatotal= {duracion:null, distancia: null};
    
    for(var j=0; j<legs.length; j++){
        sumatotal.duracion= legs[j].duration.value;
        sumatotal.distancia= legs[j].distance.value;
    }
    
    $scope.result = {
        tiempo: Math.round( sumatotal.duracion / 60 )+' minutos',
        distancia: Math.round10(sumatotal.distancia/1000,-3)+ " km",
        distanciaMetros: sumatotal.distancia,
        tiempoReal: sumatotal.duracion
    };

    $scope.loadAutomovil = function () {

        var auto = null;
        for (var i = 0; i < $scope.favoritos.length; i++)
            if ($scope.automovil === $scope.favoritos[i].nombre)
                auto = $scope.favoritos[i];

        if (auto !== null)
            $scope.result.litrosGasolina = Math.round10( ($scope.result.distanciaMetros/1000)/(auto.rendimientoPorLitro), -3);
    };
    $scope.favoritos = ResultadoFactory.getFavoritos();
};

