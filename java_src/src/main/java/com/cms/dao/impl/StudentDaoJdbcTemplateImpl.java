package com.cms.dao.impl;

import com.cms.constant.DbQueryConstant;
import com.cms.dao.StudentDao;
import com.cms.dao.qualifer.Datasource;
import com.cms.dao.qualifer.DatasourceType;
import com.cms.db.config.JdbcTemplate;
import com.cms.exception.ExceptionHandler;
import com.cms.mapper.impl.StudentRowMapperImpl;
import com.cms.model.Student;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Datasource(DatasourceType.JDBC_TEMPLATE)
public class StudentDaoJdbcTemplateImpl implements StudentDao {
    @Override
    public Optional<List<Student>> getAll() {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> Optional.of(connection.execute(DbQueryConstant.Student.GET_ALL, new StudentRowMapperImpl())),
                () -> ExceptionHandler.handle(connection::close),
                Optional.empty()));
    }

    @Override
    public Optional<Student> getById(Long id) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> Optional.of(connection.executeSingle(DbQueryConstant.Student.GET_BY_ID,
                        new StudentRowMapperImpl(), id)),
                () -> ExceptionHandler.handle(connection::close),
                Optional.empty()));
    }

    @Override
    public Optional<Student> getByEmailAddress(String emailAddress) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> Optional.of(connection.executeSingle(DbQueryConstant.Student.GET_BY_EMAIL,
                        new StudentRowMapperImpl(), emailAddress)),
                () -> ExceptionHandler.handle(connection::close),
                Optional.empty()));
    }

    @Override
    public Optional<Student> getByContactNo(String contactNo) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> Optional.of(connection.executeSingle(DbQueryConstant.Student.GET_BY_CONTACT_NO,
                        new StudentRowMapperImpl(), contactNo)),
                () -> ExceptionHandler.handle(connection::close),
                Optional.empty()));
    }

    @Override
    public Boolean save(Student student) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> connection.execute(DbQueryConstant.Student.SAVE,
                        student.getName(), student.getEmail(), student.getContactNo()) >= 1,
                () -> ExceptionHandler.handle(connection::close),
                false
        ));
    }

    @Override
    public Boolean update(Student student) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> connection.execute(DbQueryConstant.Student.UPDATE,
                        student.getName(), student.getEmail(), student.getContactNo(), student.getId()) >= 1,
                () -> ExceptionHandler.handle(connection::close),
                false
        ));
    }

    @Override
    public Boolean delete(Long id) {
        return JdbcTemplate.process((connection) -> ExceptionHandler.handle(
                () -> connection.execute(DbQueryConstant.Student.DELETE, id) >= 1,
                () -> ExceptionHandler.handle(connection::close),
                false
        ));
    }
}
