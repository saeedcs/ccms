<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:message code="pages.title" text="default" var="title"/>
<jsp:include page="common/header.jsp">
    <jsp:param name="titleAppend" value="#{title}"  />
</jsp:include>
<jsp:include page="common/headernav.jsp" />
<jsp:include page="common/categories.jsp" />
<div class="content">
    <div class="row">
        <div class="col-md-10">
            <div class="google-add-horizontal">
                <div class="add">
                    <jsp:include page="common/horizontal-ad.jsp" />
                </div>
            </div>

            <div id="response"></div>
            <form action="/upload/file-upload" method="post" enctype="multipart/form-data">
                <label for="file">Upload File</label>
                <input type="file" name="file" id="file">
                <input type="button" value="Upload Image" id="upload" onclick="upload1.doUpload();">
            </form>

        </div>

        <div class="col-md-2 google-add-vertical">
            <div class="add">
                <jsp:include page="common/vertical-ad.jsp" />
            </div>
        </div>
        <div class="form-action">
            <div class="item">
                <buton type="button" class="btn btn-primary" onclick="page.jumpToCreate();">Create</buton>
            </div>

        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp" />
<script>


    $( document ).ready(function() {
        //page has been loaded. do something

    });

    var upload1 = {
        doUpload : function() {
            commonFuns.displayResponse("");
            let fd = new FormData();
            let files = $('#file')[0].files[0];
            fd.append('file', files);

            let params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.UPLOAD + appRoutes.FILE_UPLOAD;
            params['data'] = fd;
            params['beforeSendCallbackFunction'] = upload1.beforeAjax;
            params['successCallbackFunction'] = upload1.doneUpload;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            params['contentType'] = false;
            params['contentType'] = false;
            //doAjax(params);
            $.ajax({
                url: appRoutes.UPLOAD + appRoutes.FILE_UPLOAD,
                type: appObjects.REQUEST_TYPE.post,
                data: fd,
                contentType: false,
                processData: false,
                beforeSend: function(jqXHR, settings) {
                    upload1.beforeAjax();
                },
                success: function(response){
                    console.log(response);
                    if(response != 0){
                        upload1.doneUpload(response);
                        /*$("#img").attr("src",response);
                        $(".preview img").show();*/
                    }
                },
            });
        },
        doneUpload: function (data) {
            commonFuns.displayResponse(appStrings.FILE_UPLOADED);
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