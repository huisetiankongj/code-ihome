<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>     
package ${basepackage}.${modelName}.service;

import java.util.List;
import java.util.Map;
import ${basepackage}.common.util.Utils;
import ${basepackage}.${modelName}.dao.I${className}Dao;
import ${basepackage}.${modelName}.model.${className};

public class ${className}ServiceImpl implements I${className}Service{

	private I${className}Dao ${classNameFirstLower}Dao;

	@Override
	public ${className} save(${className} entity) {
		entity.setId(Utils.randomUUID());
		return ${classNameFirstLower}Dao.save(entity);
	}

	@Override
	public void batchSave(List<${className}> entityList) {
		${classNameFirstLower}Dao.batchSave(entityList);
	}

	@Override
	public ${className} update(${className} entity) {
		return ${classNameFirstLower}Dao.update(entity);
	}

	@Override
	public void batchUpdate(List<${className}> entityList) {
		${classNameFirstLower}Dao.batchUpdate(entityList);
	}

	@Override
	public void deleteById(String id) {
		${classNameFirstLower}Dao.deleteById(id);
	}
	
	@Override
	public void batchDeleteById(List<String> idList) {
		${classNameFirstLower}Dao.batchDeleteById(idList);
	}

	@Override
	public void delete(${className} entity) {
		${classNameFirstLower}Dao.delete(entity);
	}

	@Override
	public void batchDelete(List<${className}> entityList) {
		${classNameFirstLower}Dao.batchDelete(entityList);
	}

	@Override
	public List<${className}> findAll() {
		return ${classNameFirstLower}Dao.findAll();
	}

	@Override
	public List<${className}> find(Map<String, Object> params) {
		return ${classNameFirstLower}Dao.find(params);
	}

	@Override
	public ${className} findById(String id) {
		return ${classNameFirstLower}Dao.findById(id);
	}
	
	public I${className}Dao get${className}Dao() {
		return ${classNameFirstLower}Dao;
	}

	public void set${className}Dao(I${className}Dao ${classNameFirstLower}Dao) {
		this.${classNameFirstLower}Dao = ${classNameFirstLower}Dao;
	}
}
