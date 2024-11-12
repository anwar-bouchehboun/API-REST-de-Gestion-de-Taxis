package com.YC.RidePilot.entity.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ChauffeurAnalyticsDto {
    private Double tauxOccupation;
    private Map<String, Long> repartitionStatuts;
    private Map<String, Long> repartitionParPlageHoraire;
    private Map<String, Long> repartitionParPlageHorairefin;



}
