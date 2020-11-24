<%--
  Created by IntelliJ IDEA.
  User: olive
  Date: 2020/11/13
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        /*web background*/
        .container{
            display:table;
            height:100%;
        }

        .row{
            display: table-cell;
            vertical-align: middle;
        }
        /* centered columns styles */
        .row-centered {
            text-align:center;
        }
        .col-centered {
            display:inline-block;
            float:none;
            text-align:left;
            margin-right:-4px;
        }
        .form-horizontal{
            padding: 20px 10px 10px 100px ;
            font-size: 18px;
            /*background: #0c91e5;*/

        }
        body{
            margin: 12px auto;
            background-repeat:no-repeat;
            background: #1ab8db;
        }
    </style>
    <script language="JavaScript">
        //监听复选框，并设置每次只能点击一个
        $(function(){
            $("#userTypeCheck").find('input[type=checkbox]').bind('click', function () {
                $(this).attr("checked", true);
                $('#userTypeCheck').find('input[type=checkbox]').not(this).attr("checked", false);
            })
        });
        function loin(){

            var user_str=$("#userAccount").val();

            var password_str=$("#pwd").val();

            var check_str=$("#check").val();

            var tag=false;

            if (user_str===null||user_str===undefined||user_str===""){
                alert("用户名为空");
                tag = false;
            }
            else if(password_str===null||password_str===undefined||password_str===""){
                alert("密码为空");
                tag = false;
            }
            else if (check_str===null||check_str===undefined||check_str===""){
                alert("验证码为空");
                tag = false;
            }
            else if (check_str!=="uwx6"){
                alert("验证码错误");
                tag = false;
            }else if($("input[name='usertype']:checked").length==0){
                alert("请选择您的用户类型");
                tag= false;
            }
            else{
                tag = true;
            }
            if (tag) {
                var data = $('#form1').serialize();
                //序列化获得表单数据，结果为：userAccount=&pwd=&usertype

                var submitData = decodeURIComponent(data, true);
                //submitData是解码后的表单数据，结果同上
                $.ajax({
                type:"POST",
                url:"/servlet/loginServlet",
                async:true,//true异步传输,false同步传输
                data:submitData,
                    beforeSend:function(){
                        //请求前
                        $("#login_btn").attr({disabled:"disabled"});//防止重复提交
                        //弹出modal框
                        $('#modal_sm_black_content').html('<img src="/foto/loading.gif" width="18" height="18" class="mr-2">正在处理，请稍后');
                        $("#modal_sm_black").modal("show");
                    },
                    success:function(result){
                        //请求成功时
                        $('#modal_sm_black').modal('hide');
                        alert(result+"操作成功");
                    },
                    complete: function(){
                        $("#login_btn").removeAttr('disabled');
                        $('#modal_sm_black').modal('hide');
                    },
                    error:function(){
                        //请求失败时
                        alert(result.responseText);
                    }
            });
            }
        }
    </script>
</head>
<body>
    <div class="container"><!--容器-->
        <div class="row row-centered">
            <div class="col-sm-12 col-centered">
                <form id="form1" class="form-horizontal" role="form" onsubmit="loin()">
                    <div class="form-group">
                         <%--@declare id="name"--%><label for="name" class="col-sm-1">账号：</label>
                             <div class="col-sm-4">
                                 <input type="text" class="form-control" id="userAccount" name="userAccount" placeholder="请输入您的账户">
                             </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1">密码：</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="pwd" name="pwd" placeholder="请输入您的密码">
                        </div>
                    </div>
                    <div class="form-group" id="userTypeCheck">
                        <div class="col-sm-12 col-sm-offset-1"><!--col-sm-offset-1偏移1个单位-->
                            <div class="radio-inline"><input type="radio" class="usertype" name="usertype" value="学生">学生</div><!--checkbox-inline复选框一行-->
                            <div class="radio-inline"><input type="radio" class="usertype" name="usertype" value="老师">教师</div>
                            <div class="radio-inline"><input type="radio" class="usertype" name="usertype" value="管理员">管理员</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-1 col-sm-offset-1">
                            <td><label>验证码</label></td>
                        </div>
                            <div class="col-sm-2">
                                <input id="check" type="text" class="form-control col-sm-2">
                            </div>
                        <img src="../foto/timg.jpg" alt="验证码" width="50" height="25" class="img-responsive">
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12 col-sm-offset-1">
                            <button type="submit" id="login_btn" class="btn btn-info" >提交</button>
                            <button type="reset" class="btn btn-default col-sm-offset-1" id="reset">重置</button>
                        </div>
                    </div>
                    <div class="register-form col-sm-offset-1">
                        <p>
                            <a href="register.jsp" id="register-btn">Create an account</a>
                        </p>
                    </div>
                </form>

            </div>
        </div>
    </div>
    <!--modal框：-->
    <div class="modal" id="modal_sm_black" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog modal-sm">
            <div class="modal-content bg-opacity">
                <div class="modal-body py-3 text-white text-center text-md" id="modal_sm_black_content"></div>
            </div>
        </div>
    </div>
</body>
</html>
