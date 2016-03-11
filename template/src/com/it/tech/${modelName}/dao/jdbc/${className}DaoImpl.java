<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign classNameUpperCase = className?upper_case>
package ${basepackage}.${modelName}.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import ${basepackage}.${modelName}.dao.I${className}Dao;
import ${basepackage}.${modelName}.model.${className};
import ${basepackage}.common.pagination.PaginationDaoSupport;

public class ${className}DaoImpl extends PaginationDaoSupport implements I${className}Dao{

	private static final String SAVE_${classNameUpperCase} = "INSERT INTO ${classNameUpperCase} (<#list table.columns as column> ${column.columnName}<#if column_has_next>,</#if></#list>) " +
			"VALUES(<#list table.columns as column>:${column.columnNameLower}<#if column_has_next>,</#if></#list>)";

	private static final String UPDATE_${classNameUpperCase} ="UPDATE  ${classNameUpperCase} SET <#list table.columns as column>${column.columnName}=:${column.columnNameLower}<#if column_has_next>,</#if></#list>  WHERE ID=:id";
	private static final String DELETE_${classNameUpperCase}_BY_ID = "DELETE FROM ${classNameUpperCase} WHERE ID=?";
	private static final String DELETE_${classNameUpperCase} = "DELETE FROM ${classNameUpperCase} WHERE ID=:id";
	private static final String FIND_ALL = "SELECT * FROM ${classNameUpperCase}";
	private static final String FIND_BY_ID = "SELECT * FROM ${classNameUpperCase} T WHERE T.ID=?";
	
	private final static RowMapper<${className}> ROWMAPPER = new RowMapper<${className}>() {
		@Override
		public ${className} mapRow(ResultSet rs, int idx) throws SQLException {
			${className} ${classNameLowerCase} = new ${className}();
			<#list table.columns as column>
				<#if column.isDateTimeColumn>
				${classNameLowerCase}.set${column.columnName}(rs.getTimestamp("${column.columnName}"));
				<#elseif column.isStringColumn>
				${classNameLowerCase}.set${column.columnName}(rs.getString("${column.columnName}"));
				<#else>
				   <#if column.sqlTypeName="int">
				 ${classNameLowerCase}.set${column.columnName}(rs.getInt("${column.columnName}"));
                                   <#else>
                                        ${classNameLowerCase}.set${column.columnName}(rs.get${column.javaType}("${column.columnName}"));
                                   </#if>
				</#if> 
			</#list>
			return ${classNameLowerCase};
		}
	};
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		if (namedParameterJdbcTemplate != null) {
			return namedParameterJdbcTemplate;
		}
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				this.getJdbcTemplate());
		return namedParameterJdbcTemplate;
	}

	@Override
	public ${className} save(${className} entity) {
		getNamedParameterJdbcTemplate().update(SAVE_${classNameUpperCase}, new BeanPropertySqlParameterSource(entity));
		return entity;
	}

	@Override
	public void batchSave(final List<${className}> entityList) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(entityList.toArray());
		getNamedParameterJdbcTemplate().batchUpdate(SAVE_${classNameUpperCase}, batch);
	}

	@Override
	public ${className} update(${className} entity) {
		getNamedParameterJdbcTemplate().update(UPDATE_${classNameUpperCase}, new BeanPropertySqlParameterSource(entity));
		return entity;
	}

	@Override
	public void batchUpdate(final List<${className}> entityList) {
		SqlParameterSource[] batch = SqlParameterSourceUtils
				.createBatch(entityList.toArray());
		getNamedParameterJdbcTemplate().batchUpdate(UPDATE_${classNameUpperCase}, batch);
		
	}

	@Override
	public void deleteById(String id) {
		this.getJdbcTemplate().update(DELETE_${classNameUpperCase}_BY_ID,id);
	}

	@Override
	public void batchDeleteById(final List<String> idList) {
		this.getJdbcTemplate().batchUpdate(DELETE_${classNameUpperCase}_BY_ID, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setObject(1, idList.get(i));
			}
			
			@Override
			public int getBatchSize() {
				return idList.size();
			}
		});
	}
	
	@Override
	public void delete(${className} entity) {
		this.getNamedParameterJdbcTemplate().update(DELETE_${classNameUpperCase}, new BeanPropertySqlParameterSource(entity));
	}

	@Override
	public void batchDelete(final List<${className}> entityList) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(entityList.toArray());
		this.getNamedParameterJdbcTemplate().batchUpdate(DELETE_${classNameUpperCase}, batch);
	}

	@Override
	public List<${className}> findAll() {
		return this.getJdbcTemplate().query(FIND_ALL, ROWMAPPER);
	}

	@Override
	public List<${className}> find(Map<String, Object> params) {
		return this.paginalQuery(getNamedParameterJdbcTemplate(), FIND_ALL, ROWMAPPER, params);
	}

	@Override
	public ${className} findById(String id) {
		try {
			return this.getJdbcTemplate().queryForObject(FIND_BY_ID, ROWMAPPER, id);
		} catch (Exception e) {
			return null;
		}
	}
}