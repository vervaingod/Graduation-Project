<!DOCTYPE html>
<%@page import="com.equipment.bean.PageBean"%>
<%@page import="com.equipment.bean.PermissionBean"%>
<%@page import="java.util.List"%>
<%@page import="com.equipment.dao.PermissionDaoImpl"%>
<%@page import="com.equipment.dao.PermissionDao"%>
<%@page import="com.equipment.dao.PeopleDaoImpl"%>
<%@page import="com.equipment.dao.PeopleDao"%>
<%@page import="com.equipment.bean.PeopleBean"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html" charset="utf-8">
<title>公安网设备维修管理管理系统</title>
<link rel="stylesheet" href="styles/common.css" />
</head>
<body>
	<%
		PeopleBean peopleBean = (PeopleBean) request.getSession()
				.getAttribute("session_peoplebean");
		String user_name = peopleBean.getUser_username();
		PermissionBean permission = new PermissionBean();
		PermissionDao permissionDao = new PermissionDaoImpl();
		int permission_role = peopleBean.getUser_role();
		permission = permissionDao.findPermissionByRole(permission_role);
	%>

	<div class="page-header">
		<div class="header-banner">
			<img src="images/header.png" alt="CoolMeeting" />
		</div>
		<div class="header-title">欢迎访问公安网设备维修管理系统</div>
		<div class="header-quicklink">
			欢迎您，<strong><%=user_name%></strong>
		</div>
	</div>
	<div class="page-body">
		<div class="page-sidebar">
			<div class="sidebar-menugroup">
				<div class="sidebar-grouptitle">
					<a href="./WorkbenchServlet">工作台</a>
				</div>
			</div>
			<div class="sidebar-menugroup">
				<div class="sidebar-grouptitle">系统设置</div>
				<ul class="sidebar-menu">
					<%
						String show_information = null;
						if (permission.getPermission_information() == 1) {
							show_information = "block";
						} else {
							show_information = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display:<%=show_information%>">
						<a href="basicinfomation.jsp">基本信息</a>
					</li>
					<%
						String show_account = null;
						if (permission.getPermission_account() == 1) {
							show_account = "block";
						} else {
							show_account = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_account%>"><a
						href="./FetchUserByRoleServlet">内部账号</a></li>

					<%
						String show_department = null;
						if (permission.getPermission_department() == 1) {
							show_department = "block";
						} else {
							show_department = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_department%>"><a
						href="./OrganizationDepartmentServlet">组织部门</a></li>

					<%
						String show_fail_type = null;
						if (permission.getPermission_fail_type() == 1) {
							show_fail_type = "block";
						} else {
							show_fail_type = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_fail_type%>"><a
						href="./FailTypeServlet">故障类别</a></li>

					<%
						String show_unit = null;
						if (permission.getPermission_unit() == 1) {
							show_unit = "block";
						} else {
							show_unit = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_unit%>"><a
						href="./MaintainingUnitServlet">维修单位</a></li>

					<%
						String show_announcement = null;
						if (permission.getPermission_announcement() == 1) {
							show_announcement = "block";
						} else {
							show_announcement = "none";
						}
					%>
					<li class="sidebar-menuitem"
						style="display: <%=show_announcement%>"><a
						href="./SystemAnnouncementServlet">系统公告</a></li>

					<%
						String show_password = null;
						if (permission.getPermission_password() == 1) {
							show_password = "block";
						} else {
							show_password = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_password%>"><a
						href="changepassword.jsp">修改密码</a></li>
				</ul>
			</div>


			<div class="sidebar-menugroup">
				<div class="sidebar-grouptitle">报修管理</div>
				<ul class="sidebar-menu">
					<%
						String show_declare = null;
						if (permission.getPermission_declare() == 1) {
							show_declare = "block";
						} else {
							show_declare = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_declare%>"><a
						href="maintenance_report.jsp">维修申报</a></li>

					<%
						String show_dispatch = null;
						if (permission.getPermission_dispatch() == 1) {
							show_dispatch = "block";
						} else {
							show_dispatch = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_dispatch%>"><a
						href="./MaintenanceDispatchServlet">维修派工</a></li>
					<%
						String show_accept = null;
						if (permission.getPermission_accept() == 1) {
							show_accept = "block";
						} else {
							show_accept = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_accept%>"><a
						href="./MaintenanceAcceptServlet">报修受理</a></li>
					<%
						String show_confirm = null;
						if (permission.getPermission_confirm() == 1) {
							show_confirm = "block";
						} else {
							show_confirm = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_confirm%>"><a
						href="./MaintenanceConfirmServlet">报修确认</a></li>
					<%
						String show_statistical = null;
						if (permission.getPermission_statistical() == 1) {
							show_statistical = "block";
						} else {
							show_statistical = "none";
						}
					%>
					<li class="sidebar-menuitem" style="display: <%=show_statistical%>"><a
						href="./MaintenanceStatisticalServlet">报修统计</a></li>
				</ul>
			</div>
		</div>
		<%
			int user_role = peopleBean.getUser_role();
		%>
		<%
			List<PeopleBean> peopleBeanlist = (List<PeopleBean>) request
					.getAttribute("peopleBeanlist");
		%>
		<div class="page-content">
			<div class="content-nav">
				<table class="listtable">
					<caption>
						管理员工：<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%></caption>
					<caption>
						<a class="clickbutton" href="./FetchUserByRoleServlet?type=1">新增员工</a>
					</caption>
					<tr class="listheader">
						<th>账号</th>
						<th>姓名</th>
						<th>联系电话</th>
						<th>操作</th>
					</tr>
					<%
						for (PeopleBean peopleBean2 : peopleBeanlist) {
					%>
					<tr>
						<td><%=peopleBean2.getUser_username()%></td>
						<td><%=peopleBean2.getUser_name()%></td>
						<td><%=peopleBean2.getUser_phone()%></td>
						<td><a class="clickbutton"
							href="./FetchUserByRoleServlet?id=<%=peopleBean2.getUser_id()%>">编辑</a>
							<a class="clickbutton"
							href="./InitializePasswordServlet?id=<%=peopleBean2.getUser_id()%>&type=people">初始化密码</a></td>
					</tr>
					<%
						}
					%>
				</table>
			</div>

			<%
				PageBean pageBean = (PageBean) request.getAttribute("pageBean");
				if (pageBean != null) {
			%>
			<div class="pager-header">
				<div class="header-nav">
					<a href="./FetchUserByRoleServlet?pageno=1">首页</a> <a
						href="./FetchUserByRoleServlet?pageno=<%=pageBean.getPageno() - 1%>">上一页</a>
					<a
						href="./FetchUserByRoleServlet?pageno=<%=pageBean.getPageno() + 1%>">下一页</a>
					<a
						href="./FetchUserByRoleServlet?pageno=<%=pageBean.getMaxpage()%>">尾页</a>
				</div>
			</div>
			<%
				} else {
					out.print("暂无公告！");
				}
			%>

			<form action="./AddpeopleServlet">
				<fieldset>
					<legend>新增员工信息</legend>
					<table class="formtable">
						<tr>
							<td>账号:</td>
							<td><input id="user_username" name="user_username"
								type="text" placeholder="登录账号" maxlength="25"></td>
						</tr>
						<tr>
							<td>姓名:</td>
							<td><input id="user_name" type="text" name="user_name"
								placeholder="" maxlength="25"></td>
						</tr>
						<tr>
							<td>联系电话:</td>
							<td><input id="user_phone" type="text" name="user_phone"
								placeholder="11位数字" maxlength="25"></td>
						</tr>
						<tr>
							<td colspan="2" class="command"><input type="submit"
								value="添加" class="clickbutton" /> <input type="reset"
								value="取消" class="clickbutton" onclick="history.back()" /></td>
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