package com.learnkafka.demokafkaConsumer.jpa;

import com.learnkafka.demokafkaConsumer.entity.LibraryEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryEventsRepository extends CrudRepository<LibraryEvent, Integer> {
}
