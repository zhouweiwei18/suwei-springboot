<ul class="nav nav-list">
    <li class="active">
        <a href="${ctx.contextPath}">
            <i class="icon-dashboard"></i>
            <span class="menu-text"> 苏微控制台 </span>
        </a>
    </li>
    <li>
        <a href="#" class="dropdown-toggle">
            <i class="icon-desktop"></i>
            <span class="menu-text"> 商品管理 </span>

            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <li>
                <a href="javascript:void(0)" dataUrl="">
                    <i class="icon-double-angle-right"></i>
                    新增商品
                </a>
            </li>
            <li>
                <a href="javascript:void(0)" dataUrl="${ctx.contextPath}/itemPage/item">
                    <i class="icon-double-angle-right"></i>
                    查询商品
                </a>
            </li>
            <li>
                <a href="javascript:void(0)" dataUrl="">
                    <i class="icon-double-angle-right"></i>
                    规格参数
                </a>
            </li>
            <#--<li>
                <a href="jquery-ui.html">
                    <i class="icon-double-angle-right"></i>
                    复制房源
                </a>
            </li>
            <li>
                <a href="nestable-list.html">
                    <i class="icon-double-angle-right"></i>
                    账号设置
                </a>
            </li>-->
        </ul>
    </li>
    <li>
        <a href="#" class="dropdown-toggle">
            <i class="icon-list"></i>
            <span class="menu-text"> 网站内容管理 </span>

            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <li>
                <a href="javascript:void(0)" dataUrl="">
                    <i class="icon-double-angle-right"></i>
                    内容分类管理
                </a>
            </li>
            <li>
                <a href="javascript:void(0)" dataUrl="">
                    <i class="icon-double-angle-right"></i>
                    内容引擎
                </a>
            </li>
        </ul>
    </li>
    <li>
        <a href="#" class="dropdown-toggle">
            <i class="icon-edit"></i>
            <span class="menu-text"> 索引库管理 </span>

            <b class="arrow icon-angle-down"></b>
        </a>
        <ul class="submenu">
            <li>
                <a href="#">
                    <i class="icon-double-angle-right"></i>
                    solr索引库维护
                </a>
            </li>
        </ul>
    </li>
</ul>