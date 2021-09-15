package com.wheeler.gcp.controller;

import com.wheeler.core.dao.model.Certification;
import com.wheeler.core.service.CertificationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/certification")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(final CertificationService certificationService){
        this.certificationService = certificationService;
    }

    @PostMapping(value = "/load")
    public void load(@RequestBody final String json){
        certificationService.certificationLoad().apply(json);
    }

    @GetMapping(value = "/retrieve")
    @ResponseBody
    public List<Certification> retrieve(){
        return certificationService.certificationRetrieve().apply(Optional.empty());
    }
}