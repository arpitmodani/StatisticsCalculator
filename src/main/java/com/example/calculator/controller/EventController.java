package com.example.calculator.controller;

import com.example.calculator.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    StatisticService statistic;

    @PostMapping("{num}")
    @ResponseStatus(HttpStatus.OK)
    public void createEvent(@PathVariable int num) {
        statistic.event(num);
    }


    @GetMapping("/mean")
    @ResponseStatus(HttpStatus.OK)
    public float getMean() {
        return statistic.mean();
    }

    @GetMapping("/mean/{minutes}")
    @ResponseStatus(HttpStatus.OK)
    public float getMeanLastNMinutes(@PathVariable int minutes) {
        return statistic.mean(minutes);
    }


    @GetMapping("/variance")
    @ResponseStatus(HttpStatus.OK)
    public float getVariance() {
        return statistic.variance();
    }


    @GetMapping("/max")
    @ResponseStatus(HttpStatus.OK)
    public int getMax() {
        return statistic.maximum();
    }

    @GetMapping("/min")
    @ResponseStatus(HttpStatus.OK)
    public int getMin() {
        return statistic.minimum();
    }

}

