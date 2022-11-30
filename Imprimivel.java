import exceptions.VooSemPassageirosException;

import java.util.ArrayList;

public interface Imprimivel{
    void imprimirPassageiros(ArrayList<Reserva> reservas) throws VooSemPassageirosException ;
}
