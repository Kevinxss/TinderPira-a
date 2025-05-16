package app.model;

public class Notificacion {
    private String de;
    private String para;
    private String correoPara;

    public Notificacion(String de, String para, String correoPara) {
        this.de = de;
        this.para = para;
        this.correoPara = correoPara;
    }

    // Getters y setters
    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getCorreoPara() {
        return correoPara;
    }

    public void setCorreoPara(String correoPara) {
        this.correoPara = correoPara;
    }
}
