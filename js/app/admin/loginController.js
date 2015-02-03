'use strict';

angular.module('cpFacturasApp').controller('loginController', ['$scope', '$rootScope', 'authenticationService', LoginController]);

function LoginController ($scope, $rootScope, authenticationService) {

    // Clear scope vars
    $scope.credentials = {};

    $scope.login = function () {
        console.log($scope.credentials);
        if ($scope.credentials.username != null) {
            $rootScope.loggedInUser = $scope.credentials.username;
            $scope.redirectTo("/");
        }
    };

}