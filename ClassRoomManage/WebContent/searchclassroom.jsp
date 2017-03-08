<!DOCTYPE html>
<%@page import="com.classroom.bean.ClassroomBean"%>
<%@page import="com.classroom.bean.ClassBean"%>
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
					<li class="sidebar-menuitem"><a href="approveaccount.jsp">管理员管理</a></li>
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
		<div class="page-content">
			<div class="content-nav">教室管理 > 搜索教室</div>

			<%
				List<ClassroomBean> classroomBeanList = (List<ClassroomBean>) request
						.getAttribute("classroomBeanlist");
			%>

			<form action="./SearchClassroomServlet">
				<fieldset>
					<legend>搜索会议</legend>
					<table class="formtable">
						<tr>
							<td>教室编号：</td>
							<td><input type="text" id="classroomnumber"
								name="classroomnumber" maxlength="20" /></td>
							<td>教室名称：</td>
							<td><input type="text" id="room" name="room" maxlength="20" /></td>
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
					<th>教室编号</th>
					<th>教室名称</th>
					<th>教室状态</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
				<%
					for (ClassroomBean classroomBean : classroomBeanList) {
				%>
				<tr>
					<td><%=classroomBean.getClassroomnumber()%></td>
					<td><%=classroomBean.getRoom()%></td>
					<td>
						<%
							int state = classroomBean.getState();
								String showstate = "";
								if (state == 0) {
									showstate = "已停用";
								} else {
									showstate = "已启用";
								}
						%><%=showstate%>
					</td>
					<td><%=classroomBean.getNote()%></td>
					<td><a class="clickbutton"
						href="./FetchClassroomByroomnumberServlet?id=<%=classroomBean.getClassroomnumber()%>">查看详情</a></td>
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