<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ taglib prefix="s" uri="/struts-tags"%>--%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>高大上租房网 - 用户登录</title>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<div id="header" class="wrap">
	<div id="logo">
		<img src="images/logo.png" />
	</div>
</div>
<div id="regLogin" class="wrap">
	<div class="dialog">
		<div class="box">
			<h4>用户登录</h4>
			<form action="${pageContext.request.contextPath}/login" method="post">
				<div class="infos">
					<table class="field">
						<tr>
							<td>&nbsp;</td>
							<%--<td style="color: red;">${hint}</td>--%>
							<td id="error" style="color: red;">${error}</td>
						</tr>
						<tr>
							<td class="field">用 户 名：</td>
							<td>
								<c:if test="${not empty cookie.uid}">
									<c:set var="currentUid" value="${cookie.uid.value}" />
								</c:if>
								<c:if test="${not empty username}">
									<c:set var="currentUid" value="${username}" />
								</c:if>
								<input id="username" type="text" class="text" name="username" value="${currentUid}">
							</td>
						</tr>
						<tr>
							<td class="field">密 码：</td>
							<td>
								<input id="password" type="password" class="text" name="password">
							</td>
						</tr>
						<tr>
							<td class="field">验 证 码：</td>
							<td>
								<input id="codeStr" type="text" class="text" name="code">
								<img id="code" src="${pageContext.request.contextPath}/captcha" width="80" height="30">
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
								<input type="checkbox" name="save">
								<label>下次自动登录</label>
							</td>
						</tr>
					</table>
					<div class="buttons">
						<input type="submit" value="立即登录">
						<input id="toRegBtn" type="button" value="注册">
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
<script>
    $(function(){
        $('#code').on('click',function(){
            this.src = '${pageContext.request.contextPath}/captcha?' + Math.random();
        });
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
                            $("#error").html("验证码正确").css("color","green");
                            codeFlag=true;
                        }else{
                            $("#code").src = '${pageContext.request.contextPath}/captcha?' + Math.random();
                            $("#error").html("验证码输入错误").css("color","red");
                            codeFlag=false;
                        }
                    }
                });
            }
        });
    });
</script>
</body>
</html>


