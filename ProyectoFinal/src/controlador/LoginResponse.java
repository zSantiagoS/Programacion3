import java.io.Serializable;

public class LoginResponse implements Serializable {
    private boolean loginExitoso;
    private String mensaje;

    public LoginResponse(boolean loginExitoso, String mensaje) {
        this.loginExitoso = loginExitoso;
        this.mensaje = mensaje;
    }

    public boolean isLoginExitoso() {
        return loginExitoso;
    }

    public void setLoginExitoso(boolean loginExitoso) {
        this.loginExitoso = loginExitoso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
