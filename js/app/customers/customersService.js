'use strict';

var IN_MEMORY_SEQ = 1;

var IN_MEMORY_DB = [
    {"id": IN_MEMORY_SEQ++, "code": "Code 1", "firstName": "Nombre 1", "lastName": "Apellidos 1", "phonenumber": "", "email": "prueba1@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 2", "firstName": "Nombre 2", "lastName": "Apellidos 2", "phonenumber": "", "email": "prueba2@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 3", "firstName": "Nombre 3", "lastName": "Apellidos 3", "phonenumber": "", "email": "prueba3@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 4", "firstName": "Nombre 4", "lastName": "Apellidos 4", "phonenumber": "", "email": "prueba4@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 5", "firstName": "Nombre 5", "lastName": "Apellidos 5", "phonenumber": "", "email": "prueba5@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 6", "firstName": "Nombre 6", "lastName": "Apellidos 6", "phonenumber": "", "email": "prueba6@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 7", "firstName": "Nombre 7", "lastName": "Apellidos 7", "phonenumber": "", "email": "prueba7@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 8", "firstName": "Nombre 8", "lastName": "Apellidos 8", "phonenumber": "", "email": "prueba8@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 9", "firstName": "Nombre 9", "lastName": "Apellidos 9", "phonenumber": "", "email": "prueba9@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 10", "firstName": "Nombre 10", "lastName": "Apellidos 10", "phonenumber": "", "email": "prueba10@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 11", "firstName": "Nombre 11", "lastName": "Apellidos 11", "phonenumber": "", "email": "prueba11@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 12", "firstName": "Nombre 12", "lastName": "Apellidos 12", "phonenumber": "", "email": "prueba12@prueba.com"}
];

angular.module('cpFacturasApp').factory('customerService', ['$resource', '$filter', 'myConfig', function($resource, $filter, myConfig) {
  /*return {
    "search": function (page, elementsPerPage, sortField, sortDirection) {

        
        var firstElement = page * elementsPerPage;
        var lastElement = firstElement + elementsPerPage - 1;
        var page = {
            "data" : IN_MEMORY_DB.slice(firstElement, lastElement),
            "total" : IN_MEMORY_DB.length
        };
        return page;
    },
    "save" : function (model) {
        var exist = $filter('filter')(IN_MEMORY_DB, function(item){return item.id == model.id})[0];
        if (!exist) {
            model.id = IN_MEMORY_SEQ++;
            IN_MEMORY_DB.push(model);
        }
    },
    "delete": function (model) {
        var exist = $filter('filter')(IN_MEMORY_DB, function(item){return item.id == model.id})[0];
        if (exist) {
             var index = IN_MEMORY_DB.indexOf(exist);

            if (index !== -1) {
                IN_MEMORY_DB.splice(index, 1);
            }
        }
    },
    "findById": function (modelId) {
        return $filter('filter')(IN_MEMORY_DB, function(item){return item.id == modelId})[0];
    }
  };*/


    return $resource(myConfig.apiUrl + '/customers/:key', {key: '@key'}, {
        search : {method: 'GET'},
        update: {method: 'PUT'}
    });

}]);