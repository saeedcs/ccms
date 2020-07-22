<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:message code="pages.title" text="default" var="title"/>
<jsp:include page="common/header.jsp">
    <jsp:param name="titleAppend" value="#{title}"  />
</jsp:include>
<jsp:include page="common/headernav.jsp" />
<jsp:include page="common/categories.jsp" />
<div class="content">
    <div class="alert alert-success" role="alert">
        <h4 class="alert-heading">
            <i class="fa fa-check"></i> Well done!
        </h4>
        <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
    </div>

    <div class="alert alert-warning" role="alert">
        <h4 class="alert-heading">
            <i class="fa fa-exclamation-triangle"></i> Warning!
        </h4>
        <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
    </div>

    <div class="alert alert-danger" role="alert">
        <h4 class="alert-heading">
            <i class="fa fa-exclamation-circle"></i> Error!
        </h4>
        <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
    </div>

    <form>
        <div class="add-post-wrapper">
            <div class="img-holder">
                <img src="https://increasify.com.au/wp-content/uploads/2016/08/default-image.png" alt="" />
                <div class="upload-file btn btn-dark">
                    <i class="fa fa-camera"></i> Upload Post Image
                    <input type="file" id="upload-post-img" />
                </div>
            </div>

            <div class="form-data">
                <div class="form-group">
                    <label>Title</label>
                    <input type="text" class="form-control" placeholder="Enter Post Title" />
                </div>

                <div class="form-group">
                    <label>SEO URI</label>
                    <input type="text" class="form-control" placeholder="Enter SEO URI e.g. https://something.com" />
                </div>

                <div class="form-group">
                    <label>Post Description</label>
                    <textarea name="editor1"></textarea>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Category</label>
                            <select class="form-control">
                                <option>Choose Category</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Publish On</label>
                            <select class="form-control">
                                <option>Choose Area</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Excerpt</label>
                    <textarea class="form-control" placeholder="experpt"></textarea>
                </div>

                <div class="form-action">
                    <div class="item">
                        <buton type="submit" class="btn btn-primary">Submit</buton>
                    </div>

                    <div class="item text-right">
                        <buton type="reset" class="btn btn-light">Cancel</buton>
                    </div>
                </div>
            </div>

        </div>
    </form>
</div>
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

<jsp:include page="common/footer.jsp" />