<!DOCTYPE html>
<%@page import="com.equipment.bean.PageBean"%>
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
			<div class="content-nav"></div>
			<form action="MaintenanceDispatchServlet">
				<table class="listtable">
					<caption>
						维修派单：<%=request.getAttribute("message") != null ? request
					.getAttribute("message") : ""%>
					</caption>
					<tr>
						<%
							String show_maintenance_id = "";
							if (request.getAttribute("maintenance_id") != null) {
								if (Integer.parseInt(request.getAttribute("maintenance_id")
										.toString()) != 0) {
									show_maintenance_id = request
											.getAttribute("maintenance_id").toString();
								}
							}
						%>
						<td>报修单号</td>
						<td><input id="maintenance_id" type="text"
							value="<%=show_maintenance_id%>" name="maintenance_id"
							placeholder="请输入8位报修单号" maxlength="25" style="width: 120px" /></td>
						<%
							DepartmentDao departmentDao = new DepartmentDaoImpl();
							List<DepartmentBean> departmentBeanlist = new ArrayList<DepartmentBean>();
							departmentBeanlist = departmentDao.fetchAllPoliceStation();
							String show_maintenance_department = null;
							String message_maintenance_department = null;
							if (request.getAttribute("maintenance_department") != null) {
								message_maintenance_department = request.getAttribute(
										"maintenance_department").toString();
							}
						%>
						<td><select name="maintenance_department"
							id="maintenance_department">
								<option value="">报修单位</option>
								<%
									for (DepartmentBean departmentBean : departmentBeanlist) {
								%>
								<option value="<%=departmentBean.getDepartment_name()%>"
									<%if (departmentBean.getDepartment_name().equals(
						message_maintenance_department)) {
					show_maintenance_department = "selected='selected'";
				} else {
					show_maintenance_department = "";
				}%>
									<%=show_maintenance_department%>><%=departmentBean.getDepartment_name()%></option>
								<%
									}
								%>
						</select></td>
						<%
							FailTypeDao failTypeDao = new FailTypeDaoImpl();
							List<FailTypeBean> failTypeBeanlist = new ArrayList<FailTypeBean>();
							failTypeBeanlist = failTypeDao.fetchAllFailType();
							String show_maintenance_fail_type = null;
							String message_maintenance_fail_type = null;
							if (request.getAttribute("maintenance_fail_type") != null) {
								message_maintenance_fail_type = request.getAttribute(
										"maintenance_fail_type").toString();
							}
						%>
						<td><select name="maintenance_fail_type"
							id="maintenance_fail_type">
								<option value="">故障类型</option>
								<%
									for (FailTypeBean failTypeBean : failTypeBeanlist) {
								%>
								<option value="<%=failTypeBean.getFail_type_name()%>"
									<%if (failTypeBean.getFail_type_name().equals(
						message_maintenance_fail_type)) {
					show_maintenance_fail_type = "selected='selected'";
				} else {
					show_maintenance_fail_type = "";
				}%>
									<%=show_maintenance_fail_type%>><%=failTypeBean.getFail_type_name()%></option>
								<%
									}
								%>
						</select></td>
						<%
							int message_maintenance_dispatch_state = 3;
							if (request.getAttribute("maintenance_dispatch_state") != null) {
								message_maintenance_dispatch_state = Integer.parseInt(request
										.getAttribute("maintenance_dispatch_state").toString());
							}
							String show_maintenance_dispatch_state = null;
						%>
						<td><select name="maintenance_dispatch_state"
							id="maintenance_dispatch_state"><option value="3">派工状态</option>
								<option value="1"
									<%if (1 == message_maintenance_dispatch_state) {
				show_maintenance_dispatch_state = "selected='selected'";
			} else {
				show_maintenance_dispatch_state = "";
			}%>
									<%=show_maintenance_dispatch_state%>>已经派工</option>
								<option value="-1"
									<%if (-1 == message_maintenance_dispatch_state) {
				show_maintenance_dispatch_state = "selected='selected'";
			} else {
				show_maintenance_dispatch_state = "";
			}%>
									<%=show_maintenance_dispatch_state%>>已作废</option>
								<option value="0"
									<%if (0 == message_maintenance_dispatch_state) {
				show_maintenance_dispatch_state = "selected='selected'";
			} else {
				show_maintenance_dispatch_state = "";
			}%>
									<%=show_maintenance_dispatch_state%>>未派工</option>
						</select></td>
					</tr>
					<tr>
						<td>报修时间</td>
						<td><input id="maintenance_time" type="text"
							value="<%=request.getAttribute("maintenance_time") != null ? request
					.getAttribute("maintenance_time") : ""%>"
							name="maintenance_time" placeholder="年-月-日" maxlength="25"
							style="width: 120px" /></td>
						<%
							int message_maintenance_accept_state = 3;
							if (request.getAttribute("maintenance_accept_state") != null) {
								message_maintenance_accept_state = Integer.parseInt(request
										.getAttribute("maintenance_accept_state").toString());
							}
							String show_maintenance_accept_state = null;
						%>
						<td><select name="maintenance_accept_state"
							id="maintenance_accept_state"><option value="3">受理状态</option>
								<option value="1"
									<%if (1 == message_maintenance_accept_state) {
				show_maintenance_accept_state = "selected='selected'";
			} else {
				show_maintenance_accept_state = "";
			}%>
									<%=show_maintenance_accept_state%>>已受理</option>
								<option value="0"
									<%if (0 == message_maintenance_accept_state) {
				show_maintenance_accept_state = "selected='selected'";
			} else {
				show_maintenance_accept_state = "";
			}%>
									<%=show_maintenance_accept_state%>>未受理</option>
						</select></td>
						<%
							int message_maintenance_confirm = 3;
							if (request.getAttribute("maintenance_confirm") != null) {
								message_maintenance_confirm = Integer.parseInt(request
										.getAttribute("maintenance_confirm").toString());
							}
							String show_maintenance_confirm = null;
						%>
						<td><select id="maintenance_confirm"
							name="maintenance_confirm"><option value="3">确认状态</option>
								<option value="1"
									<%if (1 == message_maintenance_confirm) {
				show_maintenance_confirm = "selected='selected'";
			} else {
				show_maintenance_confirm = "";
			}%>
									<%=show_maintenance_confirm%>>已确认</option>
								<option value="0"
									<%if (0 == message_maintenance_confirm) {
				show_maintenance_confirm = "selected='selected'";
			} else {
				show_maintenance_confirm = "";
			}%>
									<%=show_maintenance_confirm%>>未确认</option>
						</select></td>
						<td colspan="2" class="command"><input type="submit"
							value="搜索" class="clickbutton" /></td>
					</tr>
				</table>
			</form>
			<table class="listtable">
				<tr class="listheader">
					<th>报修单号</th>
					<th>报修单位</th>
					<th>故障类型</th>
					<th>报修时间</th>
					<th>派工状态</th>
					<th>受理状态</th>
					<th>确认签字状态</th>
					<th>操作</th>
				</tr>
				<%
					try {
						List<MaintenanceBean> maintenanceBeanlist = (List<MaintenanceBean>) request
								.getAttribute("maintenanceBeanlist");
						for (MaintenanceBean maintenanceBean : maintenanceBeanlist) {
				%><tr>
					<td><%=maintenanceBean.getMaintenance_id()%></td>
					<td><%=maintenanceBean.getMaintenance_department()%></td>
					<td><%=maintenanceBean.getMaintenance_fail_type()%></td>
					<td><%=maintenanceBean.getMaintenance_time()%></td>
					<td><%=maintenanceBean.getMaintenance_dispatch_state() == 0 ? "未派工"
							: maintenanceBean.getMaintenance_dispatch_state() == -1 ? "已作废"
									: "已派工"%></td>
					<td><%=maintenanceBean.getMaintenance_accept_state() != 0 ? "已受理"
							: "未受理"%></td>
					<td><%=maintenanceBean.getMaintenance_confirm() != 0 ? "已确认"
							: "未确认"%></td>
					<td><a class="clickbutton"
						href="./FetchMaintenanceDispatchByIdServlet?id=<%=maintenanceBean.getMaintenance_id()%>">查看</a>
						<a class="clickbutton"
						href="./InvalidDispatchServlet?maintenance_id=<%=maintenanceBean.getMaintenance_id()%>">作废</a></td>
				</tr>
				<%
					}
					} catch (Exception e) {
						out.print("暂无需派工消息");
					}
				%>
			</table>
			<%
				PageBean pageBean = (PageBean) request.getAttribute("pageBean");
				if (pageBean != null) {
			%>
			<div class="pager-header">
				<div class="header-nav">
					<a
						href="./MaintenanceDispatchServlet?pageno=1&maintenance_id=<%=request.getAttribute("maintenance_id")%>&maintenance_department=<%=request.getAttribute("maintenance_department")%>&maintenance_fail_type=<%=request.getAttribute("maintenance_fail_type")%>&maintenance_dispatch_state=<%=request.getAttribute("maintenance_dispatch_state")%>&maintenance_time=<%=request.getAttribute("maintenance_time")%>&maintenance_accept_state=<%=request.getAttribute("maintenance_accept_state")%>&maintenance_confirm=<%=request.getAttribute("maintenance_confirm")%>">首页</a>
					<a
						href="./MaintenanceDispatchServlet?pageno=<%=pageBean.getPageno() - 1%>&maintenance_id=<%=request.getAttribute("maintenance_id")%>&maintenance_department=<%=request.getAttribute("maintenance_department")%>&maintenance_fail_type=<%=request.getAttribute("maintenance_fail_type")%>&maintenance_dispatch_state=<%=request.getAttribute("maintenance_dispatch_state")%>&maintenance_time=<%=request.getAttribute("maintenance_time")%>&maintenance_accept_state=<%=request.getAttribute("maintenance_accept_state")%>&maintenance_confirm=<%=request.getAttribute("maintenance_confirm")%>">上一页</a>
					<a
						href="./MaintenanceDispatchServlet?pageno=<%=pageBean.getPageno() + 1%>&maintenance_id=<%=request.getAttribute("maintenance_id")%>&maintenance_department=<%=request.getAttribute("maintenance_department")%>&maintenance_fail_type=<%=request.getAttribute("maintenance_fail_type")%>&maintenance_dispatch_state=<%=request.getAttribute("maintenance_dispatch_state")%>&maintenance_time=<%=request.getAttribute("maintenance_time")%>&maintenance_accept_state=<%=request.getAttribute("maintenance_accept_state")%>&maintenance_confirm=<%=request.getAttribute("maintenance_confirm")%>">下一页</a>
					<a
						href="./MaintenanceDispatchServlet?pageno=<%=pageBean.getMaxpage()%>&maintenance_id=<%=request.getAttribute("maintenance_id")%>&maintenance_department=<%=request.getAttribute("maintenance_department")%>&maintenance_fail_type=<%=request.getAttribute("maintenance_fail_type")%>&maintenance_dispatch_state=<%=request.getAttribute("maintenance_dispatch_state")%>&maintenance_time=<%=request.getAttribute("maintenance_time")%>&maintenance_accept_state=<%=request.getAttribute("maintenance_accept_state")%>&maintenance_confirm=<%=request.getAttribute("maintenance_confirm")%>">尾页</a>
				</div>
			</div>
			<%
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