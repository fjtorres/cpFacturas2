(function() {
  'use strict';

  function InvoiceController ($routeParams, genericService, vehiclesService, invoiceService) {
		
		var vm = this;
		vm.isUpdate = false;
		vm.entity = {'id': -1};
	
	    if ($routeParams.itemId != undefined) {
	    	vm.entity = vehiclesService.get({'key': $routeParams.itemId});
	    	vm.isUpdate = true;
	    }
	
	    vm.onSaveSubmit = function () {
	    	
	        if (vm.isUpdate) {
	        	invoiceService.resource.update({}, vm.entity, function(){
	        		genericService.showMessage('invoices.messages.update.success');
	        		genericService.redirectTo("/invoices");
	            });
	        } else {
	        	invoiceService.resource.save({}, vm.entity, function(){
	        		genericService.showMessage('invoices.messages.create.success');
	        		genericService.redirectTo("/invoices");
	            });
	        }
	    };
	   
		vm.getVehicles = function (searchValue) {
			return vehiclesService.autocomplete(searchValue).then(function(response){
				return response.data;
			});
		};
		
	   
	}
  
	angular.module('cpFacturasApp').controller('invoiceController', ['$routeParams', 'genericService', 'vehiclesService', 'invoiceService', InvoiceController]);

}());