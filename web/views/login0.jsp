<%--
  Created by IntelliJ IDEA.
  User: olive
  Date: 2020/11/16
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>login</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>
    <link href="../lib/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="../lib/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
    <link href="../lib/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../lib/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="../lib/assets/admin/pages/css/login2.css" rel="stylesheet" type="text/css"/>
    <!-- END PAGE LEVEL SCRIPTS -->
    <!-- BEGIN THEME STYLES -->
    <link href="../lib/assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
    <link href="../lib/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="../lib/assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
    <link href="../lib/assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="../lib/assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
    <!-- END THEME STYLES -->
    <link rel="shortcut icon" href="#"/>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .code
        {
            background-color: silver;
            font-family:Arial; /*设置字体*/
            font-style:initial;
            color:brown;
            font-size:20px;
            border:0;
            padding:2px 3px;
            letter-spacing:3px;
            font-weight:bolder;

            width:81px;
            height:23px;
            margin-left: 120px;
            margin-top: -35px;

        }
    </style>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="login">
<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
<div class="menu-toggler sidebar-toggler">
</div>
<!-- END SIDEBAR TOGGLER BUTTON -->
<!-- BEGIN LOGO -->
<div class="logo">
    <img src="../foto/fly.png" style="height: 150px; width: 200px" alt=""/>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- BEGIN LOGIN FORM -->
    <form class="login-form" method="post" id="form1">
        <div class="form-title">
            <span class="form-title">Welcome.</span>
            <span class="form-subtitle">Please login.</span>
        </div>
        <div class="alert alert-danger display-hide">
            <button class="close" data-close="alert"></button>
            <span>
			Please fill in the user name, the user password, user types and Verification code. </span>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">Username</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username" id="userAccount"/>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Password</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="password" autocomplete="off" placeholder="Password" name="password" id="pwd"/>
        </div>
        <div class="form-group" id="userTypeCheck">
            <div class="col-sm-12 col-sm-offset-1" ><!--col-sm-offset-1偏移1个单位-->
                <label class="radio-inline"><input type="radio" name="usertype" value="学生">student</label> <!--radio-inline复选框一行-->
                <label class="radio-inline"><input type="radio" name="usertype" value="老师">teacher</label>
                <label class="radio-inline"><input type="radio" name="usertype" value="管理员">manager</label>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Verification code</label>
            <input class="form-control form-control-solid placeholder-no-fix" type="text" autocomplete="off" placeholder="Verification code" name="Verification_code"/>
            <div class="code" id="checkCode" οnclick="createCode()" ></div>
        </div>
        <div class="form-actions">
            <button id="login_btn" type="submit" class="btn btn-primary btn-block uppercase">Login</button>
        </div>
        <div class="form-actions">
            <div class="pull-left">
                <label class="rememberme check">
                    <input type="checkbox" name="remember" value="1"/>Remember me </label>
            </div>
            <div class="pull-right forget-password-block">
                <h5>
                    <a href="javascript:;" id="register-btn" class="create-account">Create an account</a>
                </h5>
            </div>
        </div>
    </form>
    <!-- END LOGIN FORM -->
    <!-- BEGIN FORGOT PASSWORD FORM -->
    <form class="forget-form" action="" method="post">
        <div class="form-title">
            <span class="form-title">Forget Password ?</span>
            <span class="form-subtitle">Enter your e-mail to reset it.</span>
        </div>
        <div class="form-group">
            <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Email" name="email"/>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn btn-default">Back</button>
            <button type="submit" class="btn btn-primary uppercase pull-right">Submit</button>
        </div>
    </form>
    <!-- END FORGOT PASSWORD FORM -->
    <!-- BEGIN REGISTRATION FORM -->
    <form class="register-form" id="form2">
        <div class="form-title">
            <span class="form-title">Sign Up</span>
        </div>
        <p class="hint">
            Enter your personal details below:
        </p>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Full Name</label>
            <input class="form-control placeholder-no-fix" type="text" placeholder="Full Name" name="fullname"/>
        </div>
        <div class="form-group">
            <div class="col-sm-12 col-sm-offset-1" ><!--col-sm-offset-1偏移1个单位-->
                <label class="radio-inline"><input type="radio" name="usertype" value="学生">student</label> <!--radio-inline复选框一行-->
                <label class="radio-inline"><input type="radio" name="usertype" value="老师">teacher</label>
                <label class="radio-inline"><input type="radio" name="usertype" value="管理员">manager</label>
            </div>
        </div>
        <div class="form-group">
            <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
            <label class="control-label visible-ie8 visible-ie9">Email</label>
            <input class="form-control placeholder-no-fix" type="text" placeholder="Email" name="email"/>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Phone Number</label>
            <input class="form-control placeholder-no-fix" type="text" placeholder="Phone Number" name="phone_number"/>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Address</label>
            <input class="form-control placeholder-no-fix" type="text" placeholder="Address" name="address"/>
        </div>
        <p class="hint">
            Enter your account details below:
        </p>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Username</label>
            <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="Username" name="username"/>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Password</label>
            <input class="form-control placeholder-no-fix" type="password" autocomplete="off" id="register_password" placeholder="Password" name="password"/>
        </div>
        <div class="form-group">
            <label class="control-label visible-ie8 visible-ie9">Re-type Your Password</label>
            <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="Re-type Your Password" name="rpassword"/>
        </div>
        <div class="form-group margin-top-20 margin-bottom-20">
            <label class="check">
                <input type="checkbox" name="tnc"/>
                <span class="loginblue-font">I agree to the </span>
                <a href="javascript:;" class="loginblue-link">Terms of Service</a>
                <span class="loginblue-font">and</span>
                <a href="javascript:;" class="loginblue-link">Privacy Policy </a>
            </label>
            <div id="register_tnc_error">
            </div>
        </div>
        <div class="form-actions">
            <button type="button" id="register-back-btn" class="btn btn-default">Back</button>
            <button type="submit" id="register-submit-btn" class="btn btn-primary uppercase pull-right">Submit</button>
        </div>
    </form>
    <!-- END REGISTRATION FORM -->
</div>
<!-- END LOGIN -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="../lib/assets/global/plugins/respond.min.js"></script>
<script src="../lib/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="../lib/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../lib/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="../lib/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../lib/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../lib/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="../lib/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="../lib/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../lib/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../lib/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../lib/assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../scripts/login.js" type="text/javascript"></script>
<script src="../scripts/mouse_active.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
    jQuery(document).ready(function() {
        Metronic.init(); // init metronic core components
        Layout.init(); // init current layout
        Login.init();
        Demo.init();
        mouse.init();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>