package com.aurfy.haze.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aurfy.haze.dao.exceptions.MapperException;
import com.aurfy.haze.entity.Entity;

/**
 * <p>
 * interface for all CRUD mappers. <br />
 * some basic CRUD operation already defined, sub-classes and sub-interfaces may choose to implement part or all of
 * them.
 * </p>
 * 
 * @author hermano
 *
 */
public interface CRUDMapper extends HazeMapper {

	/**
	 * create a new entity in database.
	 * 
	 * @param entity
	 * @return rows affected
	 * @throws MapperException
	 */
	public <T extends Entity> int insert(T entity) throws MapperException;

	/**
	 * get an entity from database by the primary key.
	 * 
	 * @param id
	 *            the primary key
	 * @return
	 * @throws MapperException
	 */
	public <T extends Entity> T selectOne(String id) throws MapperException;

	/**
	 * <p>
	 * search the entities with the given filter.<br />
	 * taken all non-null properties of the <code>filterEntity</code> parameter as the filter criteria.<br/>
	 * it is up to the concrete mapper to decide how each property will be used (equals, contains, startWith, etc).
	 * </p>
	 * 
	 * @param filterEntity
	 * @param startIndex
	 * @param fetchRows
	 * @return
	 * @throws MapperException
	 */
	public <T extends Entity> List<T> selectBy(@Param("filterEntity") T filterEntity,
			@Param("startIndex") int startIndex, @Param("fetchRows") int fetchRows) throws MapperException;

	/**
	 * retrieve all entities from the database.
	 * 
	 * @return
	 * @throws MapperException
	 */
	public <T extends Entity> List<T> selectAll() throws MapperException;

	/**
	 * get total counter of entities that are filtered by the given criteria. <br/>
	 * check {@link #selectBy} for more detail.
	 * 
	 * @param filterEntity
	 * @return
	 * @throws MapperException
	 */
	public <T extends Entity> int countBy(@Param("filterEntity") T filterEntity) throws MapperException;

	/**
	 * get total counter of all entities in database.
	 * 
	 * @return
	 * @throws MapperException
	 */
	public <T extends Entity> int countAll() throws MapperException;

	/**
	 * update an entity.
	 * 
	 * @param entity
	 * @return row affected
	 * @throws MapperException
	 */
	public <T extends Entity> int update(T entity) throws MapperException;

	/**
	 * remove an entity from the database.
	 * 
	 * @param id
	 * @return row affected
	 * @throws MapperException
	 */
	public <T extends Entity> int delete(@Param("ID") String id) throws MapperException;
}
