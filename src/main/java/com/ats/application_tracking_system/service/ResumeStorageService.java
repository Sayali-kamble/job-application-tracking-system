package com.ats.application_tracking_system.service;

import com.ats.application_tracking_system.exception.FileStorageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class ResumeStorageService {

    private static final String RESUME_DIR = "uploads/resumes/";

    public String store(MultipartFile file) {

        try {
            Files.createDirectories(Paths.get(RESUME_DIR));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(RESUME_DIR + fileName);

            Files.write(path, file.getBytes());

            return path.toString();

        } catch (IOException e) {
            throw new FileStorageException("Resume upload failed");
        }
    }
}

