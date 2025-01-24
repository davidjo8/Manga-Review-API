package manga.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import manga.entity.Manga;
import manga.entity.Mangaka;


/* This is the same as the MangaData class but this is specifically a DTO for the Mangaka data.
 * I could have made this same kind of DTO for Genre and the MangaReview classes but it was easier
 * to have them in a DTO with the Manga class.
 */
@Data
@NoArgsConstructor
public class MangakaData {
		private Long mangakaId;
		private String mangakaFirstName;
		private String mangakaLastName;
		private String mangakaDob;
		private String mangakaCountry;
		private Set<Manga> mangas = new HashSet<>();
		
		public MangakaData (Mangaka mangaka) {
			mangakaId = mangaka.getMangakaId();
			mangakaFirstName = mangaka.getMangakaFirstName();
			mangakaLastName = mangaka.getMangakaLastName();
			mangakaDob = mangaka.getMangakaDob();
			mangakaCountry = mangaka.getMangakaCountry();
			
			for (Manga manga : mangaka.getMangas()) {
				mangas.add(manga);
		}	
	}	
}

