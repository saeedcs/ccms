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
                    <img src="https://www.shakeout.org/2008/downloads/ShakeOut_BannerAds_GetReady_728x90_v3.gif" alt="" />
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

        </div>
        <div class="col-md-2 google-add-vertical">
            <div class="add">
                <img src="https://www.google.com/adsense/static/en/images/wideskyscraper_img.jpg" alt="" />
            </div>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp" />