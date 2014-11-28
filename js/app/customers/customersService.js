'use strict';

var IN_MEMORY_SEQ = 1;

var IN_MEMORY_DB = [
    {"id": IN_MEMORY_SEQ++, "code": "Code 1", "firstName": "Nombre 1", "lastName": "Apellidos 1", "phonenumber": "", "email": "prueba1@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 2", "firstName": "Nombre 2", "lastName": "Apellidos 2", "phonenumber": "", "email": "prueba2@prueba.com"},
    {"id": IN_MEMORY_SEQ++, "code": "Code 3", "firstName": "Nombre 3", "lastName": "Apellidos 3", "phonenumber": "", "email": "prueba3@prueba.com"}
];

angular.module('cpFacturasApp').factory('customerService', ['$resource', '$filter', function($resource, $filter) {
  return {
    "search": function () {
        return IN_MEMORY_DB
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
  };
}]);