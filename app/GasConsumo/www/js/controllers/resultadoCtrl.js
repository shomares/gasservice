var resultadoCtrl = function ($scope, ResultadoFactory) {
    $scope.loadAutomovil = function (automovil) {
        $scope.automovil = automovil;
        $scope.result.litrosGasolina = $scope.automovil.consumoPorKilometro * $scope.result.result.distancia;
    };
    $scope.favoritos = ResultadoFactory.getFavoritos();
    if ($scope.favoritos.length === 0) {
        window.location.href = "#Vehiculos";
    }




};