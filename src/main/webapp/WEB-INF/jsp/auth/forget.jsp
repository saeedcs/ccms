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
            <h2 id="login">Forget Password</h2>
            <span id="response"></span>
            <form id="login-form" name="loginform" action="" method="post">
                <div class="form-group">
                    <label for="loginEmail">Email</label>
                    <input type="text" class="form-control" id="loginEmail" name="loginEmail" placeholder="Email" minlength="3">
                    <span id="loginEmailErr" class="invalid-feedback invisible">Please enter your correct email</span>
                </div>
                <button type="button" class="btn btn-info" onclick="auth.doLogin();">Submit</button>
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
        doLogin: function () {
            commonFuns.displayResponse("");
            let loginEmail = document.getElementById('loginEmail').value;
            let data = {};
            data['email'] = loginEmail;

            var params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.AUTH + appRoutes.FORGET_PASSWORD;
            params['data'] = data;
            params['beforeSendCallbackFunction'] = auth.beforeAjax;
            params['successCallbackFunction'] = auth.doneLogin;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            //params['contentType'] = 'application/json';
            doAjax(params);

        },
        doneLogin: function(data) {
            console.log(data);
            commonFuns.displayResponse(data.message);

        },
        doNothing: function() {
            return false;
        },
        validateForm: function (form) {
            let checkFlag = false;
            if(validateForms.validateEmail(form.loginEmail.value, 'loginEmail', appObjects.ERROR_MSG.email))
                checkFlag = true;
            else
                checkFlag = false;

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