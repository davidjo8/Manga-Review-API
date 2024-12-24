package manga.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manga.controller.model.MangaData;
import manga.controller.model.MangaMangaka;
import manga.dao.GenreDao;
import manga.dao.MangaDao;
import manga.dao.MangaReviewDao;
import manga.dao.MangakaDao;
import manga.entity.Manga;
import manga.entity.Mangaka;

@Service
public class MangaService {

	@Autowired
	private MangaDao mangaDao;
	@Autowired
	private MangaReviewDao mangaReviewDao;
	@Autowired
	private MangakaDao mangakaDao;
	@Autowired
	private GenreDao genreDao;
	
	@Transactional(readOnly = false)
	public MangaData saveManga(MangaData mangaData) {
		Long mangaId = mangaData.getMangaId();
		Manga manga = findOrAddManga(mangaId);
		copyMangaFields(manga, mangaData);
		return new MangaData(mangaDao.save(manga));
	}

	private Manga findOrAddManga(Long mangaId) {
		if(Objects.isNull(mangaId)) {
			return new Manga();
		} else {
			return findMangaById(mangaId);
		}
	}

	private Manga findMangaById(Long mangaId) {
		return mangaDao.findById(mangaId)
				.orElseThrow(() -> new NoSuchElementException(
						"Manga with ID=" + mangaId + " was not found."));
	}

	private void copyMangaFields(Manga manga, MangaData mangaData) {
		manga.setMangaCountry(mangaData.getMangaCountry());
		manga.setMangaId(mangaData.getMangaId());
		manga.setMangaLanguage(mangaData.getMangaLanguage());
		manga.setMangaName(mangaData.getMangaName());
		manga.setMangaPublishYear(mangaData.getMangaPublishYear());		
	}
@Transactional(readOnly = true)
	public MangaData retrieveMangabyId(Long mangaId) {
		return new MangaData(findMangaById(mangaId));
	}

public List<MangaData> retrieveAllMangas() {
	List<Manga> mangas = mangaDao.findAll();
	List<MangaData> result = new LinkedList<>();
	
	for(Manga manga : mangas) {
		MangaData md = new MangaData(manga);
		md.getGenres().clear();
		md.getMangaReviews().clear();
		result.add(md);
	}
	return result;
}

@Transactional (readOnly = false)
public MangaMangaka saveMangaka(MangaMangaka mangaMangaka) {
	Long mangakaId = mangaMangaka.getMangakaId();
	Mangaka mangaka = findOrCreateMangaka(mangakaId);
	copyMangakaFields(mangaka, mangaMangaka);
	return new MangaMangaka(mangakaDao.save(mangaka));
}

private void copyMangakaFields(Mangaka mangaka, MangaMangaka mangaMangaka) {
	mangaka.setMangakaFirstName(mangaMangaka.getMangakaFirstName());
	mangaka.setMangakaLastName(mangaMangaka.getMangakaLastName());
	mangaka.setMangakaCountry(mangaMangaka.getMangakaCountry());
	mangaka.setMangakaId(mangaMangaka.getMangakaId());
	mangaka.setMangakaDob(mangaMangaka.getMangakaDob());
	
}

private Mangaka findOrCreateMangaka(Long mangakaId) {
	if(Objects.isNull(mangakaId)) {
		return new Mangaka();
	}else {
		return findMangakaById(mangakaId);
	}
}

private Mangaka findMangakaById(Long mangakaId) {
	return mangakaDao.findById(mangakaId)
			.orElseThrow(() -> new NoSuchElementException(
					"Mangaka with ID=" + mangakaId + " was not found."));
}

public List<MangaMangaka> retrieveAllMangakas() {
	List<Mangaka> mangakas = mangakaDao.findAll();
	List<MangaMangaka> result = new LinkedList<>();
	
	for (Mangaka mangaka : mangakas) {
		MangaMangaka mgk = new MangaMangaka(mangaka);
		mgk.getMangas().clear();
		result.add(mgk);
	}
	return result;
}
	
}
