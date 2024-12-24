package manga.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import manga.entity.Manga;
import manga.entity.Mangaka;

@Data
@NoArgsConstructor
public class MangaMangaka {
		private Long mangakaId;
		private String mangakaFirstName;
		private String mangakaLastName;
		private String mangakaDob;
		private String mangakaCountry;
		private Set<Manga> mangas = new HashSet<>();
		
		public MangaMangaka (Mangaka mangaka) {
			mangakaId = mangaka.getMangakaId();
			mangakaFirstName = mangaka.getMangakaFirstName();
			mangakaLastName = mangaka.getMangakaLastName();
			mangakaDob = mangaka.getMangakaDob();
			mangakaId = mangaka.getMangakaId();
			
			for (Manga manga : mangaka.getMangas()) {
				mangas.add(manga);
		}	
		}
		
}

