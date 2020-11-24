package web;

import control.RegisterControl;
import net.jdbc.test.JDBC;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 修改信息
 * @Date:Create：in 2020/11/18 22:19
 * @Modified By：
 */

public class AlterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);//调用doPost方法
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        JDBC jdbc =new JDBC();
        PrintWriter out = response.getWriter();
        RegisterControl registerControl = new RegisterControl();
        registerControl.setUserAccount(request.getParameter("userAccount"));
        registerControl.setUsername(request.getParameter("fullname"));
        registerControl.setPhone_number(request.getParameter("phonnumber"));
        registerControl.setEmail_address(request.getParameter("email"));
        registerControl.setWorking_address(request.getParameter("address"));
        String sql = "update student set userName='"+registerControl.getUsername()+"',phoneNumber='"+registerControl.getPhone_number()+"',EmailAddress='"+registerControl.getEmail_address()+"',workAddress='"+registerControl.getWorking_address()+"' where(userAccount='"+registerControl.getUserAccount()+"');";
        try {
            int flag = jdbc.updata(sql);
            if(flag==1){
                out.print("200");
            }else {
                out.print("500");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
