package com.cesca.sensors.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.cesca.sensors.entity.DHTSensor;

@Primary
@Repository("PostgreSQL_DHTSensorDAO")
public class PostgreeSQL_DHTSensorDAO implements DHTSensorDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static class DHTSensorRowMapper implements RowMapper<DHTSensor> {

		@Override
		public DHTSensor mapRow(ResultSet rs, int rowNumber) throws SQLException {
			DHTSensor dht = new DHTSensor();
			dht.setCollectionTimestamp(rs.getTimestamp("collection_timestamp").getTime());
			dht.setHumidity(rs.getFloat("humidity"));
			dht.setTemperature(rs.getFloat("temperature"));
			return dht;
		}

	}

	@Override
	public DHTSensor getLatestData() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM dht_sensor ");
		sql.append("ORDER BY collection_timestamp DESC ");
		sql.append("LIMIT 1");

		return this.jdbcTemplate.queryForObject(sql.toString(), new DHTSensorRowMapper());
	}

}
