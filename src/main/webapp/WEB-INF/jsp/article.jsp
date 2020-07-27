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
            <c:forEach items="${articles}" var="article">
                <a href="/article/view?id=${article.id}" class="post-list">
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
                <img src="https://www.google.com/adsense/static/en/images/wideskyscraper_img.jpg" alt="" />
            </div>
        </div>
    </div>
</div>


<jsp:include page="common/footer.jsp" />
