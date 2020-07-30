<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:message code="pages.title" text="default" var="title"/>
<jsp:include page="common/header.jsp">
    <jsp:param name="titleAppend" value="#{title}"  />
</jsp:include>
<c:set var="pagesMain" value="${pagesMain}" scope="request"/>
<%--<jsp:include page="common/headernav.jsp" >
    <jsp:param name="pagesMain" value="${pagesMain}"  />
</jsp:include>--%>
<c:import url="common/headernav.jsp" />
<jsp:include page="common/member-links.jsp" />
<jsp:include page="common/categories.jsp" />
<div class="content">
    <div class="row">
        <div class="col-md-10">
            <div class="google-add-horizontal">
                <div class="add">
                    <jsp:include page="common/horizontal-ad.jsp" />
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
                <div class="item text-right">
                    <buton type="button" class="btn btn-primary" onclick="article.editArticle(${article.id});">Edit</buton>
                    <buton id="delete" type="button" class="btn btn-primary" onclick="article.editArticle(${article.id});">Delete</buton>
                    <buton type="reset" class="btn btn-light">Cancel</buton>
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
    <div class="col-md-2 google-add-vertical">
        <div class="add">
            <jsp:include page="common/vertical-ad.jsp" />
        </div>
    </div>
</div>
</div>

<jsp:include page="common/footer.jsp" />


    <!-- The Modal -->
    <div id="deleteModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <span class="close">&times;</span>
            <p>Are you sure you want to delete?
            </p>
            <buton id="delete" type="button" class="btn btn-danger" onclick="article.deleteArticle(${article.id})">Delete</buton>
            <p></p><buton type="button" class="btn btn-light" onclick="closeModal()">No</buton>

        </div>

    </div>
    <script>
        // Get the modal
        var modal = document.getElementById("deleteModal");

        // Get the button that opens the modal
        var btn = document.getElementById("delete");

        // Get the <span> element that closes the modal
        var span = document.getElementsByClassName("close")[0];

        // When the user clicks on the button, open the modal
        btn.onclick = function() {
            modal.style.display = "block";
        }

        // When the user clicks on <span> (x), close the modal
        span.onclick = function() {
            closeModal();
        }
        function closeModal() {
            modal.style.display = "none";
        }
        // When the user clicks anywhere outside of the modal, close it
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }

        var article = {
            editArticle: function(id) {
                window.location.href = '/article/create?id=' + id;
            },
            deleteArticle: function(id) {
                let data = {};
                data['id'] = id;

                let params = $.extend({}, doAjax_params_default);
                params['url'] = appRoutes.ARTICLE_LIST + appRoutes.DELETE;
                params['data'] = data;
                params['beforeSendCallbackFunction'] = article.beforeAjax;
                params['successCallbackFunction'] = article.doneDeletingArticle;
                params['requestType'] = appObjects.REQUEST_TYPE.post;
                //params['contentType'] = 'application/json';
                doAjax(params);
            },
            doneDeletingArticle: function (response) {
                console.log("Article deleted " );
                console.log(response);
                window.location.href = appRoutes.ARTICLE_LIST + '/';
            },
            beforeAjax: function() {
                let header = $("meta[name='_csrf_header']").attr("content");
                let token = $("meta[name='_csrf']").attr("content");
                $(document).ajaxSend(function (e, xhr, options) {
                    xhr.setRequestHeader(header, token);
                });
            },
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
            }
        }
    </script>
