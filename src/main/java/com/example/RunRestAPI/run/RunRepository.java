package com.example.RunRestAPI.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class RunRepository {
    private final ArrayList<Run> runs = new ArrayList<>();

    ArrayList<Run> findAll() {
        return runs;
    }

    Optional<Run> findByID(Integer id) {
        return runs.stream()
            .filter(run -> run.id().equals(id))
            .findFirst();
    }

    void create(Run run) {
        runs.add(run);
    }

    void update(Run UpdatedRun, Integer id) {
        Optional<Run> findRun = findByID(id);
        // replaces existing run in Runs with updated run, errors if existing run not found
        findRun.ifPresent(existingRun -> runs.set(runs.indexOf(existingRun), UpdatedRun));
    }

    void delete(Integer id) {
        Optional<Run> existingRun = findByID(id);

        existingRun.ifPresent(runs::remove);
    }

    Boolean is_valid_run(Run run) {
        return run.startedOn().isBefore(run.completedOn());
    }

    @PostConstruct
    private void init() {
        runs.add(
            new Run(
                1,
                "Morning Run",
                LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(30),
                5,
                Location.OUTDOOR
            )
        );
        runs.add(
            new Run(
                2,
                "Evening Run",
                LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(200),
                10,
                Location.OUTDOOR
            )
        );
    }
}
