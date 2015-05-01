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

import neu.edu.cs5200.msn.ds.model.Actor;

public class ActorManager {
	DataSource ds;

	public ActorManager() {
		try {

			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCDatasourceAssignmentDB");
			System.out.println(ds);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//Create an actor
	public void createActor(Actor newActor) {
		String sql = "insert into actor values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,newActor.getFirstName());
			statement.setString(2, newActor.getLastName());
			statement.setDate(3, newActor.getDateOfBirth());
			statement.executeUpdate();
			connection.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Retrieve All Actors
	public List<Actor> readAllActors() {
		List<Actor> actors = new ArrayList<Actor>();
		String sql = "select * from actor";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Actor actor = new Actor();
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstname"));
				actor.setLastName(results.getString("lastname"));
				actor.setDateOfBirth(results.getDate("dateofbirth"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actors;
	}
	
	//Retrieve an actor by id
	public Actor readActor(String actorId)
	{
		Actor actor = new Actor();
		
		String sql = "select * from actor where id = ?";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(actorId);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				actor.setId(results.getInt("id"));
				actor.setFirstName(results.getString("firstname"));
				actor.setLastName(results.getString("lastname"));
				actor.setDateOfBirth(results.getDate("dateofbirth"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actor;
	}
	
	//Update a movie
	public void updateActor(String actorId, Actor actor)
	{	
		String sql = "update actor set firstname=?, lastname=?, dateofbirth=? where"
				+ " id=?";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(actorId);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setDate(3, actor.getDateOfBirth());
			statement.setInt(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete a Movie
	public void deleteActor(String actorId)
	{
		String sql = "delete from actor where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(actorId);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
