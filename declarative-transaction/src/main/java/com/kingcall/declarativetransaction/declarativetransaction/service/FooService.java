package com.kingcall.declarativetransaction.declarativetransaction.service;

import com.kingcall.declarativetransaction.declarativetransaction.RollbackException;

public interface FooService {
    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;
}
