package web;

import net.jdbc.test.JDBC;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description: 初始化加载数据库信息
 * @Date:Create：in 2020/11/18 15:20
 * @Modified By：
 */
public class InitializeServlet extends HttpServlet {
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
        String sql = "select * from student";
        PrintWriter out = response.getWriter();
        try {
            ResultSet result = jdbc.result(sql);
            if (result!=null){
                int flag=1;
                while (result.next()){
                    out.println(
                            "                   <tr id=\"userAccount\" value="+result.getString("userAccount")+">" +
                            "                    <td >"+result.getString("userAccount")+"</td>" +
                            "                    <td class=\"center\">"+result.getString("userName")+"</td>" +
                            "                    <td>"+result.getString("userType")+"</td>" +
                            "                    <td class=\"center\">"+result.getString("phoneNumber")+"</td>" +
                            "                    <td>"+result.getString("EmailAddress")+"</td>" +
                            "                    <td class=\"center\">"+result.getString("workAddress")+"</td>" +
                            "                    <td><a class=\"edit\" href=\"javascript:;\">Edit </a></td>" +
                            "                    <td><a class=\"delete\" href=\"javascript:;\">Delete </a></td>" +
                            "                    </tr>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
