package com.somanyfeeds.somanyfeedsapplication;

import org.springframework.data.repository.CrudRepository;

public interface ArticlesRepository extends CrudRepository<ArticleEntity, Long> {
}
