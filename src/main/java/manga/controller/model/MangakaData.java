package manga.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import manga.entity.Manga;
import manga.entity.Mangaka;

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

