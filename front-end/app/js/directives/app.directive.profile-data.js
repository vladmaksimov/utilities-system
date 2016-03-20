/**
 * Created on 21.01.16.
 */
angular
    .module('app')
    .directive('profileData', profileData);

function profileData() {

    return {
        link: link,
        require: "ngModel",
        restrict: 'A'
    };

    function link(scope, element, attr, ctrl) {

    }
}
