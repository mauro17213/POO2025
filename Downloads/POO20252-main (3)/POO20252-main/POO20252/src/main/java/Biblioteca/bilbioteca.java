package Biblioteca;

import Controllers.libro;

public class bilbioteca {

    public static void Main(String[] args) {
        libro libro1 = new libro();
        libro1.prestar(libro1);
        libro1.devolver(libro1);
    }

}
