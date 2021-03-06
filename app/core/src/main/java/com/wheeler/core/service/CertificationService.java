package com.wheeler.core.service;

import com.wheeler.core.dao.model.Certification;
import com.wheeler.core.dao.repository.ModelRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CertificationService {

    private final ModelRepository<Certification> certificationRepository;

    public CertificationService(final ModelRepository<Certification> certificationRepository) {
        this.certificationRepository = certificationRepository;
    }

    @Bean
    public Function<String, Optional<?>> certificationLoad() {
        return (json) -> {
            certificationRepository.load(json);
            return Optional.empty();
        };
    }

    @Bean
    public Function<Optional<?>, List<Certification>> certificationRetrieve() {
        return (o) -> certificationRepository.findAll();
    }
}
