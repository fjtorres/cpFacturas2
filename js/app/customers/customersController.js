(function() {
  'use strict';

  function CustomersController ($rootScope, $routeParams, $modal, genericService, customerService) {
	    expandTable("#btnExpanCustomerList", "#customer-list");

	    var vm = this;
	    vm.entity = {'id': -1,'type': 'PERSON', 'contactData': {}};
	    
	    vm.items = [];
	    vm.isUpdate = false;
	    vm.currentPage = 1;
	    vm.totalItems = 0;
	    vm.itemsPerPage = 10;
	    vm.sortField = 'firstName';
	    vm.sortDirection = 'ASC';

	    if ($routeParams.itemId != undefined) {
	        vm.model = customerService.resource.get({'key': $routeParams.itemId});
	        vm.isUpdate = true;
	    }

	    vm.onSaveSubmit = function () {
	        if (vm.isUpdate) {
	            customerService.resource.update({}, vm.entity, function(){
	            	genericService.translate('customers.messages.update.success', function (text) {
	            		$rootScope.$broadcast('successMessage', text);
	            	});
	                $rootScope.redirectTo("/customers");
	            });
	        } else {
	            customerService.resource.save({}, vm.entity, function(){
	            	genericService.translate('customers.messages.create.success', function (text) {
	            		$rootScope.$broadcast('successMessage', text);
	            	});
	                $rootScope.redirectTo("/customers");
	            });
	        }
	    };

	    vm.onConfirmDeleteClick = function(item){
	        
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
	          vm.onDelete(item);
	        }, function () {
	          // Empty
	        });
	    };

	    vm.onDelete = function (item) {
	        customerService.resource.delete({"id":item.id}, function () {
	        	genericService.translate('customers.messages.delete.success', function (text) {
            		$rootScope.$broadcast('successMessage', text);
            	});
	        	vm.onSearch();
	        });
	    };

	    vm.onSearch = function () {
	        customerService.resource.search({'page': vm.currentPage - 1, 'pageSize': vm.itemsPerPage, 'sortField': vm.sortField, 'sortDirection': vm.sortDirection}, function (result) {
	            vm.items = result.list;
	            vm.totalItems = result.total;
	        });
	        
	    };

	    vm.onPageChanged = function () {
	        vm.onSearch();
	    };

	    vm.onSearch();
	}
  
angular.module('cpFacturasApp').controller('customersController', ['$rootScope', '$routeParams', '$modal', 'genericService', 'customerService', CustomersController]);


}());