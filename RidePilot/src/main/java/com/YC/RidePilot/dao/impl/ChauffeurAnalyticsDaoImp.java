package com.YC.RidePilot.dao.impl;

import com.YC.RidePilot.dao.ChauffeurAnalyticsDao;
import com.YC.RidePilot.entity.Chauffeur;
import com.YC.RidePilot.enums.StatutChauffeur;
import com.YC.RidePilot.repository.ChauffeurRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ChauffeurAnalyticsDaoImp implements ChauffeurAnalyticsDao {

    private final ChauffeurRepo chauffeurRepository;

    @Override
    public double calculateTauxOccupation() {
        long totalChauffeurs = chauffeurRepository.count();
        long chauffeursEnCourse = chauffeurRepository.countByStatut(StatutChauffeur.EN_COURSE);
        return totalChauffeurs > 0 ? (double) (chauffeursEnCourse * 100) / totalChauffeurs : 0;
    }

    @Override
    public Map<StatutChauffeur, Long> getStatutDistribution() {
        return chauffeurRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Chauffeur::getStatut,
                        Collectors.counting()
                ));
    }

    @Override
    public Map<LocalDateTime, Long> getDisponibiliteDistribution() {
        return chauffeurRepository.findAll().stream()
                .filter(c -> c.getDisponibiliteDebut() != null)
                .collect(Collectors.groupingBy(
                        Chauffeur::getDisponibiliteDebut,
                        Collectors.counting()
                ));
    }


    @Override
    public Map<LocalDateTime, Long> getDisponibiliteDistributionfin() {
        return chauffeurRepository.findAll().stream()
                .filter(c -> c.getDisponibiliteFin() != null )
                .collect(Collectors.groupingBy(
                        Chauffeur::getDisponibiliteFin,
                        Collectors.counting()
                ));
    }


} 