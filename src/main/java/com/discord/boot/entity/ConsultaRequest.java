package com.discord.boot.entity;

public class ConsultaRequest {
    private int casoCodigo;
    private double baseCalculo;

    // Construtores, getters e setters
    public ConsultaRequest() {
    }

    public ConsultaRequest(int casoCodigo, double baseCalculo) {
        this.casoCodigo = casoCodigo;
        this.baseCalculo = baseCalculo;
    }

    public int getCasoCodigo() {
        return casoCodigo;
    }

    public void setCasoCodigo(int casoCodigo) {
        this.casoCodigo = casoCodigo;
    }

    public double getBaseCalculo() {
        return baseCalculo;
    }

    public void setBaseCalculo(double baseCalculo) {
        this.baseCalculo = baseCalculo;
    }
}
