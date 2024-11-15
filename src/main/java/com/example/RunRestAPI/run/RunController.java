package com.example.RunRestAPI.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found");
        } else {
            return run.get();
        }
    }

    // POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody Run run) {
        runRepository.create(run);
    }


    // PUT
    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable int id) {
        runRepository.update(run, id);
    }


    // DELETE
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        runRepository.delete(id);
    }

}
