package manga.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import manga.controller.model.MangaData;
import manga.controller.model.MangaData.GenreData;
import manga.controller.model.MangaData.MangaReviewData;
import manga.controller.model.MangakaData;
import manga.dao.GenreDao;
import manga.dao.MangaDao;
import manga.dao.MangaReviewDao;
import manga.dao.MangakaDao;
import manga.entity.Genre;
import manga.entity.Manga;
import manga.entity.MangaReview;
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
		
	if (mangaData.getMangakaId() !=null) {
		Mangaka mangaka = mangakaDao.findById(mangaData.getMangakaId())
			.orElseThrow(() -> new NoSuchElementException(
					"Mangaka with ID=" + mangaData.getMangakaId() + " was not found."));
		manga.setMangaka(mangaka); //set Mangaka in the Manga object
		}else {
			throw new IllegalArgumentException("There needs to be a Mangaka ID.");
		}
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
public MangakaData saveMangaka(MangakaData mangakaData) {
	Long mangakaId = mangakaData.getMangakaId();
	Mangaka mangaka = findOrCreateMangaka(mangakaId);
	copyMangakaFields(mangaka, mangakaData);
	return new MangakaData(mangakaDao.save(mangaka));
}

private void copyMangakaFields(Mangaka mangaka, MangakaData mangakaData) {
	mangaka.setMangakaFirstName(mangakaData.getMangakaFirstName());
	mangaka.setMangakaLastName(mangakaData.getMangakaLastName());
	mangaka.setMangakaCountry(mangakaData.getMangakaCountry());
	mangaka.setMangakaId(mangakaData.getMangakaId());
	mangaka.setMangakaDob(mangakaData.getMangakaDob());
	
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

public List<MangakaData> retrieveAllMangakas() {
	List<Mangaka> mangakas = mangakaDao.findAll();
	List<MangakaData> result = new LinkedList<>();
	
	for (Mangaka mangaka : mangakas) {
		MangakaData mgk = new MangakaData(mangaka);
		mgk.getMangas().clear();
		result.add(mgk);
	}
	return result;
}

@Transactional(readOnly = false)
public MangaReviewData saveMangaReview(MangaReviewData mangaReviewData) {
    Long mangaReviewId = mangaReviewData.getMangaReviewId();
    MangaReview mangaReview = findOrCreateMangaReview(mangaReviewId);

    copyMangaReviewFields(mangaReview, mangaReviewData);
    if (mangaReviewData.getMangaId() != null) {
        Manga manga = mangaDao.findById(mangaReviewData.getMangaId())
                .orElseThrow(() -> new NoSuchElementException(
                		"Manga with ID=" + mangaReviewData.getMangaId() + " was not found."));

        mangaReview.setManga(manga);
        manga.getMangaReviews().add(mangaReview);
    } else {
        throw new IllegalArgumentException("Manga ID is required to associate with the MangaReview.");
    }
    MangaReview dbMangaReview = mangaReviewDao.save(mangaReview);
    return new MangaReviewData(dbMangaReview);
}

private void copyMangaReviewFields(MangaReview mangaReview, MangaReviewData mangaReviewData) {
	mangaReview.setMangaReviewId(mangaReviewData.getMangaReviewId());
	mangaReview.setReviewAuthor(mangaReviewData.getReviewAuthor());
	mangaReview.setReviewContent(mangaReviewData.getReviewContent());
	mangaReview.setReviewRating(mangaReviewData.getReviewRating());
	if (mangaReviewData.getMangaId() != null) {
        Manga manga = mangaDao.findById(mangaReviewData.getMangaId())
            .orElseThrow(() -> new NoSuchElementException("Manga with ID=" + mangaReviewData.getMangaId() + " was not found."));
        mangaReview.setManga(manga);
    }
}

private MangaReview findOrCreateMangaReview(Long mangaReviewId) {
	if(Objects.isNull(mangaReviewId)) {
		return new MangaReview();
	}
	return findMangaReviewById(mangaReviewId);
}

private MangaReview findMangaReviewById(Long mangaReviewId) {
	return mangaReviewDao.findById(mangaReviewId)
			.orElseThrow(() -> new NoSuchElementException(
			"Mangaka with ID=" + mangaReviewId + " was not found."));
}

public List<MangaReviewData> retrieveAllMangaReviews() {
	List<MangaReview> mangaReviews = mangaReviewDao.findAll();
	List<MangaReviewData> result = new LinkedList<>();

	for(MangaReview mangaReview : mangaReviews) {
		MangaReviewData mrd = new MangaReviewData(mangaReview);
		result.add(mrd);
	}
	return result;
}

@Transactional(readOnly = false)
public GenreData saveGenre(GenreData genreData) {
	Long genreId = genreData.getGenreId();
	Genre genre = findOrCreateGenre(genreId);
	copyGenreFields(genre, genreData);
	return new GenreData(genreDao.save(genre));
}

private void copyGenreFields(Genre genre, GenreData genreData) {
	genre.setGenreId(genreData.getGenreId());
	genre.setGenreType(genreData.getGenreType());

}

private Genre findOrCreateGenre(Long genreId) {
	if(Objects.isNull(genreId)) {
		return new Genre();
	}else {
		return findGenreById(genreId);
	}
}

private Genre findGenreById(Long genreId) {
	return genreDao.findById(genreId)
			.orElseThrow(() -> new NoSuchElementException(
					"Mangaka with ID=" + genreId + " was not found."));
}

public List<GenreData> retrieveAllGenres() {
	List<Genre> genres = genreDao.findAll();
	List<GenreData> result = new LinkedList<>();
	
	for (Genre genre : genres) {
		GenreData gd = new GenreData(genre);
		result.add(gd);
	}
	return result;
}
@Transactional(readOnly = false)
public void deleteMangaReviewById(Long mangaReviewId) {
	MangaReview mangaReview = findMangaReviewById(mangaReviewId);
	mangaReviewDao.delete(mangaReview);
	
}

public MangaData addGenreToManga(Long mangaId, Long genreId) {
	Manga manga = mangaDao.findById(mangaId)
			.orElseThrow(() -> new NoSuchElementException(
					"Manga with ID=" + mangaId + " was not found."));
	Genre genre = genreDao.findById(genreId)
			.orElseThrow(() -> new NoSuchElementException(
					"Manga with ID=" + genreId + " was not found."));;
	
	manga.getGenres().add(genre);
	Manga dbmanga = mangaDao.save(manga);
	return new MangaData(dbmanga);
	
}

public Set<GenreData> retrieveGenresForManga(Long mangaId) {
	Manga manga = findMangaById(mangaId);
	Set<Genre> genres = manga.getGenres();
	Set<GenreData> genreDataSet = new HashSet<>();
	for (Genre genre : genres) {
		genreDataSet.add(new GenreData(genre));
	}
	return genreDataSet;
}
}
