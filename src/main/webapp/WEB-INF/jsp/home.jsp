<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:shiro="http://www.pollix.at/thymeleaf/shiro">
<head>
    <meta charset="GBK">
    <title>Title</title>
</head>
<body>
jsp home

<br>

<a shiro:hasPermission="user:add" href="createUser.html">添加用户</a>


</body>
</html>