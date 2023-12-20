package com.cms.exception.wrapper;

@FunctionalInterface
public interface StatementWrapper {

    void execute() throws Exception;
}
