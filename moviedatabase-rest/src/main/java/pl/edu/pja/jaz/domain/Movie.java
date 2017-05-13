package pl.edu.pja.jaz.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Movie extends AbstractEntity {
	
	private String title;
	private Set<Actor> actors = new HashSet<>();
	private Set<Comment> comments = new HashSet<>();
	private List<Integer> rates = new ArrayList<>();
	
	public Movie() {
		super(-1);
	}
	
	public Movie(int id) {
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public List<Integer> getRates() {
		return rates;
	}

	public void setRates(List<Integer> rates) {
		this.rates = rates;
	}
	
	public double getRating() {
		double sum = 0;
		for (Integer rate : rates) {
			sum += rate;
		}
		return (rates.size() > 0 ? sum/rates.size() : 0);
	}
	
	
}
