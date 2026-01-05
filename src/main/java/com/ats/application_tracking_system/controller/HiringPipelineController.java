package com.ats.application_tracking_system.controller;

import com.ats.application_tracking_system.dto.CandidateStageHistoryDTO;
import com.ats.application_tracking_system.dto.UpdateHiringStageRequestDTO;
import com.ats.application_tracking_system.service.HiringPipelineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruiter/pipeline")
@RequiredArgsConstructor
public class HiringPipelineController {

    private final HiringPipelineService service;

    @PutMapping("/candidates/{id}/stage")
    public ResponseEntity<Void> updateStage(
            @PathVariable Long id,
            @RequestBody @Valid UpdateHiringStageRequestDTO dto,
            Authentication authentication) {

        service.updateStage(id, dto, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/candidates/{id}/history")
    public ResponseEntity<List<CandidateStageHistoryDTO>> getHistory(
            @PathVariable Long id,
            Authentication authentication) {

        return ResponseEntity.ok(
                service.getStageHistory(id, authentication.getName()));
    }
}

