public class Cliente extends Pessoa {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente(String nome, String cpf, String telefone, String email) {
        super(nome, cpf, telefone);
        this.email = email;
    }
}
