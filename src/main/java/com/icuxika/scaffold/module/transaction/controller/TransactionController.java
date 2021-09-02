package com.icuxika.scaffold.module.transaction.controller;

import com.icuxika.scaffold.module.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 事务注解、传播属性测试
 */
@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("fun1")
    public void fun1() {
        transactionService.fun1();
    }
}
