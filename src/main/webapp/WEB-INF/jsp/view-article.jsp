<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:message code="pages.title" text="default" var="title"/>
<jsp:include page="common/header.jsp">
    <jsp:param name="titleAppend" value="#{title}"  />
</jsp:include>
<jsp:include page="common/headernav.jsp" />
<jsp:include page="common/member-links.jsp" />
<div class="content">
    <div class="row">
        <div class="col-md-10">
            <div class="google-add-horizontal">
                <div class="add">
                    <img src="https://www.shakeout.org/2008/downloads/ShakeOut_BannerAds_GetReady_728x90_v3.gif" alt="" />
                </div>
            </div>
            <div class="description">
                <h3>${article.articleTitle}</h3>

                <div class="author-info">
                    <i class="fa fa-user-circle-o"></i>
                    <div class="name">${article.author}</div>
                    <div class="post-info">
                        <span>4 jul 2020</span>
                        <span>1 min read</span>
                    </div>
                </div>
            </div>
            <div class="form-action">
                <div class="item">
                    <buton type="button" class="btn btn-primary" onclick="article.editArticle(${article.id})">Edit</buton>
                </div>

                <div class="item text-right">
                    <buton type="reset" class="btn btn-light">Cancel</buton>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-2 google-add-vertical">
        <div class="add">
            <img src="https://www.google.com/adsense/static/en/images/wideskyscraper_img.jpg" alt="" />
        </div>
    </div>
    <div class="md-form">
        <i class="fas fa-pencil-alt prefix"></i>
        <label >Leave a Comment</label>
        <textarea id="comment" name="comment" class="md-textarea form-control" rows="3" placeholder="Write your comment here..."></textarea>
    </div>
    <div class="form-action">
        <div class="item">
            <buton type="Add Comment" class="btn btn-primary" onclick="article.addComment()">Add Comment</buton>
        </div>

        <div class="item text-right">
            <buton type="reset" class="btn btn-light">Cancel</buton>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp" />

<script>
    var article = {
        editArticle: function(id) {
            window.location.href = '/article/create?id=' + id;
        }
    }
</script>
<script>
    var article = {
        addComment : function () {
            let comment= document.getElementById('comment').value;
            let data = {};
            data['comment'] = comment;
            data['articleId'] = ${article.id};
            console.log(data);

            let params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.ARTICLE_LIST + appRoutes.ADD_COMMENT;
            params['data'] = data;
            params['beforeSendCallbackFunction'] = article.beforeAjax;
            params['successCallbackFunction'] = article.doneAddingComment;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            //params['contentType'] = 'application/json';
            doAjax(params);
        },
        doneAddingComment: function (response) {
            console.log("Comment added" );
            console.log(response);
        },
        beforeAjax: function() {
            let header = $("meta[name='_csrf_header']").attr("content");
            let token = $("meta[name='_csrf']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        },
    }
</script>
<%--<script>--%>
<%--    $(document).ready(function(){--%>
<%--        $("abc").click(function(){--%>
<%--            var comment = $.trim($("#comment").val());--%>
<%--            if(comment != ""){--%>
<%--                // Show alert dialog if value is not blank--%>
<%--                alert(comment);--%>
<%--            }--%>
<%--        });--%>

<%--    });--%>
<%--</script>--%>

