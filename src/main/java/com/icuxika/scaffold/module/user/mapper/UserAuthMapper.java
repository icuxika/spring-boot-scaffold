package com.icuxika.scaffold.module.user.mapper;

import com.icuxika.scaffold.module.user.entity.UserAuth;
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

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.icuxika.scaffold.module.user.mapper.UserAuthDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface UserAuthMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    BasicColumn[] selectList = BasicColumn.columnList(id, userId, authId, type);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<UserAuth> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserAuth> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("UserAuthResult")
    Optional<UserAuth> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "UserAuthResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "user_id", property = "userId", jdbcType = JdbcType.BIGINT),
            @Result(column = "auth_id", property = "authId", jdbcType = JdbcType.BIGINT),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER)
    })
    List<UserAuth> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, userAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, userAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default int insert(UserAuth record) {
        return MyBatis3Utils.insert(this::insert, record, userAuth, c ->
                c.map(id).toProperty("id")
                        .map(userId).toProperty("userId")
                        .map(authId).toProperty("authId")
                        .map(type).toProperty("type")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default int insertMultiple(Collection<UserAuth> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, userAuth, c ->
                c.map(id).toProperty("id")
                        .map(userId).toProperty("userId")
                        .map(authId).toProperty("authId")
                        .map(type).toProperty("type")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default int insertSelective(UserAuth record) {
        return MyBatis3Utils.insert(this::insert, record, userAuth, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(userId).toPropertyWhenPresent("userId", record::getUserId)
                        .map(authId).toPropertyWhenPresent("authId", record::getAuthId)
                        .map(type).toPropertyWhenPresent("type", record::getType)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default Optional<UserAuth> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, userAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default List<UserAuth> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, userAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default List<UserAuth> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, userAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9907704+08:00", comments = "Source Table: t_user_auth")
    default Optional<UserAuth> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9917698+08:00", comments = "Source Table: t_user_auth")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, userAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9917698+08:00", comments = "Source Table: t_user_auth")
    static UpdateDSL<UpdateModel> updateAllColumns(UserAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(userId).equalTo(record::getUserId)
                .set(authId).equalTo(record::getAuthId)
                .set(type).equalTo(record::getType);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9917698+08:00", comments = "Source Table: t_user_auth")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(userId).equalToWhenPresent(record::getUserId)
                .set(authId).equalToWhenPresent(record::getAuthId)
                .set(type).equalToWhenPresent(record::getType);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9917698+08:00", comments = "Source Table: t_user_auth")
    default int updateByPrimaryKey(UserAuth record) {
        return update(c ->
                c.set(userId).equalTo(record::getUserId)
                        .set(authId).equalTo(record::getAuthId)
                        .set(type).equalTo(record::getType)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9917698+08:00", comments = "Source Table: t_user_auth")
    default int updateByPrimaryKeySelective(UserAuth record) {
        return update(c ->
                c.set(userId).equalToWhenPresent(record::getUserId)
                        .set(authId).equalToWhenPresent(record::getAuthId)
                        .set(type).equalToWhenPresent(record::getType)
                        .where(id, isEqualTo(record::getId))
        );
    }
}