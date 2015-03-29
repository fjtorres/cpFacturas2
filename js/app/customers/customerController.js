(function() {
  'use strict';

  function CustomersController ($rootScope, $routeParams, $modal, genericService, customerService) {
	    expandTable("#btnExpanCustomerList", "#customer-list");

	    var vm = this;
	    vm.entity = {'id': -1,'type': 'PERSON', 'contactData': {}};
	    vm.isUpdate = false;

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

	}
  
  angular.module('cpFacturasApp').controller('customersController', ['$rootScope', '$routeParams', '$modal', 'genericService', 'customerService', CustomersController]);


}());