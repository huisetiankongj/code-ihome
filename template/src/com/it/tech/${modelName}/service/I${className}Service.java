<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>     
package ${basepackage}.${modelName}.service;

import java.util.List;
import java.util.Map;
import ${basepackage}.${modelName}.model.${className};

public interface I${className}Service {
	
	/**
	 * 新增
	 * @param entity
	 */
	public ${className} save(${className} entity);

	/**
	 * 批量新增
	 * @param entityList
	 */
	public void batchSave(List<${className}> entityList);

	/**
	 * 修改
	 * @param entity
	 */
	public ${className} update(${className} entity);

	/**
	 * 批量保存
	 * @param entityList
	 */
	public void batchUpdate(List<${className}> entityList);

	/**
	 * 按主键删除
	 * @param id
	 */
	public void deleteById(String id);

	/**
	 * 按主键批量删除
	 * 
	 * @param id
	 */
	public void batchDeleteById(List<String> idList);
	
	/**
	 * 按对象删除
	 */
	public void delete(${className} entity);

	/**
	 * 按对象批量删除
	 */
	public void batchDelete(List<${className}> entityList);

	/**
	 * 取所有
	 * @return
	 */
	public List<${className}> findAll();

	/**
	 * 条件查询
	 */
	public List<${className}> find(Map<String, Object> params);

	/**
	 * 主键取对象
	 */
	public ${className} findById(java.lang.String id);
}
