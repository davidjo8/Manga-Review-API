package manga.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import manga.entity.MangaReview;

public interface MangaReviewDao extends JpaRepository<MangaReview, Long> {

}
