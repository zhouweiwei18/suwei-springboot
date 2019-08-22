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
<div style="padding:10px 10px 10px 10px">
    <form class="form-horizontal" style="margin-top: 10px;width:50%" method="post" id="contentAddForm">
        <input type="hidden" name="categoryId" value="${id}"/>
        <div class="form-group">
            <label class="col-sm-2 control-label">内容标题:</label>
            <div class="col-sm-10">
                <input type="text" name="title" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">内容子标题:</label>
            <div class="col-sm-10">
                <input type="text" name="title" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">内容描述:</label>
            <div class="col-sm-10">
                <textarea name="sellPoint" rows="3" class="form-control"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">URL:</label>
            <div class="col-sm-10">
                <input type="text" name="url" class="form-control">
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">商品图片:</label>
            <div class="col-sm-10 form-inline">
                <button type="button" id="onePicUpload" class="btn btn-default">上传图片</button>
                <input type="hidden" name="image"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">商品描述:</label>
            <div class="col-sm-10 form-inline">
                <textarea  id="desc" name="desc"></textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="button" class="btn btn-default" onclick="submitForm()">提交</button>
                <button type="button" class="btn btn-default" onclick="clearForm()">重置</button>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    //上传文件
    var contentAddEditor ;
    //页面初始化完毕后执行此方法
    $(function(){
        //创建富文本编辑器
        contentAddEditor = E3.createEditor("#contentAddForm [name=desc]");

        //初始化类目选择和图片上传器
        E3.initOnePicUpload();
    });

    //提交表单
    function submitForm(){
        //有效性验证
        //校验(暂且没做)
        //取商品价格，单位为“分”
        $("#contentAddForm [name=price]").val(eval($("#contentAddForm [name=priceView]").val()) * 100);
        //同步文本框中的商品描述
        contentAddEditor.sync();

        $.post("/content/save",$("#contentAddForm").serialize(), function(data){
            if(data.status == 200){

                alert("添加成功！")

            }
        });
    }

    function clearForm(){
        $("#contentAddForm")[0].reset();
        $("#form-inline").remove();
    }
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

        initOnePicUpload : function(){
            $("#onePicUpload").click(function(){
                var _self = $(this);
                KindEditor.editor(E3.kingEditorParams).loadPlugin('image', function() {
                    this.plugin.imageDialog({
                        showRemote : false,
                        clickFn : function(url, title, width, height, border, align) {
                            var input = _self.siblings("input");
                            input.parent().find("img").remove();
                            input.val(url);
                            input.after("<a href='"+url+"' target='_blank'><img src='"+url+"' width='80' height='50'/></a>");
                            this.hideDialog();
                        }
                    });
                });
            });
        },

        // 初始化图片上传组件
        initPicUpload : function(data){
            $("#picFileUpload").each(function(i,e){
                var _ele = $(e);
                _ele.siblings("#form-inline").remove();
                _ele.after('<div class="col-sm-11 form-inline" style="margin-top: 5px;height: auto;" id="form-inline"></div>');
                // 回显图片
                if(data && data.pics){
                    var imgs = data.pics.split(",");
                    for(var i in imgs){
                        if($.trim(imgs[i]).length > 0){
                            _ele.siblings("#form-inline").append("<a href='"+imgs[i]+"' target='_blank'><img src='"+imgs[i]+"'/></a>");
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
                                    form.find("#form-inline").append("<a href='"+data.url+"' target='_blank'><img src='"+data.url+"' class='img-rounded' style='float: left;width: 60px;'/></a>");
                                });
                                form.find("[name=image]").val(imgArray.join(","));
                                editor.hideDialog();
                            }
                        });
                    });
                });
            });
        },
        createEditor : function(select){
            return KindEditor.create(select, E3.kingEditorParams);
        }
    };
</script>
</body>
</html>