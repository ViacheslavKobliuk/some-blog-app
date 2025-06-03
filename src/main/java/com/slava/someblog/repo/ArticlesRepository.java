package com.slava.someblog.repo;

import com.slava.someblog.models.Articles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticlesRepository extends CrudRepository<Articles, Long> {
}
