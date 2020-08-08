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
<h2>Assign Roles</h2>

            <c:forEach items="${users}" var="user">
                <div class="description">
                    <h4>${user.username} </h4>
                    <div class="row" class="width-100">
                        <div class="width-50">
                            Enabled  <select  onchange="assignRoles.enabledChange(this.value, '${user.username}');">
                            <option value="false" <c:if test="${user.enabled == false}">selected</c:if>>No</option>
                            <option value="true" <c:if test="${user.enabled == true}">selected</c:if>>Yes</option>
                        </select>
                        </div>

                            <%--<c:forEach items="${user.roles}" var="role">
                                <div >${role.name}</div>
                            </c:forEach>--%>
                        <div >

                            <select multiple onchange="assignRoles.performChange(this, '${user.username}');">
                                <c:forEach items="${roles}" var="role1">

                                    <option value="${role1.id}" <c:if test="${user.roles.contains(role1)}">selected</c:if>>${role1.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
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

    var assignRoles = {
        performChange: function (list, username) {
            console.log(username);
            console.log($(list).val());
        },
        enabledChange: function(enabledVal, username) {
            console.log(username);
            console.log(enabledVal);

            let data = {};
            data['username'] = username;
            data['enabled'] = enabledVal;

            let params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.APP + appRoutes.ASSIGN_ROLES_ENABLED;
            params['data'] = data;
            params['beforeSendCallbackFunction'] = assignRoles.beforeAjax;
            params['successCallbackFunction'] = assignRoles.doneChangeEnable;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            //params['contentType'] = 'application/json';
            doAjax(params);
        },
        doneChangeEnable: function (response) {
            console.log(response);
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
