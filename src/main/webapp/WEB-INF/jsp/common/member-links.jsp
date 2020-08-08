<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="hasAnyRole('ADMIN', 'USER')">
<!-- left bar start -->
<div class="left-bar">
    <div class="inner" data-aos="fade-right">
        <div class="top">
            <a href="#" class="logo desktop" data-aos="fade-down">
                CCMS <span class="slogan">Your CMS, your way</span>
            </a>

            <a href="#" class="logo mobile text-left" data-aos="fade-down">K</a>

        </div>

        <h3>Manage</h3>
        <ul class="menu">
            <li><a href="/article/" class="active"><i class="fa fa-file-text"></i>Articles</a></li>
            <li><a href="/page/"><i class="fa fa-file"></i> <span>Pages</span></a></li>
            <li><a href="#"><i class="fa fa-folder-open"></i> <span>Categories</span></a></li>
            <li><a href="#"><i class="fa fa-thumbs-up"></i> <span>Approve Comments</span></a></li>
            <li><a href="#"><i class="fa fa-user"></i> <span>Assign Roles</span></a></li>
        </ul>

        <h3>Settings</h3>
        <ul class="menu">
            <li><a href="#"><i class="fa fa-cog"></i> <span>General</span></a></li>
            <li><a href="#"><i class="fa fa-paint-brush"></i> <span>Design</span></a></li>
            <li><a href="#"><i class="fa fa-code"></i> <span>Code injection</span></a></li>
            <li><a href="#"><i class="fa fa-codepen"></i> <span>Integration</span></a></li>
            <li><a href="/logout"><i class="fa fa-window-close-o"></i> <span>Logout</span></a></li>
        </ul>
    </div>

    <div class="leftmenu-toggle">
        <span class="open"><i class="fa fa-angle-right" aria-hidden="true"></i></span>
        <span class="close"><span>Close</span><i class="fa fa-angle-left" aria-hidden="true"></i></span>
    </div>
</div>
<!-- Left bar end -->
</sec:authorize>