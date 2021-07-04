package ca.sheridancollege.fadhlmi.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.fadhlmi.bean.Appointment;

@Repository
public class DatabaseAccess {

	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public ArrayList<Appointment> getApps() {
		String query = "Select id, name, email from appointment";
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		List<Map<String, Object>> rows =	
				jdbc.queryForList(query, new HashMap<String,Object>());
		System.out.println(rows);
		for (Map<String, Object> row : rows) {
		list.add(new Appointment(
				(Long) row.get("id"),
				(String) row.get("name"),
				(String) row.get("email")		));
		}
		return list;
		}
	
	public ArrayList<Appointment> getApps1() {
		String q = "Select id, name, email from appointment";
		ArrayList<Appointment> ap =
		(ArrayList<Appointment>)jdbc.query(q,
		new BeanPropertyRowMapper<Appointment>(Appointment.class));
		return ap;
		}
	public Appointment getApp(int id) {
		String query = "SELECT id, name, email FROM appointment where id=:id";
		ArrayList<Appointment> list = new ArrayList<Appointment>();
		Map<String, Object> map= new HashMap<String,Object>();
		
		map.put("id", id);
		
		List<Map<String, Object>> rows =	
				jdbc.queryForList(query, map);
		
		System.out.println(rows);
		
		for (Map<String, Object> row : rows) {
		list.add(new Appointment(
				(Long) row.get("id"),
				(String) row.get("name"),
				(String) row.get("email")
		));
		}
		return list.get(0);
		}
	public void insertApp(Appointment ap) {
		String query="INSERT INTO appointment (name, email)"
				+ " VALUES (:name, :email)";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", ap.getName());
		namedParameters.addValue("email", ap.getEmail());
		
		jdbc.update(query, namedParameters);
	}
	public void deleteAp(int id) {
		String query = "delete from appointment where id=:id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		jdbc.update(query, namedParameters);
	}
	public void saveAppointment(Appointment ap) {
		String sql = "update appointment set name=:n, email=:e where id=:i";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("n", ap.getName());
		params.put("e", ap.getEmail());
		params.put("i",ap.getId());
		jdbc.update(sql, params);
	}
}
