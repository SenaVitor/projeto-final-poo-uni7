import exceptions.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int op;
        int index = -1;
        ArrayList<Voo> voos = new ArrayList<>();
        ArrayList<Reserva> reservas = new ArrayList<>();
        String senha;
        System.out.println("Aeroportos: " + Voo.getLocais());
        try {
            do {
                op = Integer.parseInt(JOptionPane.showInputDialog(null, "Menu \n1 - Comprar passagem \n2 - Alterar o assento \n" +
                        "3 - Alterar titularidade de uma reserva \n" + "4 - Cancelar reserva \n5 - Imprimir lista de passageiros \n6 - Sair"));
                switch (op) {
                    case (1):
                        try {
                            String origem = JOptionPane.showInputDialog(null, "Digite o aeroporto de origem:");
                            String destino = JOptionPane.showInputDialog(null, "Digite o aeroporto de destino:");
                            if (voos.size() == 0) {
                                voos.add(new Voo(origem, destino));
                                index = 0;
                            } else {
                                for (int i = 0; i < voos.size(); i++) {
                                    if (voos.get(i).getOrigem().equals(origem) && voos.get(i).getDestino().equals(destino)) {
                                        index = i;
                                        break;
                                    } else if (i + 1 == voos.size()) {
                                        voos.add(new Voo(origem, destino));
                                        index = i + 1;
                                        break;
                                    }
                                }
                            }
                            int continuar = JOptionPane.showOptionDialog(null, "Confirma a compra da passagem de " + voos.get(index).getValor() + " R$?", "Continuar?", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                            if (continuar == 0) {
                                String nome = JOptionPane.showInputDialog(null, "Digite o nome do passageiro:");
                                String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do passageiro:");
                                Cliente cliente = new Cliente(nome, cpf);
                                Reserva reserva = new Reserva(cliente, voos.get(index));
                                reservas.add(reserva);
//                                for (int i = 0; i < 10; i++) {
                                    voos.get(index).comprarPassagem(reserva);
//                                }
                                JOptionPane.showMessageDialog(null, cliente.getNome() + ", sua reserva foi realizada com sucesso! " +
                                        " seu código é " + reserva.getCodigo());
                            }
                        } catch (LocalInvalidoException e) {
                            JOptionPane.showMessageDialog(null, "Local Inválido!");
                        } catch (VooLotadoException e) {
                            JOptionPane.showMessageDialog(null, "Voo Lotado!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro Geral " + e);
                        }
                        break;
                    case (2):
                        //Alterar assento
                        break;
                    case (3):
                        //alterar titularidade
                        break;
                    case (4):
                        //cancelar reserva
                        break;
                    case (5):
                        try {
                            senha = JOptionPane.showInputDialog(null, "Digite a senha:");
                            if(!senha.equals("123")) throw new SenhaInvalidaException();
                            index = -1;
                            String origem = JOptionPane.showInputDialog(null, "Digite o aeroporto de origem:");
                            String destino = JOptionPane.showInputDialog(null, "Digite o aeroporto de destino:");
                            for (int i = 0; i < voos.size(); i++) {
                                if (voos.get(i).getOrigem().equals(origem) && voos.get(i).getDestino().equals(destino)) {
                                    index = i;
                                    break;
                                }
                            }
                            if(index < 0) throw new VooInexistenteException();
                            voos.get(index).imprimirPassageiros(reservas);
                        }catch (SenhaInvalidaException e){
                            JOptionPane.showMessageDialog(null, "Senha inválida");
                        }catch (VooInexistenteException e){
                            JOptionPane.showMessageDialog(null, "Voo inexistente");
                        }catch (VooSemPassageirosException e){
                            JOptionPane.showMessageDialog(null, "Voo sem passageiros");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null, "Voo não existe");
                        }
                        break;
                    case (6):
                        System.out.println("Saindo...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Digite uma opção válida!");
                }
            } while (op != 6);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Operação cancelada");
        }
    }
}