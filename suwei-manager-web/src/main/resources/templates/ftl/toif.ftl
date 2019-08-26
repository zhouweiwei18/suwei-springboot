<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
名字为:${name}
<#if age<20>
    青年
    <#elseif age<40>
      中年
    <#else>
    老年
</#if>

</body>
</html>