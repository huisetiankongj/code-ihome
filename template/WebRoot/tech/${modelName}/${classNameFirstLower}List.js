<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>     
$(function() {
	var rootPath = Svc.rootPath();
	var ${classNameFirstLower}Svc = {
			url : {
					find${className} : rootPath + "${modelName}/find${className}.do?t=" + new Date().getTime(),
					${classNameFirstLower}Form : rootPath + "${modelName}/${classNameFirstLower}Form.do?t=" + new Date().getTime(),
					del${className} : rootPath + "${modelName}/del${className}.do?t=" + new Date().getTime()
			},
			fnDetail : function(id){//查看
				top.API.data("${classNameFirstLower}Model","detail");
				top.API.data("${classNameFirstLower}Id",id);
				${classNameFirstLower}Svc.fnShowDialog("查看",${classNameFirstLower}Svc.url.${classNameFirstLower}Form,800,450);
			},
			fnSave : function(){//新增
				top.API.data("${classNameFirstLower}Model","save");
				${classNameFirstLower}Svc.fnShowDialog("新增",${classNameFirstLower}Svc.url.${classNameFirstLower}Form,800,500);
			},
			fnUpdate : function(id){//修改
				top.API.data("${classNameFirstLower}Model","update");
				top.API.data("${classNameFirstLower}Id",id);
				${classNameFirstLower}Svc.fnShowDialog("编辑",${classNameFirstLower}Svc.url.${classNameFirstLower}Form,800,500);
			},
			fnDel : function(info){//删除
				var params = {},ids = [];
				$.each(info, function(i,v){
					ids.push(v.id);
				})
				params.ids=ids;
				layer.confirm("确定删除所勾选的数据吗？",{icon:3},function(){
					Svc.AjaxJson.post(${classNameFirstLower}Svc.url.del${className},params,function(data){
						oTable.fnDraw();
						DTCheckbox.fnGetInstance("dataTables_${classNameFirstLower}").selectData={};
						layer.alert('删除成功!', {icon: 6});
					});
				});
			},
			fnShowDialog : function(title,url,width,height,isFull){//弹框
				var index = layer.open({
					type: 2,
					area: [width+'px', height +'px'],
					fix: false, //不固定
					maxmin: true,
					shade:0.4,
					title: title,
					content: url,
					cancel:function(index){
						var ${classNameFirstLower}Model = top.API.data("${classNameFirstLower}Model"),
							opSuccess = top.API.data("${classNameFirstLower}Ops");
						if(opSuccess)
							oTable.fnDraw();
						top.API.removeData("${classNameFirstLower}Model");
						top.API.removeData("${classNameFirstLower}Id");
						top.API.removeData("${classNameFirstLower}Index");
						top.API.removeData("${classNameFirstLower}Ops");
					}
				});
				top.API.data("${classNameFirstLower}Index",index);
				isFull && layer.full(index);
			}
	}
	
	//搜索按钮
	$("#searchBtn").click(function(){
		var params = Svc.formToJson($("#searchForm"));
		oTable.dataTableSettings[0].fnServerData = fnServer(params);
		oTable.fnDraw();
	});
	
	//重置按钮
	$("#resetBtn").click(function(){
		Svc.resetForm($("#searchForm"));
	});

	//查看按钮
	$(".viewBtn").live("click",function(){
		${classNameFirstLower}Svc.fnDetail($(this).attr("data-id"));
	});
	
	//编辑按钮
	$(".editBtn").live("click",function(){
		${classNameFirstLower}Svc.fnUpdate($(this).attr("data-id"));
	});
	
	/**
	 * 表格定义
	 */
	oTable = $('#dataTables_${classNameFirstLower}').dataTable(
		{
			sAjaxSource : ${classNameFirstLower}Svc.url.find${className},
			fnServerData : fnServer(),
			oDTCheckbox: {
		        iColIndex :0
		    },
			aoColumnDefs : [
			             <#list table.columns as column>
				<#if column_index=0>
					{ aTargets : [ 0 ], mData : "${column.columnNameLower}", sClass : "text-center", sTitle : "<input type='checkBox' class='TableCheckall'>", sWidth : "13px"}<#if column_has_next>,</#if><#else>	{ aTargets : [ ${column_index} ], mDataProp : "${column.columnNameLower}", sClass : "text-center", sTitle : "${column.remarks}", bSortable : false},</#if>
				</#list>
				{ aTargets : [ 6 ], sClass : "text-center", sTitle : "操作", bSortable : false,bVisible: model?false:true, mData: function(data){
						return '<a href="javascript:;;;" class="viewBtn label label-warning radius tableBtn mr-5" data-id="'+data.id+'"><i class="Hui-iconfont">&#xe695;</i> 查看</a>'
								+'<a href="javascript:;;;" class="editBtn label label-success radius tableBtn" data-id="'+data.id+'"><i class="Hui-iconfont">&#xe60c;</i> 编辑</a>';
					}}],
			oTableTools : {
				sRowSelect : "single",
				aButtons : [
				             { sExtends : "text", sButtonClass:"btn btn-primary mr-5 radius", sButtonText : "<i class='Hui-iconfont'>&#xe600;</i> 新增", fnClick : function(nButton, oConfig) {
				            	 	${classNameFirstLower}Svc.fnSave();
				             }},
				             { sExtends : "text", sButtonClass:"btn btn-danger mr-5 radius", sButtonText : "<i class='Hui-iconfont'>&#xe6e2;</i> 批量删除", fnClick : function(nButton, oConfig) {
					            	var info = DTCheckbox.fnGetInstance("dataTables_${classNameFirstLower}").selectData;
									if($.isEmptyObject(info)){
										 layer.alert("请选择要删除的记录！", {icon: 6});
										 return;
									}
									${classNameFirstLower}Svc.fnDel(info);
				             }}]
			}
		});
});