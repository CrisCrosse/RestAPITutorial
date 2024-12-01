package com.example.RunRestAPI.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJSONDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RunJSONDataLoader.class);
    private final JdbcClientRunRepository jdbcClientRunRepository;
    private final ObjectMapper objectMapper;

    public RunJSONDataLoader(JdbcClientRunRepository jdbcClientRunRepository, ObjectMapper objectMapper) {
        this.jdbcClientRunRepository = jdbcClientRunRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(0 == jdbcClientRunRepository.count()) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data and saving to postgres instance.", allRuns.runs().size());
                jdbcClientRunRepository.saveAll(allRuns.runs());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data.", e);
            }
        } else {
            log.info("Not loading Runs from JSON data because the database contains {} rows of data",
                    jdbcClientRunRepository.count()
            );
        }

    }
}
