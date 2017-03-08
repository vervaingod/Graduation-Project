
<%@page import="java.util.List"%>
<%@page import="com.classroom.bean.ClassroomBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><html>
<head>
<meta http-equiv="content-type" content="text/html" charset="utf-8">
<title>中北大学教室管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
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
		<div class="page-content">
			<div class="content-nav">教室管理 > 查看教室</div>
			<%
				List<ClassroomBean> classroomBeanList = (List<ClassroomBean>) request
						.getAttribute("classroomBeanlist");
			%>
			<table class="listtable">
				<caption>所有教室:</caption>
				<tr class="listheader">

					<th>教室编号</th>
					<th>教室名称</th>
					<th>教室状态</th>
					<th>教室备注</th>
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