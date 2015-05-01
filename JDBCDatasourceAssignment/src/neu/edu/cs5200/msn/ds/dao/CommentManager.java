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

import neu.edu.cs5200.msn.ds.model.Comment;

public class CommentManager {
	DataSource ds;

	public CommentManager() {
		try {

			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/JDBCDatasourceAssignmentDB");
			System.out.println(ds);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//Create a comment
	public void createComment(Comment newComment) {
		String sql = "insert into comment values (null,?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,newComment.getComment());
			statement.setDate(2, newComment.getDate());
			statement.setString(3, newComment.getUserid());
			statement.setInt(3, newComment.getMovieid());
			statement.executeUpdate();
			connection.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Retrieve All Comments
	public List<Comment> readAllComments() {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserid(results.getString("userid"));
				comment.setMovieid(results.getInt("movieid"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	//Retrieve all comments for an username
	public List<Comment> readAllCommentsForUsername(String username) {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where userid=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,username);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserid(results.getString("userid"));
				comment.setMovieid(results.getInt("movieid"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	//Retrieve all comments for a movie
	public List<Comment> readAllCommentsForMovie(String movieId) {
		List<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where movieid=?";
				
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,movieId);
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				Comment comment = new Comment();
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserid(results.getString("userid"));
				comment.setMovieid(results.getInt("movieid"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	
	//Retrieve a comment by commentId
	public Comment readCommentForId(String commentId)
	{
		Comment comment = new Comment();
		
		String sql = "select * from comment where id = ?";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(commentId);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				comment.setId(results.getInt("id"));
				comment.setComment(results.getString("comment"));
				comment.setDate(results.getDate("date"));
				comment.setUserid(results.getString("userid"));
				comment.setMovieid(results.getInt("movieid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}
	
	//Update a Comment
	public void updateMovie(String commentId, String comment)
	{	
		String sql = "update comment set comment=? where"
				+ " id=?";
		Connection connection;
		try {
			connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(commentId);
			statement.setString(1, comment);
			statement.setInt(2, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Delete a Comment
	public void deleteComment(String commentId)
	{
		String sql = "delete from comment where id = ?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			int id = Integer.parseInt(commentId);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
