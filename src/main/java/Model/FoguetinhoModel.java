package Model;

import java.util.Random;

public class FoguetinhoModel {
    private double saldo = 1000.0;
    private double aposta;
    private double multiplicador;
    private double crashMultiplicador;
    private boolean emAndamento;

    public void iniciarJogo(double aposta) {
        this.aposta = aposta;
        this.multiplicador = 1.0;
        this.crashMultiplicador = gerarCrashMultiplicador();
        this.emAndamento = true;
        saldo -= aposta;
    }

    private double gerarCrashMultiplicador() {
        Random random = new Random();
        return 1.01 + (5 - 1.01) * random.nextDouble();
    }

    public double getMultiplicador() {
        return multiplicador;
    }

    public void aumentarMultiplicador() {
        if (emAndamento) {
            multiplicador += 0.005;
        }
    }

    public boolean verificarCrash() {
        return multiplicador >= crashMultiplicador;
    }

    public double calcularGanho() {
        return aposta * multiplicador;
    }

    public void encerrarJogo() {
        emAndamento = false;
    }

    public double getSaldo() {
        return saldo;
    }

    public void adicionarGanho(double ganho) {
        saldo += ganho;
    }

    public boolean saldoSuficiente(double aposta) {
        return saldo >= aposta;
    }

    public boolean isEmAndamento() {
        return emAndamento;
    }
}
