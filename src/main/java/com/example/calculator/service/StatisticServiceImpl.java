package com.example.calculator.service;

import com.example.calculator.entity.Event;
import com.example.calculator.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private EventRepository eventRepository;
    
    AtomicInteger atomicMax = new AtomicInteger(Integer.MIN_VALUE);
    AtomicInteger atomicMin = new AtomicInteger(Integer.MAX_VALUE);


    @Override
    public void event(int value) {
        Event event = new Event(value, System.currentTimeMillis());

        atomicMax.updateAndGet(val -> Math.max(val, value));
        atomicMin.updateAndGet(val -> Math.min(val, value));
        eventRepository.save(event);
    }

    @Override
    public float mean() {
        List<Event> events = eventRepository.findAll();
        if (events.size() > 0) {
            long s = events.size();
            long sum = (events.stream().map(Event::getNumber).mapToInt(Integer::intValue).sum());
            return sum / (s * 1.0f);
        }
        return 0;
    }

    @Override
    public float mean(int lastNMinutes) {
        long current = System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(lastNMinutes);
        List<Event> events = eventRepository.findAllByTimestampGreaterThanEqual(current);
        if (events.size() > 0) {
            long s = events.size();
            long sum = (events.stream().map(Event::getNumber).mapToInt(Integer::intValue).sum());
            return sum / (s * 1.0f);
        }
        return 0;
    }

    @Override
    public float variance() {
        List<Event> events = eventRepository.findAll();
        if (events.size() > 0) {
            long s = events.size();
            long sum = (events.stream().map(Event::getNumber).mapToInt(Integer::intValue).sum());
            float mean = sum / (s * 1.0f);
            float sqDiff = 0;
            for (Event e : events) {
                sqDiff += (e.getNumber() - mean) *
                        (e.getNumber() - mean);
            }
            return (float) sqDiff / events.size();


        }
        return 0;
    }

    @Override
    public int minimum() {
        return atomicMin.get();
    }

    @Override
    public int maximum() {
        return atomicMax.get();
    }
}
