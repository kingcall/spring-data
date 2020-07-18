package com.kingcall.declarativetransaction.declarativetransaction.service.impl;

import com.kingcall.declarativetransaction.declarativetransaction.RollbackException;
import com.kingcall.declarativetransaction.declarativetransaction.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("fooService4")
public class FooServiceImp4 implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insertRecord() {

    }

    @Override
    @Transactional(rollbackFor = RollbackException.class,propagation = Propagation.NESTED)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
       // throw new RollbackException();
    }

    /**
     * 这个地方没有事务,但是可以通过注入进来的对象实现方法的内部调用
     * @throws RollbackException
     */
    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void invokeInsertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
        insertThenRollback();
        throw new RollbackException();
    }
}
