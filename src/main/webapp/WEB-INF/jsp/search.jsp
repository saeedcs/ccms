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
<div class="content">
    <div class="row">
        <div class="col-md-10">
            <div class="google-add-horizontal">
                <div class="add">
                    <jsp:include page="common/horizontal-ad.jsp" />
                </div>
            </div>

            <c:forEach items="${articles}" var="article">
                <a href="/article/view/${article.seoUri}" class="post-list">
                    <div class="description">
                        <h3>${article.title}</h3>

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
            <div class="search-page-buttons">
                <a href="#" class="btn btn-primary" id="prevPage" onclick="search1.prevPage(${prevPage});">Prev</a>
                <a href="#" class="btn btn-primary" id="nextPage" onclick="search1.nextPage(${nextPage});">Next</a>
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
<script>

    $( document ).ready(function() {
        //page has been loaded. do something

    });

    var search1 = {
        prevPage: function (pageNum) {
            window.location.href = appRoutes.SEARCH + '/s?str=' + '${str}' + '&page=' + pageNum;

        },
        nextPage: function (pageNum) {
            window.location.href = appRoutes.SEARCH + '/s?str=' + '${str}' + '&page=' + pageNum

        }
    }
</script>