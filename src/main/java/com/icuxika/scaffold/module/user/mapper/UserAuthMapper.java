package com.icuxika.scaffold.module.user.mapper;

import static com.icuxika.scaffold.module.user.mapper.UserAuthDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.icuxika.scaffold.module.user.entity.UserAuth;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface UserAuthMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, authId, type);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<UserAuth> insertStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserAuth> multipleInsertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserAuthResult")
    Optional<UserAuth> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserAuthResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="auth_id", property="authId", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<UserAuth> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userAuth, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userAuth, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(UserAuth record) {
        return MyBatis3Utils.insert(this::insert, record, userAuth, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(authId).toProperty("authId")
            .map(type).toProperty("type")
        );
    }

    default int insertMultiple(Collection<UserAuth> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userAuth, c ->
            c.map(id).toProperty("id")
            .map(userId).toProperty("userId")
            .map(authId).toProperty("authId")
            .map(type).toProperty("type")
        );
    }

    default int insertSelective(UserAuth record) {
        return MyBatis3Utils.insert(this::insert, record, userAuth, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(authId).toPropertyWhenPresent("authId", record::getAuthId)
            .map(type).toPropertyWhenPresent("type", record::getType)
        );
    }

    default Optional<UserAuth> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userAuth, completer);
    }

    default List<UserAuth> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userAuth, completer);
    }

    default List<UserAuth> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userAuth, completer);
    }

    default Optional<UserAuth> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userAuth, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(UserAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(authId).equalTo(record::getAuthId)
                .set(type).equalTo(record::getType);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(authId).equalToWhenPresent(record::getAuthId)
                .set(type).equalToWhenPresent(record::getType);
    }

    default int updateByPrimaryKey(UserAuth record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(authId).equalTo(record::getAuthId)
            .set(type).equalTo(record::getType)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(UserAuth record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(authId).equalToWhenPresent(record::getAuthId)
            .set(type).equalToWhenPresent(record::getType)
            .where(id, isEqualTo(record::getId))
        );
    }
}