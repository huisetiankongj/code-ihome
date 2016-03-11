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

<%@ include file="/common/include/formPlugin.html"%>
<script type="text/javascript" src="tech/${modelName}/${classNameFirstLower}Form.js"></script>

</head>

<body>
<div class="pd-20">
	<form id="${classNameLowerCase}Form" class="form form-horizontal" action="javascript:;;;">
		<input type="hidden" name="id" id="id"/>
		<#list table.columns as column>
		<div class="row cl">
			<label class="form-label col-2">
				${column.remarks}：
			</label>
			<div class="formControls col-4">
				<input class="input-text" type="text" name="${column.columnNameLower}">
			</div>
		</div>
		</#list>
		<div class="row cl btnRow">
			<div class="col-9 col-offset-2">
				<input class="btn btn-primary radius" type="button" id="commitBtn" value="  提交  ">
			</div>
		</div>
	</form>
</div>
</body>
</html>
