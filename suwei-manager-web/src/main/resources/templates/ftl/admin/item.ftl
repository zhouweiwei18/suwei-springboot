<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<!--引入-->
<#include "../common/commoncss.ftl">
<#include "../common/commonjs.ftl">
    <style>
        .colStyle {
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
        }
    </style>
<body>

<div id="toolbar">
    <button id="btn_add" type="submit" style="float: left;margin-left: 5px;" class="btn btn-default"><a href="${ctx.contextPath}/itemPage/item-add">添加</a></button>
    <button id="btn_edit" type="submit" style="float: left;margin-left: 5px;" class="btn btn-default">编辑</button>
    <button id="btn_del" type="submit" style="float: left;margin-left: 5px;" class="btn btn-default">删除</button>
    <button id="btn_sj" type="submit" style="float: left;margin-left: 5px;" class="btn btn-default">上架</button>
    <button id="btn_xj" type="submit" style="float: left;margin-left: 5px;" class="btn btn-default">下架</button>

</div>
<!--显示表格的内容-->
<table id="tb_admin" style='table-layout:fixed;'></table>

<script type="text/javascript">
    $(function(){
        //表格内容的数据展示
        $("#tb_admin").bootstrapTable({
            url:"${ctx.contextPath}/item/list",//访问地址
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
                title:"商品标题",
                class:'colStyle',
                width:150,
                valign:'middle',
                align: 'center',
                formatter:function(value, row, index) {
                    //console.log(value);
                    var values = row.title;
                    var span=document.createElement('span');
                    span.setAttribute('title',row.title);
                    span.innerHTML = row.title;
                    return span.outerHTML;
                }
            },{
                field:"sellPoint",
                width:150,
                title:"商品卖点",
                class:'colStyle',
                formatter:function(value, row, index) {
                    var values = row.sellPoint;
                    var span=document.createElement('span');
                    span.setAttribute('title',row.title);
                    span.innerHTML = row.sellPoint;
                    return span.outerHTML;
                }
            },{
                field:"price",
                width:60,
                title:"商品价格",
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
                field:"num",
                width:70,
                title:"库存数量",
                class:'colStyle',
                valign:'middle',
                align: 'center'
            },{
                field:"barcode",
                width:100,
                title:"商品条形码",
                class:'colStyle',
                valign:'middle',
                align: 'center',
                formatter:function(value, row, index) {
                    //console.log(value);
                    var values = row.barcode;
                    var span=document.createElement('span');
                    span.setAttribute('title',row.title);
                    span.innerHTML = row.barcode;
                    return span.outerHTML;
                }
            },{
                field:"cid",
                width:70,
                title:"叶子类目",
                class:'colStyle',
                valign:'middle',
                align:"center"
            },{
                field:"status",
                width:80,
                title:"商品状态",
                class:'colStyle',
                valign:'middle',
                align: 'center',
                formatter:function(value, row, index) {
                    var status = null;
                    //判断商品状态
                    // 商品状态，1-正常，2-下架，3-删除
                    if(value==1){
                        status = "正常";
                    }else if(value==2){
                        status = "下架";
                    }else if (value==3){
                        status = "删除";
                    }
                    var span=document.createElement('span');
                    span.innerHTML = status;
                    return span.outerHTML;
                }
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
            }/*,{
                title:"操作",
                width:200,
                valign:'middle',
                align:"center",
                formatter:function(value,row,index){
                    var  updateValue = '<button type="button" onclick="updateFun('+row.id+')" class="btn btn-success">修改</button>';
                    var  deleteValue = '<button type="button" onclick="deleteFun('+row.id+')" class="btn btn-success">删除</button>';
                    return updateValue+deleteValue;
                }
            }*/]
        })
    })
</script>

</body>
</html>