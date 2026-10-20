package Projet-JAVA.src.com.schottenTotten;

import com.schottenTotten.view.VueConsole;

public class Main {
    public static void main(String[] args) {
        VueConsole vue = new VueConsole("Alice", "Bob");
        vue.demarrer();
    }
}