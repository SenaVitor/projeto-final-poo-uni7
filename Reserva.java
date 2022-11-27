public class Reserva {
    private Cliente cliente;
    private int numAssento;
    private Voo voo;
    private static int cont = 1;
    private int codigo;

    public Reserva(Cliente cliente, Voo voo){
        this.cliente = cliente;
        this.voo = voo;
        codigo = cont;
        cont++;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumAssento() {
        return numAssento;
    }

    public void setNumAssento(int numAssento) {
        this.numAssento = numAssento;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}