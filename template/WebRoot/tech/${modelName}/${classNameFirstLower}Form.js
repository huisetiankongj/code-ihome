<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>     
$(function(){
	var rootPath = Svc.rootPath(),
		${classNameFirstLower}Model = top.API.data("${classNameFirstLower}Model"),
		${classNameFirstLower}Id = top.API.data("${classNameFirstLower}Id"),
		${classNameFirstLower}Index = top.API.data("${classNameFirstLower}Index");
	var ${classNameFirstLower}FormSvc = {
			url : {
				find${className}ById : rootPath + "${modelName}/find${className}ById.do?t=" + new Date().getTime(),
				save${className} : rootPath + "${modelName}/save${className}.do?t=" + new Date().getTime(),
				update${className} : rootPath + "${modelName}/update${className}.do?t=" + new Date().getTime()
			},
			fnCommit : function(){//新增、编辑
				if($("#${classNameFirstLower}Form").valid()){
					var ${classNameFirstLower}Json = Svc.formToJson($("#${classNameFirstLower}Form"));
					var url = ${classNameFirstLower}Model == "save" ? ${classNameFirstLower}FormSvc.url.save${className} : ${classNameFirstLower}FormSvc.url.update${className};
					Svc.AjaxJson.post(url,${classNameFirstLower}Json,function(data){
						top.API.data("${classNameFirstLower}Ops",true);
						if(${classNameFirstLower}Model == "save"){
							layer.alert('新增成功!', {icon: 6},function(index){
								parent.layerClose(${classNameFirstLower}Index);
							});
						}else if(${classNameFirstLower}Model == "update"){
							layer.alert('编辑成功!', {icon: 6},function(index){
								parent.layerClose(${classNameFirstLower}Index);
							});
						}
					});
				}
			}
	}
	
	//事件注册
	function registEvent(){
		//保存按钮
		$("#commitBtn").click(function(){
			${classNameFirstLower}FormSvc.fnCommit();
		})
	}
	
	//页面初始化
	function initPage(){
		switch (${classNameFirstLower}Model) {
		case "detail":
			$("#${classNameFirstLower}Form input").attr("readonly", true);
			$("#${classNameFirstLower}Form textarea").attr("readonly", true);
			$(".btnRow").hide();
			Svc.AjaxForm.post(${classNameFirstLower}FormSvc.url.find${className}ById,{id:${classNameFirstLower}Id},function(data){
				Svc.jsonToDom(data,$("#${classNameFirstLower}Form"));
			});
			break;
		case "save":
			registEvent();
			break;
		case "update":
			registEvent();
			Svc.AjaxForm.post(${classNameFirstLower}FormSvc.url.find${className}ById,{id:${classNameFirstLower}Id},function(data){
				Svc.jsonToDom(data,$("#${classNameFirstLower}Form"));
			});
			break;
		default:
			break;
		}
	}
	initPage();
});