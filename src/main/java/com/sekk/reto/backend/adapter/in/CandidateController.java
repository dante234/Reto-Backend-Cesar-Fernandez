package com.sekk.reto.backend.adapter.in;

import com.sekk.reto.backend.domain.model.Candidates;
import com.sekk.reto.backend.domain.services.CandisateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandisateService candidateService;


    public CandidateController(CandisateService candisateService) {
        this.candidateService = candisateService;
    }

    // Endpoint para crear un candidato
    @Operation(summary = "Guardar un candidato",
            description = "Create un candidato",
            tags = {"Candidatos"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "ok"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Candidates.class))))

    @PostMapping("/create")
    public ResponseEntity<Candidates> createCandidate(@RequestBody Candidates candidate) {
        try{
        Candidates createdCandidate = candidateService.createCandidate(candidate);
        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Obtener todos los candidato",
            description = "Obtener todos los candidato",
            tags = {"Candidatos"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "ok"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")
            })
    // Endpoint para obtener todos los candidatos
    @GetMapping("/all")
    public ResponseEntity<List<Candidates>> getAllCandidates() {
        try {
            List<Candidates> candidates = candidateService.getAllCandidates();
            return new ResponseEntity<>(candidates, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Obtener candidatos por ID",

            description = "Obtener candidato por ID",
            tags = {"Candidatos"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "ok"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found")
            })
    // Endpoint para obtener un candidato por ID
    @GetMapping("/{id}")
    public ResponseEntity<Candidates> getCandidateById(@PathVariable Long id) {
    Optional<Candidates> candidate = candidateService.getCandidateById(id);

    return candidate.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @Operation(summary = "Eliminar candidatos por ID",
            description = "Eliminar candidato por ID",
            tags = {"Candidatos"},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "no content"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not Found")
            })
    // Endpoint para eliminar un candidato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        try {

            candidateService.deleteCandidate(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
