package com.cms.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T map(ResultSet resultSet) throws Exception;
}
