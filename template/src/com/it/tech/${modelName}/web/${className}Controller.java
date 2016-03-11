<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>     
package ${basepackage}.${modelName}.web;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ${basepackage}.${modelName}.model.${className};
import ${basepackage}.${modelName}.service.I${className}Service;
import ${basepackage}.${modelName}.dto.${className}Dto;
import ${basepackage}.common.pagination.ThreadLocalPaginate;

@Controller
@RequestMapping("${modelName}/")
public class ${className}Controller {

	@Resource(name = "tech.${classNameFirstLower}Service")
	private I${className}Service ${classNameFirstLower}Service;
	
	/**
	 * 列表页面
	 * @return
	 */
	@RequestMapping("${classNameFirstLower}List")
	public Object ${classNameLowerCase}ListPage() {
		return "${modelName}/${classNameFirstLower}List";
	}
	
	/**
	 * 表单页面
	 * @return
	 */
	@RequestMapping("${classNameFirstLower}Form")
	public Object ${classNameLowerCase}FormPage() {
		return "${modelName}/${classNameFirstLower}Form";
	}
	
	/**
	 * 条件查询
	 * @param params
	 * @return
	 */
	@RequestMapping("find${className}")
	@ResponseBody
	public Object find${className}(@RequestBody Map<String,Object> params) {
		ThreadLocalPaginate.set(params);
		List<${className}> list = this.${classNameFirstLower}Service.find(params);
		Object rs = ThreadLocalPaginate.get();
		if(rs != null)
			return rs;
		return list;
	}
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	@RequestMapping("save${className}")
	@ResponseBody
	public Object save${className}(@RequestBody ${className} entity) {
		this.${classNameFirstLower}Service.save(entity);
		return entity;
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@RequestMapping("update${className}")
	@ResponseBody
	public Object update${className}(@RequestBody ${className} entity) {
		this.${classNameFirstLower}Service.update(entity);
		return entity;
	}
	
	/**
	 * 删除(含批量删除)
	 * @param ${classNameFirstLower}Dto
	 * @return
	 */
	@RequestMapping("del${className}")
	@ResponseBody
	public Object del${className}(@RequestBody ${className}Dto ${classNameFirstLower}Dto) {
		List<String> idList = ${classNameFirstLower}Dto.getIds();
		if(idList.size()>1)
			this.${classNameFirstLower}Service.batchDeleteById(idList);
		else
			this.${classNameFirstLower}Service.deleteById(idList.get(0));
		return Boolean.TRUE;
	}
	
	/**
	 * 根据主键查找对象
	 * @param ${classNameFirstLower}id
	 * @return
	 */
	@RequestMapping("find${className}ById")
	@ResponseBody
	public Object find${className}ById(String id) {
		return this.${classNameFirstLower}Service.findById(id);
	}
	
}