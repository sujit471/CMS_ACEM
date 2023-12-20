package com.cms.exception.wrapper;

@FunctionalInterface
public interface ReturnableStatementWrapper<T> {

    T execute() throws Exception;
}
