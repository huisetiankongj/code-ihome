<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>     
package ${basepackage}.${modelName}.dto;

import java.util.List;

import ${basepackage}.${modelName}.model.${className};

public class ${className}Dto {
	
	private List<String> ids;
	private List<${className}> ${classNameLowerCase}s;
	
	public List<${className}> get${className}s() {
		return ${classNameLowerCase}s;
	}
	public void set${className}s(List<${className}> ${classNameLowerCase}s) {
		this.${classNameLowerCase}s = ${classNameLowerCase}s;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
}
