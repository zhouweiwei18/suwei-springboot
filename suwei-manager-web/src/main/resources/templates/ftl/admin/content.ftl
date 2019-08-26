<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
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
<body>

<div id="toolbar">
    <button id="btn_add" type="button" onclick="toContentPage()" style="float: left;margin-left: 5px;"
            class="btn btn-default">添加
    </button>
    <button id="btn_edit" type="button" style="float: left;margin-left: 5px;" class="btn btn-default">编辑</button>
    <button id="btn_del" type="button" style="float: left;margin-left: 5px;" class="btn btn-default">删除</button>
</div>

<div class="row">
    <div class="col-md-2">
        <div class="zTreeDemoBackground left">
            <ul id="treeDemo" class="ztree"></ul>
        </div>
    </div>
    <div class="col-md-10">
        <!--显示表格的内容-->
        <table id="tb_admin" style="table-layout: auto"></table>
    </div>
</div>

<SCRIPT type="text/javascript">

    var setting = {
        view: {
            selectedMulti: false
        },
        check: {
            enable: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        edit: {
            enable: false
        },
        async: {
            enable: true,
            url: "${ctx.contextPath}/content/category/list",
            autoParam: ["id"]
        }
    };

    $(document).ready(function () {
        $.fn.zTree.init($("#treeDemo"), setting, null);
    });

    //跳转到对应页面(添加)
    function toContentPage() {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var nodes = zTree.getSelectedNodes()[0];
        if (nodes == null) {
            alert("请选择左边内容类别!")
            //alert(nodes.id + "----" + nodes.name);
        } else {
            //Restful风格
            location.href = "${ctx.contextPath}/content/toAdd/" + nodes.id;
        }
    }
</SCRIPT>
<script type="text/javascript">
    $(function () {
        //表格内容的数据展示
        $("#tb_admin").bootstrapTable({
            url: "${ctx.contextPath}/content/list",//访问地址
            method: "post",//请求的方式
            toolbar: "#toolbar",//工具栏
            search: true,
            pagination: true,//允许分页
            paginationShowPageGo: true,
            pageSize: 10,//每页显示的条数
            pageList: [10, 20, 30],//每页显示的条数集合
            showColumns: true,//是否显示所有的列
            showRefresh: true,//是否显示刷新
            striped: true,//隔行变色
            columns: [{
                checkbox: true,
                class: 'colStyle',
                align: "center"
            }, {
                field: "id",
                title: "编号",
                class: 'colStyle',
                align: 'center',
                sortable: true

            }, {
                field: "title",
                title: "内容标题",
                class: 'colStyle',
                align: 'center',
                formatter: function (value, row, index) {
                    var values = row.title;
                    var span = document.createElement('span');
                    span.setAttribute('title', row.title);
                    span.innerHTML = row.title;
                    return span.outerHTML;
                }
            }, {
                field: "subTitle",
                title: "内容子标题",
                align: 'center',
                class: 'colStyle'
            }, {
                field: "titleDesc",
                title: "标题描述",
                class: 'colStyle',
                align: 'center'
            }, {
                field: "url",
                title: "内容连接",
                class: 'colStyle',
                align: 'center'
            }, {
                field: "pic",
                title: "图片",
                class: 'colStyle',
                align: 'center'
            }, {
                field: "created",
                title: "创建时间",
                width: 170,
                class: 'colStyle',
                align: 'center',
                formatter: function (value, row, index) {
                    var values = row.created;
                    var d = new Date(values);
                    var times = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
                    var span = document.createElement('span');
                    span.setAttribute('title', row.title);
                    span.innerHTML = times;
                    return span.outerHTML;
                }
            }, {
                field: "updated",
                width: 170,
                title: "更新时间",
                class: 'colStyle',
                align: 'center',
                formatter: function (value, row, index) {
                    var values = row.updated;
                    var d = new Date(values);
                    var times = d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
                    var span = document.createElement('span');
                    span.setAttribute('title', row.title);
                    span.innerHTML = times;
                    return span.outerHTML;
                }
            }]
        })
    })
</script>
</body>
</html>