package com.YC.RidePilot.controllers;

import com.YC.RidePilot.entity.dto.ChauffeurDto;
import com.YC.RidePilot.entity.dto.ChauffeurAnalyticsDto;
import com.YC.RidePilot.exception.Chauffeur.ClassSucesseExption;
import com.YC.RidePilot.exception.Chauffeur.ValidationException;
import com.YC.RidePilot.services.InterfacesServices.ChauffeurInterfaces;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/chauffeurs")
@RequiredArgsConstructor
public class ChauffeurController {

    private final ChauffeurInterfaces chauffeurInterfaces;

    @PostMapping
    public ResponseEntity<ChauffeurDto> create(@Valid @RequestBody ChauffeurDto chauffeurDto) {
        log.info("Création d'un nouveau chauffeur");
        if (chauffeurDto == null) {
            throw new ValidationException("Les données du chauffeur ne peuvent pas être nulles");
        }
        return ResponseEntity.ok(chauffeurInterfaces.create(chauffeurDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChauffeurDto> update(@PathVariable Long id, @Valid @RequestBody ChauffeurDto chauffeurDto) {
        log.info("Mise à jour du chauffeur avec l'ID: {}", id);
        if (id == null) {
            throw new ValidationException("L'ID du chauffeur ne peut pas être nul");
        }
        if (chauffeurDto == null) {
            throw new ValidationException("Les données du chauffeur ne peuvent pas être nulles");
        }
        chauffeurDto.setId(id);
        return ResponseEntity.ok(chauffeurInterfaces.update(chauffeurDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Suppression du chauffeur avec l'ID: {}", id);
        if (id == null) {
            throw new ValidationException("L'ID du chauffeur ne peut pas être nul");
        }
        chauffeurInterfaces.delete(id);
        throw new ClassSucesseExption("Classe supprimée avec succès : " + id);
    }

    @GetMapping
    public ResponseEntity<List<ChauffeurDto>> getAll() {
        log.info("Récupération de tous les chauffeurs");
        return ResponseEntity.ok(chauffeurInterfaces.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChauffeurDto> getById(@PathVariable Long id) {
        log.info("Récupération du chauffeur avec l'ID: {}", id);
        return chauffeurInterfaces.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/{nom}")
    public ResponseEntity<List<ChauffeurDto>> searchByNom(@PathVariable  String nom) {
        log.info("Recherche des chauffeurs par nom: {}", nom);
        return ResponseEntity.ok(chauffeurInterfaces.findByNom(nom));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countChauffeurs() {
        log.info("Comptage du nombre total de chauffeurs");
        return ResponseEntity.ok(chauffeurInterfaces.countChauffeurs());
    }

    @GetMapping("/analytics")
    public ResponseEntity<ChauffeurAnalyticsDto> getAnalytics() {
        log.info("Récupération des analytics des chauffeurs");
        return ResponseEntity.ok(chauffeurInterfaces.getAnalytics());
    }
}
