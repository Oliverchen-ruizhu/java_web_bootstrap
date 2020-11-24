<%--
  Created by IntelliJ IDEA.
  User: olive
  Date: 2020/10/26
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>failed to login</title>
</head>
<body>
<%
    //判断登录逻辑问题
    String failInfo = null;
    if(request.getAttribute("fail_Info")!=null){
        int tag = (int) request.getAttribute("fail_Info");
        switch (tag){
            case 0:failInfo = "密码错误!请检查！！！";break;
            case 1:failInfo = "用户名错误！请检查！！！";break;
            case 2:failInfo = "用户类型错误！请检查！！！";break;
        }
    }
%>
<center>
    <% if (failInfo!=null){%>
    <h1><%=failInfo%></h1>
    <h2><a href="/views/login.jsp">返回登录页面</a></h2>
    <%}else if (request.getAttribute("fail_register")!=null) {%>
    <%
        //判断注册逻辑
            String flag = (String) request.getAttribute("fail_register");
            if (flag.equals("当前账号已注册")) {
                failInfo = "当前账号已注册!!!请检查！！！";
            } else {
                failInfo = "网络错误!!!请稍后！！！";
            }
    %>
    <h1><%=failInfo%></h1>
    <h2><a href="/views/register.jsp">返回注册页面</a></h2>
    <%}else if (request.getAttribute("fail_type")!=null){
        failInfo = "当前登录对象和操作对象不一致!!!无法修改!!!";
    %>
    <h1><%=failInfo%></h1>
    <h2><a href="/views/user_management.jsp">重新选择修改对象</a></h2>
    <%}%>
</center>

</body>
</html>
