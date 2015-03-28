'use strict';

angular.module('cpFacturasApp').factory('vehiclesService', ['$resource', 'myConfig', function($resource, myConfig) {

    return $resource(myConfig.apiUrl + '/vehicles/:id', {key: '@id'}, {
        search : {method: 'GET'},
        update: {method: 'PUT'}
    });

}]);

angular.module('cpFacturasApp').factory('vehicleBrandsService', ['$resource', 'myConfig', function($resource, myConfig) {

    return $resource(myConfig.apiUrl + '/vehicles/brands/:id', {key: '@id'}, {
        search : {method: 'GET'},
        update: {method: 'PUT'}
    });

}]);

angular.module('cpFacturasApp').factory('vehicleModelsService', ['$resource', 'myConfig', function($resource, myConfig) {

    return $resource(myConfig.apiUrl + '/vehicles/models/:id', {key: '@id'}, {
        search : {method: 'GET'},
        update: {method: 'PUT'}
    });

}]);