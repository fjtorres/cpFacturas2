(function() {
    'use strict';

    function GenericService($rootScope, $location, $translate) {

        this.redirectTo = function(path) {
            $location.path(path);
        };


        this.translate = function(key, callback) {
            $translate(key).then(function(text) {
                callback(text);
            });
        }
        
        return this;
    }

    angular.module('cpFacturasApp').factory('genericService', ['$rootScope', '$location', '$translate', GenericService]);

}());
