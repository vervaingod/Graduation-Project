﻿<!DOCTYPE html>
<html>
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
					<li class="sidebar-menuitem"><a href="./FetchAllStudentServlet">学生管理</a></li>
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
			<div class="content-nav">教室管理 > 添加教室</div>
			<form action="./AddClassRoomServlet">
				<fieldset>
					<legend>教室信息</legend>
					<table class="formtable">
						<tr>
							<td>教室编号:</td>
							<td><input id="classroomnumber" name="classroomnumber"
								type="text" placeholder="例如：12201" maxlength="10" /></td>
						</tr>
						<tr>
							<td>教室名称:</td>
							<td><input id="room" type="text" name="room"
								placeholder="例如：高等数学教室" maxlength="20" /></td>
						</tr>
						<tr>
							<td>当前状态：</td>
							<td><input type="radio" id="status" name="status"
								checked="checked" value="1" /><label for="status">空闲</label> <input
								type="radio" id="status" name="status" /><label for="status"
								value="0">忙碌</label></td>
						</tr>
						<tr>
							<td>备注：</td>
							<td><textarea id="note" name="note" maxlength="200" rows="5"
									cols="60" placeholder="200字以内的文字描述"></textarea></td>
						</tr>
						<tr>
							<td colspan="2" class="command"><input type="submit"
								value="添加" class="clickbutton" /> <input type="reset"
								value="重置" class="clickbutton" /></td>
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