<!DOCTYPE html>
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
			<div class="content-nav">课程管理> 添加课程</div>
			<form action="./AddClassServlet">
				<fieldset>
					<legend>教室信息</legend>
					<table class="formtable">
						<tr>
							<td>课程号:</td>
							<td><input id="classnum" name="classnum"
								type="text" placeholder="例如：12201" maxlength="10" /></td>
						</tr>
						<tr>
							<td>课程名:</td>
							<td><input id="classname" type="text" name="classname"
								placeholder="例如：高等数学" maxlength="20" /></td>
						</tr>
						<tr>
							<td>上课地点:</td>
							<td><input id="classroom" type="text" name="classroom"
								placeholder="例如：11201" maxlength="20" /></td>
						</tr>

						<tr>
							<td>当前状态：</td>
							<td><input type="radio" id="state" name="state"
								checked="checked" value="1" /><label for="state">正常</label> <input
								type="radio" id="state" name="state" /><label for="state"
								value="0">停课</label> <input type="radio" id="state"
								name="state" /><label for="state" value="-1">删除</label></td>
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