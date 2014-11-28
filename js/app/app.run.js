angular.module('cpFacturasApp').constant('app.version', '0.0.1');

angular.module('cpFacturasApp').run(['$rootScope', '$location', '$translate', 'app.version', function($rootScope, $location, $translate, appVersion){
    
    $rootScope.redirectTo = function ( path ) {
        $location.path( path );
    };

    $rootScope.currentLanguage = function () {
        return $translate.use();
    }

    $rootScope.changeLanguage = function (lang) {
        if ($rootScope.currentLanguage() !== lang) {
            $translate.use(lang);
        }
    };

    $translate('app.title').then(function (text) {
        $rootScope.appTitle = text;
    });

    $rootScope.appVersion = appVersion;
}]);