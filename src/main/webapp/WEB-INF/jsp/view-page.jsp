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
<c:import url="common/headernav.jsp"/>
<jsp:include page="common/member-links.jsp" />
<jsp:include page="common/categories.jsp" />
<link rel="stylesheet" href="/css/modal.css" />
<div class="content">
    <div class="row">
        <div class="col-md-10">
            <div class="google-add-horizontal">
                <div class="add">
                    <jsp:include page="common/horizontal-ad.jsp" />
                </div>
            </div>
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
            <div class="form-action">
                <div class="item text-right">
                    <buton type="button" class="btn btn-primary" onclick="page.editPage(${page.id})">Edit</buton>
                    <buton id="delete" type="button" class="btn btn-primary" onclick="page.editPage(${page.id})">Delete</buton>
                    <buton type="reset" class="btn btn-light">Cancel</buton>
                </div>


            </div>
        </div>
        <div class="col-md-2 google-add-vertical">
            <div class="add">
                <img src="https://www.google.com/adsense/static/en/images/wideskyscraper_img.jpg" alt="" />
            </div>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp" />

<script>
    var page = {
        editPage: function(id) {
            window.location.href = '/page/create?id=' + id;
        }
    }
</script>

<!-- The Modal -->
<div id="deleteModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <p>Are you sure you want to delete?
        </p>
        <buton id="delete" type="button" class="btn btn-danger" onclick="page.deletePage(${page.id})">Delete</buton>
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

    var page = {
        deletePage: function(id) {
            let data = {};
            data['id'] = id;

            let params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.PAGE_LIST + appRoutes.PAGE_DELETE;
            params['data'] = data;
            params['beforeSendCallbackFunction'] = page.beforeAjax;
            params['successCallbackFunction'] = page.doneDeletingPage;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            //params['contentType'] = 'application/json';
            doAjax(params);
        },
        doneDeletingPage: function (response) {
            console.log("Page deleted " );
            console.log(response);
            window.location.href = appRoutes.PAGE_LIST + '/';
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