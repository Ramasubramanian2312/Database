package neu.edu.cs5200.msn.ds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import neu.edu.cs5200.msn.ds.model.Cast;

public class CastManager {
	DataSource ds;

	public CastManager() {
		try {

			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCDatasourceAssignmentDB");
			System.out.println(ds);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//Create a Cast
	public void createCast(Cast newCast) {
		String sql = "insert into cast values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,newCast.getCharacterName());
			statement.setInt(2, newCast.getActorid());
			statement.setInt(3, newCast.getMovieid());
			statement.executeUpdate();
			connection.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		//Retrieve All Casts
		public List<Cast> readAllCast() 
		{
			List<Cast> casts = new ArrayList<Cast>();
			String sql = "select * from cast";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					Cast cast = new Cast();
					cast.setId(results.getInt("id"));
					cast.setCharacterName(results.getString("charactername"));
					cast.setActorid(results.getInt("actorid"));
					cast.setMovieid(results.getInt("movieid"));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return casts;
		}
		
		//Retrieve All Cast for an Actor
		public List<Cast> readAllCastForActor(String actorId) 
		{
			List<Cast> casts = new ArrayList<Cast>();
			String sql = "select * from cast where actorid = ?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				int id = Integer.parseInt(actorId);
				statement.setInt(1,id);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					Cast cast = new Cast();
					cast.setId(results.getInt("id"));
					cast.setCharacterName(results.getString("charactername"));
					cast.setActorid(results.getInt("actorid"));
					cast.setMovieid(results.getInt("movieid"));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return casts;
		}
		
		//Retrieve All Cast for a Movie
		public List<Cast> readAllCastForMovie(String movieId) 
		{
			List<Cast> casts = new ArrayList<Cast>();
			String sql = "select * from cast where movieid = ?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				int id = Integer.parseInt(movieId);
				statement.setInt(1,id);
				ResultSet results = statement.executeQuery();
				while(results.next()) {
					Cast cast = new Cast();
					cast.setId(results.getInt("id"));
					cast.setCharacterName(results.getString("charactername"));
					cast.setActorid(results.getInt("actorid"));
					cast.setMovieid(results.getInt("movieid"));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return casts;
		}
		
		//Retrieve a cast by castId
		public Cast readCastForId(String castId)
		{
			Cast cast = new Cast();
			
			String sql = "select * from cast where id = ?";
			Connection connection;
			try {
				connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				int id = Integer.parseInt(castId);
				statement.setInt(1, id);
				ResultSet results = statement.executeQuery();
				if(results.next()) {
					cast.setId(results.getInt("id"));
					cast.setCharacterName(results.getString("charactername"));
					cast.setActorid(results.getInt("actorid"));
					cast.setMovieid(results.getInt("movieid"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return cast;
		}
		
		//Update a Cast
		public void updateCast(String castId, Cast newCast)
		{	
			String sql = "update cast set charactername=?, actorid=?, movieid=? where"
					+ " id=?";
			Connection connection;
			try {
				connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				int id = Integer.parseInt(castId);
				statement.setString(1, newCast.getCharacterName());
				statement.setInt(2, newCast.getActorid());
				statement.setInt(3, newCast.getMovieid());
				statement.setInt(4, id);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Delete a Cast
		public void deleteCast(String castId)
		{
			String sql = "delete from cast where id = ?";
			try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql);
				int id = Integer.parseInt(castId);
				statement.setInt(1, id);
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
