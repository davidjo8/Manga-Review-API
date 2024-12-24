package manga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import manga.controller.model.MangaData;
import manga.controller.model.MangaMangaka;
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
	public MangaMangaka insertMangaka(@RequestBody MangaMangaka mangaMangaka) {
		log.info("Adding Mangaka {}", mangaMangaka);
		return mangaService.saveMangaka(mangaMangaka);
	}
	
	@GetMapping("/mangaka")
	public List<MangaMangaka> retrieveAllMangaka(){
		log.info("Retrieving all Mangaka");
		return mangaService.retrieveAllMangakas();
	}
	
}
