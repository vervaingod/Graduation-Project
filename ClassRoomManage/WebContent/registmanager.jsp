
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>中北大学教室管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
<style type="text/css">
</style>
</head>
<body>
	<div class="page-header">
		<div class="header-banner">
			<img src="images/header.png" alt="CoolMeeting" />
		</div>
		<div class="header-title">欢迎访问中北大学教室管理系统</div>
		<div class="header-quicklink">
			<a href="login.jsp">[返回登录]</a>
		</div>
	</div>
	<div class="page-body">
		<div class="page-sidebar">
			<div class="sidebar-menugroup">
				<div class="sidebar-grouptitle">教室申请</div>
				<ul class="sidebar-menu">
					<li class="sidebar-menuitem"><a
						href="./FetchAllApplyinfoServlet">所有申请</a></li>
					<li class="sidebar-menuitem active"><a
						href="./FetchWaitingApplyServlet">待审批</a></li>
					<li class="sidebar-menuitem"><a
						href="./FetchAppledInfoServlet">已通过</a></li>
				</ul>
			</div>
			<div class="sidebar-menugroup">
				<div class="sidebar-grouptitle">人员管理</div>
				<ul class="sidebar-menu">
					<li class="sidebar-menuitem"><a
						href="./FetchAllStudentServlet">学生管理</a></li>
					<li class="sidebar-menuitem"><a href="registmanager.jsp">管理员注册</a></li>
					<li class="sidebar-menuitem"><a href="./FetchAllManageServlet">管理员查看</a></li>
				</ul>
			</div>
			<div class="sidebar-menugroup">
				<div class="sidebar-grouptitle">教室管理</div>
				<ul class="sidebar-menu">
					<li class="sidebar-menuitem"><a href="addclassroom.jsp">添加教室</a></li>
					<li class="sidebar-menuitem"><a
						href="./FetchAllClassroomServlet">搜索教室</a></li>
				</ul>
			</div>
			<div class="sidebar-menugroup">
				<div class="sidebar-grouptitle">课程管理</div>
				<ul class="sidebar-menu">
					<li class="sidebar-menuitem"><a href="./FetchAllClassServlet">查看课程</a></li>
					<li class="sidebar-menuitem"><a href="addclass.jsp">添加课程</a></li>
				</ul>
			</div>
		</div>
		<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%>
		<div class="page-content">
			<div class="content-nav">人员管理 > 管理员注册</div>
			<form action="RegisterManagerServlet" method="post">
				<fieldset>
					<legend>管理员信息</legend>
					<table class="formtable" style="width: 50%">
						<tr>
							<td>姓名：</td>
							<td><input type="text" id="name" maxlength="20" name="name"
								value="<%=request.getAttribute("name") != null ? request
					.getAttribute("name") : ""%>" />
							</td>
						</tr>
						<tr>
							<td>学号：</td>
							<td><input type="text" id="username" maxlength="20"
								name="username"
								value="<%=request.getAttribute("username") != null ? request
					.getAttribute("username") : ""%>" /></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><input type="password" id="password" maxlength="20"
								placeholder="请输入6位以上的密码" name="password"
								value="<%=request.getAttribute("password") != null ? request
					.getAttribute("password") : ""%>" /></td>
						</tr>
						<tr>
							<td>确认密码：</td>
							<td><input type="password" id="confirm" maxlength="20"
								name="pwdconfirm" /></td>
						</tr>
						<tr>
							<td>联系电话：</td>
							<td><input type="text" id="phone" maxlength="20"
								name="phone"
								value="<%=request.getAttribute("phone") != null ? request
					.getAttribute("phone") : ""%>" /></td>
						</tr>
						<tr>
							<td>电子邮件：</td>
							<td><input type="text" id="email" maxlength="20"
								name="email"
								value="<%=request.getAttribute("email") != null ? request
					.getAttribute("email") : ""%>" /></td>
						</tr>
						<tr>
							<td>班级：</td>
							<td><input type="text" id="classid" maxlength="20"
								name="departmentid"
								value="<%=request.getAttribute("classid") != null ? request
					.getAttribute("classid") : ""%>" /></td>
						</tr>
						<tr>
							<td colspan="6" class="command"><input type="submit"
								class="clickbutton" value="注册" /> <input type="submit"
								class="clickbutton" value="重置" /></td>
						</tr>
					</table>
				</fieldset>
			</form>
		</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>