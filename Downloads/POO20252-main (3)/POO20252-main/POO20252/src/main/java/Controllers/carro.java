package Controllers;

public class carro {
    //modificador de acceso + tipo de dato + nombre de atributo en camel case
    private String color;
    private String marca;
    private String modelo;
    private String kilometraje;
    private String chasis;
    private String placa;
    private String año;

    public carro() {
    }

    public carro(String color, String placa) {
        this.color = color;
        this.placa = placa;
    }

    public carro(String color, String marca, String modelo, String kilometraje, String chasis, String placa, String año) {
        this.color = color;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.chasis = chasis;
        this.placa = placa;
        this.año = año;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(String kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }
}
