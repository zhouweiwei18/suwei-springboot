<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
<!--引入-->
<#include "../common/commoncss.ftl">
<#include "../common/commonjs.ftl">
<body>
<div style="padding:10px 10px 10px 10px">
    <form class="form-horizontal" style="margin-top: 10px;width:50%" method="post">
        <div class="form-group">
            <label class="col-sm-2 control-label">商品类目:</label>
            <div class="col-sm-10 form-inline">
                <button type="button" class="btn btn-default" id="selectItemCat">选择类目</button>
                <input type="text" style="" id="selectedItemCat" class="form-control" disabled>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">商品标题:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">商品卖点:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">商品价格:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">库存数量:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">条形码:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">商品图片:</label>
            <div class="col-sm-10 form-inline">
                <button type="button" id="picFileUpload" class="btn btn-default">上传图片</button>
                <input type="hidden" name="image"/>
                <img src="http://192.168.25.133/group1/M00/00/00/wKgZhV1UMTuAAahZAAS9l-w-A0M143.jpg" class="img-responsive" alt="Responsive image">
                <img src="http://192.168.25.133/group1/M00/00/00/wKgZhV1UMTuAAahZAAS9l-w-A0M143.jpg" class="img-responsive" alt="Responsive image">
                <img src="http://192.168.25.133/group1/M00/00/00/wKgZhV1UMTuAAahZAAS9l-w-A0M143.jpg" class="img-responsive" alt="Responsive image">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">商品描述:</label>
            <textarea class="form-control" style="margin-left:125px;width: 60%" rows="3"></textarea>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default">提交</button>
                <button type="button" class="btn btn-default">重置</button>
            </div>
        </div>
    </form>
</div>

<!-- 商品类目模态框（Modal） -->
<div class="modal fade" id="itemCat" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">选择类目</h4>
            </div>
            <div class="modal-body">
                <form id="myAuthorityForm" class="form-horizontal">
                    <input type="hidden" id="myAuthorityRole" name="myRoleId">
                    <ul id="treeDemo" class="ztree"></ul>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="closeBtn" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //加载商品类目
    $("#selectItemCat").click(function () {
        $('#itemCat').modal().on('shown.bs.modal',
            function() {
                var zTreeObj;
                // zTree 的参数配置，深入使用请参考 API 文档(setting 配置详解)
                var setting = {
                    async: {
                        enable: true,
                        url: "${ctx.contextPath}/item/cat/list",
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


    })

    //选中树的子节点
    function zTreeOnClick(event, treeId, treeNode) {
        //alert(treeNode.tId + ", " + treeNode.name);
        //获取页面输入框
        $("#selectedItemCat").val(treeNode.name);
        $("#closeBtn").click();
    };

    //上传文件
    var itemAddEditor ;
    //页面初始化完毕后执行此方法
    $(function(){
        //初始化类目选择和图片上传器
        E3.init({fun:function(node){
            }});
    });
</script>

 <script>
     var E3 = {
         // 编辑器参数
         kingEditorParams : {
             //指定上传文件参数名称
             filePostName  : "uploadFile",
             //指定上传文件请求的url。
             uploadJson : '/pic/upload',
             //上传类型，分别为image、flash、media、file
             dir : "image"
         },

         init : function(data){
             // 初始化图片上传组件
             this.initPicUpload(data);
         },
         // 初始化图片上传组件
         initPicUpload : function(data){
             $("#picFileUpload").each(function(i,e){
                 var _ele = $(e);
                 _ele.siblings("div.pics").remove();
                 _ele.after('\
    			<div class="pics">\
        			<ul></ul>\
        		</div>');
                 // 回显图片
                 if(data && data.pics){
                     var imgs = data.pics.split(",");
                     for(var i in imgs){
                         if($.trim(imgs[i]).length > 0){
                             _ele.siblings(".pics").find("ul").append("<li><a href='"+imgs[i]+"' target='_blank'><img src='"+imgs[i]+"' width='80' height='50' /></a></li>");
                         }
                     }
                 }
                 //给“上传图片按钮”绑定click事件
                 $(e).click(function(){
                     var form = $(this).parentsUntil("form").parent("form");
                     //打开图片上传窗口
                     KindEditor.editor(E3.kingEditorParams).loadPlugin('multiimage',function(){
                         var editor = this;
                         editor.plugin.multiImageDialog({
                             clickFn : function(urlList) {
                                 var imgArray = [];
                                 KindEditor.each(urlList, function(i, data) {
                                     imgArray.push(data.url);
                                     form.find(".pics ul").append("<li><a href='"+data.url+"' target='_blank'><img src='"+data.url+"' class=\"img-responsive\" /></a></li>");
                                 });
                                 form.find("[name=image]").val(imgArray.join(","));
                                 editor.hideDialog();
                             }
                         });
                     });
                 });
             });
         },
     };
 </script>
</body>
</html>