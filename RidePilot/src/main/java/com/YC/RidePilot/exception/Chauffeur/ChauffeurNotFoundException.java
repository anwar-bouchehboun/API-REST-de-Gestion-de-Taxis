package com.YC.RidePilot.exception.Chauffeur;

import lombok.Getter;

@Getter
public class ChauffeurNotFoundException extends RuntimeException {
    public ChauffeurNotFoundException(String message) {
        super(message);
    }
}