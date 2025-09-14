package socnet.com.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType; // Often useful for OneToMany relationships
import jakarta.persistence.FetchType;   // Often useful for OneToMany relationships


@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String title;
	String content;
	LocalDate date=LocalDate.now();

	@Lob
    private byte[] image;

	@Column(name = "is_violent")
	private boolean isViolent = false; // default to false


	@ManyToOne
	User user;
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Comment> comments;

	// Add mappedBy to indicate that the 'post' field in the Reaction entity owns this relationship.
	// Also, consider FetchType.LAZY for performance to avoid loading all reactions unless needed.
	// CascadeType.ALL can be useful if you want reactions to be deleted when a post is deleted.
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	List<Reaction> reactions;

	public Post() {
		super();
	}

	public Post(String title, String content, LocalDate date, byte[] image, User postowner) {
		super();
		this.title = title;
		this.content = content;
		this.date = date;
		this.image = image;
		this.user = postowner;
	}

	// --- Getters and Setters for all fields ---

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isViolent() {
		return isViolent;
	}

	public void setViolent(boolean isViolent) {
		this.isViolent = isViolent;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}