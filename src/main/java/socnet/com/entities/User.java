package socnet.com.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String nom;
	@Column(name = "login",unique = true,length = 255)
	String username;
	String password;
	LocalDate date=LocalDate.now();
	Role role;
	@OneToMany(mappedBy = "urecieve")
	List<Invitation> invsend;
	@OneToMany(mappedBy = "usend")
	List<Invitation> invrec;
	
	@Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;  // Field to store image data

	
	
	@OneToMany(mappedBy = "user")
	List<Post> posts;

	@OneToMany(mappedBy = "user")
	List<Reaction> reactions;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String nom, String username, String password, LocalDate date) {
		super();
		this.nom = nom;
		this.username = username;
		this.password = password;
		this.date = date;
	}
	public List<Invitation> getInvsend() {
		return invsend;
	}
	public void setInvsend(List<Invitation> invsend) {
		this.invsend = invsend;
	}
	public List<Invitation> getInvrec() {
		return invrec;
	}
	public void setInvrec(List<Invitation> invrec) {
		this.invrec = invrec;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
}
