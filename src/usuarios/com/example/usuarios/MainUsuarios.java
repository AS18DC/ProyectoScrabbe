package com.example.usuarios;

import juego.Authentication;
import juego.BuscarArchivo;
import juego.Jugador;
import juego.ManejadorDeArchivos;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para la gestión de usuarios en el sistema del juego.
 * Proporciona funcionalidades como mostrar usuarios, registrar nuevos,
 * modificar datos y eliminar usuarios existentes.
 */
public class MainUsuarios {
    public static Scanner read = new Scanner(System.in);
    public static boolean validInput = false;

    public static String RESET = "\u001B[0m";
    public static String TEXTO_ROJO = "\u001B[31m";
    public static String TEXTO_VERDE = "\u001B[32m";
    public static String TEXTO_AZUL = "\u001B[34m";
    public static String TEXTO_NEGRO = "\u001B[30m";

    public static String FONDO_BLANCO= "\u001B[107m";
    public static String FONDO_VERDE = "\u001B[102m";
    public static String FONDO_AMARILLO = "\u001B[103m";
    public static String FONDO_CYAN = "\u001B[106m";

    /**
     * Muestra una lista de todos los usuarios registrados en el sistema.
     * Los usuarios se recuperan desde archivos almacenados localmente.
     */
    public static void mostrarUsuarios() {
        ManejadorDeArchivos manejadorDeArchivos = new ManejadorDeArchivos();
        File directory = new File(System.getProperty("user.dir"));
        String extension = ".jug";  // Extensión de los archivos de usuario
        List<String> fileNames = BuscarArchivo.getFileNamesWithExtension(directory, extension);

        ArrayList<Jugador> jugadores = new ArrayList<>();
        for (String fileName : fileNames) {
            int lastIndex = fileName.lastIndexOf(".");
            fileName = fileName.substring(0, lastIndex);
            Jugador jugador = manejadorDeArchivos.restaurarJugador(fileName);
            jugadores.add(jugador);
        }

        // Mostrar encabezados de la tabla de usuarios
        System.out.print("alias");
        System.out.printf("%15s ", "correo");
        System.out.println("");

        for (Jugador jugador : jugadores) {
            System.out.println(jugador.getAlias());
            System.out.printf("%15s", jugador.getEmail());
            System.out.println("");
        }
    }

    /**
     * Permite modificar los datos de un usuario existente.
     * El usuario se busca por su alias, y se pueden actualizar el alias y/o el correo electrónico.
     */
    public static void modificarUsuario() {
        ManejadorDeArchivos manejadorDeArchivos = new ManejadorDeArchivos();
        System.out.println("Ingrese el alias del usuario a modificar:");
        String alias = read.next();

        File directory = new File(System.getProperty("user.dir"));
        String extension = ".jug";
        List<String> fileNames = BuscarArchivo.getFileNamesWithExtension(directory, extension);

        boolean usuarioEncontrado = false;
        Jugador jugadorAModificar = null;

        for (String fileName : fileNames) {
            int lastIndex = fileName.lastIndexOf(".");
            String jugador = fileName.substring(0, lastIndex);
            if (alias.equals(jugador)) {
                usuarioEncontrado = true;
                jugadorAModificar = manejadorDeArchivos.restaurarJugador(jugador);
                break;
            }
        }

        if (!usuarioEncontrado) {
            System.out.println(TEXTO_ROJO + "Usuario no encontrado." + RESET);
            return;
        }

        System.out.println("Usuario encontrado: " + jugadorAModificar.getAlias() + ", " + jugadorAModificar.getEmail());

        System.out.println("¿Desea modificar el alias? (s/n)");
        char modificarAlias = read.next().charAt(0);
        if (modificarAlias == 's' || modificarAlias == 'S') {
            System.out.println("Ingrese el nuevo alias:");
            String nuevoAlias = read.next();
            jugadorAModificar.setAlias(nuevoAlias);
        }

        System.out.println("¿Desea modificar el correo electrónico? (s/n)");

        char modificarEmail = read.next().charAt(0);
        if (modificarEmail == 's' || modificarEmail == 'S') {
            System.out.println("Ingrese el nuevo correo electrónico:");
            String nuevoEmail = read.next();
            Authentication auth = new Authentication();
            // Validar el nuevo correo electrónico
            while (!auth.validateEmail(nuevoEmail)) {
                System.out.println(TEXTO_ROJO + "Correo inválido, por favor ingrese otro" + RESET);
                nuevoEmail = read.next();
            }
            jugadorAModificar.setEmail(nuevoEmail);
        }

        // Guardar los cambios en el archivo
        manejadorDeArchivos.salvarJugador(jugadorAModificar);
        System.out.println(TEXTO_VERDE + "Usuario modificado exitosamente." + RESET);
    }

