'use strict';

angular.module('cpFacturasApp').controller('messagesController', ['$scope', MessagesController]);

function MessagesController ($scope) {
    
    $scope.$on('successMessage', function (event, message) {
        $scope.alerts = [
            {"type": "success", "msg" : message}
        ];
    });

    $scope.$on('errorMessage', function (event, message) {
        $scope.alerts = [
            {"type": "danger", "msg" : message}
        ];
    });

    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };
}