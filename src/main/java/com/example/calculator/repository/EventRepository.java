package com.example.calculator.repository;

import com.example.calculator.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByTimestampGreaterThanEqual(long timestamp);

}
