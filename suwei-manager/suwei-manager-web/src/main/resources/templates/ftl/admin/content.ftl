<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--引入-->
    <#include "../common/commoncss.ftl">
    <#include "../common/commonjs.ftl">
</head>
<body>
<div id="toolbar">
    <button id="btn_add" type="submit" style="float: left;margin-left: 5px;" class="btn btn-default"><a href="${ctx.contextPath}/">添加</a></button>
    <button id="btn_edit" type="submit" style="float: left;margin-left: 5px;" class="btn btn-default">编辑</button>
    <button id="btn_del" type="submit" style="float: left;margin-left: 5px;" class="btn btn-default">删除</button>
</div>
<!--显示表格的内容-->
<table id="tb_admin" style='table-layout:fixed;'></table>

<script type="text/javascript">
    $(function(){
        //表格内容的数据展示
        $("#tb_admin").bootstrapTable({
            url:"${ctx.contextPath}/content/list",//访问地址
            method:"get",//请求的方式
            toolbar:"#toolbar",//工具栏
            search:true,
            pagination:true,//允许分页
            paginationShowPageGo: true,
            pageSize:10,//每页显示的条数
            pageList:[10,20,30],//每页显示的条数集合
            showColumns:true,//是否显示所有的列
            showRefresh:true,//是否显示刷新
            striped:true,//隔行变色
            columns:[{
                checkbox:true,
                valign:'middle',
                class:'colStyle',
                align:"center"
            },{
                field:"id",
                title:"编号",
                class:'colStyle',
                width:80,
                valign:'middle',
                align: 'center',
                sortable:true

            },{
                field:"title",
                title:"内容标题",
                class:'colStyle',
                width:150,
                valign:'middle',
                align: 'center',
                formatter:function(value, row, index) {
                    var values = row.title;
                    var span=document.createElement('span');
                    span.setAttribute('title',row.title);
                    span.innerHTML = row.title;
                    return span.outerHTML;
                }
            },{
                field:"subTitle",
                width:150,
                title:"内容子标题",
                valign:'middle',
                align: 'center',
                class:'colStyle',
                formatter:function(value, row, index) {
                    var values = row.sellPoint;
                    var span=document.createElement('span');
                    span.setAttribute('title',row.title);
                    span.innerHTML = row.sellPoint;
                    return span.outerHTML;
                }
            },{
                field:"titleDesc",
                width:60,
                title:"标题描述",
                class:'colStyle',
                valign:'middle',
                align: 'center',
                formatter:function(value, row, index) {
                    var values = row.price/1000;
                    var span=document.createElement('span');
                    span.innerHTML = values.toFixed(2);
                    return span.outerHTML;
                }
            },{
                field:"url",
                width:70,
                title:"内容连接",
                class:'colStyle',
                valign:'middle',
                align: 'center'
            },{
                field:"pic",
                width:100,
                title:"图片",
                class:'colStyle',
                valign:'middle',
                align: 'center',
                formatter:function(value, row, index) {
                    var values = row.barcode;
                    var span=document.createElement('span');
                    span.setAttribute('title',row.title);
                    span.innerHTML = row.barcode;
                    return span.outerHTML;
                }
            },{
                field:"pic2",
                width:70,
                title:"图片2",
                class:'colStyle',
                valign:'middle',
                align:"center"
            },{
                field:"created",
                width:170,
                title:"创建时间",
                class:'colStyle',
                valign:'middle',
                align: 'center',
                formatter:function(value, row, index) {
                    var values = row.created;
                    var d = new Date(values);
                    var times=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
                    var span=document.createElement('span');
                    span.setAttribute('title',row.title);
                    span.innerHTML = times;
                    return span.outerHTML;
                }
            },{
                field:"updated",
                width:170,
                title:"更新时间",
                class:'colStyle',
                valign:'middle',
                align: 'center',
                formatter:function(value, row, index) {
                    var values = row.updated;
                    var d = new Date(values);
                    var times=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
                    var span=document.createElement('span');
                    span.setAttribute('title',row.title);
                    span.innerHTML = times;
                    return span.outerHTML;
                }
            }]
        })
    })
</script>

</body>
</html>