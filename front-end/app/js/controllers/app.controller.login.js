/**
 * Created on 20.01.16.
 */
angular
    .module('app')
    .controller('LoginController', LoginController);

LoginController.$inject = ['$state', 'AuthService'];

function LoginController($state, AuthService) {
    var vm = this;
    var authService = AuthService;

    vm.credentials = {
        username: '',
        password: '',
        rememberMe: false
    };

    vm.authenticate  = authenticate ;

    function authenticate () {
        var user = vm.credentials;
        authService.authorize(user.username, user.password, user.rememberMe).then(
            function(response) {
                $state.go('private');
            }
        );
    }

}