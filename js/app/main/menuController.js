'use strict';

angular.module('cpFacturasApp').controller('menuController', ['$scope', '$location', MenuController]);

function MenuController ($scope, $location) {
    $scope.isActive = function (viewLocation) { 
        return viewLocation === $location.path();
    };

    $scope.isActiveGroup = function (viewGroup) { 
        return $location.path().indexOf(viewGroup) > -1 ;
    };
}