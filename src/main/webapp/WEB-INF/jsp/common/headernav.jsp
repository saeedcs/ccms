<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body <sec:authorize access="!hasAnyRole('ADMIN', 'USER')">class="simple-page" </sec:authorize>>
<div class="overlay d-none"></div>

<div class="page-wrapper">
    <!-- header start -->
    <header>
        <div class="leftbar-mobile-toggle"><i class="fa fa-angle-double-right" aria-hidden="true"></i></div>
        <div class="topnav-toggle"><i class="fa fa-bars" aria-hidden="true"></i></div>
        <div class="inner">
            <div class="close-nav">Close <i class="fa fa-long-arrow-right" aria-hidden="true"></i></div>
            <div class="navigation" data-aos="fade-down">
                <ul>
                    <c:forEach items="${requestScope.pagesMain}" var="article">
                    <li><a href="#">${article.pageTitle}</a></li>
                    </c:forEach>
                </ul>
            </div>

            <div class="social-media"  data-aos="fade-down">
                <h3>Find Us on Social media</h3>
                <ul>
                    <li><span data-href="<spring:message code="base.uri" text=""/>"><a target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=<spring:message code="base.uri.encoded" text=""/>&amp;src=sdkpreparse"><i class="fa fa-facebook"></i></a></span></li>
                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                    <li><a href="/app/rss"><i class="fa fa-rss"></i></a></li>
                </ul>
            </div>
        </div>

        <div class="subscribe"  data-aos="fade-down">
            <div class="search-wrapper">
                <i class="fa fa-search"></i>

                <div class="search-bar">
                    <input type="text" placeholder="Search" class="form-control" />
                    <button><i class="fa fa-search"></i></button>
                </div>
            </div>

            <a href="#" class="btn btn-light" id="subscribe">Subscribe</a>
        </div>

        <a href="#" class="logo white">
            CCMS <span class="slogan">Your CMS, your way</span>
        </a>
    </header>
    <!-- header end -->