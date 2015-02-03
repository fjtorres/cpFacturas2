'use strict';

angular.module('cpFacturasApp', ['ngRoute', 'ngResource', 'pascalprecht.translate', 'ui.bootstrap']).config(['$routeProvider', '$translateProvider', '$locationProvider', function ($routeProvider, $translateProvider, $locationProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'js/app/main/main.tpl.html',
            controller: 'mainController'
        })
        .when('/login', {
            templateUrl: 'js/app/admin/login.tpl.html',
            controller: 'loginController'
        })
        .otherwise({
            redirectTo: '/'
        });
    
    // Invoices module
    $routeProvider
        .when('/invoices', {
            templateUrl: 'js/app/invoices/list.tpl.html',
            controller: 'invoicesController'
        })
        .when('/invoices/create', {
            templateUrl: 'js/app/invoices/list.tpl.html',
            controller: 'invoicesController'
        })
        .when('/invoices/update/:itemId', {
            templateUrl: 'js/app/invoices/list.tpl.html',
            controller: 'invoicesController'
        })
        .when('/invoices/report/last/month', {
            templateUrl: 'js/app/invoices/list.tpl.html',
            controller: 'invoicesController'
        });

    // Cutomers module
    $routeProvider
        .when('/customers', {
            templateUrl: 'js/app/customers/list.tpl.html',
            controller: 'customersController'
        })
        .when('/customers/create', {
            templateUrl: 'js/app/customers/create.tpl.html',
            controller: 'customersController'
        })
        .when('/customers/update/:itemId', {
            templateUrl: 'js/app/customers/update.tpl.html',
            controller: 'customersController'
        })
        .when('/companies', {
            templateUrl: 'js/app/customers/companies/list.tpl.html',
            controller: 'customersController'
        })
        .when('/companies/create', {
            templateUrl: 'js/app/companies/create.tpl.html',
            controller: 'customersController'
        })
        .when('/companies/update/:itemId', {
            templateUrl: 'js/app/companies/create.tpl.html',
            controller: 'customersController'
        })
        .when('/vehicles', {
            templateUrl: 'js/app/vehicles/list.tpl.html',
            controller: 'customersController'
        })
        .when('/vehicles/create', {
            templateUrl: 'js/app/vehicles/create.tpl.html',
            controller: 'customersController'
        })
        .when('/vehicles/update/:itemId', {
            templateUrl: 'js/app/vehicles/update.tpl.html',
            controller: 'customersController'
        });

    $translateProvider.useStaticFilesLoader({
      prefix: 'js/app/lang/locale-',
      suffix: '.json'
    });

    $translateProvider.preferredLanguage('es');
}]);