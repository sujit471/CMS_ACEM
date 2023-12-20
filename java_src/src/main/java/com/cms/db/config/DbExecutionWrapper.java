package com.cms.db.config;

public interface DbExecutionWrapper<T> {

    T execute(DbConnector dbConnector);
}
