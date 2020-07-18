package com.kingcall.declarativetransaction.declarativetransaction.service.impl;

import com.kingcall.declarativetransaction.declarativetransaction.RollbackException;
import com.kingcall.declarativetransaction.declarativetransaction.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("fooService2")
public class FooServiceImp2 implements FooService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("fooService2")
    FooService fooService;

    @Override
    @Transactional
    public void insertRecord() {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('AAA')");
    }

    @Override
    @Transactional(rollbackFor = RollbackException.class)
    public void insertThenRollback() throws RollbackException {
        jdbcTemplate.execute("INSERT INTO FOO (BAR) VALUES ('BBB')");
        throw new RollbackException();
    }

    /**
     * 这个地方没有事务,但是可以通过注入进来的对象实现方法的内部调用,这个可以被正确回滚
     * @throws RollbackException
     */
    @Override
    public void invokeInsertThenRollback() throws RollbackException {
        fooService.insertThenRollback();
    }
}
