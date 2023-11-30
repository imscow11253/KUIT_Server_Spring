package kuit2.server.ServerApplication.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Slf4j
@Repository
public class StoreDao {
    private final JdbcTemplate jdbcTemplate;

    public StoreDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean isThereStoreId(long store_id){
        String sql = "SELECT EXISTS(SELECT store_id FROM `store` WHERE store_id=?)";
        return Boolean.TRUE.equals(this.jdbcTemplate.queryForObject(sql, boolean.class, store_id));
    }

    public String getStoreNameById(long store_id){
        String sql = "SELECT name FROM `store` WHERE store_id=?";
        return this.jdbcTemplate.queryForObject(sql, String.class, store_id);
    }

    public double getAverageValuationById(long store_id){
        String sql = "SELECT average_valuation FROM `store` WHERE store_id=?";
        return this.jdbcTemplate.queryForObject(sql, double.class, store_id);
    }

    public double getMinimumAcceptableOrderById(long store_id){
        String sql = "SELECT minimum_acceptable_order FROM `store` WHERE store_id=?";
        return this.jdbcTemplate.queryForObject(sql, double.class, store_id);
    }
}
