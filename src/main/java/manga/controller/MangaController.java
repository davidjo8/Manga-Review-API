package manga.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import manga.controller.model.MangaData;
import manga.controller.model.MangaData.GenreData;
import manga.controller.model.MangaData.MangaReviewData;
import manga.controller.model.MangakaData;
import manga.service.MangaService;

@RestController
@RequestMapping("/manga")
@Slf4j
public class MangaController {
	@Autowired
	private MangaService mangaService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public MangaData insertManga(@RequestBody MangaData mangaData) {
		log.info("Adding Manga {}", mangaData);
		return mangaService.saveManga(mangaData);
	}
	
	@GetMapping
	public List<MangaData> retrieveAllManga(){
		log.info("Retrieving all manga");
		return mangaService.retrieveAllMangas();
	}
	
	@GetMapping("/{mangaId}")
	public MangaData retrieveMangabyId(@PathVariable Long mangaId) {
		log.info("Retrieving manga with ID={}", mangaId);
		return mangaService.retrieveMangabyId(mangaId);
	}
	
	@PostMapping("/mangaka")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MangakaData insertMangaka(@RequestBody MangakaData mangakaData) {
		log.info("Adding Mangaka {}", mangakaData);
		return mangaService.saveMangaka(mangakaData);
	}
	
	@GetMapping("/mangaka")
	public List<MangakaData> retrieveAllMangaka(){
		log.info("Retrieving all Mangaka");
		return mangaService.retrieveAllMangakas();
	}
	
	@PostMapping("/{mangaId}/mangareview")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MangaReviewData addReviewToManga(@PathVariable Long mangaId, @RequestBody MangaReviewData mangaReviewData) {
		log.info("Adding Review {} to manga with ID={}", mangaReviewData, mangaId);
		return mangaService.saveMangaReview(mangaId, mangaReviewData);
	}
	
	@GetMapping("/mangareview")
	public List<MangaReviewData> retrieveAllMangaReview(){
		log.info("Retrieveing all Manga Review");
		return mangaService.retrieveAllMangaReviews();
	}
	
	@PutMapping("/{mangaId}/mangareview/{mangaReviewId}")
	public MangaReviewData updateMangaReview(@PathVariable Long mangaReviewId, @PathVariable Long mangaId,
			@RequestBody MangaReviewData mangaReviewData) {
		mangaReviewData.setMangaReviewId(mangaReviewId);
		log.info("Updating manga review {}", mangaReviewData);
		return mangaService.saveMangaReview(mangaId, mangaReviewData);
	}
	
	@DeleteMapping("/mangareview/{mangaReviewId}")
	public Map<String, String> deleteMangaReviewById(@PathVariable Long mangaReviewId){
		log.info("Deleting manga review with ID={}", mangaReviewId);
		mangaService.deleteMangaReviewById(mangaReviewId);
		return Map.of("message", "Manga review with ID=" + mangaReviewId + " deleted.");
	}
	
	@PostMapping("/genre")
	@ResponseStatus(code = HttpStatus.CREATED)
	public GenreData addGenre(@RequestBody GenreData genreData) {
		log.info("Adding Genre {}", genreData);
		return mangaService.saveGenre(genreData);
	}
	
	@GetMapping("/genre")
	public List<GenreData> retrieveAllGenre(){
		log.info("Retrieving all Genre Review");
		return mangaService.retrieveAllGenres();
	}
	
	@PostMapping("/{mangaId}/genre/{genreId}")
	public Map<String, String> addGenreToManga(@PathVariable Long mangaId, @PathVariable Long genreId) {
		mangaService.addGenreToManga(mangaId, genreId);
		return Map.of("message", "Adding genreId " + genreId + " to mangaId " + mangaId);
	}
	
	@GetMapping("/{mangaId}/genres")
	public Set<MangaData.GenreData> getGenresForManga(@PathVariable Long mangaId){
		return mangaService.retrieveGenresForManga(mangaId);
	}
	
	
}
