package com.market.market_system.model;

import javax.persistence.GeneratedValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class Bodypayment {

    @GeneratedValue
    private UUID idBody = java.util.UUID.randomUUID();

    public List<String> getCodigosProdutos() {
        return codigosProdutos;
    }

    public String getCpfComprador() {
        return cpfComprador;
    }

    public String getCnpjVendedor() {
        return cnpjVendedor;
    }

    public String getDateBoleto() {
        return dateBoleto;
    }

    public void setCodigosProdutos(List<String> codigosProdutos) {
        this.codigosProdutos = codigosProdutos;
    }

    public void setCpfComprador(String cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public void setCnpjVendedor(String cnpjVendedor) {
        this.cnpjVendedor = cnpjVendedor;
    }

    public void setDateBoleto(String dateBoleto) {
        this.dateBoleto = dateBoleto;
    }

    private List<String> codigosProdutos;
    private String cpfComprador;
    private String cnpjVendedor;
    private String dateBoleto;


    public Bodypayment(List<String> codigosProdutos, String cpfComprador, String cnpjVendedor, String dateBoleto) {
        this.codigosProdutos = codigosProdutos;
        this.cpfComprador = cpfComprador;
        this.cnpjVendedor = cnpjVendedor;
        this.dateBoleto = dateBoleto;
    }

    public Bodypayment() {
    }
}
