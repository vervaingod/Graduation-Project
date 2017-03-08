<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.equipment.bean.MaintenanceBean"%>
<%@page import="com.equipment.bean.DepartmentBean"%>
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
						href="./FetchUserByRoleServlet?type=0">内部账号</a></li>

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
			MaintenanceBean maintenanceBean = (MaintenanceBean) request
					.getAttribute("maintenanceBean");
		%>
		<div class="page-content">
			<div class="content-nav"></div>
			<form action="./AddDispatchServlet">
				<fieldset>
					<legend>
						维修派单 &nbsp;&nbsp;&nbsp;<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%></legend>
					<legend>派单:</legend>
					<table class="formtable">
						<tr>
							<td>报修单号:</td>
							<td><input id="maintenance_id" type="text"
								name="maintenance_id"
								style="border: 0px; background-color: lightgray"
								value="<%=maintenanceBean.getMaintenance_id()%>"
								readonly="readonly" placeholder="" maxlength="25" /></td>
							<td>报修单位:</td>
							<td><input id="maintenance_department" type="text"
								name="maintenance_department"
								style="border: 0px; background-color: lightgray"
								value="<%=maintenanceBean.getMaintenance_department() != null ? maintenanceBean
					.getMaintenance_department() : ""%>"
								readonly="readonly" placeholder="" maxlength="25" /></td>
						</tr>
						<tr>
							<td>报修人:</td>
							<td><input id="maintenance_name" type="text"
								name="maintenance_name"
								style="border: 0px; background-color: lightgray"
								value="<%=maintenanceBean.getMaintenance_name() != null ? maintenanceBean
					.getMaintenance_name() : ""%>"
								readonly="readonly" placeholder="" maxlength="25" /></td>
							<td>报修电话:</td>
							<td><input id="maintenance_phone" type="text"
								name="maintenance_phone"
								style="border: 0px; background-color: lightgray"
								value="<%=maintenanceBean.getMaintenance_phone() != null ? maintenanceBean
					.getMaintenance_phone() : ""%>"
								readonly="readonly" placeholder="" maxlength="25" /></td>
							<td>报修时间:</td>
							<td><input id="maintenance_time" type="text"
								name="maintenance_time"
								style="border: 0px; background-color: lightgray"
								value="<%=maintenanceBean.getMaintenance_time() != null ? maintenanceBean
					.getMaintenance_time() : ""%>"
								readonly="readonly" placeholder="" maxlength="25" /></td>
						</tr>
						<tr>
							<td>故障类别:</td>
							<td><input id="maintenance_fail_type" type="text"
								name="maintenance_fail_type"
								style="border: 0px; background-color: lightgray"
								value="<%=maintenanceBean.getMaintenance_fail_type() != null ? maintenanceBean
					.getMaintenance_fail_type() : ""%>"
								readonly="readonly" placeholder="" maxlength="25" /></td>
							<td>故障描述:</td>
							<td><input id="maintenance_description" type="text"
								name="maintenance_description"
								style="border: 0px; background-color: lightgray"
								value="<%=maintenanceBean.getMaintenance_description() != null ? maintenanceBean
					.getMaintenance_description() : ""%>"
								readonly="readonly" placeholder="" maxlength="25" /></td>
						</tr>
						<%
							PeopleDao peopleDao = new PeopleDaoImpl();
							List<PeopleBean> peopleBeanlist = new ArrayList<PeopleBean>();
							peopleBeanlist = peopleDao.FindAllGuy(11);
						%>
						<tr>
							<td>维修员：</td>
							<td><select name="maintenance_man" id="maintenance_man">
									<%
										for (PeopleBean peopleBean2 : peopleBeanlist) {
									%>
									<option value="<%=peopleBean2.getUser_name()%>"><%=peopleBean2.getUser_name()%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td>预计到达时间：</td>
							<td><input id="dispatch_expect_time" type="text"
								name="dispatch_expect_time" /></td>
						</tr>
						<tr>
							<td colspan="2" class="command"><input type="submit"
								value="派工" class="clickbutton" /><input type="reset" value="取消"
								class="clickbutton" onclick="history.back()" /></td>
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