<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>     
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=9" />
<base href="<%=basePath%>">

<title></title>

<%@ include file="/common/include/listPlugin.html"%>
<script type="text/javascript" src="tech/${modelName}/${classNameFirstLower}List.js"></script>

</head>

<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe625;</i>首页<span class="c-gray en">></span>系统管理<span class="c-gray en">></span>用户管理
	<a class="btn btn-success radius r mr-20" title="刷新" href="javascript:location.replace(location.href);" style="line-height:1.6em;margin-top:3px"><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="pd-20">
	<div class="text-c mb-20">
		<form id="searchForm" action="javascript:;;;">
			<input id="userName" name="userName" class="input-text" type="text" style="width:250px" placeholder=" 用户名">
			<input type="hidden" name="pfather" id="pfather"/>
			<input type="text" id="pname" class="input-text s_dept_tree" readonly="readonly" style="width:250px" placeholder=" 组织机构"/>
			<button id="searchBtn" class="btn btn-success" type="button">
				<i class="Hui-iconfont"></i>
				搜用户
			</button>
		</form>
	</div>
	<table id="dataTables_${classNameFirstLower}" class="display  table-bordered" cellspacing="0"></table>
</div>
</body>
</html>
