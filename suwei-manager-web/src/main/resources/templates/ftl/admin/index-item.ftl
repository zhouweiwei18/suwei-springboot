<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商品索引</title>
    <#include "../common/commoncss.ftl">
    <#include "../common/commonjs.ftl">
</head>
<body>
    <button class="btn-primary" onclick="importItems()">一键添加索引</button>
    <script type="text/javascript">
        function importItems() {
            $.post("/index/item/import",null,function(data){
                if(data.status == 200){
                    alert("提示导入索引库成功！");
                } else {
                    alert("提示导入索引库失败！");
                }
            });
        }
    </script>
</body>
</html>