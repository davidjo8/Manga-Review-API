package manga.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import manga.entity.Mangaka;

public interface MangakaDao extends JpaRepository<Mangaka, Long> {

}
