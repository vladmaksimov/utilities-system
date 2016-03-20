angular
    .module('app')
    .config(function ($stateProvider, $urlRouterProvider) {
    /*routerProvider config*/
    $urlRouterProvider.otherwise('/');

    /*routes config*/
    $stateProvider
        .state('main', {
            url: '/',
            templateUrl: "templates/main.html",
            controller: 'MainCtrl',
            controllerAs: 'vm'
        })
});