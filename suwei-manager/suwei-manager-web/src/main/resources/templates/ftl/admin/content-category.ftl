<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>内容目录</title>
<!--引入-->
<#include "../common/commoncss.ftl">
<#include "../common/commonjs.ftl">
</head>
<body>
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</body>
<script>
    $(function () {
        var zTreeObj;
        // zTree 的参数配置，深入使用请参考 API 文档(setting 配置详解)
        var setting = {
            async: {
                enable: true,
                url: "${ctx.contextPath}/content/category/list",
                autoParam: ["id"]
            },
            callback: {
                onClick: zTreeOnClick
            }
        };
        $(document).ready(function(){
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting,null);
        });
    })

    //选中树的子节点
    function zTreeOnClick(event, treeId, treeNode) {

        alert(treeNode.id + ", " + treeNode.name);

    };
</script>
</html>