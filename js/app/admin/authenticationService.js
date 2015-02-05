'use strict';

angular.module('cpFacturasApp').factory('authenticationService', function($resource, $http, transformRequestAsFormPost) {

    var service = $resource('http://localhost:8081/server/api/authentication', {}, {
        authenticate: {
            method: 'POST',
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        },
        logout: {
            url: "http://localhost:8081/server/api/authentication/logout",
            method: 'GET'
        }
    });

    return service;
});