<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>     
package ${basepackage}.${modelName}.model;

public class ${className} {

<#list table.columns as column>
	private ${column.javaType} ${column.columnNameLower};//${column.remarks}
</#list>

<@generateJavaColumns/>
}

<#macro generateJavaColumns>
	<#list table.columns as column>
		
	public void set${column.columnName}(${column.javaType} v) {
		this.${column.columnNameLower} = v;
	}
	
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>
