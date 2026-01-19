package com.handball.manager.dto;

public class StatisticsDTO {
    private long totalMatches;
    private long wins;
    private long draws;
    private long losses;
    private double trainingAttendanceRate;

    public StatisticsDTO() {}

    public StatisticsDTO(long totalMatches, long wins, long draws, 
                        long losses, double trainingAttendanceRate) {
        this.totalMatches = totalMatches;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.trainingAttendanceRate = trainingAttendanceRate;
    }

    // Getters and Setters
    public long getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(long totalMatches) {
        this.totalMatches = totalMatches;
    }

    public long getWins() {
        return wins;
    }

    public void setWins(long wins) {
        this.wins = wins;
    }

    public long getDraws() {
        return draws;
    }

    public void setDraws(long draws) {
        this.draws = draws;
    }

    public long getLosses() {
        return losses;
    }

    public void setLosses(long losses) {
        this.losses = losses;
    }

    public double getTrainingAttendanceRate() {
        return trainingAttendanceRate;
    }

    public void setTrainingAttendanceRate(double trainingAttendanceRate) {
        this.trainingAttendanceRate = trainingAttendanceRate;
    }
}