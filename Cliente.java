import exceptions.DadoVazioException;

public class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) throws DadoVazioException {
        if(checaDado(nome) || checaDado(cpf)) throw new DadoVazioException();
        this.nome = nome;
        this.cpf = cpf;
    }

    public boolean checaDado(String dado){
    if(dado.equals(null) || dado.equals("")) return true;
    return false;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DadoVazioException{
        if(checaDado(nome)) throw new DadoVazioException();
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) throws DadoVazioException{
        if(checaDado(cpf)) throw new DadoVazioException();
        this.cpf = cpf;
    }
}