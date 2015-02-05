'use strict';

angular.module('cpFacturasApp', ['ngRoute', 'ngResource', 'ngCookies', 'pascalprecht.translate', 'ui.bootstrap']).config(['$routeProvider', '$translateProvider', '$locationProvider', '$httpProvider', function ($routeProvider, $translateProvider, $locationProvider, $httpProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'js/app/main/main.tpl.html',
            controller: 'mainController',
            access: {
                allowAnonymous: false
            }
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
            controller: 'invoicesController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/invoices/create', {
            templateUrl: 'js/app/invoices/list.tpl.html',
            controller: 'invoicesController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/invoices/update/:itemId', {
            templateUrl: 'js/app/invoices/list.tpl.html',
            controller: 'invoicesController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/invoices/report/last/month', {
            templateUrl: 'js/app/invoices/list.tpl.html',
            controller: 'invoicesController',
            access: {
                allowAnonymous: false
            }
        });

    // Cutomers module
    $routeProvider
        .when('/customers', {
            templateUrl: 'js/app/customers/list.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/customers/create', {
            templateUrl: 'js/app/customers/create.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/customers/update/:itemId', {
            templateUrl: 'js/app/customers/update.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/companies', {
            templateUrl: 'js/app/customers/companies/list.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/companies/create', {
            templateUrl: 'js/app/companies/create.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/companies/update/:itemId', {
            templateUrl: 'js/app/companies/create.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/vehicles', {
            templateUrl: 'js/app/vehicles/list.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/vehicles/create', {
            templateUrl: 'js/app/vehicles/create.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        })
        .when('/vehicles/update/:itemId', {
            templateUrl: 'js/app/vehicles/update.tpl.html',
            controller: 'customersController',
            access: {
                allowAnonymous: false
            }
        });

    $translateProvider.useStaticFilesLoader({
      prefix: 'js/app/lang/locale-',
      suffix: '.json'
    });

    $translateProvider.preferredLanguage('es');

    /*
    * Register error interceptor
    */
    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
        return {
            'responseError': function(rejection) {
                var status = rejection.status;
                var config = rejection.config;
                var method = config.method;
                var url = config.url;
      
                if (status == 401) {
                    $location.path( "/login" );
                } else {
                    $rootScope.$broadcast('errorMessage', "ERROR!! Show me in bootstrap alert");
                }
              
                return $q.reject(rejection);
            }
        };
    });

    /*
    * Register auth token interceptor.
    */
    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
        return {
            'request': function(config) {
                var isRestCall = config.url.indexOf('/api/') != -1;
                if (isRestCall && angular.isDefined($rootScope.authToken)) {
                    config.headers['X-Auth-Token'] = $rootScope.authToken;
                }
                return config || $q.when(config);
            }
        };
    });
}]);