<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
    <table class="table table-striped">
        <caption>Your Todos are</caption>
        <thead>
            <tr>
                <th>Description</th>
                <th>Date</th>
                <th>Completed</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${pages}" var="page">
                <tr>
                    <td>${page.pageTitle}</td>
                    <td>${page.pageBody}</td>
                    <td><a type="button" class="btn btn-primary"
                        href="/update-todo?id=${page.id}">Edit</a> <a type="button"
                        class="btn btn-warning" href="/delete-todo?id=${page.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <div>
        <a type="button" class="btn btn-success" href="/add-todo">Add</a>
    </div>
</div>
<%@ include file="common/footer.jspf"%>