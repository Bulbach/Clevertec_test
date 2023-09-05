package org.example.repository.jdbc;

import org.example.exceptions.AppBankException;
import org.example.exceptions.ApplicationBaseException;
import org.example.jdbc.mapper.ResultSetMapper;
import org.example.jdbc.query.CrudJdbcSqlQueryHolder;
import org.example.jdbc.statement.StatementInitializer;
import org.example.model.Model;
import org.example.repository.GenericAbstractRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCrudDao<T extends Model> extends BaseAbstractJdbcDao implements GenericAbstractRepository<T> {


    private static final String ERROR_CONNECTION_WITH_METHOD = "Error get connection with data base method";
    private static final Logger logger = LoggerFactory.getLogger(AbstractCrudDao.class);
    protected abstract Logger getLogger();
    protected abstract <R extends CrudJdbcSqlQueryHolder> R getSqlHolder();

    protected abstract StatementInitializer<T> queryTranslate();

    protected abstract ResultSetMapper<T> abstractResultSet();

    private T getById(Long id, Connection connection) throws AppBankException {
        try (PreparedStatement pr = connection.prepareStatement(getSqlHolder().getByIdSql())) {

            queryTranslate().setByIdQueryStatement(pr, id);

            try (ResultSet rs = pr.executeQuery()) {

                if (rs.next()) {
                    return abstractResultSet().processResultSet(rs);
                }
                throw new AppBankException("Error create entity");
            } catch (SQLException e) {
                getLogger().error(e.getMessage(),e);
                e.printStackTrace();
            } catch (AppBankException e) {
                throw new AppBankException("Error for mapping in getById", e);
            }
            throw new AppBankException("Error getById");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppBankException("Error get connection with data base method getById", e);
        }
    }
    @Override
    public T getById(Long id) throws AppBankException {

        try (Connection connection = getConnector().getConnection()) {
            return getById(id, connection);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppBankException("Error get connection with data base method getById", e);
        }
    }

    @Override
    public Collection<T> getAll() {
        return getAllByQuery(getSqlHolder().getAllSql());
    }
    @Override
    public T create(T t) throws AppBankException {
        try (Connection con = getConnector().getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement pr = con.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS)) {
                queryTranslate().createQueryStatement(pr, t);
                pr.executeUpdate();
                ResultSet rs = pr.getGeneratedKeys();
                if (rs.next()) {
                    T item = getById(rs.getLong(1), con);
                    con.commit();
                    return item;
                }
                throw new AppBankException("Error create");
            } catch (SQLException e) {
                con.rollback();
                throw new AppBankException("Error query", e);
            } catch (AppBankException e) {
                con.rollback();
                logger.error(e.getMessage(),e);
                throw e;
            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppBankException(ERROR_CONNECTION_WITH_METHOD + " create", e);
        }
    }

    @Override
    public T update(T t) throws AppBankException {
        try (Connection con = getConnector().getConnection()) {
            try (PreparedStatement pr = con.prepareStatement(getSqlHolder().updateSql())) {
                con.setAutoCommit(false);
                queryTranslate().updateQueryStatement(pr, t);
                pr.executeUpdate();
                con.commit();
                return t;
            } catch (SQLException throwables) {
                con.rollback();
                throw new AppBankException(throwables.getMessage() + "in method update");
            } finally {
                con.setAutoCommit(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppBankException("Error with method update");
        }
    }

    @Override
    public void delete(Long id) throws AppBankException {

        try (Connection con = getConnector().getConnection()) {
            try (PreparedStatement pr = con.prepareStatement(getSqlHolder().deleteSql())) {
                con.setAutoCommit(false);
                pr.setLong(1, id);
                pr.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                con.rollback();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppBankException("Error with method delete");
        }

    }


    public T createWithId(T t) throws AppBankException {
        try (Connection con = getConnector().getConnection();
             PreparedStatement ps = con.prepareStatement(getSqlHolder().createWithIdSql())) {
            con.setAutoCommit(false);
            queryTranslate().createWithIdQueryStatement(ps, t);

            if (ps.executeUpdate() == 0) {
                throw new AppBankException("Error createWithId");
            }
            con.commit();
            con.setAutoCommit(true);
            return t;
        } catch (SQLException throwables) {
            throw new AppBankException(ERROR_CONNECTION_WITH_METHOD + " createWithId");
        }
    }

    public void saveAll(Collection<T> saveCollection) throws AppBankException {

        for (T item : saveCollection) {

            createOrUpdate(item);
        }
    }

    public T createOrUpdate(T t) throws AppBankException {

        T temp;
        if (isContainsById(t)) {
            temp = update(t);
        } else if (t.getId() > 0) {
            temp = createWithId(t);
        } else {
            temp = create(t);
        }
        return temp;
    }

    protected T getOneByQuery(String sqlQuery) throws AppBankException {
        try (Connection con = getConnector().getConnection();
             PreparedStatement pr = con.prepareStatement(sqlQuery);
             ResultSet resultSet = pr.executeQuery()) {

            return abstractResultSet().processResultSet(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new AppBankException("Error in database method getOneByQuery");
        }
    }

    protected Collection<T> getAllByQuery(String sqlQuery) {
        try (Connection con = getConnector().getConnection();
             PreparedStatement pr = con.prepareStatement(sqlQuery);
             ResultSet rs = pr.executeQuery()) {

            List<T> lt = new ArrayList<>();
            while (rs.next()) {
                lt.add(abstractResultSet().processResultSet(rs));
            }
            return lt;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationBaseException(ERROR_CONNECTION_WITH_METHOD + " getAllByQuery");
        }
    }

    private boolean isContainsById(T t) {
        try {
            getById(t.getId());
            return true;
        } catch (AppBankException e) {
            return false;
        }
    }
}
