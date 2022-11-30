import exceptions.*;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.*;

public class Voo implements Imprimivel{
    private String origem;
    private String destino;
    private boolean apenasSeisPrimeiroslivres = false;
    private ArrayList<Cliente> passageiros;
    private static final ArrayList<String> locais = new ArrayList<>(Arrays.asList("FOR", "CGH", "SSA", "BSB", "MAO"));
    private ArrayList<Integer> assentosOcupados;
    private int numPassageiros = 0;
    private static final int numAssentos = 220;
    private int numAssentosDisponiveis;
    private Random random = new Random();

    private static double valorTotal = 0;
    private double valor;

    public Voo(String origem, String destino) throws exceptions.LocalInvalidoException{
        if(checaLocal(origem) && checaLocal(destino) && !origem.equals(destino)){
            passageiros = new ArrayList<>();
            assentosOcupados = new ArrayList<>();
            this.origem = origem;
            this.destino = destino;
            numAssentosDisponiveis = numAssentos;
            valor = 100+Math.pow(5, Math.log10(1));
        }else{
            throw new LocalInvalidoException();
        }
    }

    public void comprarPassagem(Reserva reserva) throws exceptions.VooLotadoException{
        if(numAssentosDisponiveis > 0){
            reserva.setNumAssento(definirAssento(random.nextInt(numAssentos)));
            numPassageiros++;
            valor = 100+Math.pow(5, Math.log10(numPassageiros));
//            valor = Double.parseDouble(new DecimalFormat(".##").format(valor));
            reserva.setValor(valor);
            valorTotal += valor;
            passageiros.add(reserva.getCliente());
            if(numPassageiros == (numAssentos-6)) apenasSeisPrimeiroslivres = true;
        }else{
            throw new VooLotadoException();
        }
    }

    public int definirAssento(int assento){
        if(passageiros.size() > 0){
            for (int i = 0; i < assentosOcupados.size(); i++) {
                if(assento == assentosOcupados.get(i)){
//                    assento = definirAssento(random.nextInt(numAssentos));
                    String n = String.valueOf(Math.random() * numAssentos);
                    assento = definirAssento(Integer.parseInt(n));
                }
            }
        }
        if(!apenasSeisPrimeiroslivres){
            for (int j = 1; j <= 6; j++) {
//                if(assento == j) assento = definirAssento(random.nextInt(numAssentos));
                if(assento == j){
                    String n = String.valueOf(Math.random() * numAssentos);
                    assento = definirAssento(Integer.parseInt(n));
                }
                break;
            }
        }
        numAssentosDisponiveis--;
        assentosOcupados.add(assento);
        return assento;
    }

    public void imprimirPassageiros(ArrayList <Reserva> reservas) throws VooSemPassageirosException {
        if(passageiros.size() > 0){
            String msg = "Lista de Passageiros";
            for (int i = 0; i < passageiros.size(); i++) {
                msg += "\nNome " + passageiros.get(i).getNome() + " Cpf " + passageiros.get(i).getCpf() + " Valor " + reservas.get(i).getValor();
//                System.out.println(passageiros.get(i).getNome());
            }
            JOptionPane.showMessageDialog(null, msg + "\nValor Total: " + valorTotal);
        }else{
            throw new VooSemPassageirosException();
        }
    }

    public boolean checaLocal(String local){
        for (int i = 0; i < locais.size(); i++) {
            if(local.equals(locais.get(i))) return true;
        }
        return false;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public double getValor() {
        return valor;
    }

    public static ArrayList<String> getLocais() {
        return locais;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}