package Controllers;

public class libro {
    private String nombreLibro;
    private String autor;
    private int ejemplaresDisponibles;
    private int ejemplaresPrestados;

    public libro() {
    }

    public libro(String nombreLibro, String autor, int ejemplaresDisponibles, int ejemplaresPrestados) {
        this.nombreLibro = nombreLibro;
        this.autor = autor;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }

    public void setEjemplaresPrestados(int ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public String getNombreLibro() {
        return nombreLibro;
    }

    public String getAutor() {
        return autor;
    }

    public int getEjemplaresDisponibles() {
        return ejemplaresDisponibles;
    }

    public int getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public boolean prestar (libro libro) {
        if (libro.ejemplaresDisponibles >= 1) {
            libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() - 1);
            return true;
        }else
            return false;
    }

    public boolean devolver (libro libro) {
        if (libro.getEjemplaresPrestados() >= 0) {
            libro.setEjemplaresDisponibles(libro.getEjemplaresPrestados() - 1);
            return true;
        }else{
            return false;
        }
    }


}
