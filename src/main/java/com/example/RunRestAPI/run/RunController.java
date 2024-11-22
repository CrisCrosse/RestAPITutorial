package com.example.RunRestAPI.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    ArrayList<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findByID(@PathVariable int id) {

        Optional<Run> run = runRepository.findByID(id);
        if (run.isEmpty()) {
            throw new RunNotFoundException();
        } else {
            return run.get();
        }
    }

    // POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }


    // PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable int id) {
        runRepository.update(run, id);
    }


    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        runRepository.delete(id);
    }

}
