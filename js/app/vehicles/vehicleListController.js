(function() {
  'use strict';

  function VehicleListController ($rootScope, $modal, genericService, vehiclesService) {
        var vm = this;
       
        vm.items = [];
        vm.currentPage = 1;
        vm.totalItems = 0;
        vm.itemsPerPage = 10;
        vm.sortField = 'firstName';
        vm.sortDirection = 'ASC';
        
        vm.onConfirmDeleteClick = function(item){
            
            var modalInstance = $modal.open({
              templateUrl: 'js/app/main/confirmation.tpl.html',
              controller: 'confirmController',
              size: 'md',
              resolve: {
                title: function(){return "messages.confirmation.title";},
                body: function () {return "vehicles.messages.delete.confirmation";}
              }
            });
    
            modalInstance.result.then(function () {
              vm.onDelete(item);
            }, function () {
              // Empty
            });
        };
    
        vm.onDelete = function (item) {
            vehiclesService.delete({"id":item.id}, function () {
        		genericService.translate('vehicles.messages.delete.success', function (text) {
            		$rootScope.$broadcast('successMessage', text);
            	});
            	vm.onSearch();
            });
        };
    
        vm.onSearch = function () {
            vehiclesService.search({'page': vm.currentPage - 1, 'pageSize': vm.itemsPerPage, 'sortField': vm.sortField, 'sortDirection': vm.sortDirection}, function (result) {
                vm.items = result.list;
                vm.totalItems = result.total;
            });
            
        };
    
        vm.onPageChanged = function () {
            vm.onSearch();
        };
        
        // Call init methods
        vm.onSearch();
       
    }
  
    angular.module('cpFacturasApp').controller('vehicleListController', ['$rootScope', '$modal', 'genericService', 'vehiclesService', VehicleListController]);

}());