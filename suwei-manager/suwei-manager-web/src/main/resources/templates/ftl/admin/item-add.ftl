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
            <div class="col-sm-10">
                <button type="button" class="btn btn-default" id="selectItemCat">选择类目</button>
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
            <div class="col-sm-10">
                <button type="submit" class="btn btn-default">上传图片</button>
                <input type="hidden" name="image"/>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="btnSaveAthority" type="button" class="btn btn-primary">保存</button>
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
                    check: {
                        enable: true//复选框
                    },
                    view: {
                        showLine: false
                    },
                    async: {
                        enable: true,
                        url: "${ctx.contextPath}/item/cat/list?id=0"  //访问json数据
                    }
                };
                $(document).ready(function(){
                    zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, null);
                });
            })
    })
</script>

</body>
</html>