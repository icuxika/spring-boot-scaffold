package com.icuxika.scaffold.module.transaction.service;

import com.icuxika.scaffold.module.transaction.entity.TransactionOne;
import com.icuxika.scaffold.module.transaction.entity.TransactionThree;
import com.icuxika.scaffold.module.transaction.entity.TransactionTwo;
import com.icuxika.scaffold.module.transaction.mapper.TransactionOneMapper;
import com.icuxika.scaffold.module.transaction.mapper.TransactionThreeMapper;
import com.icuxika.scaffold.module.transaction.mapper.TransactionTwoMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionOneMapper transactionOneMapper;

    @Autowired
    private TransactionTwoMapper transactionTwoMapper;

    @Autowired
    private TransactionThreeMapper transactionThreeMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void fun1() {
        ((TransactionService) AopContext.currentProxy()).fun2();
        insert1();
//        throw new RuntimeException("测试1");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void fun2() {
        insert2();
        throw new RuntimeException("测试2");
    }

    @Override
    public void fun3() {

    }

    public void insert1() {
        TransactionOne one = new TransactionOne();
        one.setNickname("1");
        one.setAvatar("1");
        int result = transactionOneMapper.insert(one);
        System.out.println("insert1 result: " + result);
    }

    public void insert2() {
        TransactionTwo two = new TransactionTwo();
        two.setNickname("2");
        two.setAvatar("2");
        int result = transactionTwoMapper.insert(two);
        System.out.println("insert2 result: " + result);
    }

    public void insert3() {
        TransactionThree three = new TransactionThree();
        three.setNickname("3");
        three.setAvatar("3");
        int result = transactionThreeMapper.insert(three);
        System.out.println("insert3 result: " + result);
    }
}
