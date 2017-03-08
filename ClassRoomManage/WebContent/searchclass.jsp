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
			<div class="content-nav">课程管理 > 查看课程</div>



			<form action="./SearchClassServlet">
				<fieldset>
					<legend>搜索课程</legend>
					<table class="formtable">
						<tr>
							<td>课程编号：</td>
							<td><input type="text" id="classnum" name="classnum"
								maxlength="20" /></td>
							<td>课程名称：</td>
							<td><input type="text" id="classname" name="classname"
								maxlength="20" /></td>
							<td>上课地点：</td>
							<td><input type="text" id="classroom" name="classroom"
								maxlength="20" /></td>
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
			<%
				List<ClassBean> classBeanList = (List<ClassBean>) request
						.getAttribute("classBeanList");
			%>
			<table class="listtable">
				<caption>所有课程信息：</caption>
				<tr class="listheader">
					<th>课程号</th>
					<th>课程名</th>
					<th>上课地点</th>
					<th>当前状态</th>
					<th>操作</th>
				</tr>
				<%
					for (ClassBean classBean : classBeanList) {
				%>
				<tr>
					<td><%=classBean.getClassnum()%></td>
					<td><%=classBean.getClassname()%></td>
					<td><%=classBean.getClassroom()%></td>
					<td>
						<%
							int classstate = classBean.getClassstate();
								String showclassstate = "";
								if (classstate == 0) {
									showclassstate = "停课";
								} else {
									showclassstate = "正常";
								}
						%><%=showclassstate%></td>

					<td><a class="clickbutton"
						href="./StartClassServlet?classnum=<%=classBean.getClassnum()%>">启用</a>
						<a class="clickbutton"
						href="./StopClassServlet?classnum=<%=classBean.getClassnum()%>">停课</a></td>
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