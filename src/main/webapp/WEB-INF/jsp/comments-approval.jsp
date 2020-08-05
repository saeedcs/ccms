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

            <div class="panel panel-default widget">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-comment"></span>
                    <h3 class="panel-title">
                        Un-Approved Comments</h3>
                    <span class="label label-info">
                    </span>
                </div>
                <c:forEach items="${comments}" var="comments" >
<%--        <c:set var="comments" scope="session" value="${comments}"/>--%>
<%--                <c:if test = "${comments.isApproved != true}">--%>
                <div class="panel-body" >
                    <ul class="list-group"  >
                        <li class="list-group-item" style="background-color: aliceblue; width:50%; height:80px" >
                            <div class="row">
                                <div class="col-xs-2 col-md-1 ">
                                    <img src="http://placehold.it/80" class="img-circle img-responsive" alt="" width="50px"/></div>
                                <div class="col-xs-10 col-md-11">
                                    <div>
                                        <div class="mic-info">
                                            <small> <span style="color:#ff0000;"> on 2 Aug 2013</span> </small>
                                        </div>
                                    </div>
                                    <div class="comment-text">
                                        <h6  style="color:black"> ${comments.commentText}</h6>
                                        <buton type="button" class="btn btn-light" onclick="comment.approveComment()" id="approve">Approve This Comment</buton>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
<%--                    </c:if>--%>
                    </c:forEach>
                </div>

        </div>
        <div class="col-md-2 google-add-vertical">
            <div class="add">
                <jsp:include page="common/vertical-ad.jsp" />
            </div>
        </div>
    </div>
</div>
</div>

<script>
    var comment = {
        approveComment: function() {
            let data = {};
            data['isApproved'] = true;
            console.log(data);
        }
    }
</script>