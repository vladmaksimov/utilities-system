angular
    .module('app')
    .config(function ($stateProvider, $urlRouterProvider) {

        init();

        function init() {
            $stateProvider.state('login', loginState());
            $stateProvider.state('registration', registrationState());
            $stateProvider.state('accessDenied', accessDeniedState());

            $stateProvider.state('private', privateState());

            $urlRouterProvider.otherwise('/');
        }

        function loginState() {
            return {
                url: '/',
                templateUrl: 'templates/auth/login.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            }
        }
        
        function registrationState() {
            return {
                url: '/registration',
                templateUrl: 'templates/auth/registration.html',
                controller: 'RegistrationController',
                controllerAs: 'registrationVm'
            }
        }
        
        function accessDeniedState() {
            return {
                url: '/access',
                templateUrl: 'templates/accessDenied.html'
            }
        }

        function privateState() {
            return {
                //abstract: true,
                url: '/main',
                templateUrl: 'templates/hello.html',
                controller: 'MainController',
                controllerAs: 'mainVm',
                resolve: {
                    authorize: ['AuthService', '$q', '$state',
                        function (AuthService, $q, $state) {
                            return $q(function (resolve, reject) {
                                    AuthService.resolve().then(
                                        function (user) {
                                            resolve(user)
                                        }, function () {
                                            $state.go('login');
                                            reject();
                                        }
                                    )
                                }
                            ).then(
                                function (principal) {
                                    return principal;
                                }
                            )
                        }]
                }

            }
        }

    });