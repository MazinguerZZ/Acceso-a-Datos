import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Email {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String email;
	
	@ManyToOne
	Persona persona;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Email [id=" + id + ", email=" + email + "]";
	}
	
	
	
	
}
