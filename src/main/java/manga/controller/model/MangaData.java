package manga.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import manga.entity.Genre;
import manga.entity.Manga;
import manga.entity.MangaReview;
import manga.entity.Mangaka;

@Data
@NoArgsConstructor
public class MangaData {
	private Long mangaId;
	private String mangaName;
	private String mangaCountry;
	private String mangaPublishYear;
	private String mangaLanguage;
	private Mangaka mangaMangaka;
	private Set<MangaGenre> genres = new HashSet<>();
	private Set<MangaMangaReview> mangaReviews = new HashSet<>();
	
	public MangaData (Manga manga) {
		mangaId = manga.getMangaId();
		mangaName = manga.getMangaName();
		mangaCountry = manga.getMangaCountry();
		mangaPublishYear = manga.getMangaPublishYear();
		mangaLanguage = manga.getMangaLanguage();
		mangaMangaka = manga.getMangaka();
		
		for (Genre genre : manga.getGenres()) {
			genres.add(new MangaGenre(genre));
		}
		
		for (MangaReview mangaReview : manga.getMangaReviews()) {
			mangaReviews.add(new MangaMangaReview(mangaReview));
		}
		
	}
	@Data
	@NoArgsConstructor
	public static class MangaGenre {
		private Long genreId;
		private String genreType;
		
		public MangaGenre(Genre genre) {
			genreId = genre.getGenreId();
			genreType = genre.getGenreType();
		}
	}
	@Data
	@NoArgsConstructor
	public static class MangaMangaReview {
		private Long mangaReviewId;
		private String reviewAuthor;
		private String reviewRating;
		private String reviewContent;
		
		public MangaMangaReview(MangaReview mangaReview) {
			mangaReviewId = mangaReview.getMangaReviewId();
			reviewAuthor = mangaReview.getReviewAuthor();
			reviewRating = mangaReview.getReviewRating();
			reviewContent = mangaReview.getReviewContent();
		}
	}

	}
