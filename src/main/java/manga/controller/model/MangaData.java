package manga.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import manga.entity.Genre;
import manga.entity.Manga;
import manga.entity.MangaReview;

@Data
@NoArgsConstructor
public class MangaData {
	private Long mangaId;
	private String mangaName;
	private String mangaCountry;
	private String mangaPublishYear;
	private String mangaLanguage;
	private Long mangakaId;
	private Set<GenreData> genres = new HashSet<>();
	private Set<MangaReviewData> mangaReviews = new HashSet<>();
	
	public MangaData (Manga manga) {
		mangaId = manga.getMangaId();
		mangaName = manga.getMangaName();
		mangaCountry = manga.getMangaCountry();
		mangaPublishYear = manga.getMangaPublishYear();
		mangaLanguage = manga.getMangaLanguage();
		if (manga.getMangaka() != null) {
			mangakaId = manga.getMangaka().getMangakaId();
		} else {
			mangakaId = null;
		}
		
		for (Genre genre : manga.getGenres()) {
			genres.add(new GenreData(genre));
		}
		
		for (MangaReview mangaReview : manga.getMangaReviews()) {
			mangaReviews.add(new MangaReviewData(mangaReview));
		}
		
	}
	@Data
	@NoArgsConstructor
	public static class GenreData {
		private Long genreId;
		private String genreType;
		
		public GenreData(Genre genre) {
			genreId = genre.getGenreId();
			genreType = genre.getGenreType();
		}
	}
	@Data
	@NoArgsConstructor
	public static class MangaReviewData {
		private Long mangaReviewId;
		private String reviewAuthor;
		private String reviewRating;
		private String reviewContent;
		private Long mangaId;
		
		public MangaReviewData(MangaReview mangaReview) {
			mangaReviewId = mangaReview.getMangaReviewId();
			reviewAuthor = mangaReview.getReviewAuthor();
			reviewRating = mangaReview.getReviewRating();
			reviewContent = mangaReview.getReviewContent();
			if (mangaReview.getManga() != null) {
				mangaId = mangaReview.getManga().getMangaId();
			} else {
				mangaId = null;
			}
		}
	}
}
