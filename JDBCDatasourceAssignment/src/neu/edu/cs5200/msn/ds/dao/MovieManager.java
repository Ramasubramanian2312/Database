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

import neu.edu.cs5200.msn.ds.model.Movie;

public class MovieManager {
	DataSource ds;

	public MovieManager() {
		try {

			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCDatasourceAssignmentDB");
			System.out.println(ds);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//Create a movie
	public void createMovie(Movie newMovie) {
		String sql = "insert into movie values (null,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,newMovie.getTitle());
			statement.setString(2, newMovie.getPosterImage());
			statement.setDate(3, newMovie.getReleaseDate());
			statement.executeUpdate();
			connection.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Retrieve All Movies
	public List<Movie> readAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		String sql = "select * from movie";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Movie movie = new Movie();
				movie.setId(results.getInt("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterimage"));
				movie.setReleaseDate(results.getDate("releasedate"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	
	//Retrieve a movie by id
	public Movie readMovie(String movieId)
	{
		Movie movie = new Movie();
		
		String sql = "select * from movie where id = ?";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(movieId);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				movie.setId(results.getInt("id"));
				movie.setTitle(results.getString("title"));
				movie.setPosterImage(results.getString("posterimage"));
				movie.setReleaseDate(results.getDate("releasedate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie;
	}
	
	//Update a movie
	public void updateMovie(String movieId, Movie movie)
	{	
		String sql = "update movie set title=?, posterimage=?, releasedate=? where"
				+ " id=?";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(movieId);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setDate(3, movie.getReleaseDate());
			statement.setInt(4, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete a Movie
	public void deleteMovie(String movieId)
	{
		String sql = "delete from movie where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(movieId);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
