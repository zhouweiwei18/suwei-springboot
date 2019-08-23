<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>内容目录</title>
    <!--引入-->
    <#include "../common/commoncss.ftl">
    <#include "../common/commonjs.ftl">
    <style type="text/css">
        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
</head>
<SCRIPT type="text/javascript">

    var setting = {
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        edit: {
            enable: true,
            editNameSelectAll: true,
            showRemoveBtn: showRemoveBtn,
            showRenameBtn: showRenameBtn
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeDrag: beforeDrag,
            beforeEditName: beforeEditName,
            beforeRemove: beforeRemove,
            beforeRename: beforeRename,
            onRemove: onRemove,
            onRename: onRename
        },
        async: {
            enable: true,
            url: "${ctx.contextPath}/content/category/list",
            autoParam: ["id"]
        }
    };

    var log, className = "dark";

    function beforeDrag(treeId, treeNodes) {
        return false;
    }

    function beforeEditName(treeId, treeNode) {
        className = (className === "dark" ? "" : "dark");
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.selectNode(treeNode);
        setTimeout(function () {
            if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
                setTimeout(function () {
                    zTree.editName(treeNode);
                }, 0);
            }
        }, 0);
        return false;
    }

    function beforeRemove(treeId, treeNode) {
        className = (className === "dark" ? "" : "dark");
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.selectNode(treeNode);
        var oFlag = confirm("确认删除 节点 -- " + treeNode.name + " 吗？");//这里前后台交互
        if (oFlag) {
            $.post("/content/category/delete", {id: treeNode.id},function (data) {
                if(data.status==200){
                    alert("删除成功");
                    return true;
                }else{
                    alert(data.msg);//是父节点
                }
                return false;
            });
        } else {
            return false;
        }
    }

    function onRemove(e, treeId, treeNode) {

    }

    function beforeRename(treeId, treeNode, newName, isCancel) {
        className = (className === "dark" ? "" : "dark");
        if (newName.length == 0) {
            setTimeout(function () {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                zTree.cancelEditName();
                alert("节点名称不能为空.");
            }, 0);
            return false;
        }
        return true;
    }

    function onRename(e, treeId, treeNode, isCancel) {
        alert(treeNode.id + ", " + treeNode.name);
        //重命名(同步到数据库)
        $.post("/content/category/update", {id: treeNode.id, name: treeNode.name});
    }

    function showRemoveBtn(treeId, treeNode) {
        return !treeNode.isParent;
    }

    function showRenameBtn(treeId, treeNode) {
        return true;
    }

    function getTime() {
        var now = new Date(),
            h = now.getHours(),
            m = now.getMinutes(),
            s = now.getSeconds(),
            ms = now.getMilliseconds();
        return (h + ":" + m + ":" + s + " " + ms);
    }

    var newCount = 1;

    function addHoverDom(treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='add node' onfocus='this.blur();'></span>";
        sObj.after(addStr);
        var btn = $("#addBtn_" + treeNode.tId);
        if (btn) btn.bind("click", function () {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                var newName = "new node" + (newCount++);
                //同步到数据库(后台交互)
                alert("添加节点");
                // 新增节点
                $.post("/content/category/create", {parentId: treeNode.id, name: newName}, function (data) {
                    if (data.status == 200) {
                        //给新增节点补全数据库的id
                        //alert(data.data.id);
                        //alert(data.data.name);
                        //页面上添加
                        zTree.addNodes(treeNode, {id: data.data.id, pId: treeNode.id, name: newName});
                        alert("success");
                    } else {
                        alert("创建" + newName + "分类失败!");
                    }
                });
                return false;
            }
        );
    };

    function removeHoverDom(treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    };

    function selectAll() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.setting.edit.editNameSelectAll = $("#selectAll").attr("checked");
    }

    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting, null);
        $("#selectAll").bind("click", selectAll);
    });
</SCRIPT>
<BODY>
<div class="content_wrap">
    <div class="zTreeDemoBackground left">
        <ul id="treeDemo" class="ztree"></ul>
    </div>
</div>
</BODY>

</html>