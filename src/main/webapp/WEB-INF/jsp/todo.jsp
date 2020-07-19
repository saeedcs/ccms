<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    ${todo.description} - <h1><spring:message code="greeting" text="default"/></h1>

        <form:form method="post" modelAttribute="todo">
        <form:hidden path="id" />
        <fieldset class="form-group">
            <form:label path="description">Description</form:label>
            <form:input path="description" type="text" class="form-control"
                required="required" />
            <form:errors path="description" cssClass="text-warning" />
        </fieldset>
        <fieldset class="form-group">
            <form:label path="targetDate">Target Date</form:label>
            <form:input path="targetDate" type="text" class="form-control"
                required="required" />
            <form:errors path="targetDate" cssClass="text-warning" />
        </fieldset>
        <button type="submit" class="btn btn-success">Submit</button>
    </form:form>
</div>

<%@ include file="common/footer.jspf"%>

<script>
    $('#targetDate').datepicker({
        format : 'dd/mm/yyyy'
    });

    $('#targetDate').on('keyup keydown blur', function() {
        // get date value
        dob = $('#targetDate').val();
        // date regex checker
        var dobReg = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
        // validate birthday input length
        if (dob.length != 10) {
            $('#dateResp').html('<i class="fa fa-times"></i> format mm/dd/yyyy').show();
            // not approved
            exeDob = false;
        }
        // test date regex
        else if (!dobReg.test(dob)) {
            $('#dateResp').html('<i class="fa fa-times"></i> Birth date format mm/dd/yyyy').show();
            // not approved
            exeDob = false;
        } else {
            $('#dateResp').html('').hide();
            // approved
            exeDob = true;
        }
    });
</script>