    /**
     * Registra un nuevo usuario solicitando el alias y correo electrónico.
     * Realiza una validación básica del correo antes de guardar los datos.
     */
    public static void registrarUsuario() {
        String alias = "";
        String email = "";
        Authentication auth = new Authentication();
        System.out.println(TEXTO_AZUL + "\nIngrese la opción que quiere elegir: " + RESET);
        int opc = read.nextInt();
        System.out.println("Ingresa el correo electrónico");
        email = read.next();
        // Validar el correo electrónico
        while (!auth.validateEmail(email)) {
            System.out.println(TEXTO_ROJO + "Correo inválido, por favor ingrese otro" + RESET);
            email = read.next();
        }
        System.out.println("Ingresa el alias del jugador");
        alias = read.next();
        auth.register(alias, email);
        System.out.println("El usuario fue registrado");
    }

    /**
     * Elimina un usuario del sistema basado en su alias.
     *
     * @param alias El alias del usuario a eliminar.
     */
    public static void eliminarUsuario(String alias) {
        ManejadorDeArchivos manejadorDeArchivos = new ManejadorDeArchivos();
        File directory = new File(System.getProperty("user.dir"));
        String extension = ".jug";  // Extensión de los archivos de usuario
        List<String> fileNames = BuscarArchivo.getFileNamesWithExtension(directory, extension);

        for (String fileName : fileNames) {
            int lastIndex = fileName.lastIndexOf(".");
            String jugador = fileName.substring(0, lastIndex);
            if (alias.equals(jugador)) {
                File archivo = new File(fileName);
                if (archivo.delete()) {
                    System.out.println("El usuario fue eliminado");
                } else {
                    System.out.println("El usuario no se pudo eliminar");
                }
            }
        }
    }

    /**
     * Muestra el menú principal para la gestión de usuarios.
     * Permite al usuario seleccionar diferentes opciones de gestión.
     */
    public static void menu() {
        System.out.println("\n" + FONDO_CYAN + TEXTO_NEGRO + "¿Qué le gustaría hacer?" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "1. Ver todos los usuarios" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "2. Registrar usuario" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "3. Eliminar usuario" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "4. Modificar usuario" + RESET);
        System.out.println(FONDO_CYAN + TEXTO_NEGRO + "0. Salir" + RESET);
    }

    /**
     * Lee un número ingresado por el usuario y lo valida.
     *
     * @return Un número entero ingresado por el usuario.
     */
    public static int leerNumero() {
        int opc = -1;
        while (!validInput) {
            try {
                opc = read.nextInt();
                validInput = true;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, introduce un número.");
                read.nextLine();
            }
        }
        validInput = false;
        return opc;
    }

    /**
     * Método principal que gestiona el flujo de la aplicación.
     * Presenta un menú de opciones y ejecuta las acciones correspondientes.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        System.out.println(FONDO_VERDE + TEXTO_NEGRO + "****************************************" + RESET);
        System.out.println(FONDO_VERDE + TEXTO_NEGRO + "*********** Gestión de Usuarios *************" + RESET);
        menu();
        int opc = leerNumero();
        while (opc != 0) {
            switch (opc) {
                case 1:
                    mostrarUsuarios();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    System.out.println("Ingrese el alias del usuario a eliminar");
                    String alias = read.nextLine();
                    eliminarUsuario(alias);
                    break;
                case 4:
                    modificarUsuario();
                    break;
                default:
                    System.out.println("Ingrese una opción válida");
                    break;
            }
            menu();
            opc = leerNumero();
        }
    }
}
