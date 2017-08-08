<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>高大上租房网 - 用户注册</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">
		<img src="images/logo.png" />
	</div>
</div>
<div id="regLogin" class="wrap">
	<div class="dialog">
		<dl class="clearfix">
			<dt>新用户注册</dt>
			<dd class="past">填写个人信息</dd>
		</dl>
		<div class="box">
			<form action="${pageContext.request.contextPath}/registe" method="post">
				<div class="infos">
					<table class="field">
						<tr>
							<td>&nbsp;</td>
							<td style="color: red">${hint}</td>
							<td style="color: red"></td>
						</tr>
						<tr>
							<td class="field">用 户 名：</td>
							<td>
								<input id="username" type="text" class="text" name="username">
								<span id="isUnique"></span></td>
						</tr>
						<tr>
							<td class="field">密 码：</td>
							<td><input id="pwd" type="password" class="text" name="password"></td>
						</tr>
						<tr>
							<td class="field">确认密码：</td>
							<td><input id="repwd" type="password" class="text" name="repassword">
								<span id="isSame"></span></td>
						</tr>
						<tr>
							<td class="field">电 话：</td>
							<td><input id="tel" type="text" class="text" name="tel"></td>
						</tr>
						<tr>
							<td class="field">用户姓名：</td>
							<td><input id="realname" type="text" class="text" name="realname"></td>
						</tr>
						<tr>
							<td class="field">验 证 码：</td>
							<td>
								<input type="text" class="text" name="code">
								<img id="code" src="${pageContext.request.contextPath}/captcha" width="80" height="25">
							</td>
						</tr>
						<tr>
							<td><span id="codeError"></span></td>
						</tr>
					</table>
					<div class="buttons">
						<input type="submit" name="submit" value="立即注册" />
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="footer" class="wrap">
	<dl>
		<dt>高大上租房网 &copy; 2013 攀峰科技 川ICP证1000001号</dt>
		<dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
	</dl>
</div>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
    $(function(){
        $('#code').on('click',function(){
            this.src = '${pageContext.request.contextPath}/captcha?' + Math.random();
        });
        var hasName=false;
        $('#username').on('blur', function() {
            var username = $(this).val();
            if(username!=""){
				/*$.getJSON('${pageContext.request.contextPath}/validate', {
				 "username" : username
				 }, function(data) {
				 alert(data)
				 data.flag ? showErrorHint() : showCorrectHint();
				 });*/
                $.ajax({
                    url:"${pageContext.request.contextPath}/validate",
                    data:{'username':username},
                    type:"get",
                    dataType:"json",
                    success:function(data){
                        if(data.flag){
                            showCorrectHint();
                            hasName = false;
                        }else{
                            showErrorHint();
                            hasName = true;
                        }
                    }
                });
            }
        });
		/* $("#repwd").on("blur",function(){
		 var repassword = $(this).val();
		 var password = $("#pwd").val();
		 if(repassword != password){
		 $('#isSame').text("请输入相同密码")
		 }else{
		 $('#isSame').text("")
		 }
		 }); */
        var codeFlag = true;
        $("input[name='code']").on('blur',function(){
            var code = $(this).val();
            if(code!=""){
                $.ajax({
                    url:"${pageContext.request.contextPath}/vcaptch",
                    data:{"code":code},
                    type:"post",
                    dataType:"json",
                    success:function(data){
                        if(data.flag){
                            $("#codeError").html("正确").css("color","green");
                            codeFlag=true;
                        }else{
                            $("#codeError").html("验证码输入错误").css("color","red");
                            codeFlag=false;
                        }
                    }
                });
            }
        });
        $("form").submit(function(){
            var username = $("#username").val();
            var pwd = $("#pwd").val();
            var repwd = $("#repwd").val();
            var tel = $("#tel").val();
            var realname = $("#realname").val();
            var code = $("input[name='code']").val();

            var usernameReg = /^[a-zA-Z\d]\w{3,11}[a-zA-Z\d]$/;
            var pwdReg = /^(\w){6,20}$/;
            var telReg = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;

            if(!usernameReg.test(username)||username==""){
                alert("请输入正确的用户名格式。");
                $("#username").focus();
                return false;
            }else if(hasName){
                $("#username").focus();
                return false;
            }else if(!pwdReg.test(pwd)||pwd==""){
                alert("请输入正确的密码格式。");
                $("#pwd").focus();
                return false;
            }else if(pwd!=repwd){
                alert("两次输入的密码不相同");
                $("#repwd").focus();
                return false;
            }else if(!telReg.test(tel)||tel==""){
                alert("请输入正确的电话号码格式");
                $("#tel").focus();
                return false;
            }else if(realname==""){
                alert("姓名不能为空");
                $("#realname").focus();
                return false;
            }else if(code==""){
                alert("请输入验证码");
                $("input[name='code']").focus();
                return false;
            }else if(!codeFlag){
                getCaptch();
                $("input[name='code']").focus();
                return false;
            }
        });
        function getCaptch(){
            $('#code').on('click',function(){
                this.src = '${pageContext.request.contextPath}/captcha?' + Math.random();
            });
        }
        function showErrorHint() {
            $('#isUnique').text('X').css('color', 'red');
        }
        function showCorrectHint() {
            $('#isUnique').text('√').css('color', 'green');
        }
    });
</script>
</body>
</html>

