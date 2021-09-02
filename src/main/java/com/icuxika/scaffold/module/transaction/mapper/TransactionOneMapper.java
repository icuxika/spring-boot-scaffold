package com.icuxika.scaffold.module.transaction.mapper;

import com.icuxika.scaffold.module.transaction.entity.TransactionOne;
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

import static com.icuxika.scaffold.module.transaction.mapper.TransactionOneDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface TransactionOneMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, nickname, avatar);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type = SqlProviderAdapter.class, method = "insert")
    int insert(InsertStatementProvider<TransactionOne> insertStatement);

    @InsertProvider(type = SqlProviderAdapter.class, method = "insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<TransactionOne> multipleInsertStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @ResultMap("TransactionOneResult")
    Optional<TransactionOne> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type = SqlProviderAdapter.class, method = "select")
    @Results(id = "TransactionOneResult", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "nickname", property = "nickname", jdbcType = JdbcType.VARCHAR),
            @Result(column = "avatar", property = "avatar", jdbcType = JdbcType.VARCHAR)
    })
    List<TransactionOne> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type = SqlProviderAdapter.class, method = "update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, transactionOne, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, transactionOne, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    default int insert(TransactionOne record) {
        return MyBatis3Utils.insert(this::insert, record, transactionOne, c ->
                c.map(id).toProperty("id")
                        .map(nickname).toProperty("nickname")
                        .map(avatar).toProperty("avatar")
        );
    }

    default int insertMultiple(Collection<TransactionOne> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, transactionOne, c ->
                c.map(id).toProperty("id")
                        .map(nickname).toProperty("nickname")
                        .map(avatar).toProperty("avatar")
        );
    }

    default int insertSelective(TransactionOne record) {
        return MyBatis3Utils.insert(this::insert, record, transactionOne, c ->
                c.map(id).toPropertyWhenPresent("id", record::getId)
                        .map(nickname).toPropertyWhenPresent("nickname", record::getNickname)
                        .map(avatar).toPropertyWhenPresent("avatar", record::getAvatar)
        );
    }

    default Optional<TransactionOne> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, transactionOne, completer);
    }

    default List<TransactionOne> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, transactionOne, completer);
    }

    default List<TransactionOne> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, transactionOne, completer);
    }

    default Optional<TransactionOne> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
                c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, transactionOne, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(TransactionOne record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(nickname).equalTo(record::getNickname)
                .set(avatar).equalTo(record::getAvatar);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(TransactionOne record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(nickname).equalToWhenPresent(record::getNickname)
                .set(avatar).equalToWhenPresent(record::getAvatar);
    }

    default int updateByPrimaryKey(TransactionOne record) {
        return update(c ->
                c.set(nickname).equalTo(record::getNickname)
                        .set(avatar).equalTo(record::getAvatar)
                        .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(TransactionOne record) {
        return update(c ->
                c.set(nickname).equalToWhenPresent(record::getNickname)
                        .set(avatar).equalToWhenPresent(record::getAvatar)
                        .where(id, isEqualTo(record::getId))
        );
    }
}