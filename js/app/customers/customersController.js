'use strict';

angular.module('cpFacturasApp').controller('customersController', ['$scope', '$rootScope', '$routeParams', 'customerService', CustomersController]);

function CustomersController ($scope, $rootScope, $routeParams, customerService) {
    expandTable("#btnExpanCustomerList", "#customer-list");

    // Clear scope vars
    $scope.model = {};
    $scope.items = [];
    $scope.isUpdate = false;

    if ($routeParams.itemId != undefined) {
        $scope.model = customerService.findById($routeParams.itemId);
        $scope.isUpdate = true;
    }

    $scope.save = function () {
        customerService.save($scope.model);
        $scope.redirectTo("/customers");
        
        if ($scope.isUpdate) {
            $rootScope.$broadcast('successMessage', "Save customer");
        } else {
            $rootScope.$broadcast('successMessage', "Create customer");
        }
        
    };

    $scope.delete = function (item) {
        customerService.delete(item);
        $rootScope.$broadcast('successMessage', "Delete customer");
    };

    $scope.search = function () {
        $scope.items = customerService.search();
    };

    $scope.search();
}