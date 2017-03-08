<!DOCTYPE html>
<%@page import="com.equipment.bean.PageBean"%>
<%@page import="com.equipment.dao.MaintenanceDaoImpl"%>
<%@page import="com.equipment.dao.MaintenanceDao"%>
<%@page import="com.equipment.bean.DispatchBean"%>
<%@page import="com.equipment.bean.MaintenanceBean"%>
<%@page import="com.equipment.bean.FailTypeBean"%>
<%@page import="com.equipment.dao.FailTypeDaoImpl"%>
<%@page import="com.equipment.dao.FailTypeDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.equipment.dao.DepartmentDaoImpl"%>
<%@page import="com.equipment.dao.DepartmentDao"%>
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
			<form action="MaintenanceAcceptServlet">
				<div class="content-nav">
					<table class="listtable">
						<caption>
							维修受理：<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%>
						</caption>
						<tr>
							<td>报修单号</td>
							<td><input id="maintenance_id" type="text"
								name="maintenance_id" placeholder="请输入8位报修单号" maxlength="25"
								style="width: 120px" /></td>
							<%
								DepartmentDao departmentDao = new DepartmentDaoImpl();
								List<DepartmentBean> departmentBeanlist = new ArrayList<DepartmentBean>();
								departmentBeanlist = departmentDao.fetchAllPoliceStation();
							%>
							<td><select name="maintenance_department"><option
										value="">报修单位</option>
									<%
										for (DepartmentBean departmentBean : departmentBeanlist) {
									%>
									<option value="<%=departmentBean.getDepartment_name()%>"><%=departmentBean.getDepartment_name()%></option>
									<%
										}
									%>
							</select></td>
							<%
								FailTypeDao failTypeDao = new FailTypeDaoImpl();
								List<FailTypeBean> failTypeBeanlist = new ArrayList<FailTypeBean>();
								failTypeBeanlist = failTypeDao.fetchAllFailType();
							%>
							<td><select name="maintenance_fail_type">
									<option value="">故障类型</option>
									<%
										for (FailTypeBean failTypeBean : failTypeBeanlist) {
									%>
									<option value="<%=failTypeBean.getFail_type_name()%>"><%=failTypeBean.getFail_type_name()%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td>报修时间</td>
							<td><input id="maintenance_time" type="text"
								name="maintenance_time" placeholder="" maxlength="25"
								style="width: 120px" /></td>
							<td><select name="maintenance_accept_state"
								id="maintenance_accept_state"><option value="3">受理状态</option>
									<option value="1">已受理</option>
									<option value="0">未受理</option>
							</select></td>
							<td><select name="maintenance_confirm"
								id="maintenance_confirm"><option value="3">确认状态</option>
									<option value="1">已确认</option>
									<option value="0">未确认</option>
							</select></td>
							<%
								PeopleDao peopleDao = new PeopleDaoImpl();
								List<PeopleBean> peopleBeanlist = new ArrayList<PeopleBean>();
								peopleBeanlist = peopleDao.FindAllGuy(11);
							%>
							<td colspan="2" class="command"><input type="submit"
								style="width: 50px" value="搜索" class="clickbutton" /></td>
						</tr>
					</table>
				</div>
			</form>
			<table class="listtable">
				<tr class="listheader">
					<th>报修单号</th>
					<th>报修单位</th>
					<th>故障类型</th>
					<th>报修时间</th>
					<th>预计到达时间</th>
					<th>派工状态</th>
					<th>受理状态</th>
					<th>确认签字状态</th>
					<th>操作</th>
				</tr>
				<%
					try {
						List<DispatchBean> dispatchBeanlist = (List<DispatchBean>) request
								.getAttribute("dispatchBeanlist");
						for (DispatchBean dispatchBean : dispatchBeanlist) {
				%>
				<tr>
					<td><%=dispatchBean.getDispatch_maintenance_id()%></td>
					<%
						MaintenanceDao maintenanceDao = new MaintenanceDaoImpl();
								MaintenanceBean maintenanceBean = new MaintenanceBean();
								maintenanceBean = maintenanceDao
										.findMaintenanceById(dispatchBean
												.getDispatch_maintenance_id());
					%>
					<td><%=maintenanceBean.getMaintenance_department()%></td>
					<td><%=maintenanceBean.getMaintenance_fail_type()%></td>
					<td><%=maintenanceBean.getMaintenance_time()%></td>
					<td><%=dispatchBean.getDispatch_expect_time()%></td>
					<td><%=maintenanceBean.getMaintenance_dispatch_state() != 0 ? "已派工"
							: "未派工"%></td>
					<td><%=maintenanceBean.getMaintenance_accept_state() != 0 ? "已受理"
							: "未受理"%></td>
					<td><%=maintenanceBean.getMaintenance_confirm() != 0 ? "已确认"
							: "未确认"%></td>
					<td><a class="clickbutton"
						href="./FetchMaintenanceAcceptByIdServlet?id=<%=maintenanceBean.getMaintenance_id()%>">查看</a></td>
				</tr>
				<%
					}
					} catch (Exception e) {

					}
				%>
			</table>
			<%
				PageBean pageBean = (PageBean) request.getAttribute("pageBean");
				if (pageBean != null) {
			%>
			<div class="pager-header">
				<div class="header-nav">
					<a href="./MaintenanceAcceptServlet?pageno=1">首页</a> <a
						href="./MaintenanceAcceptServlet?pageno=<%=pageBean.getPageno() - 1%>">上一页</a>
					<a
						href="./MaintenanceAcceptServlet?pageno=<%=pageBean.getPageno() + 1%>">下一页</a>
					<a
						href="./MaintenanceAcceptServlet?pageno=<%=pageBean.getMaxpage()%>">尾页</a>
				</div>
			</div>
			<%
				} else {
					out.print("暂无派工记录！");
				}
			%>
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