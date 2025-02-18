package manga.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

//This is the mangaka entity table, where its relationship to the other tables is defined.
@Entity
@Data
public class Mangaka {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mangakaId;
	private String mangakaFirstName;
	private String mangakaLastName;
	private String mangakaDob;
	private String mangakaCountry;
	
	@OneToMany(mappedBy = "mangaka", cascade = CascadeType.PERSIST, orphanRemoval = false)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Manga> mangas = new HashSet<>();
}
