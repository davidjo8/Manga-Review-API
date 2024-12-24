package manga.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import manga.entity.Manga;

public interface MangaDao extends JpaRepository<Manga, Long> {

}
