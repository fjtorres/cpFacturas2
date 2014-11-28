'use strict';

angular.module('cpFacturasApp').controller('loginController', ['$scope', '$rootScope', 'authenticationService', LoginController]);

function LoginController ($scope, $rootScope, authenticationService) {

    // Clear scope vars
    $scope.credentials = {};

    $scope.login = function () {

    };

}