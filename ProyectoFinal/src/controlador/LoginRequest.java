import java.io.Serializable;

public class LoginRequest implements Serializable {
    private Usuarios usuario;

    public LoginRequest(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}

