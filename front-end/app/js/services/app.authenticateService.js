/**
 * Created on 02.04.2016.
 */
angular
    .module('app')
    .service('AuthService', AuthService);

AuthService.$inject = ['$http', '$q', 'constant'];

function AuthService($http, $q, constant) {

    var authDefer;
    var userHolder;

    return {
        authorize: authorize,
        resolve: resolve,
        logout: logout
    };

    function authorize(username, password, rememberMe) {

        var url;
        var auth = username && password;
        var headers = auth ? {
            authorization: "Basic " + btoa(username + ":" + password)
        } : {};

        if (authDefer == null || auth) {

            authDefer = $q.defer();
            if (!rememberMe) {
                url = constant.API_PATH + constant.USER_URL;
            } else {
                url = constant.API_PATH + constant.USER_URL_REMEMBER_ME;
            }

            $http.get(url, {headers: headers}).then(
                function (response) {
                    userHolder = response.data;
                    authDefer.resolve(response.data)
                }, function (data, errorCode) {
                    userHolder = null;
                    authDefer.reject({response: data, errorCode: errorCode});
                }
            )
        }

        return authDefer.promise;
    }

    function resolve() {
        return checkAuthorization().then(
            function (identity) {
                return identity;
            }
        );
    }

    function logout() {
        return $http.post(constant.API_PATH.concat(constant.LOGOUT)).then(
            function () {
                $state.go('login');
            }
        )
    }

    function checkAuthorization() {
        return authorize();
    }
}
