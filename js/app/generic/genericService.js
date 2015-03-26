(function() {
    'use strict';

    function GenericService($rootScope, $location, $translate) {

        this.redirectTo = function(path) {
            $location.path(path);
        };


        this.translate = function(key) {
            $translate(key).then(function(text) {
                return text;
            });
        }
    }

    angular.module('cpFacturasApp').factory('genericService', ['$rootScope', '$location', '$translate', GenericService]);

}());
