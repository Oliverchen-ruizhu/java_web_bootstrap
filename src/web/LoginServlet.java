package web;

import net.jdbc.test.JDBC;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 登录验证和控制
 * @Date:Create：in 2020/10/12 17:27
 * @Modified By：
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);//调用doPost方法
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        LoginServlet servlet = new LoginServlet();
        RegisterServlet register = new RegisterServlet();
        String tag = request.getParameter("phone_number");
        if(tag==null){//登录页面没有电话号码属性
            servlet.login(request,response);
        }else {//注册页面有电话号码属性
            try {
                register.register(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }
    public void login(HttpServletRequest request,HttpServletResponse response){
        try{
            //向服务器发送请求，获取数据
            String user = request.getParameter("username");
            String remember_pwd = request.getParameter("remember");//记住用户密码标记
            String pwd = request.getParameter("password");
            JDBC jdbc = new JDBC();
            List<Map> re= jdbc.login(user,pwd);
            PrintWriter out = response.getWriter();
            int failInfo = 0;
            String failStr = null;
            if (re.size()!=0){
                for (Map map1:re){
                    for (Object k : map1.keySet())
                    {
                        if(k.equals("fail_Info")){
                            failStr= (String) map1.get(k);
                        }
                    }
                }
                if(failStr==null){
                    String usertype = null;
                    String [] strusertype = request.getParameterValues("usertype");
                    for (Map map1:re){
                        for (Object k : map1.keySet())
                        {
                            if(k.equals("user_type")){
                                usertype=(String) map1.get(k);//用户类型
                            }
                        }
                    }
                    for(int i=0;i<strusertype.length;i++){
                        if(!usertype.equals(strusertype[i])){
                            failStr="用户类型错误";
                        }
                    }
                    if(failStr==null) {
                        String username = null;
                        for (Map map1:re){
                            for (Object k : map1.keySet())
                            {
                                if(k.equals("user_name")){
                                    username= (String) map1.get(k);
                                }
                            }
                        }
                        /*
                        out.println("<HTML>");
                        out.println("<HEAD>");
                        out.println("<TITLE>Servlet实例</TITLE>");
                        out.println("</script>");
                        out.println("</HEAD>");
                        out.println("<BODY>");
                        out.println("<CENTER>");
                        out.println("<H1>欢迎用户（" + usertype + "）:");
                        out.println(username + "</H1>");
                        out.println("<H3>页面正在跳转</H3>");
                        String userType = "0";
                        switch (usertype){
                            case "学生":userType="0";break;
                            case "老师":userType="1";break;
                            case "管理员":userType="2";break;
                        }
                        //该跳转方法能使用url进行传值，但request.setAttribute将无法传值，3秒后跳转
                        response.setHeader("refresh", "3;url=/views/userInfo.jsp?username=" + user + "&password=" + pwd + "&remember=" + remember_pwd+"&userType="+userType);
                        out.println("</CENTER>");
                        out.println("</BODY>");
                        out.println("</HTML>");*/
                        out.print(username);
                    }else{
                        System.out.println("用户名错误！请检查！！！");
                        failInfo=2;
                        out.print(failInfo);
                        //request.setAttribute("fail_Info",failInfo);
                        //request.getRequestDispatcher("/views/loginfail.jsp").forward(request,response);
                    }
                }else {
                    System.out.println("用户类型错误！请检查！！！");
                    failInfo=1;
                    out.print(failInfo);
                    //request.setAttribute("fail_Info",failInfo);
                    //request.getRequestDispatcher("/views/loginfail.jsp").forward(request,response);
                }
            }else {
                System.out.println("登录失败！！(密码错误)");
                out.print(failInfo);
                //request.setAttribute("fail_Info",failInfo);
                //request.getRequestDispatcher("/views/loginfail.jsp").forward(request,response);
            }
            out.flush();
            out.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
