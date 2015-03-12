'use strict';

angular.module('cpFacturasApp').controller('customersController', ['$scope', '$rootScope', '$routeParams', '$modal', '$log', 'customerService', CustomersController]);

function CustomersController ($scope, $rootScope, $routeParams, $modal, $log, customerService) {
    expandTable("#btnExpanCustomerList", "#customer-list");

    // Clear scope vars
    $scope.model = {'id': -1,'type': 'PERSON', 'contactData': {}};
    $scope.items = [];
    $scope.isUpdate = false;
    $scope.currentPage = 1;
    $scope.totalItems = 0;
    $scope.itemsPerPage = 10;
    $scope.sortField = 'firstName';
    $scope.sortDirection = 'ASC';

    if ($routeParams.itemId != undefined) {
        $scope.model = customerService.get({'key': $routeParams.itemId});
        $scope.isUpdate = true;
    }

    $scope.save = function () {
        if ($scope.isUpdate) {
            customerService.update({}, $scope.model, function(){
                $rootScope.$broadcast('successMessage', "Save customer");
                $scope.redirectTo("/customers");
            });
        } else {
            customerService.save({}, $scope.model, function(){
                $rootScope.$broadcast('successMessage', "Create customer");
                $scope.redirectTo("/customers");
            });
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
        customerService.search({'page': $scope.currentPage - 1, 'pageSize': $scope.itemsPerPage, 'sortField': $scope.sortField, 'sortDirection': $scope.sortDirection}, function (result) {
            $scope.items = result.list;
            $scope.totalItems = result.total;
        });
        
    };

    $scope.pageChanged = function () {
        $scope.search();
    };

    $scope.search();
}