'use strict';

angular.module('cpFacturasApp').controller('loginController', ['$scope', '$rootScope', 'authenticationService', LoginController]);

function LoginController ($scope, $rootScope, authenticationService) {

    // Clear scope vars
    $scope.credentials = {};
    $scope.rememberMe = false;

    $scope.login = function () {
        authenticationService.save($.param({username: $scope.credentials.username, password: $scope.credentials.password}), function (authenticationResult) {
            console.log(authenticationResult);

            authenticationService.get(function(user){
                $rootScope.user = user;
                $location.path("/");
            });
        });
    };

}