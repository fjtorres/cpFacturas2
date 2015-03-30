'use strict';

angular.module('cpFacturasApp').controller('invoicesController', ['$scope', InvoicesController]);

function InvoicesController ($scope) {
    expandTable("#btnExpandInvoiceList", "#invoice-list");
}