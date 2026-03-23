package br.com.quickreader.productapi.controller;

import br.com.quickreader.productapi.dto.CepResponse;
import br.com.quickreader.productapi.service.CepService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cep")
@Validated
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepResponse> buscarCep(
            @PathVariable @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos numéricos") String cep) {
        return ResponseEntity.ok(cepService.buscarCep(cep));
    }
}
