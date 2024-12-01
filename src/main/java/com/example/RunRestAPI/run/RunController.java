package com.example.RunRestAPI.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findByID(@PathVariable int id) {

        Optional<Run> run = runRepository.findById(id);
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
        runRepository.save(run);
    }


    // PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable int id) throws Exception {
        if (run.id() == id) {
            if (runRepository.existsById(id)){
                runRepository.save(run);
            } else {
                throw new RunNotFoundException();
            }
        } else {
            throw new Exception("requested id to update does not match id passed in body, use post if intended");
        }
    }


    // DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        runRepository.deleteById(id);
    }


    // Find by Location
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/LOC/{requestedLocation}")
    List<Run> findByLocation(@PathVariable String requestedLocation) {
        Location requestedLocationEnum = Location.valueOf(requestedLocation);
        return runRepository.findAllByLocation(requestedLocationEnum);
    }
}
