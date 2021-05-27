package com.icuxika.scaffold.module.user.mapper;

import com.icuxika.scaffold.module.user.entity.ThirdAuth;
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

import static com.icuxika.scaffold.module.user.mapper.ThirdAuthDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface ThirdAuthMapper {
    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    BasicColumn[] selectList = BasicColumn.columnList(id, openId, type);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source Table: t_third_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source Table: t_third_auth")
    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source Table: t_third_auth")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "record.id")
    int insert(InsertStatementProvider<ThirdAuth> insertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.995769+08:00", comments = "Source Table: t_third_auth")
    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<ThirdAuth> multipleInsertStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("ThirdAuthResult")
    Optional<ThirdAuth> selectOne(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "ThirdAuthResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "open_id", property = "openId", jdbcType = JdbcType.BIGINT),
            @Result(column = "type", property = "type", jdbcType = JdbcType.INTEGER)
    })
    List<ThirdAuth> selectMany(SelectStatementProvider selectStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, thirdAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, thirdAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default int insert(ThirdAuth record) {
        return MyBatis3Utils.insert(this::insert, record, thirdAuth, c ->
                c.map(id).toProperty("id")
                        .map(openId).toProperty("openId")
                        .map(type).toProperty("type")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default int insertMultiple(Collection<ThirdAuth> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, thirdAuth, c ->
                c.map(id).toProperty("id")
                        .map(openId).toProperty("openId")
                        .map(type).toProperty("type")
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default int insertSelective(ThirdAuth record) {
        return MyBatis3Utils.insert(this::insert, record, thirdAuth, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(openId).toPropertyWhenPresent("openId", record::getOpenId)
                        .map(type).toPropertyWhenPresent("type", record::getType)
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default Optional<ThirdAuth> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, thirdAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default List<ThirdAuth> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, thirdAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default List<ThirdAuth> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, thirdAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default Optional<ThirdAuth> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, thirdAuth, completer);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    static UpdateDSL<UpdateModel> updateAllColumns(ThirdAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(openId).equalTo(record::getOpenId)
                .set(type).equalTo(record::getType);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(ThirdAuth record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(openId).equalToWhenPresent(record::getOpenId)
                .set(type).equalToWhenPresent(record::getType);
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default int updateByPrimaryKey(ThirdAuth record) {
        return update(c ->
                c.set(openId).equalTo(record::getOpenId)
                        .set(type).equalTo(record::getType)
                        .where(id, isEqualTo(record::getId))
        );
    }

    @Generated(value = "org.mybatis.generator.api.MyBatisGenerator", date = "2021-05-26T14:08:41.9967693+08:00", comments = "Source Table: t_third_auth")
    default int updateByPrimaryKeySelective(ThirdAuth record) {
        return update(c ->
                c.set(openId).equalToWhenPresent(record::getOpenId)
                        .set(type).equalToWhenPresent(record::getType)
                        .where(id, isEqualTo(record::getId))
        );
    }
}