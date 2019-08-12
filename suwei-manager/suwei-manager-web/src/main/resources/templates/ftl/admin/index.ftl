<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>苏微商城管理平台</title>
    <#include "../common/assetscss.ftl">
    <script
            src="${ctx.contextPath}/assets/js/ace-extra.min.js"></script>
</head>
<body>

<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="${ctx.contextPath}" class="navbar-brand"> <small> <i
                            class="icon-leaf"></i> 苏微商城管理系统
                </small>
            </a>
        </div>

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="/images/6.jpg" alt="Jason's Photo" />
                        <span class="user-info"> <small>欢迎光临,</small>root
						</span> <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li><a href="${ctx.contextPath}"> <i class="icon-cog"></i> 设置
                            </a></li>

                        <li><a href="${ctx.contextPath}"> <i class="icon-user"></i> 管理员
                            </a></li>

                        <li class="divider"></li>

                        <li><a href="${ctx.contextPath}"> <i class="icon-off"></i> 退出
                            </a></li>
                    </ul></li>
            </ul>
        </div>
    </div>
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#"> <span
                    class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                    <button class="btn btn-success">
                        <i class="icon-signal"></i>
                    </button>

                    <button class="btn btn-info">
                        <i class="icon-pencil"></i>
                    </button>

                    <button class="btn btn-warning">
                        <i class="icon-group"></i>
                    </button>

                    <button class="btn btn-danger">
                        <i class="icon-cogs"></i>
                    </button>
                </div>

                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span> <span class="btn btn-info"></span>

                    <span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
                </div>
            </div>

            <!-- 左边的菜单 -->
            <#include "../admin/leftmenu.ftl">

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left"
                   data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>

        <div class="main-content">
            <div class="page-content">
                <iframe id="menuFrame" name="menuFrame"
                        src="${ctx.contextPath}/itemPage/background"
                        style="overflow: visible;" scrolling="yes" frameborder="yes"
                        width="100%" height="630px">
                </iframe>
                <div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    window.jQuery
    || document
        .write("<script src='${ctx.contextPath}/assets/js/jquery-2.0.3.min.js'>"
            + "<"+"script>");
</script>

<script type="text/javascript">
    if ("ontouchend" in document)
        document
            .write("<script src='${ctx.contextPath}/assets/js/jquery.mobile.custom.min.js'>"
                + "<"+"script>");
</script>
<#include "../common/assestsjs.ftl">
<script type="text/javascript">
    //菜单点击事件
    $(function() {
        $(".submenu li a").click(function() {
            var menuUrl = $(this).attr("dataUrl");
            $("#menuFrame").attr("src", menuUrl);
        });
    });
</script>

</body>
</html>