/**
    * Created on 22.02.2016.
*/
angular
    .module('app')
    .service('PopUpMessageService', PopUpMessageService);

PopUpMessageService.$inject = ['toaster'];

function PopUpMessageService(toaster) {

    return {
        warningMessage: warningMessage,
        errorMessage: errorMessage,
        successMessage: successMessage,
        infoMessage: infoMessage
    };

    function warningMessage(head, message) {
        choiceMessageType('warning', head, message)
    }

    function errorMessage(head, message) {
        choiceMessageType('error', head, message);
    }

    function successMessage(head, message) {
        choiceMessageType('success', head, message);
    }

    function infoMessage(head, message) {
        choiceMessageType('info', head, message);
    }

    function choiceMessageType(type, head, message) {
        toaster.pop(type, head, message, 5000);
    }
}