String.prototype.endsWith = function(suffix) {
    return this.indexOf(suffix, this.length - suffix.length) !== -1;
};

var doAjax_params_default = {
    'url': null,
    'requestType': "GET",
    'contentType': 'application/x-www-form-urlencoded; charset=UTF-8',
    'dataType': 'json',
    'data': {},
    'headers': undefined,
    'beforeSendCallbackFunction': null,
    'successCallbackFunction': null,
    'completeCallbackFunction': null,
    'errorCallBackFunction': null,
};


function doAjax(doAjax_params) {

    var url = doAjax_params['url'];
    var requestType = doAjax_params['requestType'];
    var contentType = doAjax_params['contentType'];
    var dataType = doAjax_params['dataType'];
    var data = doAjax_params['data'];
    var headers = doAjax_params['headers'];
    var beforeSendCallbackFunction = doAjax_params['beforeSendCallbackFunction'];
    var successCallbackFunction = doAjax_params['successCallbackFunction'];
    var completeCallbackFunction = doAjax_params['completeCallbackFunction'];
    var errorCallBackFunction = doAjax_params['errorCallBackFunction'];

    //make sure that url ends with '/'
    /*if(!url.endsWith("/")){
     url = url + "/";
    }*/

    $.ajax({
        url: url,
        crossDomain: true,
        type: requestType,
        contentType: contentType,
        dataType: dataType,
        headers: headers,
        data: data,
        beforeSend: function(jqXHR, settings) {
            if (typeof beforeSendCallbackFunction === "function") {
                beforeSendCallbackFunction();
            }
        },
        success: function(response, textStatus, jqXHR) {
            if (typeof successCallbackFunction === "function") {
                successCallbackFunction(response);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            if (typeof errorCallBackFunction === "function") {
                errorCallBackFunction(errorThrown);
            }

        },
        complete: function(jqXHR, textStatus) {
            if (typeof completeCallbackFunction === "function") {
                completeCallbackFunction();
            }
        }
    });
}

var validateForms = {
    validateEmail: function (email, fieldId, errormsg) {
        if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
            this.validField(fieldId);
            return (true);
        }
        document.getElementById(fieldId + appObjects.ERROR_CLASS.errClass).innerHTML = errormsg;
        this.invalidField(fieldId, errormsg);
        return (false);
    },
    validatePassword: function (password, fieldId, errormsg) {
        if(password.length >= 6) {
            this.validField(fieldId);
            return (true);
        }
        this.invalidField(fieldId, errormsg);
        return (false);
    },
    validateConfirmPassword: function (password, confirmPassword, fieldId, errormsg) {
        if(password == confirmPassword) {
            this.validField(fieldId);
            return (true);
        }
        this.invalidField(fieldId, errormsg);
        return (false);
    },
    validateUserExists: function(isUserExists, email, fieldId, errormsg) {
        if(isUserExists == false) {
            this.validField(fieldId);
            return (true);
        }
        document.getElementById(fieldId + appObjects.ERROR_CLASS.errClass).innerHTML = appObjects.ERROR_MSG.userExists;
        this.invalidField(fieldId, errormsg);
        return (false);
    },
    validField: function(fieldId) {
        document.getElementById(fieldId).classList.remove(appObjects.ERROR_CLASS.invalid);
        document.getElementById(fieldId + appObjects.ERROR_CLASS.errClass).classList.remove(appObjects.ERROR_CLASS.visible);
        document.getElementById(fieldId + appObjects.ERROR_CLASS.errClass).classList.add(appObjects.ERROR_CLASS.invisible);

    },
    invalidField: function(fieldId, errormsg) {
        document.getElementById(fieldId).classList.add(appObjects.ERROR_CLASS.invalid);
        document.getElementById(fieldId + appObjects.ERROR_CLASS.errClass).classList.remove(appObjects.ERROR_CLASS.invisible);
        document.getElementById(fieldId + appObjects.ERROR_CLASS.errClass).classList.add(appObjects.ERROR_CLASS.visible);
        //document.getElementById(fieldId + appObjects.ERRORCLASS.errClass).append(errormsg);

    }
}
var commonFuns = {
    displayResponse(response) {
        document.getElementById('response').innerHTML = response
    }
}