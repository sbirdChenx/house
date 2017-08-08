<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>高大上租房网 -发布房屋信息</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<style type="text/css">
			.addbutton, .minusbutton { width: 30px; height: 30px; font-size: 18px; }
			.minusbutton { margin-left: 5px; }
			td img { margin:5px 5px; }
		</style>
	</head>
	<body>
		<div id="header" class="wrap">
			<div id="logo"><img src="images/logo.png" /></div>
		</div>
		<div id="regLogin" class="wrap">
			<div class="dialog">
				<dl class="clearfix">
					<dt>新房屋信息发布</dt>
					<dd class="past">填写房屋信息</dd>
				</dl>
				<div class="box">
					<form action="${pageContext.request.contextPath}/add" method="post" enctype="multipart/form-data">
						<div style="color:red;"></div>
						<div class="infos">
							<table class="field">
								<tr>
									<td class="field">标 题：</td>
									<td><input type="text" class="text" name="title"></td>
								</tr>
								<tr>
									<td class="field">户 型：</td>
									<td>
										<select  class="text" name="houseType.id">
											<c:forEach items="${applicationScope.houseType}" var="type">
											<option value="${type.id}">${type.name}</option>
											</c:forEach>
										</select>
								   </td>
								</tr>
								<tr>
									<td class="field">面 积：</td>
									<td><input type="number" class="mtext" name="area"></td>
								</tr>
								<tr>
									<td class="field">楼 层：</td>
									<td>
										<input type="number" name="floor" class="stext" maxlength="3">
										/
										<input type="number" name="totalFloor" class="stext" maxlength="3">
									</td>
								</tr>
								<tr>
									<td class="field">价 格：</td>
									<td><input type="number" class="mtext" name="price"></td>
								</tr>
		
		                        <tr>
									<td class="field">区：</td>
									<td>	
										<select  class="text" name="district.id">
											<c:forEach items="${applicationScope.district}" var="dis">
											<option value='${dis.id}'>${dis.name}</option>
											</c:forEach>
										</select>
		                            </td>
								</tr>
								<tr>
									<td class="field">街 道：</td>
									<td>
										<input type="text" name="street" class="text">
									</td>
								</tr>
								<tr>
									<td class="field">联系人：</td>
									<td><input type="text" class="text" name="contacter.contacterName"></td>
								</tr>
		                        <tr>
									<td class="field">联系电话：</td>
									<td><input type="tel" class="text" name="contacter.contacterTel"></td>
								</tr>
								 <tr>
									<td class="field">QQ：</td>
									<td><input type="text" class="text" name="contacter.contacterQQ"></td>
								</tr>
								 <tr>
									<td class="field">E-mail：</td>
									<td><input type="email" class="text" name="contacter.contacterEmail"></td>
								</tr>
								<tr>
									<td class="field">上传图片：</td>
									<td>
										<input id="file" type="file" class="text" name="primaryPhoto">
										<input id="addbutton" type="button" value="+" class="addbutton">
									</td>
								</tr>
								<tr>
									<td class="field">图片预览：</td>
									<td id="prevArea"><img id="housePhoto" src="" alt="" width="160" height="120"></td>
								</tr>
		                        <tr>
									<td class="field">详细信息：</td>
									<td><textarea name="detail"></textarea></td>
								</tr>
							</table>
							<div class="buttons"><input type="submit" value="立即发布"></div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="footer" class="wrap">
			<dl>
		    	<dt>高大上租房网 &copy; 2014 攀峰科技 川ICP证1000001号</dt>
		        <dd>关于我们 · 联系方式 · 意见反馈 · 帮助中心</dd>
		    </dl>
		</div>
		<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
		<script type="text/javascript">
			$(function() {
				$('#addbutton').on('click', function() {
					var tr = $(this).parent().parent();
					var img = $('<img>').attr('width', 160).attr('height', 120);
					$('#prevArea').append(img);
					var newTr = $('<tr>');
					var newTd1 = $('<td class="field"></td>');
					var newTd2 = $('<td>');
					var fileInput = $('<input type="file" name="primaryPhoto" class="text"/>');
					fileInput.on('change', function() {
						doPreview(this, img);
					});
					var minusButton = $('<input type="button" value="-" class="minusbutton"/>');
					minusButton.on('click', function() {
						newTr.remove();
						img.remove();
					});
					newTd2.append(fileInput);
					newTd2.append(minusButton);
					newTr.append(newTd1);
					newTr.append(newTd2);
					tr.after(newTr);
				});
				
				$('#file').on('change', function() {
					doPreview(this, $('#housePhoto'));
				});
			
			});
			
			function doPreview(source, target) {
				var file = source.files[0];	// 从文件选择器获取选中的文件
				if(/image\/\w+/.test(file.type)) {
					if(window.FileReader) {	// 判断是否支持FileReader
						var fr = new FileReader();	// 创建FileReader对象
						fr.onloadend = function() {	// 绑定回调函数（文件读取完毕时回调）
							target.attr('src', this.result);	// 修改img标签的src属性将图片预览出来
						};
						fr.readAsDataURL(file);	// 根据URL读取文件
					}
				}
				else {
					alert("只能选择图片文件");
					source.value = "";	// 清除文件选择中的内容
					target.attr('src', '');	// 清除图片预览
				}
			}
		</script>
	</body>
</html>