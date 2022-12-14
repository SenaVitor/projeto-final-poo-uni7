import exceptions.*;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int op;
        int index = -1;
        ArrayList<Voo> voos = new ArrayList<>();
        ArrayList<Reserva> reservas = new ArrayList<>();
        String senha;
        int continuar;
        try {
            do {
                op = Integer.parseInt(JOptionPane.showInputDialog(null, "Menu \n1 - Comprar passagem \n2 - Alterar o assento \n" +
                        "3 - Alterar titularidade de uma reserva \n" + "4 - Cancelar reserva \n5 - Imprimir lista de passageiros \n6 - Sair"));
                switch (op) {
                    case (1):
                        try {
                            System.out.println("Aeroportos: " + Voo.getLocais());
                            String origem = JOptionPane.showInputDialog(null, "Digite o aeroporto de origem:").toUpperCase();
                            String destino = JOptionPane.showInputDialog(null, "Digite o aeroporto de destino:").toUpperCase();
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
                            continuar = JOptionPane.showOptionDialog(null, "Confirma a compra da passagem de " + voos.get(index).getValor() + " R$?", "Continuar?", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                            if (continuar == 0) {
                                String nome = JOptionPane.showInputDialog(null, "Digite o nome do passageiro:");
                                String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do passageiro:");
                                Cliente cliente = new Cliente(nome, cpf);
                                Reserva reserva = new Reserva(cliente, voos.get(index));
//                                for (int i = 0; i < 10; i++) {
                                index = voos.get(index).comprarPassagem(reserva);
                                reservas.add(index, reserva);
//                                }
                                JOptionPane.showMessageDialog(null, cliente.getNome() + ", sua reserva foi realizada com sucesso! " +
                                        " seu c??digo ?? " + reserva.getCodigo() + "\nAssento " + reserva.getNumAssento());
                            }
                        } catch (LocalInvalidoException e) {
                            JOptionPane.showMessageDialog(null, "Local Inv??lido!");
                        } catch (VooLotadoException e) {
                            JOptionPane.showMessageDialog(null, "Voo Lotado!");
                        } catch (DadoVazioException e) {
                            JOptionPane.showMessageDialog(null, "Nome ou cpf inv??lido(s)");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro Geral");
                        }
                        break;
                    case (2):
                        try{
                            continuar = 0;
                            int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o c??digo da sua reserva"));
                            boolean reservaExiste = false;
                            for (Reserva reserva:reservas) {
                                if(reserva.getCodigo() == codigo) {
                                    ArrayList<Integer> assentosDisponiveis = new ArrayList<>();
                                    for(int i = 1; i <= reserva.getVoo().getNumAssentos();i++){
                                        if(!reserva.getVoo().getAssentosOcupados().contains(i)) assentosDisponiveis.add(i);
                                    }
                                    if(assentosDisponiveis.size() == 0) throw new VooLotadoException();
                                    System.out.println("Assentos dispon??veis:\n" + assentosDisponiveis);
                                    int assento = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o novo assento"));
                                    if(reserva.getVoo().getPrimeirosAssentos().contains(assento)) continuar = JOptionPane.showOptionDialog(null,
                                            "Esse assento requer um adicional de 50 R$, deseja continuar?", "Continuar?",
                                            JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, null, 0);
                                    if (continuar == 0) {
                                        index = reserva.getVoo().alterarAssento(reserva, assento);
                                        reservas.remove(reserva);
                                        reservas.add(index, reserva);
                                        reservaExiste = true;
                                    }else{
                                        throw new OperacaoCanceladaException();
                                    }
                                    break;
                                }
                            }
                            if(!reservaExiste) throw new CodigoInexistenteException();
                        } catch (CodigoInexistenteException e) {
                            JOptionPane.showMessageDialog(null, "C??digo Inexistente!");
                        } catch (OperacaoCanceladaException e) {
                            JOptionPane.showMessageDialog(null, "Opera????o Cancelada!");
                        } catch (AssentoInvalidoException e) {
                            JOptionPane.showMessageDialog(null, "Assento Inv??lido!");
                        } catch (VooLotadoException e) {
                            JOptionPane.showMessageDialog(null, "Voo Lotado!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro Geral");
                        }
                        break;
                    case (3):
                        try{
                            int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o c??digo da sua reserva"));
                            boolean reservaExiste = false;
                            for (Reserva reserva:reservas) {
                                if (reserva.getCodigo() == codigo) {
                                    String nome = JOptionPane.showInputDialog(null, "Digite o nome do novo cliente");
                                    String cpf = JOptionPane.showInputDialog(null, "Digite o cpf do novo cliente");
                                    reserva.getCliente().setNome(nome);
                                    reserva.getCliente().setCpf(cpf);
                                    JOptionPane.showMessageDialog(null, "Titularidade alterada com sucesso!");
                                    reservaExiste = true;
                                }
                            }
                            if(!reservaExiste) throw new CodigoInexistenteException();
                        } catch (CodigoInexistenteException e) {
                            JOptionPane.showMessageDialog(null, "C??digo Inexistente!");
                        } catch (DadoVazioException e) {
                            JOptionPane.showMessageDialog(null, "Nome ou cpf inv??lido(s)");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro Geral!");
                        }
                        break;
                    case (4):
                        try{
                            int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o c??digo da sua reserva"));
                            boolean reservaExiste = false;
                            for (Reserva reserva:reservas) {
                                if (reserva.getCodigo() == codigo) {
                                    reserva.getVoo().cancelarReserva(reserva);
                                    reservas.remove(reserva);
                                    reserva = null;
                                    JOptionPane.showMessageDialog(null, "Reserva cancelada com sucesso!");
                                    reservaExiste = true;
                                    break;
                                }
                            }
                            if(!reservaExiste) throw new CodigoInexistenteException();
                        } catch (CodigoInexistenteException e) {
                            JOptionPane.showMessageDialog(null, "C??digo Inexistente!");
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Erro Geral!");
                        }
                        break;
                    case (5):
                        try {
                            System.out.println("Aeroportos: " + Voo.getLocais());
                            senha = JOptionPane.showInputDialog(null, "Digite a senha:");
                            if(!senha.equals("123")) throw new SenhaInvalidaException();
                            index = -1;
                            String origem = JOptionPane.showInputDialog(null, "Digite o aeroporto de origem:").toUpperCase();
                            String destino = JOptionPane.showInputDialog(null, "Digite o aeroporto de destino:").toUpperCase();
                            for (int i = 0; i < voos.size(); i++) {
                                if (voos.get(i).getOrigem().equals(origem) && voos.get(i).getDestino().equals(destino)) {
                                    index = i;
                                    break;
                                }
                            }
                            if(index < 0) throw new VooInexistenteException();
                            voos.get(index).imprimirPassageiros(reservas);
                        }catch (SenhaInvalidaException e){
                            JOptionPane.showMessageDialog(null, "Senha inv??lida");
                        }catch (VooInexistenteException e){
                            JOptionPane.showMessageDialog(null, "Voo inexistente");
                        }catch (VooSemPassageirosException e){
                            JOptionPane.showMessageDialog(null, "Voo sem passageiros");
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(null, "Voo n??o existe");
                        }
                        break;
                    case (6):
                        System.out.println("Saindo...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Digite uma op????o v??lida!");
                }
            } while (op != 6);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Opera????o cancelada");
        }
    }
}