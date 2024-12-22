package manga.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Manga {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mangaId;
	private String mangaName;
	private String mangaCountry;
	private String mangaPublishYear;
	private String mangaLanguage;

	@ManyToOne
	@JoinColumn(name = "mangaka_id", nullable = false) // Foreign key
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Mangaka> mangakas = new HashSet<>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "manga_genre", joinColumns = @JoinColumn(name = "manga_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Genre> genres = new HashSet<>();
	
	@OneToMany(mappedBy = "manga", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<MangaReview> mangaReviews = new HashSet<>();
	
}
