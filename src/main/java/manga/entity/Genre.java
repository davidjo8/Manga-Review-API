package manga.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long genreId;
	private String genreType;
	
	@ManyToMany(mappedBy = "genres")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Manga> mangas = new HashSet<>();
}
