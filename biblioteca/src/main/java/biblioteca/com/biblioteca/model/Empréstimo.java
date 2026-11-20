package main.model;

public class Empréstimo {
    private double data_retirada;
    private double data_devolucao;

    public double getData_retirada() {
        return data_retirada;
    }

    public void setData_retirada(double data_retirada) {
        this.data_retirada = data_retirada;
    }

    public double getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(double data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
