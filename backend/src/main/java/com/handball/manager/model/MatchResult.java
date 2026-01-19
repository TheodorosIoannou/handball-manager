package com.handball.manager.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MatchResult {
    private Long id;
    
    @NotNull(message = "Home score is required")
    @Min(value = 0, message = "Home score must be non-negative")
    private Integer homeScore;
    
    @NotNull(message = "Away score is required")
    @Min(value = 0, message = "Away score must be non-negative")
    private Integer awayScore;
    
    private String summary;

    // Constructors
    public MatchResult() {}

    public MatchResult(Long id, Integer homeScore, Integer awayScore, String summary) {
        this.id = id;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.summary = summary;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getResult() {
        if (homeScore > awayScore) {
            return "WIN";
        } else if (homeScore < awayScore) {
            return "LOSS";
        } else {
            return "DRAW";
        }
    }
}