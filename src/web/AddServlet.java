package web;

import control.RegisterControl;
import net.jdbc.test.JDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 添加信息
 * @Date:Create：in 2020/11/19 23:04
 * @Modified By：
 */
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String userAccount = request.getParameter("userAccount");
        String sql = "select * from student where(userAccount='"+userAccount+"')";
        JDBC jdbc = new JDBC();
        PrintWriter out = response.getWriter();
        try {
            ResultSet result = jdbc.result(sql);
            Map map = jdbc.read(result);
            if(map.size()==0){//未查询到用户账号注册过信息
                RegisterControl registerControl = new RegisterControl();//实例化model
                registerControl.setUsername(request.getParameter("fullname"));
                registerControl.setUserType(request.getParameter("user_type"));
                registerControl.setUserAccount(request.getParameter("userAccount"));
                registerControl.setPwd1("111111");
                registerControl.setPhone_number(request.getParameter("phonnumber"));
                registerControl.setEmail_address(request.getParameter("email"));
                registerControl.setWorking_address(request.getParameter("address"));
                String sql1 = "insert into student(userName,userType,phoneNumber,EmailAddress,workAddress,userAccount,passWord) values('"+registerControl.getUsername()+"'"+",'"+registerControl.getUserType()+"','"+registerControl.getPhone_number()+"','"+registerControl.getEmail_address()+"','"+registerControl.getWorking_address()+"','"+registerControl.getUserAccount()+"','"+registerControl.getPwd1()+"')";
                int flag = jdbc.updata(sql1);
                if(flag==1){
                    System.out.println("添加成功");
                    out.print("200");
                }else {
                    System.out.println("网络错误");
                    out.print("500");
                }
            }else {
                out.print("201");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
