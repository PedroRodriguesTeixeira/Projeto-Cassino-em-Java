package Model;

import java.util.Random;

public class FortuneTigerModel {
    private int[][] simbolos;
    private int saldo;
    private int valorAposta;
    private Random random;
    private int[] combinacaoVencedora;

    public FortuneTigerModel() {
        simbolos = new int[3][3];
        saldo = 1000;
        random = new Random();
    }

    public void girar() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                simbolos[i][j] = random.nextInt(11);
            }
        }
    }

    public int[][] getSimbolos() {
        return simbolos;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setValorAposta(int valorAposta) {
        this.valorAposta = valorAposta;
    }

    public int getValorAposta() {
        return valorAposta;
    }

    public boolean verificarCombinacao() {
        int[][] combinacoesVencedoras = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int i = 0; i < combinacoesVencedoras.length; i++) {
            int simbolo1 = simbolos[combinacoesVencedoras[i][0] / 3][combinacoesVencedoras[i][0] % 3];
            int simbolo2 = simbolos[combinacoesVencedoras[i][1] / 3][combinacoesVencedoras[i][1] % 3];
            int simbolo3 = simbolos[combinacoesVencedoras[i][2] / 3][combinacoesVencedoras[i][2] % 3];

            if ((simbolo1 == simbolo2 || simbolo1 == 10 || simbolo2 == 10) &&
                    (simbolo1 == simbolo3 || simbolo1 == 10 || simbolo3 == 10) &&
                    (simbolo2 == simbolo3 || simbolo2 == 10 || simbolo3 == 10)) {
                combinacaoVencedora = combinacoesVencedoras[i];
                return true;
            }
        }
        combinacaoVencedora = null;
        return false;
    }


    public int calcularGanho(int simbolo) {
        int multiplicador = 0;

        switch (simbolo) {
            case 0:  multiplicador = 10; break;
            case 1:  multiplicador = 5;  break;
            case 2:  multiplicador = 3;  break;
            case 3:  multiplicador = 7;  break;
            case 4:  multiplicador = 8;  break;
            case 5:  multiplicador = 2;  break;
            case 6:  multiplicador = 4;  break;
            case 7:  multiplicador = 6;  break;
            case 8:  multiplicador = 9;  break;
            case 9:  multiplicador = 11; break;
            case 10: multiplicador = 15; break;
            default: multiplicador = 2;  break;
        }

        return valorAposta * multiplicador;
    }

    public void atualizarSaldo(int ganho) {
        saldo += ganho;
    }

    public int[] getCombinacaoVencedora() {
        return combinacaoVencedora;
    }
}