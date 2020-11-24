package net.jdbc.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 0.1
 * @Author: oliver chen
 * @Description:  连接MySQL
 * @Date:Create：in 2020/10/19 19:14
 * @Modified By：
 */
public class JDBC {
    static final String DB_URL="jdbc:mysql://localhost:3306/test?serverTimezone=UTC&characterEncoding=utf-8";
    static final String USER = "root";
    static final String PASSWORD="zx236739";
    static ResultSet re = null;
    static Connection connection = null;
    static Statement statement=null;
    public static Connection connection(){
        try{
            //注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库
            connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  connection;
    }
    //查询数据库结果
    public  static ResultSet result(String sql) throws SQLException {
        //List<Map> list =new ArrayList<Map>();//创建list集合用于存入map的键值对集合
        //操作数据库
        if(connection==null){
            JDBC.connection();
        }
        statement = connection.createStatement();//获取操作数据库对象
        ResultSet resultSet = statement.executeQuery(sql);//执行sql，获取结果
        re = resultSet;
        return re;
    }
    //检查当前用户名和密码
    public List<Map> login(String username,String password) throws SQLException {
        List<Map> maps = new ArrayList<Map>();
        if(connection==null){
            JDBC.connection();
        }
        String sql = "select * from student "+"where(userAccount='"+username+"')";
        ResultSet result = JDBC.result(sql);//未查询到不会返回空，引入map判断是否未查询到
        Map map = JDBC.read(result);
        if(map.size()!=0){
            String sql1 = "select passWord from student " + "where(userAccount='"+username+"')";
            ResultSet resultSet = JDBC.result(sql1);
            while (resultSet.next()){
                if(password.equals(resultSet.getString("passWord"))){
                    System.out.println("匹配到用户名");
                    String sql2 = "select * from student "+"where(userAccount='"+username+"')";
                    ResultSet resultSet1 = JDBC.result(sql2);//获取当前用户名的全部信息
                    Map map1 = JDBC.read(resultSet1);
                    maps.add(map1);
                }
            }
        }else {
            map.put("fail_Info","用户名错误");
            maps.add(map);
        }
        return maps;
    }
    //根据查询结果，进行属性赋值
    public static Map read(ResultSet resultSet) throws SQLException {
        Map map = new HashMap();
        while (resultSet.next()){
        map.put("user_id",resultSet.getInt("userId"));
        map.put("user_name",resultSet.getString("userName"));
        map.put("user_type",resultSet.getString("userType"));
        map.put("phone_number",resultSet.getString("phoneNumber"));
        map.put("Email_address",resultSet.getString("EmailAddress"));
        map.put("work_address",resultSet.getString("workAddress"));
        map.put("user_account",resultSet.getString("userAccount"));
        map.put("pass_word",resultSet.getString("passWord"));
        }
        return map;
    }
    //上传用户信息
    public static int updata(String sql) throws SQLException {
        if(connection==null){
            JDBC.connection();
        }
        int count=0;
        count = statement.executeUpdate(sql);//上传成功count为1
        return count;
    }
    public static void close() throws SQLException {
        if(connection!=null){
            connection.close();
        }else if(re!=null){
            re.close();
        }else if(statement!=null){
            statement.close();
        }
    }
    //测试数据库数据
    public static void main(String[] args) {
        List<Map> list =new ArrayList<Map>();//创建list集合用于存入map的键值对集合
        try{
            //注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库
            Connection connection = DriverManager.getConnection(DB_URL,USER,PASSWORD);
            //操作数据库
            Statement statement = connection.createStatement();//获取操作数据库对象
            String sql = "select * from student";
            ResultSet resultSet = statement.executeQuery(sql);//执行sql，获取结果
            while (resultSet.next()){
                int user_id = resultSet.getInt("userId");
                String user_name = resultSet.getString("userName");
                String user_type = resultSet.getString("userType");
                String phone_number = resultSet.getString("phoneNumber");
                String Email_address = resultSet.getString("EmailAddress");
                String work_address = resultSet.getString("workAddress");
                String user_account = resultSet.getString("userAccount");
                String pass_word = resultSet.getString("passWord");
                Map map = new HashMap();
                map.put("user_id",user_id);
                map.put("user_name",user_name);
                map.put("user_type",user_type);
                map.put("phone_number",phone_number);
                map.put("Email_address",Email_address);
                map.put("work_address",work_address);
                map.put("user_account",user_account);
                map.put("pass_word",pass_word);
                list.add(map);
            }
            //输出检查
            for(Map map1 :list){
                System.out.println(map1);
            }
            //关闭结果集、数据库操作对象、数据库连接
            resultSet.close();
            statement.close();
            connection.close();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
