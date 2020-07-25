<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:message code="pages.title" text="default" var="title"/>
<jsp:include page="../common/header.jsp">
    <jsp:param name="titleAppend" value="#{title}"  />
</jsp:include>
<jsp:include page="../common/headernav.jsp" />
<jsp:include page="../common/categories.jsp" />
<div class="content">
    <div class="row">
        <div class="col-md-10">
            <div class="google-add-horizontal">
                <div class="add">
                    <img src="https://www.shakeout.org/2008/downloads/ShakeOut_BannerAds_GetReady_728x90_v3.gif" alt="" />
                </div>
            </div>
            <h2 id="register">Register</h2>
            <span id="response"></span>
            <form id="login-form" name="loginform" action="" method="post">
            <div class="form-group">
                <label for="loginEmail">Email</label>
                <input type="text" class="form-control" id="loginEmail" name="loginEmail" placeholder="Email" minlength="3"
                       onblur="auth.checkMemberExist();" onkeyup="auth.checkMemberExist();">
                <span id="loginEmailErr" class="invalid-feedback invisible">Please enter your correct email</span>
            </div>
            <div class="form-group">
                <label for="loginPassword">Password</label>
                <input type="password" class="form-control" id="loginPassword" name="loginPassword" placeholder="Password"
                    onblur="">
                <span id="loginPasswordErr" class="invalid-feedback invisible">Password length should at least be 6</span>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password"
                    onblur="">
                <span id="confirmPasswordErr" class="invalid-feedback invisible">Passwords do not match</span>
            </div>
                <button type="button" class="btn btn-info" onclick="auth.validateForm(document.loginform)? auth.doLogin(): auth.doNothing();">Submit</button>

            </form>
        </div>
        <div class="col-md-2 google-add-vertical">
            <div class="add">
                <img src="https://www.google.com/adsense/static/en/images/wideskyscraper_img.jpg" alt="" />
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />

<script>


    $( document ).ready(function() {
        //page has been loaded. do something

    });

    var auth = {
        checkMemberExist: function () {
            let loginEmail = document.getElementById('loginEmail').value;
            let data = {};
            data['username'] = loginEmail;

            var params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.AUTH + appRoutes.USER_EXISTS;
            params['data'] = data;
            params['beforeSendCallbackFunction'] = auth.beforeAjax;
            params['successCallbackFunction'] = auth.isMemberExists;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            //params['contentType'] = 'application/json';
            doAjax(params);

        },
        isMemberExists : function(data) {
            console.log(data);
            validateForms.validateUserExists(data.exists, document.getElementById('loginEmail').value, 'loginEmail', appObjects.ERROR_MSG.userExists)
        },
        doLogin: function () {
            commonFuns.displayResponse("");
            const queryString = window.location.search;
            const urlParams = new URLSearchParams(queryString);
            let loginEmail = document.getElementById('loginEmail').value;
            let loginPassword = document.getElementById('loginPassword').value;
            let confirmPassword = document.getElementById('confirmPassword').value;
            let code = urlParams.get('code');
            let data = {};
            data['email'] = loginEmail;
            data['password'] = loginPassword;
            data['confirmPassword'] = confirmPassword;
            data['code'] = code;

            var params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.AUTH + appRoutes.REGISTER;
            params['data'] = data;
            params['beforeSendCallbackFunction'] = auth.beforeAjax;
            params['successCallbackFunction'] = auth.doneLogin;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            //params['contentType'] = 'application/json';
            doAjax(params);

        },
        doneLogin: function(data) {
            console.log(data);
            if(data.registered == true) {
                commonFuns.displayResponse(appStrings.REGISTER_SUCCESS);
            } else {
                commonFuns.displayResponse(appStrings.REGISTER_FAIL);
            }
        },
        doNothing: function() {
            return false;
        },
        validateForm: function (form) {
            let checkFlag = false;
            if(validateForms.validateEmail(form.loginEmail.value, 'loginEmail', appObjects.ERROR_MSG.email)) {
                checkFlag = true;
            }
            else {
                checkFlag = false;
            }
            if(validateForms.validatePassword(form.loginPassword.value, 'loginPassword', appObjects.ERROR_MSG.password)) {
                if(checkFlag == true) checkFlag = true;
            }
            else {
                checkFlag = false;
            }
            if(validateForms.validateConfirmPassword(form.loginPassword.value, form.confirmPassword.value, 'confirmPassword',
                appObjects.ERROR_MSG.confirmPassword )) {
                if(checkFlag == true) checkFlag = true;
            }
            else {
                checkFlag = false;
            }
            return checkFlag;
        },
        beforeAjax: function() {
            let header = $("meta[name='_csrf_header']").attr("content");
            let token = $("meta[name='_csrf']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        }

    }



</script>