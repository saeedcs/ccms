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
            <c:forEach items="${}" var="page">
                <a href="/page/view?id=${page.id}" class="post-list">
                    <div class="description">
                        <h3>${page.pageTitle}</h3>

                        <div class="author-info">
                            <i class="fa fa-user-circle-o"></i>
                            <div class="name">${page.author}</div>
                            <div class="post-info">
                                <span>4 jul 2020</span>
                                <span>1 min read</span>
                            </div>
                        </div>
                    </div>
                </a>
            </c:forEach>

            <c:forEach var="points" items="${appMap}" varStatus="index">
                <div class="row">
                    <div class="col-md-6">
                        <label for="pointMap[${index.index}]-value">${points.key}</label>
                    </div>
                    <div class="col-md-6">
                        <input type="text" id="pointMap[${index.index}]-value" name="pointsValueMap[${points.key}]" value="${points.value}"
                               data-key="${points.key}" style="width:100%"/>
                    </div>
                </div>
            </c:forEach>
            <buton type="button" class="btn btn-primary" onclick="app.submitCreate();">Submit</buton>
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
    $(document).ready(function () {
        console.log('Done loading');
    });

    var app = {
        submitCreate: function() {

            var inputStore = [];

            $("input[name*=pointsValueMap]").each(function(){
                console.log($(this).data('key'));
                console.log($(this).val());
                let obj = {};
                obj['key'] = $(this).data('key');
                obj['value'] = $(this).val();
                inputStore.push(obj);
            });

            console.log(JSON.stringify(inputStore));

            /*let data = {};
            data['keyvalue'] = inputStore;*/

            let params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.APP + '/';
            params['data'] = JSON. stringify(inputStore);
            params['beforeSendCallbackFunction'] = app.beforeAjax;
            params['successCallbackFunction'] = app.doneSubmitting;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            params['contentType'] = 'application/json';
            doAjax(params);

        },
        doneSubmitting: function (response) {
            console.log("Page created " );
            console.log(response);

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