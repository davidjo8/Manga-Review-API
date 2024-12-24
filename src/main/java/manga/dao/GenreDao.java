package manga.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import manga.entity.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {

}
