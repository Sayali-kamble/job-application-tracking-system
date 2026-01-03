package com.ats.application_tracking_system.specification;

import com.ats.application_tracking_system.dto.ApplicationSearchRequest;
import com.ats.application_tracking_system.model.CandidateApplication;
import com.ats.application_tracking_system.model.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
//import java.util.function.Predicate;

public class CandidateApplicationSpecification {

    public static Specification<CandidateApplication> filter(
            User candidate,
            ApplicationSearchRequest request) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("candidate"), candidate));

            if (request.getCompanyName() != null) {
                predicates.add(
                        cb.like(cb.lower(root.get("companyName")),
                                "%" + request.getCompanyName().toLowerCase() + "%")
                );
            }

            if (request.getJobRole() != null) {
                predicates.add(
                        cb.like(cb.lower(root.get("jobRole")),
                                "%" + request.getJobRole().toLowerCase() + "%")
                );
            }

            if (request.getPlatform() != null) {
                predicates.add(cb.equal(root.get("platform"), request.getPlatform()));
            }

            if (request.getStatus() != null) {
                predicates.add(cb.equal(root.get("status"), request.getStatus()));
            }

            if (request.getStartDate() != null && request.getEndDate() != null) {
                predicates.add(
                        cb.between(root.get("appliedDate"),
                                request.getStartDate(),
                                request.getEndDate())
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

