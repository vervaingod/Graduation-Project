<!DOCTYPE html>
<%@page import="com.equipment.bean.CostBean"%>
<%@page import="com.equipment.bean.DispatchBean"%>
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
			<form action="./AcceptMaintenanceByIdServlet">
				<fieldset>
					<legend>
						报修受理 &nbsp;&nbsp;&nbsp;<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%></legend>
					<legend>报修单:</legend>
					<table class="formtable">
						<tr>
							<td>编号:</td>
							<td><%=maintenanceBean.getMaintenance_id() != 0 ? maintenanceBean
					.getMaintenance_id() : ""%></td>
							<td>报修单位:</td>
							<td><%=maintenanceBean.getMaintenance_department() != null ? maintenanceBean
					.getMaintenance_department() : ""%></td>
						</tr>
						<tr>
							<td>报修人:</td>
							<td><%=maintenanceBean.getMaintenance_name() != null ? maintenanceBean
					.getMaintenance_name() : ""%></td>
							<td>报修电话:</td>
							<td><%=maintenanceBean.getMaintenance_phone() != null ? maintenanceBean
					.getMaintenance_phone() : ""%></td>
							<td>报修时间:</td>
							<td><%=maintenanceBean.getMaintenance_time() != null ? maintenanceBean
					.getMaintenance_time() : ""%></td>
						</tr>
						<tr>
							<td>故障类别:</td>
							<td><%=maintenanceBean.getMaintenance_fail_type() != null ? maintenanceBean
					.getMaintenance_fail_type() : ""%></td>
							<td>故障描述:</td>
							<td><%=maintenanceBean.getMaintenance_description() != null ? maintenanceBean
					.getMaintenance_description() : ""%></td>
						</tr>
					</table>
					<%
						DispatchBean dispatchBean = (DispatchBean) request
								.getAttribute("dispatchBean");
					%>
					<legend>派工单:</legend>
					<table class="formtable">
						<tr>
							<td>维修人:</td>
							<td><%=request.getAttribute("dispatch_user_name")%></td>
							<td>联系电话:</td>
							<td><%=request.getAttribute("dispatch_user_phone")%></td>
							<td>预计到达时间:</td>
							<td><input id="dispatch_expect_time" type="text"
								name="dispatch_expect_time"
								style="border: 0px; background-color: lightgray"
								value="<%=dispatchBean.getDispatch_expect_time() != null ? dispatchBean
					.getDispatch_expect_time() : ""%>"
								readonly="readonly" placeholder="" maxlength="25" /></td>
						</tr>
					</table>
					<legend>维修单:</legend>
					<table class="formtable">
						<tr>
							<td>编号:</td>
							<td><input id="maintenance_id" type="text"
								name="maintenance_id"
								value="<%=maintenanceBean.getMaintenance_id() != 0 ? maintenanceBean
					.getMaintenance_id() : ""%>"
								style="border: 0px; background-color: lightgray"
								readonly="readonly" placeholder="" maxlength="25" /></td>
							<td>维修委托单位:</td>
							<td><input id="maintenance_department" type="text"
								name="maintenance_department"
								style="border: 0px; background-color: lightgray"
								readonly="readonly"
								value="<%=maintenanceBean.getMaintenance_department() != null ? maintenanceBean
					.getMaintenance_department() : ""%>"
								placeholder="" maxlength="25" /></td>
						</tr>
						<tr>
							<td>设备名称:</td>
							<td><input id="maintenance_equipment" type="text"
								name="maintenance_equipment"
								value="<%=maintenanceBean.getMaintenance_equipment() != null ? maintenanceBean
					.getMaintenance_equipment() : ""%>"
								placeholder="" maxlength="25" /></td>
							<td>是否联网备案:</td>
							<td><input id="maintenance_record" type="radio"
								name="maintenance_record" value="1"
								<%=request.getAttribute("yeschecked")%> /> <label for="state">是</label>
								<input id="maintenance_record" type="radio"
								name="maintenance_record" value="0"
								<%=request.getAttribute("nochecked")%>><label>否</label></td>
						</tr>
						<tr>
							<td>IP地址:</td>
							<td><input id="maintenance_IP" type="text"
								name="maintenance_IP"
								value="<%=maintenanceBean.getMaintenance_IP() != null ? maintenanceBean
					.getMaintenance_IP() : ""%>"
								placeholder="" maxlength="25" /></td>
							<td>MAC地址:</td>
							<td><input id="maintenance_MAC" type="text"
								name="maintenance_MAC"
								value="<%=maintenanceBean.getMaintenance_MAC() != null ? maintenanceBean
					.getMaintenance_MAC() : ""%>"
								placeholder="" maxlength="25" /></td>
						</tr>
						<tr>
							<td>设备故障描述:</td>
							<td><%=maintenanceBean.getMaintenance_description() != null ? maintenanceBean
					.getMaintenance_description() : ""%></td>
						</tr>
						<tr>
							<td>维修人:</td>
							<td><%=request.getAttribute("dispatch_user_name")%></td>
							<td>完工时间:</td>
							<td><%=maintenanceBean.getMaintenance_completion() != null ? maintenanceBean
					.getMaintenance_description() : ""%></td>
						</tr>
					</table>
					<%
						List<CostBean> costBeanlist = (List<CostBean>) request
								.getAttribute("costBeanlist");
					%>
					<a
						href="./FetchAllCostServlet?maintenance_id=<%=maintenanceBean.getMaintenance_id()%>">新增项目</a>
					<table class="listtable">
						<tr class="listheader">
							<th>编号</th>
							<th>服务项目</th>
							<th>材料费</th>
							<th>服务费</th>
							<th>操作</th>
						</tr>
						<%
							for (CostBean costBean : costBeanlist) {
						%>
						<tr>
							<td><%=costBean.getCost_id()%></td>
							<td><%=costBean.getCost_project()%></td>
							<td><%=costBean.getCost_material()%></td>
							<td><%=costBean.getCost_service()%></td>
							<td><input type="submit" value="修改" class="clickbutton" />
								<a class="clickbutton"
								href="DeleteCostServlet?cost_id=<%=costBean.getCost_id()%>&maintenance_id=<%=costBean.getCost_maintenance_id()%>">删除</a></td>
						</tr>
						<%
							}
						%>
						<tr>
							<td><input type="text" id="sum" name="sum"
								style="display: none" value="<%=request.getAttribute("sum")%>" /></td>
						</tr>
					</table>
					总费用：<%=request.getAttribute("sum")%><br> <input type="submit"
						value="受理" class="clickbutton" />
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