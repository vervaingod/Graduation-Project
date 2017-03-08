<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="com.classroom.bean.ApplyInfoBean"%>
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
			<div class="content-nav">教室申请> 已通过</div>
			<%
				List<ApplyInfoBean> applyInfoBeanList = (List<ApplyInfoBean>) request
						.getAttribute("applyInfoBeanList");
			%>
			<table class="listtable">
				<caption>已审批信息：</caption>
				<tr class="listheader">
					<th>申请编号</th>
					<th>申请人</th>
					<th>申请教室</th>
					<th>教室状态</th>
					<th>申请状态</th>
					<th>评价</th>
					<th>操作</th>
				</tr>
				<%
					for (ApplyInfoBean applyInfoBean : applyInfoBeanList) {
				%>
				<tr>
					<td><%=applyInfoBean.getId()%></td>
					<td><%=applyInfoBean.getApplystudent()%></td>
					<td><%=applyInfoBean.getApplyclassroom()%></td>
					<td>
						<%
							int roomstate = applyInfoBean.getRoomstate();
								String showroomstate = "";
								if (roomstate == 0) {
									showroomstate = "忙碌";
								} else {
									showroomstate = "空闲";
								}
						%><%=showroomstate%>
					</td>

					<td>
						<%
							int applystate = applyInfoBean.getApplystate();
								String showapplystate = "";
								if (applystate == 0) {
									showapplystate = "待审批";
								} else if (applystate == -1) {
									showapplystate = "已拒绝";
								} else {
									showapplystate = "已通过";
								}
						%><%=showapplystate%></td>
					<td><%=applyInfoBean.getRemark()%></td>
					<td><a class="clickbutton"
						href="./EvaluateApplyServlet?id=<%=applyInfoBean.getId()%>">评价</a>
						<a class="clickbutton"
						href="./DeleteApplyServlet?id=<%=applyInfoBean.getId()%>">删除记录</a></td>
				</tr>
				<%
					}
				%>

			</table>
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
		</div>
	</div>
	<div class="page-footer">
		<hr />
		更多问题，欢迎联系<a href="mailto:webmaster@eeg.com">管理员</a> <img
			src="images/footer.png" alt="CoolMeeting" />
	</div>
</body>
</html>