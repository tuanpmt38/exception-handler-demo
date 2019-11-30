package com.example.demo.repository;

import com.example.demo.dto.NewAddDto;
import com.example.demo.entity.News;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends JpaRepository<News, Long> {

  News save (NewAddDto newAddDto);

  Optional<News> findByTitle(String imageUrl);

  Optional<News> findByImageUrl(String imageUrl);

  Optional<News> findByContent(String content);
}
