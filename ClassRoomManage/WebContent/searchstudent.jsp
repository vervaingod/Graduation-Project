<!DOCTYPE html>
<%@page import="com.classroom.bean.ApplyInfoBean"%>
<%@page import="com.classroom.bean.PeopleBean"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html" charset="utf-8">
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
			欢迎您，<strong>admin</strong>
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
					<li class="sidebar-menuitem"><a href="./FetchAllManageServlet">管理员管理</a></li>
				</ul>
			</div>
			<div class="sidebar-menugroup">
				<div class="sidebar-grouptitle">教室管理</div>
				<ul class="sidebar-menu">
					<li class="sidebar-menuitem"><a href="addclassroom.jsp">添加教室</a></li>
					<li class="sidebar-menuitem"><a href="./FetchAllClassServlet">搜索教室</a></li>
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
		<div class="page-content">
			<div class="content-nav">人员管理 > 搜索学生</div>
			<%
				List<PeopleBean> peopleBeanList = (List<PeopleBean>) request
						.getAttribute("peopleBeanList");
			%>


			<form action="./SearchStudentServlet">
				<fieldset>
					<legend>搜索学生</legend>
					<table class="formtable">
						<tr>
							<td>姓名：</td>
							<td><input type="text" id="name" name="name" maxlength="20" /></td>
							<td>账号名：</td>
							<td><input type="text" id="username" name="username"
								maxlength="20" /></td>
							<td>状态：</td>
							<td><input type="radio" id="state" name="state" value="1"
								checked="checked" /><label>正常</label> <input type="radio"
								id="state" name="state" value="0" /><label>待开放</label> 
							</td>
						</tr>
						<tr>
							<td colspan="6" class="command"><input type="submit"
								class="clickbutton" value="查询" /> <input type="reset"
								class="clickbutton" value="重置" /></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<div>
				<div class="pager-header">
					<div class="header-nav">
						<input type="button" class="clickbutton" value="首页" /> <input
							type="button" class="clickbutton" value="上页" /> <input
							type="button" class="clickbutton" value="下页" /> <input
							type="button" class="clickbutton" value="末页" /> 跳到第<input
							type="text" id="pagenum" class="nav-number" />页 <input
							type="button" class="clickbutton" value="跳转" />
					</div>
				</div>
			</div>
			<table class="listtable">
				<tr class="listheader">
					<th>学号</th>
					<th>姓名</th>
					<th>角色</th>
					<th>所在班级</th>
					<th>联系电话</th>
					<th>电子邮件</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<%
					for (PeopleBean peopleBean : peopleBeanList) {
				%>
				<tr>
					<td><%=peopleBean.getUsername()%></td>
					<td><%=peopleBean.getName()%></td>
					<td>
						<%
							int role = peopleBean.getRole();
								String showrole = "";
								if (role == 0) {
									showrole = "学生";
								} else {
									showrole = "管理员";
								}
						%><%=showrole%></td>
					<td><%=peopleBean.getClassid()%></td>
					<td><%=peopleBean.getPhone()%></td>
					<td><%=peopleBean.getEmail()%></td>
					<td>
						<%
							int state = peopleBean.getState();
								String showstate = "";
								if (state == 0) {
									showstate = "已停用";
								} else {
									showstate = "正常";
								}
						%><%=showstate%></td>
					<td><a class="clickbutton" type="button"
						href="./StartStudentAccountServlet?id=<%=peopleBean.getUsername()%>">启用</a>
						<a class="clickbutton"
						href="./StopStudentAccountServlet?id=<%=peopleBean.getUsername()%>">停用</a></td>
				</tr>

				<%
					}
				%>




			</table>
		</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>