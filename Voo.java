import exceptions.*;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.*;

public class Voo{
    private String origem;
    private String destino;
    private boolean apenasSeisPrimeiroslivres = false;
    private ArrayList<Cliente> passageiros;
    private static final ArrayList<String> locais = new ArrayList<>(Arrays.asList("FOR", "CGH", "SSA", "BSB", "MAO"));

    private final ArrayList <Integer> primeirosAssentos = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
    private ArrayList<Integer> assentosOcupados;
    private int numPassageiros = 0;
    private final int numAssentos = 220;
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

    public int comprarPassagem(Reserva reserva) throws exceptions.VooLotadoException{
        if(numAssentosDisponiveis > 0){
            reserva.setNumAssento(definirAssento(random.nextInt(numAssentos)));
            numPassageiros++;
            valor = 100+Math.pow(5, Math.log10(numPassageiros));
            reserva.setValor(valor);
            valorTotal += valor;
            int index = ordenarLista(reserva);
            checaPassageiros();
            return index;
        }else{
            throw new VooLotadoException();
        }
    }

    public int ordenarLista(Reserva reserva){
//        System.out.println(reserva.getCliente().getNome() + " Código " + reserva.getCodigo() + " Assento " + reserva.getNumAssento());
        for(int i = 0; i < assentosOcupados.size(); i++){
            if(reserva.getNumAssento() < assentosOcupados.get(i)){
                passageiros.add(i, reserva.getCliente());
                assentosOcupados.add(i, reserva.getNumAssento());
                return i;
            }
        }
        passageiros.add(reserva.getCliente());
        assentosOcupados.add(reserva.getNumAssento());
        return passageiros.size()-1;
    }

    public int definirAssento(int assento){
        String n;
        if(assento == 0){
            n = String.valueOf(Math.random() * numAssentos);
            assento = definirAssento(Integer.parseInt(n));
        }
        if(passageiros.size() > 0){
            for (int i = 0; i < assentosOcupados.size(); i++) {
                if(assento == assentosOcupados.get(i)){
                    n = String.valueOf(Math.random() * numAssentos);
                    assento = definirAssento(Integer.parseInt(n));
                }
            }
        }
        if(!apenasSeisPrimeiroslivres){
            for (int j = 1; j <= 6; j++) {
                if(assento == j){
                    n = String.valueOf(Math.random() * numAssentos);
                    assento = definirAssento(Integer.parseInt(n));
                }
                break;
            }
        }
        numAssentosDisponiveis--;
        return assento;
    }

    public int alterarAssento(Reserva reserva, int assento) throws AssentoInvalidoException{
        if(assentosOcupados.contains(assento)) throw new AssentoInvalidoException();
        assentosOcupados.remove(assentosOcupados.indexOf(reserva.getNumAssento()));
        if(primeirosAssentos.contains(assento)) reserva.setValor(reserva.getValor() + 50);
        passageiros.remove(reserva.getCliente());
        reserva.setNumAssento(assento);
        int index = ordenarLista(reserva);
        checaPassageiros();
        return index;
    }

    public void imprimirPassageiros(ArrayList <Reserva> reservas) throws VooSemPassageirosException {
        if(passageiros.size() > 0){
            String msg = "Lista de Passageiros";
            for (int i = 0; i < passageiros.size(); i++) {
                msg += "\nReserva " + reservas.get(i).getCodigo() + " Nome " + passageiros.get(i).getNome() + " Cpf " + passageiros.get(i).getCpf() +
                        " Assento " + reservas.get(i).getNumAssento() + " Valor " + reservas.get(i).getValor();
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

    public void checaPassageiros(){
        if(numPassageiros == (numAssentos-6)){
            int cont = 0;
            for (int assentoAtual : primeirosAssentos){
                if(assentosOcupados.contains(assentoAtual)) cont++;
            }
            if(cont == 6){
                apenasSeisPrimeiroslivres = false;
            }else{
                apenasSeisPrimeiroslivres = true;
            }
        }
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
    public int getNumAssentos(){
        return numAssentos;
    }
    public ArrayList<Integer> getAssentosOcupados(){
        return assentosOcupados;
    }
}