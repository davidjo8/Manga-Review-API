package manga.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

//This is the manga review entity table, where its relationship to the other tables is defined.
@Entity
@Data
public class MangaReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mangaReviewId;
	private String reviewAuthor;
	private String reviewRating;
	private String reviewContent;
	
	@ManyToOne
	@JoinColumn(name = "manga_id", nullable = false) // Foreign key
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Manga manga;
}
