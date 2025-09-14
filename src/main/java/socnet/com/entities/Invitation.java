package socnet.com.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Invitation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	LocalDate date=LocalDate.now();
	boolean state=false;
	String msg="Please accpet my Invitation!";
	
	@ManyToOne()
	User usend;
	@ManyToOne()
	User urecieve;
	
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
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public User getUsend() {
		return usend;
	}
	public void setUsend(User usend) {
		this.usend = usend;
	}
	public User getUrecieve() {
		return urecieve;
	}
	public void setUrecieve(User urecieve) {
		this.urecieve = urecieve;
	}
	
}
