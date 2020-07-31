<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- inner tab start -->
<div class="inner-tab">
    <div class="selected-value"><span>Select Category</span></div>
    <ul>
        <li><a href="#" class="active">Most Recent</a></li>
        <li><a href="#">Popular</a></li>
        <li><a href="#">Growth</a></li>
        <li><a href="#">Support</a></li>
        <li><a href="#">Culture</a></li>
        <li><a href="#">Product</a></li>
        <li><a href="#">Help Desk Tips</a></li>
<c:forEach items="${requestScope.catMain}" var="cat">
    <li><a href="/article/?cat=${cat.catName}" <c:if test="${param.cat == cat.catName}"> class="active"</c:if>>${cat.catName}</a></li>
</c:forEach>
    </ul>
</div>
<!-- inner tab end -->