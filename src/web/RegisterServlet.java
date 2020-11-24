package web;

import control.RegisterControl;
import net.jdbc.test.JDBC;

import javax.servlet.ServletException;
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
 * @Description: 注册上传
 * @Date:Create：in 2020/10/27 17:47
 * @Modified By：
 */
public class RegisterServlet {
    public void register(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String userAccount = request.getParameter("username");
        String sql = "select * from student where(userAccount='"+userAccount+"')";
        PrintWriter out = response.getWriter();
        JDBC jdbc = new JDBC();
        ResultSet result = jdbc.result(sql);
        Map map = jdbc.read(result);
        if(map.size()==0){//未查询到用户账号注册过信息
            String[] usertype =request.getParameterValues("usertype");
            String user_type = null;
            for(int i=0;i<usertype.length;i++){
                if(usertype[i].equals("学生")||usertype[i].equals("老师")||usertype[i].equals("管理员")){
                    user_type=usertype[i];
                }
            }
            RegisterControl registerControl = new RegisterControl();//实例化model
            registerControl.setUsername(request.getParameter("fullname"));
            registerControl.setUserType(user_type);
            registerControl.setUserAccount(request.getParameter("username"));
            registerControl.setPwd1(request.getParameter("password"));
            registerControl.setPhone_number(request.getParameter("phone_number"));
            registerControl.setEmail_address(request.getParameter("email"));
            registerControl.setWorking_address(request.getParameter("address"));
            String sql1 = "insert into student(userName,userType,phoneNumber,EmailAddress,workAddress,userAccount,passWord) values('"+registerControl.getUsername()+"'"+",'"+registerControl.getUserType()+"','"+registerControl.getPhone_number()+"','"+registerControl.getEmail_address()+"','"+registerControl.getWorking_address()+"','"+registerControl.getUserAccount()+"','"+registerControl.getPwd1()+"')";
            int flag = jdbc.updata(sql1);
            if(flag==1){
                System.out.println("注册成功");
                out.println("registered successfully!");
                //request.getRequestDispatcher("/views/success.jsp").forward(request,response);
            }else {
                System.out.println("网络错误");
                out.println("network error!");
                //request.setAttribute("fail_register","网络错误");
                //request.getRequestDispatcher("/views/loginfail.jsp").forward(request,response);
            }
        }else{//已注册用户账号过
            out.println("The current account has been registered!");
            //request.setAttribute("fail_register","当前账号已注册");
            //request.getRequestDispatcher("/views/loginfail.jsp").forward(request,response);
        }
    }
}
