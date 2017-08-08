<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>高大上租房网 - 首页</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<style type="text/css">
			#headbar { position: relative; top: 35px; left: 300px; }
			.count { display: inline-block; width: 25px; height: 25px; opacity: 0.7; background-color: black; position: relative; left: 60px; top: -40px; }
			.pub { display: inline-block; margin-left: 35px; background-image: url('images/btn_bg.gif'); }
			.pub a { display: inline-block; text-decoration: none; width: 125px; height: 36px; text-align: center; font-size: 16px; line-height: 36px; }
			.pub a:visited, .pub a:link { color: white; }
		</style>
	</head>
	<body>
		<div id="header" class="wrap">
			<div id="logo">
				<img src="images/logo.png" />
			</div>
			<div id="headbar">
				<c:choose>
					<c:when test="${not empty user.username}">
						<a href="">${user.username}</a>&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/cancel">注销</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/autoLogin">用户登录</a>
					</c:otherwise>
				</c:choose>
				<a href="toRegister">快速注册</a>
				<c:if test="${not empty user.username}">
					<div class="pub">
						<a href="${pageContext.request.contextPath}/preAdd">发布房源</a>
					</div>
				</c:if>
			</div>
		</div>
		<div id="navbar" class="wrap">
			<form method="post" action="${pageContext.request.contextPath}/search" id='sform'>
				<div class="search clearfix">
					<div class="fl">
						<ul>
							<li class="bold">房屋信息</li>
							<li>标题：
								<input type="text" class="text" name="title"> 
								<label class="ui-blue"> 
								<input type="submit" name="search" value="搜索房屋">
							</label>
							</li>
						</ul>
					</div>
					<div class="fl">
						<ul>
							<li class="first">价格</li>
							<li>
								<select name='price' id='price'>
									<option value='不限'>不限</option>
									<option value='0-600'>600元以下</option>
									<option value='600-1000'>600元—1000元</option>
									<option value='1000-1500'>1000元—1500元</option>
									<option value='1500-2000'>1500元—2000元</option>
									<option value='2000-3000'>2000元—3000元</option>
									<option value='3000-5000'>3000元—5000元</option>
									<option value='5000-99999999'>5000元以上</option>
								</select>
							</li>
						</ul>
					</div>
					<div class="fl">
						<ul>
							<li class="first">房屋位置</li>
							<li>
								<select name='districtId' id='district'>
									<option value='0'>不限</option>
									<c:forEach items="${district}" var="dis">
										<option value='${dis.id}'>${dis.name}</option>
									</c:forEach>
								</select>
							</li>
						</ul>
					</div>
					<div class="fl">
						<ul>
							<li class="first">房型</li>
							<li>
								<select name='houseTypeId' id='houseType'>
									<option value='0'>不限</option>
									<c:forEach items="${houseType}" var="houseType">
									<option value='${houseType.id}'>${houseType.name}</option>
									</c:forEach>
								</select>
							</li>
						</ul>
					</div>
					<div class="fl">
						<ul>
							<li class="first">面积</li>
							<li>
								<select name='area' id='area'>
									<option value='不限'>不限</option>
									<option value='0-40'>40以下</option>
									<option value='40-80'>40-80</option>
									<option value='80-140'>80-140</option>
									<option value='140-200'>140-200</option>
									<option value='200-1000000'>200以上</option>
								</select>
							</li>
						</ul>
					</div>
				</div>
			</form>
		</div>
		<div class="main wrap">
			<table class="house-list">
				<c:forEach items="${houses}" var="house">
					<tr>
						<td class="house-thumb">
							<div>
								<img src="upload/${house.mainPhoto}" width="160" height="120" alt="">
								<span class="count">1图</span>
							</div>
						</td>
						<td>
							<dl>
								<dt>
									<a href="">${house.title}</a>
								</dt>
								<dd>
									${house.district.name}&nbsp;${house.street}&nbsp;
									${house.area}平米&nbsp;${house.floor}层/${house.totalFloor}层 <br />
									联系人：${house.contacter.contacterName}
									联系方式：${house.contacter.contacterTel}
								</dd>
							</dl>
						</td>
						<td class="house-type">${house.houseType.name}</td>
						<td class="house-price"><span>${house.price}</span>元/月</td>
					</tr>
				</c:forEach>
			</table>
			<div class="pager">
				<ul>
					<li class="current"><a href="index.do?page=1">首页</a></li>
					<c:if test="${currentPage > 1}">
					<li><a href="index.do?page=${currentPage - 1}">上一页</a></li>
					</c:if>
					<c:if test="${currentPage < totalPage}">
					<li><a href="index.do?page=${currentPage + 1}">下一页</a></li>
					</c:if>
					<li><a href="index.do?page=${totalPage}">末页</a></li>
				</ul>
				<span class="total">${currentPage}/${totalPage}页</span>
			</div>
		</div>
		<div id="footer" class="wrap">
			<dl>
				<dt>高大上租房网 &copy; 2013 攀峰科技 川ICP证1000001号</dt>
				<dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
			</dl>
		</div>
	</body>
</html>