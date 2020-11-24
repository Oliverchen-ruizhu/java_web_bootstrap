package web;

import net.jdbc.test.JDBC;

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
 * @Description: 删除信息
 * @Date:Create：in 2020/11/18 19:39
 * @Modified By：
 */
public class DeleteServlet extends HttpServlet {
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
        String user=request.getParameter("userAccount");
        String sql = "delete from student where(userAccount='"+user+"');";
        PrintWriter out = response.getWriter();
        try {
            int flag = jdbc.updata(sql);
            //int flag=1;
            if(flag==1){
                out.print(200);
            }else {
                out.print(500);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
