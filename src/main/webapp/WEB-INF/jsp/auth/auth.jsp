<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:message code="pages.title" text="default" var="title"/>
<jsp:include page="../common/header.jsp">
    <jsp:param name="titleAppend" value="#{title}"  />
</jsp:include>
<jsp:include page="../common/headernav.jsp" />
<jsp:include page="../common/member-links.jsp" />
<jsp:include page="../common/categories.jsp" />
<div class="content">
    <div class="row">
        <div class="col-md-10">
            <div class="google-add-horizontal">
                <div class="add">
                    <jsp:include page="../common/horizontal-ad.jsp" />
                </div>
            </div>



        </div>
        <div class="col-md-2 google-add-vertical">
            <div class="add">
                <jsp:include page="../common/vertical-ad.jsp" />
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp" />