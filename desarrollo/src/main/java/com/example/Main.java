package com.example;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new Usuario("Juan Andrés", "Vb2wV@example.com", "1234567890");
        System.out.println(usuario.getNombre());
        System.out.println(usuario.getEmail());
        System.out.println(usuario.getTelefono());
    }
}