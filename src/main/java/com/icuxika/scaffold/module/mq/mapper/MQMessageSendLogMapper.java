package com.icuxika.scaffold.module.mq.mapper;

import static com.icuxika.scaffold.module.mq.mapper.MQMessageSendLogDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.icuxika.scaffold.module.mq.entity.MQMessageSendLog;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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
public interface MQMessageSendLogMapper {
    BasicColumn[] selectList = BasicColumn.columnList(id, messageId, status, exchange, routingKey, count, createTime, updateTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<MQMessageSendLog> insertStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<MQMessageSendLog> multipleInsertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MQMessageSendLogResult")
    Optional<MQMessageSendLog> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MQMessageSendLogResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="message_id", property="messageId", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="exchange", property="exchange", jdbcType=JdbcType.VARCHAR),
        @Result(column="routing_key", property="routingKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="count", property="count", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MQMessageSendLog> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, MQMessageSendLog, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, MQMessageSendLog, completer);
    }

    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    default int insert(MQMessageSendLog record) {
        return MyBatis3Utils.insert(this::insert, record, MQMessageSendLog, c ->
            c.map(id).toProperty("id")
            .map(messageId).toProperty("messageId")
            .map(status).toProperty("status")
            .map(exchange).toProperty("exchange")
            .map(routingKey).toProperty("routingKey")
            .map(count).toProperty("count")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    default int insertMultiple(Collection<MQMessageSendLog> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, MQMessageSendLog, c ->
            c.map(id).toProperty("id")
            .map(messageId).toProperty("messageId")
            .map(status).toProperty("status")
            .map(exchange).toProperty("exchange")
            .map(routingKey).toProperty("routingKey")
            .map(count).toProperty("count")
            .map(createTime).toProperty("createTime")
            .map(updateTime).toProperty("updateTime")
        );
    }

    default int insertSelective(MQMessageSendLog record) {
        return MyBatis3Utils.insert(this::insert, record, MQMessageSendLog, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(messageId).toPropertyWhenPresent("messageId", record::getMessageId)
            .map(status).toPropertyWhenPresent("status", record::getStatus)
            .map(exchange).toPropertyWhenPresent("exchange", record::getExchange)
            .map(routingKey).toPropertyWhenPresent("routingKey", record::getRoutingKey)
            .map(count).toPropertyWhenPresent("count", record::getCount)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
        );
    }

    default Optional<MQMessageSendLog> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, MQMessageSendLog, completer);
    }

    default List<MQMessageSendLog> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, MQMessageSendLog, completer);
    }

    default List<MQMessageSendLog> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, MQMessageSendLog, completer);
    }

    default Optional<MQMessageSendLog> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, MQMessageSendLog, completer);
    }

    static UpdateDSL<UpdateModel> updateAllColumns(MQMessageSendLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(messageId).equalTo(record::getMessageId)
                .set(status).equalTo(record::getStatus)
                .set(exchange).equalTo(record::getExchange)
                .set(routingKey).equalTo(record::getRoutingKey)
                .set(count).equalTo(record::getCount)
                .set(createTime).equalTo(record::getCreateTime)
                .set(updateTime).equalTo(record::getUpdateTime);
    }

    static UpdateDSL<UpdateModel> updateSelectiveColumns(MQMessageSendLog record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(messageId).equalToWhenPresent(record::getMessageId)
                .set(status).equalToWhenPresent(record::getStatus)
                .set(exchange).equalToWhenPresent(record::getExchange)
                .set(routingKey).equalToWhenPresent(record::getRoutingKey)
                .set(count).equalToWhenPresent(record::getCount)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime);
    }

    default int updateByPrimaryKey(MQMessageSendLog record) {
        return update(c ->
            c.set(messageId).equalTo(record::getMessageId)
            .set(status).equalTo(record::getStatus)
            .set(exchange).equalTo(record::getExchange)
            .set(routingKey).equalTo(record::getRoutingKey)
            .set(count).equalTo(record::getCount)
            .set(createTime).equalTo(record::getCreateTime)
            .set(updateTime).equalTo(record::getUpdateTime)
            .where(id, isEqualTo(record::getId))
        );
    }

    default int updateByPrimaryKeySelective(MQMessageSendLog record) {
        return update(c ->
            c.set(messageId).equalToWhenPresent(record::getMessageId)
            .set(status).equalToWhenPresent(record::getStatus)
            .set(exchange).equalToWhenPresent(record::getExchange)
            .set(routingKey).equalToWhenPresent(record::getRoutingKey)
            .set(count).equalToWhenPresent(record::getCount)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .where(id, isEqualTo(record::getId))
        );
    }
}