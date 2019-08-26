<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<#list  strList as s>
    ${s}<br>
</#list>

<#list strList>
    <#items  as s>
       ${s}<br>
    </#items>
</#list>

</body>
</html>