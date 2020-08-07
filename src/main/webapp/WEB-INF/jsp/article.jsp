<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:message code="pages.title" text="default" var="title"/>
<jsp:include page="common/header.jsp">
    <jsp:param name="titleAppend" value="#{title}"  />
</jsp:include>
<c:set var="pagesMain" value="${pagesMain}" scope="request"/>
<%--<jsp:include page="common/headernav.jsp" />
<jsp:param name="pagesMain" value="${pagesMain}"  />
</jsp:include>--%>
<c:import url="common/headernav.jsp"/>
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
            <div class="form-action">
                <div class="item">
                    <buton type="button" class="btn btn-primary" onclick="page.jumpToCreate();">Create</buton>
                </div>
            </div>
            <c:forEach items="${articles}" var="article">
                <a href="/article/view/${article.seoUri}" class="post-list">
                    <c:if test="${article.mainPageImg != null && article.mainPageImg != ''}" >
                        <div class="post-thumbnail">
                            <img src="${article.mainPageImg}" alt="" />
                        </div>
                    </c:if>
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
                </a>
            </c:forEach>

        </div>
        <div class="col-md-2 google-add-vertical">
            <div class="add">
                <jsp:include page="common/vertical-ad.jsp" />
            </div>
        </div>
    </div>
</div>


<jsp:include page="common/footer.jsp" />
<script>

    $( document ).ready(function() {
        //page has been loaded. do something

    });

    var page = {
        jumpToCreate: function () {
            window.location.href = appRoutes.ARTICLE_LIST + appRoutes.ARTICLE_CREATE;

        }
    }
</script>
