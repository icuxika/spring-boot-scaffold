package com.icuxika.scaffold.module.transaction.mapper;

import com.icuxika.scaffold.module.transaction.entity.TransactionThree;
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

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.icuxika.scaffold.module.transaction.mapper.TransactionThreeDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface TransactionThreeMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, nickname, avatar);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<TransactionThree> insertStatement);

    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TransactionThree> multipleInsertStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("TransactionThreeResult")
    Optional<TransactionThree> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "TransactionThreeResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar", property = "avatar", jdbcType = JdbcType.VARCHAR)
    })
    List<TransactionThree> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, transactionThree, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, transactionThree, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    default int insert(TransactionThree record) {
        return MyBatis3Utils.insert(this::insert, record, transactionThree, c ->
                c.map(id).toProperty("id")
                        .map(nickname).toProperty("nickname")
                        .map(avatar).toProperty("avatar")
        );
    }

    default int insertMultiple(Collection<TransactionThree> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, transactionThree, c ->
                c.map(id).toProperty("id")
                        .map(nickname).toProperty("nickname")
                        .map(avatar).toProperty("avatar")
        );
    }

    default int insertSelective(TransactionThree record) {
        return MyBatis3Utils.insert(this::insert, record, transactionThree, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(nickname).toPropertyWhenPresent("nickname", record::getNickname)
                        .map(avatar).toPropertyWhenPresent("avatar", record::getAvatar)
        );
    }

    default Optional<TransactionThree> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, transactionThree, completer);
    }

    default List<TransactionThree> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, transactionThree, completer);
    }

    default List<TransactionThree> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, transactionThree, completer);
    }

    default Optional<TransactionThree> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, transactionThree, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(TransactionThree record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(nickname).equalTo(record::getNickname)
                .set(avatar).equalTo(record::getAvatar);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(TransactionThree record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(avatar).equalToWhenPresent(record::getAvatar);
    }

    default int updateByPrimaryKey(TransactionThree record) {
        return update(c ->
                c.set(nickname).equalTo(record::getNickname)
                        .set(avatar).equalTo(record::getAvatar)
                        .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(TransactionThree record) {
        return update(c ->
                c.set(nickname).equalToWhenPresent(record::getNickname)
                        .set(avatar).equalToWhenPresent(record::getAvatar)
                        .where(id, isEqualTo(record::getId))
        );
    }
}