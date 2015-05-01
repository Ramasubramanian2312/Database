<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="neu.edu.cs5200.msn.ds.dao.*" import="neu.edu.cs5200.msn.ds.model.*"
    import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Hello World</h1>
	
	<%
	MovieManager mm = new MovieManager();
	
	
	List<Movie> movies = mm.readAllMovies();
	
	Movie movie = mm.readMovie("1");
	
	movie.setTitle("Avatar 3");
	mm.updateMovie("1", movie);
	
	mm.deleteMovie("1");
	%>
	
	
</body>
</html>