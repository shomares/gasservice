var BuscadorCtrl = function ($scope, $uibModalInstance) {
    $scope.displayLoading = {display: 'none'};
    var geocoder = new google.maps.Geocoder();
    $scope.response = new Array();

    $scope.buscar = function () {
        var result = new Array();
        $scope.displayLoading = {display: 'block'};
        $scope.response = new Array();
        geocoder.geocode({'address': $scope.direccion}, function (results, status) {
            if (status === 'OK') {

                for (var i = 0; i < results.length; i++)
                    result.push({formatted_address: results[i].formatted_address, location: results[i].geometry.location});
                $scope.response = result;
                $scope.displayLoading = {display: 'none'};
                $scope.$apply();

            } else {
                alert('Geocode was not successful for the following reason: ' + status);
            }
        });
    };

    $scope.send = function (dir) {
        $uibModalInstance.close(dir);
    };





};