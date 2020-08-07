<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- inner tab start -->
<div class="inner-tab">
    <div class="selected-value"><span>Select Category</span></div>
    <ul>
        <li><a href="/article/" <c:if test="${param.cat == null}"> class="active"</c:if>>Most Recent</a></li>
<c:forEach items="${requestScope.catMain}" var="cat">
    <li><a href="/article/?cat=${cat.catName}" <c:if test="${param.cat == cat.catName}"> class="active"</c:if>>${cat.catName}</a></li>
</c:forEach>
    </ul>
</div>
<!-- inner tab end -->