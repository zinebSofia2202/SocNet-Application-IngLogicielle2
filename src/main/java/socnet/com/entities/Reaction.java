package socnet.com.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated; // Import this!
import jakarta.persistence.EnumType;   // Import this!

@Entity
public class Reaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	// Use only one field for the reaction type and name it consistently
	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING) // Store enum as its string name in DB
    private ReactionType reactionType; // Renamed from 'type' for consistency with common usage

	LocalDate date = LocalDate.now(); // Default to now if not explicitly set

	@ManyToOne
	User user;

	@ManyToOne
	Post post;

	// JPA requires a public no-argument constructor
	public Reaction() {
		super();
	}

	// You can keep this constructor, but the no-arg one is essential for JPA
	public Reaction(User user, Post post, ReactionType reactionType) {
		super();
		this.user = user;
		this.post = post;
		this.reactionType = reactionType; // Initialize the enum
		this.date = LocalDate.now(); // Set date upon creation, or let DB handle if using timestamps
	}

	// --- Getters and Setters for all fields ---

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	// Getter and Setter for the corrected reactionType field
	public ReactionType getReactionType() {
		return reactionType;
	}

	public void setReactionType(ReactionType reactionType) {
		this.reactionType = reactionType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}