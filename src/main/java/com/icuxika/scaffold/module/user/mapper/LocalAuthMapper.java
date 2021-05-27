package com.icuxika.scaffold.module.user.mapper;

import com.icuxika.scaffold.module.user.entity.LocalAuth;
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

import static com.icuxika.scaffold.module.user.mapper.LocalAuthDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface LocalAuthMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    BasicColumn[] selectList = BasicColumn.columnList(id, username, password, phone);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source Table: t_local_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source Table: t_local_auth")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source Table: t_local_auth")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<LocalAuth> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source Table: t_local_auth")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<LocalAuth> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9927697+08:00", comments = "Source Table: t_local_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("LocalAuthResult")
    Optional<LocalAuth> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "LocalAuthResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR)
    })
    List<LocalAuth> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, localAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, localAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default int insert(LocalAuth record) {
        return MyBatis3Utils.insert(this::insert, record, localAuth, c ->
                c.map(id).toProperty("id")
                        .map(username).toProperty("username")
                        .map(password).toProperty("password")
                        .map(phone).toProperty("phone")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default int insertMultiple(Collection<LocalAuth> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, localAuth, c ->
                c.map(id).toProperty("id")
                        .map(username).toProperty("username")
                        .map(password).toProperty("password")
                        .map(phone).toProperty("phone")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default int insertSelective(LocalAuth record) {
        return MyBatis3Utils.insert(this::insert, record, localAuth, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(username).toPropertyWhenPresent("username", record::getUsername)
                        .map(password).toPropertyWhenPresent("password", record::getPassword)
                        .map(phone).toPropertyWhenPresent("phone", record::getPhone)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default Optional<LocalAuth> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, localAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default List<LocalAuth> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, localAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9937737+08:00", comments = "Source Table: t_local_auth")
    default List<LocalAuth> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, localAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9947691+08:00", comments = "Source Table: t_local_auth")
    default Optional<LocalAuth> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9947691+08:00", comments = "Source Table: t_local_auth")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, localAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9947691+08:00", comments = "Source Table: t_local_auth")
    static UpdateDSL<UpdateModel> updateAllColumns(LocalAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(username).equalTo(record::getUsername)
                .set(password).equalTo(record::getPassword)
                .set(phone).equalTo(record::getPhone);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9947691+08:00", comments = "Source Table: t_local_auth")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(LocalAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(username).equalToWhenPresent(record::getUsername)
                .set(password).equalToWhenPresent(record::getPassword)
                .set(phone).equalToWhenPresent(record::getPhone);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9947691+08:00", comments = "Source Table: t_local_auth")
    default int updateByPrimaryKey(LocalAuth record) {
        return update(c ->
                c.set(username).equalTo(record::getUsername)
                        .set(password).equalTo(record::getPassword)
                        .set(phone).equalTo(record::getPhone)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9947691+08:00", comments = "Source Table: t_local_auth")
    default int updateByPrimaryKeySelective(LocalAuth record) {
        return update(c ->
                c.set(username).equalToWhenPresent(record::getUsername)
                        .set(password).equalToWhenPresent(record::getPassword)
                        .set(phone).equalToWhenPresent(record::getPhone)
                        .where(id, isEqualTo(record::getId))
        );
    }
}