package com.illarli.middleware.controllers;

import com.illarli.middleware.models.DocumentType;
import com.illarli.middleware.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/document-type")
public class DocumentTypeController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping("")
    public ResponseEntity<List<DocumentType>> index() {
        return new ResponseEntity<>(documentTypeService.getAll(), HttpStatus.OK);
    }
}
