package com.somanyfeeds.feeddataaccess;

import org.springframework.data.repository.CrudRepository;

interface FeedCrudRepository extends CrudRepository<FeedCrudEntity, Long> {
}
