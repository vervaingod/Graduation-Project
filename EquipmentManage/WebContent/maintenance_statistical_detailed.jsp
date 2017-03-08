<!DOCTYPE html>
<%@page import="com.equipment.bean.LogBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.equipment.bean.CostBean"%>
<%@page import="com.equipment.bean.DispatchBean"%>
<%@page import="javax.xml.ws.Dispatch"%>
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
		<div class="page-content">
			<div class="content-nav"></div>
			<%
				MaintenanceBean maintenanceBean = (MaintenanceBean) request
						.getAttribute("maintenanceBean");
			%>
			<form action="./MaintenanceStatisticalServlet">
				<fieldset>
					<legend>
						报修确认 &nbsp;&nbsp;&nbsp;<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%></legend>
					<legend>报修单:</legend>
					<table class="formtable">
						<tr>
							<td>状态:</td>
							<%
								String show_state = "";
								if (maintenanceBean.getMaintenance_confirm() == 1) {
									show_state = "已确认";
								} else if (maintenanceBean.getMaintenance_accept_state() == 1) {
									show_state = "已受理";
								} else if (maintenanceBean.getMaintenance_dispatch_state() == 1) {
									show_state = "已派工";
								} else if (maintenanceBean.getMaintenance_dispatch_state() == 0) {
									show_state = "未派工";
								} else {
									show_state = "已作废";
								}
							%>
							<td><%=show_state%></td>
						</tr>
						<tr>
							<td>编号:</td>
							<td><%=maintenanceBean.getMaintenance_id()%></td>
							<td><input type="text" id="maintenance_id"
								name="maintenance_id"
								value="<%=maintenanceBean.getMaintenance_id()%>"
								style="display: none;" /></td>
							<td>报修单位:</td>
							<td><%=maintenanceBean.getMaintenance_department()%></td>
						</tr>
						<tr>
							<td>报修人:</td>
							<td><%=maintenanceBean.getMaintenance_name()%></td>
							<td>报修电话:</td>
							<td><%=maintenanceBean.getMaintenance_phone()%></td>
							<td>报修时间:</td>
							<td><%=maintenanceBean.getMaintenance_time()%></td>
						</tr>
						<tr>
							<td>故障类别:</td>
							<td><%=maintenanceBean.getMaintenance_fail_type()%></td>
							<td>故障描述:</td>
							<td><%=maintenanceBean.getMaintenance_description()%></td>
						</tr>
					</table>
					<legend>派工单:</legend>
					<table class="formtable">
						<%
							DispatchBean dispatchBean = (DispatchBean) request
									.getAttribute("dispatchBean");
						%>
						<tr>
							<td>状态:</td>
							<td><%=maintenanceBean.getMaintenance_dispatch_state() == 0 ? "未派工"
					: maintenanceBean.getMaintenance_dispatch_state() == -1 ? "已作废"
							: "已派工"%></td>
						</tr>
						<tr>
							<td>维修人:</td>
							<td><%=request.getAttribute("user_name") != null ? request
					.getAttribute("user_name") : "未派工"%></td>
							<td>联系电话:</td>
							<td><%=dispatchBean.getDispatch_maintenance_phone() != null ? dispatchBean
					.getDispatch_maintenance_phone() : "未派工"%></td>
							<td>预计到达时间:</td>
							<td><%=dispatchBean.getDispatch_expect_time() != null ? dispatchBean
					.getDispatch_expect_time() : "未派工"%></td>
						</tr>
					</table>
					<legend>维修单:</legend>
					<table class="formtable">
						<tr>
							<td>编号:</td>
							<td><%=maintenanceBean.getMaintenance_id()%></td>
							<td>维修委托单位:</td>
							<td><%=maintenanceBean.getMaintenance_unit()%></td>
						</tr>
						<tr>
							<td>设备名称:</td>
							<td><%=maintenanceBean.getMaintenance_equipment() != null ? maintenanceBean
					.getMaintenance_equipment() : "未维修"%></td>
							<td>是否联网备案:</td>
							<td><%=request.getAttribute("record")%></td>
						</tr>
						<tr>
							<td>IP地址:</td>
							<td><%=maintenanceBean.getMaintenance_IP() != null ? maintenanceBean
					.getMaintenance_IP() : "未维修"%></td>
							<td>MAC地址:</td>
							<td><%=maintenanceBean.getMaintenance_MAC() != null ? maintenanceBean
					.getMaintenance_MAC() : "未维修"%></td>
						</tr>
						<tr>
							<td>设备故障描述:</td>
							<td><%=maintenanceBean.getMaintenance_description()%></td>
						</tr>
						<tr>
							<td>维修人:</td>
							<td><%=request.getAttribute("user_name") != null ? request
					.getAttribute("user_name") : "未维修"%></td>
							<td>完工时间:</td>
							<td><%=maintenanceBean.getMaintenance_completion() != null ? maintenanceBean
					.getMaintenance_completion() : "该维修还尚未完成"%></td>
						</tr>
						<tr>
							<td>维修项目:</td>
						</tr>
					</table>
					<%
						List<CostBean> costBeanlist = (List<CostBean>) request
								.getAttribute("costBeanlist");
					%>
					<table class="listtable">
						<tr class="listheader">
							<th>序号</th>
							<th>服务项目</th>
							<th>材料费</th>
							<th>服务费</th>
						</tr>
						<%
							for (CostBean costBean : costBeanlist) {
						%>
						<tr>
							<td><%=costBean.getCost_id()%></td>
							<td><%=costBean.getCost_project()%></td>
							<td><%=costBean.getCost_material()%></td>
							<td><%=costBean.getCost_service()%></td>
						</tr>
						<%
							}
						%>
					</table>
					<table class="formtable">
						<tr>
							<td>费用合计:<%=maintenanceBean.getMaintenance_sum_cost()%></td>
						</tr>
					</table>
					<legend>操作日志:</legend>
					<%
						List<LogBean> logBeanlist = (List<LogBean>) request
								.getAttribute("logBeanlist");
					%>
					<table class="listtable">
						<tr class="listheader">
							<th>操作</th>
							<th>操作内容</th>
							<th>操作人</th>
							<th>操作时间</th>
						</tr>
						<%
							for (LogBean logBean : logBeanlist) {
						%>
						<tr>
							<td><%=logBean.getLog_operation()%></td>
							<td><%=logBean.getLog_content()%></td>
							<td><%=logBean.getLog_people()%></td>
							<td><%=logBean.getLog_time()%></td>
						</tr>
						<%
							}
						%>
					</table>
				</fieldset>
				<div align="center">
					<input type="submit" class="clickbutton" value="返回" />
				</div>
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
<%
	out.flush();
%>