'use strict';

angular.module('cpFacturasApp').controller('customersController', ['$scope', '$rootScope', '$routeParams', '$modal', '$log', 'customerService', CustomersController]);

function CustomersController ($scope, $rootScope, $routeParams, $modal, $log, customerService) {
    expandTable("#btnExpanCustomerList", "#customer-list");

    // Clear scope vars
    $scope.model = {};
    $scope.items = [];
    $scope.isUpdate = false;
    $scope.currentPage = 1;
    $scope.totalItems = 0;
    $scope.itemsPerPage = 10;

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

    $scope.confirmDelete = function(item){
        
        var modalInstance = $modal.open({
          templateUrl: 'js/app/main/confirmation.tpl.html',
          controller: 'confirmController',
          size: 'md',
          resolve: {
            title: function(){return "messages.confirmation.title";},
            body: function () {return "customers.messages.delete.confirmation";}
          }
        });

        modalInstance.result.then(function () {
          $scope.delete(item);
        }, function () {
          // Empty
        });
    };

    $scope.delete = function (item) {
        customerService.delete(item);
        $rootScope.$broadcast('successMessage', "Delete customer");
    };

    $scope.search = function () {
        var page = customerService.search($scope.currentPage - 1, $scope.itemsPerPage);
        $scope.items = page.data;
        $scope.totalItems = page.total;
    };

    $scope.pageChanged = function () {
        $log.log('Page changed to: ' + $scope.currentPage);
        $scope.search();
    };

    $scope.search();
}