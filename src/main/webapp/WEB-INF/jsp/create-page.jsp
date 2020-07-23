<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<spring:message code="pages.title" text="default" var="title"/>
<jsp:include page="common/header.jsp">
    <jsp:param name="titleAppend" value="#{title}"  />
</jsp:include>
<jsp:include page="common/headernav.jsp" />
<jsp:include page="common/categories.jsp" />
<div class="content">
    <div class="alert alert-success invisible" role="alert">
        <h4 class="alert-heading">
            <i class="fa fa-check"></i> Well done!
        </h4>
        <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
    </div>

    <div class="alert alert-warning invisible" role="alert">
        <h4 class="alert-heading">
            <i class="fa fa-exclamation-triangle"></i> Warning!
        </h4>
        <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
    </div>

    <div class="alert alert-danger invisible" role="alert">
        <h4 class="alert-heading">
            <i class="fa fa-exclamation-circle"></i> Error!
        </h4>
        <p>Aww yeah, you successfully read this important alert message. This example text is going to run a bit longer so that you can see how spacing within an alert works with this kind of content.</p>
    </div>

    <form>
        <div class="add-post-wrapper">


            <div class="form-data">
                <div class="form-group">
                    <label>Title</label>
                    <input name="pageTitle" id="pageTitle" type="text" class="form-control" placeholder="Enter Post Title" value="${page.pageTitle}"/>
                </div>

                <div class="form-group">
                    <label>SEO URI</label>
                    <input name="seoUri" id="seoUri" type="text" class="form-control" placeholder="Enter SEO URI e.g. https://something.com"  value="${page.seoUri}" />
                </div>

                <div class="form-group">
                    <label>Body</label>
                    <textarea name="pageBody" id="pageBody">${page.pageBody}</textarea>
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
                    <input id="pid" name="pid" type="hidden" value="${page.id}"/>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>Empty</label>
                            <textarea class="form-control" placeholder="empty"></textarea>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="showMain">Show Link on Main Page </label>
                            <input type="checkbox" id="showMain" name="showMain">
                        </div>
                    </div>
                </div>

                <div class="form-action">
                    <div class="item">
                        <buton type="button" class="btn btn-primary" onclick="page.submitCreate();">Submit</buton>
                    </div>

                    <div class="item text-right">
                        <buton type="reset" class="btn btn-light">Cancel</buton>
                    </div>
                </div>
            </div>

        </div>
    </form>
</div>

<jsp:include page="common/footer.jsp" />
<script src="/js/tinymce/tinymce.min.js" referrerpolicy="origin"></script>

<script>
    tinymce.init({
        selector: '#pageBody',
        relative_urls : false,
        plugins: 'print preview paste importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists wordcount imagetools textpattern noneditable help charmap emoticons',
        imagetools_cors_hosts: ['picsum.photos'],
        menubar: 'file edit view insert format tools table help',
        toolbar: 'undo redo | bold italic underline strikethrough | fontselect fontsizeselect formatselect | alignleft aligncenter alignright alignjustify | outdent indent |  numlist bullist | forecolor backcolor removeformat | pagebreak | charmap emoticons | fullscreen  preview save print | insertfile image media template link anchor codesample | ltr rtl',
        toolbar_sticky: true,
        autosave_ask_before_unload: true,
        autosave_interval: "30s",
        autosave_prefix: "{path}{query}-{id}-",
        autosave_restore_when_empty: false,
        autosave_retention: "2m",
        image_advtab: true,
        content_css: '//www.tiny.cloud/css/codepen.min.css',
        link_list: [
            { title: 'My page 1', value: 'http://www.tinymce.com' },
            { title: 'My page 2', value: 'http://www.moxiecode.com' }
        ],
        image_list: [
            { title: 'My page 1', value: 'http://www.tinymce.com' },
            { title: 'My page 2', value: 'http://www.moxiecode.com' }
        ],
        image_class_list: [
            { title: 'None', value: '' },
            { title: 'Some class', value: 'class-name' }
        ],
        importcss_append: true,
        file_picker_callback: function (callback, value, meta) {
            /* Provide file and text for the link dialog */
            if (meta.filetype === 'file') {
                callback('https://www.google.com/logos/google.jpg', { text: 'My text' });
            }

            /* Provide image and alt text for the image dialog */
            if (meta.filetype === 'image') {
                callback('https://www.google.com/logos/google.jpg', { alt: 'My alt text' });
            }

            /* Provide alternative source and posted for the media dialog */
            if (meta.filetype === 'media') {
                callback('movie.mp4', { source2: 'alt.ogg', poster: 'https://www.google.com/logos/google.jpg' });
            }
        },
        templates: [
            { title: 'New Table', description: 'creates a new table', content: '<div class="mceTmpl"><table width="98%%"  border="0" cellspacing="0" cellpadding="0"><tr><th scope="col"> </th><th scope="col"> </th></tr><tr><td> </td><td> </td></tr></table></div>' },
            { title: 'Starting my story', description: 'A cure for writers block', content: 'Once upon a time...' },
            { title: 'New list with dates', description: 'New List with dates', content: '<div class="mceTmpl"><span class="cdate">cdate</span><br /><span class="mdate">mdate</span><h2>My List</h2><ul><li></li><li></li></ul></div>' }
        ],
        template_cdate_format: '[Date Created (CDATE): %m/%d/%Y : %H:%M:%S]',
        template_mdate_format: '[Date Modified (MDATE): %m/%d/%Y : %H:%M:%S]',
        height: 600,
        image_caption: true,
        quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote quickimage quicktable',
        noneditable_noneditable_class: "mceNonEditable",
        toolbar_mode: 'sliding',
        contextmenu: "link image imagetools table",
        file_picker_types: 'image',
        automatic_uploads: true,
        images_upload_url: '/upload/file-upload',
        images_upload_base_path: '/upload/'
    });
</script>

<script>
    $(document).ready(function () {
        console.log('Done loading');
    });

    var page = {
        submitCreate: function() {
            tinyMCE.triggerSave();
            let title = document.getElementById('pageTitle').value;
            let body = document.getElementById('pageBody').value;
            let seoUri = document.getElementById('seoUri').value;
            let id = document.getElementById('pid').value;

            let data = {};
            data['pageTitle'] = title;
            data['pageBody'] = body;
            data['seoUri'] = seoUri;


            if(id != undefined && id != null) {
                data['id'] = id;
            }
            if ($('#showMain').is(":checked")) {
                data['showMain'] = true;
            } else {
                data['showMain'] = false;
            }
            var params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.PAGE_LIST + appRoutes.PAGE_CREATE;
            params['data'] = JSON. stringify(data);
            params['beforeSendCallbackFunction'] = page.beforeCreatingPage;
            params['successCallbackFunction'] = page.doneCreatingPage;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            params['contentType'] = 'application/json';
            doAjax(params);

        },
        doneCreatingPage: function (response) {
            console.log("Page created " );
            console.log(response);
            window.location.href = appRoutes.PAGE_LIST + '/';
        },
        beforeCreatingPage: function() {
            let header = $("meta[name='_csrf_header']").attr("content");
            let token = $("meta[name='_csrf']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        }
    }
</script>