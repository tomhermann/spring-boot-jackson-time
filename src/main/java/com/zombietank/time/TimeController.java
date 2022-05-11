package com.zombietank.time;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("/time")
public class TimeController {
    private final Clock clock;

    public TimeController(Clock clock) {
        this.clock = clock;
    }

    @GetMapping("/current")
    public TimeResponse current() {
        return new TimeResponse(OffsetDateTime.now(clock));
    }
}
