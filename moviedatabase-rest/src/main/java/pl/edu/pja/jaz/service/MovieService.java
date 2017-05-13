package pl.edu.pja.jaz.service;

import java.util.Set;

import pl.edu.pja.jaz.dao.Dao;
import pl.edu.pja.jaz.domain.Comment;
import pl.edu.pja.jaz.domain.Movie;

public class MovieService extends AbstractService<Movie> {
	
	public MovieService(Dao<Movie> dao) {
		super(dao);
	}
	
	public Movie rate(int id, int rating) {
		Movie m = get(id);
		if (m == null) {
			return null;
		}
		m.getRates().add(rating);
		return m;
	}
	
	public Double rating(int id) {
		Movie m = get(id);
		if (m == null) {
			return null;
		}
		return m.getRating();
	}
	
	public Set<Comment> listComments(int id) {
		Movie m = get(id);
		if (m == null) {
			return null;
		}
		return m.getComments();
	}
	
	public Comment createComment(int id, Comment comment) {
		Movie m = get(id);
		if (m == null) {
			return null;
		}
		Comment c = newComment(m.getComments().size());
		copyComment(comment, c);
		m.getComments().add(c);
		return c;
	}
	
	public Comment getComment(int id, int commentId) {
		for (Comment comment : listComments(id)) {
			if (comment.getId() == commentId) {
				return comment;
			}
		}
		return null;
	}
	
	public Comment updateComment(int id, int commentId, Comment comment) {
		Comment c = getComment(id, commentId);
		if (c == null) {
			return null;
		}
		
		copyComment(comment, c);
		return c;
	}
	
	public Comment deleteComment(int id, int commentId) {
		Set<Comment> comments = listComments(id);
		Comment c = getComment(id, commentId);
		if (c == null || comments == null) {
			return null;
		}
		
		comments.remove(c);
		return c;
	}
	
	protected Comment newComment(int id) {
		return new Comment(id);
	}
	
	protected void copyComment(Comment source, Comment destination) {
		destination.setText(source.getText());
	}
	
}
