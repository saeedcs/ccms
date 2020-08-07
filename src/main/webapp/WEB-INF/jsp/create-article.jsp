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
                    <input name="articleTitle" id="articleTitle" type="text" class="form-control" placeholder="Enter Article Title" value="${article.articleTitle}"  onblur="article.makeSeoUrl(${(article.id)});" onkeyup="article.makeSeoUrl(${(article.id)});"/>
                    <span id="articleTitleErr" class="invalid-feedback invisible"></span>
                </div>

                <div class="form-group">
                    <label>SEO URI</label>
                    <input name="seoUri" id="seoUri" type="text" class="form-control" placeholder="Enter SEO URI e.g. https://something.com"  value="${article.seoUri}" onblur="article.checkSeoUriDuplicate(this.value, ${(article.id)})" onkeyup="article.checkSeoUriDuplicate(this.value, ${(article.id)})"/>
                    <span id="seoUriErr" class="invalid-feedback invisible">Your SEO URI is not unique, Please change it</span>
                </div>

                <div class="form-group">
                    <label>Body</label>
                    <textarea name="articleBody" id="articleBody">${article.articleBody}</textarea>
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
                    <input id="pid" name="pid" type="hidden" value="${article.id}"/>
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
                        <buton type="button" class="btn btn-primary" onclick="article.submitCreate();">Submit</buton>
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
        selector: '#articleBody',
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
            { title: 'My article 1', value: 'http://www.tinymce.com' },
            { title: 'My article 2', value: 'http://www.moxiecode.com' }
        ],
        image_list: [
            { title: 'My article 1', value: 'http://www.tinymce.com' },
            { title: 'My article 2', value: 'http://www.moxiecode.com' }
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
        images_upload_url: appRoutes.UPLOAD + appRoutes.FILE_UPLOAD,
        images_upload_base_path: '/upload/',
        images_upload_handler: function (blobInfo, success, failure) {
            var xhr, formData;

            xhr = new XMLHttpRequest();
            xhr.withCredentials = false;
            xhr.open('POST', appRoutes.UPLOAD + appRoutes.FILE_UPLOAD);

            xhr.onload = function() {
                var json;

                if (xhr.status != 200) {
                    failure('HTTP Error: ' + xhr.status);
                    return;
                }

                json = JSON.parse(xhr.responseText);

                if (!json || typeof json.location != 'string') {
                    failure('Invalid JSON: ' + xhr.responseText);
                    return;
                }

                success(json.location);
            };

            formData = new FormData();
            formData.append('file', blobInfo.blob(), blobInfo.filename());
            let header = $("meta[name='_csrf_header']").attr("content");
            let token = $("meta[name='_csrf']").attr("content");

            xhr.setRequestHeader(header, token);

            // append CSRF token in the form data
            formData.append(header, token);

            xhr.send(formData);
        }
    });
</script>

<script>
    $(document).ready(function () {
        console.log('Done loading');
    });

    var article = {
        submitCreate: function() {
            tinyMCE.triggerSave();
            let title = document.getElementById('articleTitle').value;
            let body = document.getElementById('articleBody').value;
            let seoUri = document.getElementById('seoUri').value;
            let id = document.getElementById('pid').value;
            let mainPageImg = article.extractFirstImgSrc(body);

            let data = {};
            data['articleTitle'] = title;
            data['articleBody'] = body;
            data['seoUri'] = seoUri;
            data['mainPageImg'] = mainPageImg;


            if(id != undefined && id != null) {
                data['id'] = id;
            }
            if ($('#showMain').is(":checked")) {
                data['showMain'] = true;
            } else {
                data['showMain'] = false;
            }
            let params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.ARTICLE_LIST + appRoutes.ARTICLE_CREATE;
            params['data'] = JSON. stringify(data);
            params['beforeSendCallbackFunction'] = article.beforeAjax();
            params['successCallbackFunction'] = article.doneCreatingArticle;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            params['contentType'] = 'application/json';
            doAjax(params);

        },
        doneCreatingArticle: function (response) {
            console.log("Article created " );
            console.log(response);
            window.location.href = appRoutes.ARTICLE_LIST + '/';
        },
        beforeAjax: function() {
            let header = $("meta[name='_csrf_header']").attr("content");
            let token = $("meta[name='_csrf']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        },
        toSeoUrl: function (url) {
            return url.toString()               // Convert to string
                .normalize('NFD')               // Change diacritics
                .replace(/[\u0300-\u036f]/g,'') // Remove illegal characters
                .replace(/\s+/g,'-')            // Change whitespace to dashes
                .toLowerCase()                  // Change to lowercase
                .replace(/&/g,'-and-')          // Replace ampersand
                .replace(/[^a-z0-9\-]/g,'')     // Remove anything that is not a letter, number or dash
                .replace(/-+/g,'-')             // Remove duplicate dashes
                .replace(/^-*/,'')              // Remove starting dashes
                .replace(/-*$/,'');             // Remove trailing dashes
        },
        makeSeoUrl: function (id) {
            let articleTitle = document.getElementById('articleTitle').value;

            if(articleTitle != undefined && articleTitle.trim() != '') {
                let seoUri = document.getElementById('seoUri').value = article.toSeoUrl(articleTitle);
                this.checkSeoUriDuplicate(seoUri, id);
            }

        },
        checkSeoUriDuplicate: function(seoUri, id) {
            let data = {}
            data['seoUri'] = seoUri;
            data['id'] = id;

            let params = $.extend({}, doAjax_params_default);
            params['url'] = appRoutes.ARTICLE_LIST + appRoutes.SEO_URI_CHECK;
            params['data'] = data;
            params['successCallbackFunction'] = article.isSeoUriDuplicate;
            params['beforeSendCallbackFunction'] = article.beforeAjax;
            params['requestType'] = appObjects.REQUEST_TYPE.post;
            //params['contentType'] = 'application/json';
            doAjax(params);
        },
        isSeoUriDuplicate: function(response) {
            console.log(response);
            if(response.exists === true) {
                validateForms.invalidField('seoUri', appObjects.ERROR_MSG.seoUriExists);
            } else {
                validateForms.validField('seoUri')
            }
        },
        extractFirstImgSrc(str) {
            var re = /<img[^>]+src="([^">]+)/g
            var results = re.exec(str);
            if(results == undefined || results == null) {
                return '';
            }
            var source = results[1];

            return source;
        }
    }
</script>