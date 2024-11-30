import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class biblioteca {

    private static HashMap<String, String> usuariosRegistrados = new HashMap<>();
    private static HashMap<String, String> nombresUsuarios = new HashMap<>();
    private static ArrayList<String> librosSolicitados = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro e Inicio de Sesión");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 0, 15));

        JLabel imageLabel = new JLabel(new ImageIcon("logo.png"));
        JLabel welcomeLabel = new JLabel("¡Bienvenido!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JButton registerButton = new JButton("Regístrate");
        configurarBoton(registerButton);

        JButton loginButton = new JButton("Inicia sesión");
        configurarBoton(loginButton);

        registerButton.addActionListener(e -> {
            abrirVentanaRegistro();
            frame.dispose();
        });

        loginButton.addActionListener(e -> {
            abrirVentanaInicioSesion();
            frame.dispose();
        });

        frame.add(imageLabel);
        frame.add(welcomeLabel);
        frame.add(registerButton);
        frame.add(loginButton);
        frame.setVisible(true);
    }

    public static void abrirVentanaRegistro() {
        JFrame registroFrame = new JFrame("Ventana de Registro");
        registroFrame.setSize(400, 400);
        registroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel imageLabel = new JLabel(new ImageIcon("logo.png"));
        panel.add(imageLabel);

        JLabel nombreLabel = new JLabel("Nombre del alumno:");
        JTextField nombreField = new JTextField(20);
        panel.add(nombreLabel);
        panel.add(nombreField);

        JLabel matriculaLabel = new JLabel("Matrícula:");
        JTextField matriculaField = new JTextField(20);
        panel.add(matriculaLabel);
        panel.add(matriculaField);

        JLabel contraseñaLabel = new JLabel("Nueva contraseña:");
        JPasswordField contraseñaField = new JPasswordField(20);
        panel.add(contraseñaLabel);
        panel.add(contraseñaField);

        JButton submitButton = new JButton("Registrar");
        configurarBoton(submitButton);

        submitButton.addActionListener(e -> {
            String nombre = nombreField.getText();
            String matricula = matriculaField.getText();
            String contraseña = new String(contraseñaField.getPassword());

            if (!matricula.isEmpty() && !contraseña.isEmpty()) {
                usuariosRegistrados.put(matricula, contraseña);
                nombresUsuarios.put(matricula, nombre);  // Guardar el nombre del usuario
                JOptionPane.showMessageDialog(registroFrame, "Registro exitoso para " + nombre);
            } else {
                JOptionPane.showMessageDialog(registroFrame, "Por favor, ingrese todos los campos.");
            }
        });

        JButton loginButton = new JButton("Inicia sesión");
        configurarBoton(loginButton);

        loginButton.addActionListener(e -> {
            abrirVentanaInicioSesion();
            registroFrame.dispose();
        });

        panel.add(submitButton);
        panel.add(loginButton);
        registroFrame.add(panel);
        registroFrame.setVisible(true);
    }

    public static void abrirVentanaInicioSesion() {
        JFrame inicioSesionFrame = new JFrame("Ventana de Inicio de Sesión");
        inicioSesionFrame.setSize(400, 300);
        inicioSesionFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel matriculaLabel = new JLabel("Matrícula:");
        JTextField matriculaField = new JTextField(20);
        panel.add(matriculaLabel);
        panel.add(matriculaField);

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        JPasswordField contraseñaField = new JPasswordField(20);
        panel.add(contraseñaLabel);
        panel.add(contraseñaField);

        JButton loginButton = new JButton("Iniciar sesión");
        configurarBoton(loginButton);

        loginButton.addActionListener(e -> {
            String matricula = matriculaField.getText();
            String contraseña = new String(contraseñaField.getPassword());

            if (usuariosRegistrados.containsKey(matricula) && usuariosRegistrados.get(matricula).equals(contraseña)) {
                String nombre = nombresUsuarios.get(matricula);
                JOptionPane.showMessageDialog(inicioSesionFrame, "Inicio de sesión exitoso.");
                inicioSesionFrame.dispose();
                abrirVentanaBienvenida(nombre);
            } else {
                JOptionPane.showMessageDialog(inicioSesionFrame, "Matrícula o contraseña incorrecta.");
            }
        });

        panel.add(loginButton);
        inicioSesionFrame.add(panel);
        inicioSesionFrame.setVisible(true);
    }

    public static void abrirVentanaBienvenida(String nombre) {
        JFrame bienvenidaFrame = new JFrame("Bienvenido a la Biblioteca");
        bienvenidaFrame.setSize(500, 400);
        bienvenidaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bienvenidaFrame.setLayout(new BorderLayout(10, 10));

        JLabel bienvenidaLabel = new JLabel("¡Bienvenido, " + nombre + "! ¿Qué desea hacer hoy?", SwingConstants.CENTER);
        bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 18));
        bienvenidaFrame.add(bienvenidaLabel, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new FlowLayout());

        JButton solicitarLibroButton = new JButton("Solicitar Libro");
        JButton librosSolicitadosButton = new JButton("Libros Solicitados");
        JButton entregarLibroButton = new JButton("Entregar Libro");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");

        configurarBoton(solicitarLibroButton);
        configurarBoton(librosSolicitadosButton);
        configurarBoton(entregarLibroButton);
        configurarBoton(cerrarSesionButton);

        solicitarLibroButton.addActionListener(e -> {
            abrirVentanaBiblioteca(nombre);
            bienvenidaFrame.dispose();
        });

        librosSolicitadosButton.addActionListener(e -> {
            abrirVentanaLibrosSolicitados(nombre);
            bienvenidaFrame.dispose();
        });

        entregarLibroButton.addActionListener(e -> {
            abrirVentanaEntregarLibro(nombre);
            bienvenidaFrame.dispose();
        });

        cerrarSesionButton.addActionListener(e -> {
            bienvenidaFrame.dispose();
            main(new String[]{}); // Volver a la ventana principal
        });

        botonesPanel.add(solicitarLibroButton);
        botonesPanel.add(librosSolicitadosButton);
        botonesPanel.add(entregarLibroButton);
        botonesPanel.add(cerrarSesionButton);

        bienvenidaFrame.add(botonesPanel, BorderLayout.CENTER);
        bienvenidaFrame.setVisible(true);
    }

    public static void abrirVentanaBiblioteca(String nombre) {
        JFrame bibliotecaFrame = new JFrame("Categorías de la Biblioteca");
        bibliotecaFrame.setSize(400, 300);
        bibliotecaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        String[] categorias = {"Drama", "Terror", "Romance", "Aventura", "Histórico", "Ciencia", "Juvenil", "Investigación"};

        for (String categoria : categorias) {
            JButton categoryButton = new JButton(categoria);
            configurarBoton(categoryButton);

            categoryButton.addActionListener(e -> abrirVentanaLibros(categoria, nombre));
            panel.add(categoryButton);
        }

        JButton volverButton = new JButton("Volver");
        configurarBoton(volverButton);
        volverButton.addActionListener(e -> {
            bibliotecaFrame.dispose();
            abrirVentanaBienvenida(nombre);  // Volver a la ventana de bienvenida
        });

        panel.add(volverButton);
        bibliotecaFrame.add(panel);
        bibliotecaFrame.setVisible(true);
    }

    public static void abrirVentanaLibros(String categoria, String nombre) {
        JFrame librosFrame = new JFrame(categoria);
        librosFrame.setSize(400, 300);
        librosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        String[] libros = obtenerLibrosPorCategoria(categoria);
        for (String libro : libros) {
            JButton libroButton = new JButton(libro);
            configurarBoton(libroButton);
            libroButton.addActionListener(e -> mostrarInformacionLibro(libro, categoria, nombre));
            panel.add(libroButton);
        }

        JButton volverButton = new JButton("Volver");
        configurarBoton(volverButton);
        volverButton.addActionListener(e -> {
            librosFrame.dispose();
            abrirVentanaBiblioteca(nombre);  // Volver a la ventana de categorías
        });

        panel.add(volverButton);
        librosFrame.add(panel);
        librosFrame.setVisible(true);
    }

    public static void mostrarInformacionLibro(String libro, String categoria, String nombre) {
        JFrame infoFrame = new JFrame(libro);
        infoFrame.setSize(400, 300);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel libroLabel = new JLabel("Libro: " + libro);
        JLabel categoriaLabel = new JLabel("Categoría: " + categoria);
        JLabel descripcionLabel = new JLabel("Descripción del libro...");
        panel.add(libroLabel);
        panel.add(categoriaLabel);
        panel.add(descripcionLabel);

        JButton solicitarButton = new JButton("Solicitar");
        configurarBoton(solicitarButton);
        solicitarButton.addActionListener(e -> {
            librosSolicitados.add(libro);
            JOptionPane.showMessageDialog(infoFrame, "Libro '" + libro + "' agregado a tus solicitudes.");
        });

        JButton volverButton = new JButton("Volver");
        configurarBoton(volverButton);
        volverButton.addActionListener(e -> {
            infoFrame.dispose();
            abrirVentanaLibros(categoria, nombre);  // Volver a la ventana de libros
        });

        panel.add(solicitarButton);
        panel.add(volverButton);
        infoFrame.add(panel);
        infoFrame.setVisible(true);
    }

    public static void abrirVentanaLibrosSolicitados(String nombre) {
        JFrame librosSolicitadosFrame = new JFrame("Libros Solicitados");
        librosSolicitadosFrame.setSize(400, 300);
        librosSolicitadosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (String libro : librosSolicitados) {
            panel.add(new JLabel(libro));
        }

        JButton volverButton = new JButton("Volver");
        configurarBoton(volverButton);
        volverButton.addActionListener(e -> {
            librosSolicitadosFrame.dispose();
            abrirVentanaBienvenida(nombre);  // Volver a la ventana de bienvenida
        });

        panel.add(volverButton);
        librosSolicitadosFrame.add(panel);
        librosSolicitadosFrame.setVisible(true);
    }

    public static void abrirVentanaEntregarLibro(String nombre) {
        JFrame entregarFrame = new JFrame("Entregar Libro");
        entregarFrame.setSize(400, 300);
        entregarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel mensajeLabel = new JLabel("Introduce el título del libro que deseas entregar:");
        JTextField libroField = new JTextField(20);
        panel.add(mensajeLabel);
        panel.add(libroField);

        JButton entregarButton = new JButton("Entregar");
        configurarBoton(entregarButton);

        entregarButton.addActionListener(e -> {
            String libro = libroField.getText();
            if (librosSolicitados.contains(libro)) {
                librosSolicitados.remove(libro); // Eliminar el libro de los solicitados
                JOptionPane.showMessageDialog(entregarFrame, "El libro '" + libro + "' ha sido entregado.");
            } else {
                JOptionPane.showMessageDialog(entregarFrame, "Este libro no está en la lista de libros solicitados.");
            }
        });

        JButton volverButton = new JButton("Volver");
        configurarBoton(volverButton);
        volverButton.addActionListener(e -> {
            entregarFrame.dispose();
            abrirVentanaBienvenida(nombre);  // Volver a la ventana de bienvenida
        });

        panel.add(entregarButton);
        panel.add(volverButton);

        entregarFrame.add(panel);
        entregarFrame.setVisible(true);
    }

    public static void configurarBoton(JButton boton) {
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    public static String[] obtenerLibrosPorCategoria(String categoria) {
        // Aquí puedes devolver los libros según la categoría seleccionada
        switch (categoria) {
            case "Drama":
                return new String[]{"La Casa de Bernarda Alba", "Hamlet", "El Gran Gatsby"};
            case "Terror":
                return new String[]{"Drácula", "It", "El Resplandor"};
            case "Romance":
                return new String[]{"Orgullo y Prejuicio", "Cumbres Borrascosas", "Bajo la Misma Estrella"};
            case "Aventura":
                return new String[]{"La Isla del Tesoro", "Don Quijote de la Mancha", "El Hobbit"};
            case "Histórico":
                return new String[]{"Los Pilares de la Tierra", "El Nombre de la Rosa", "Sombra del Viento"};
            case "Ciencia":
                return new String[]{"El Origen de las Especies", "Breve Historia del Tiempo", "Cosmos"};
            case "Juvenil":
                return new String[]{"Harry Potter y la Piedra Filosofal", "Los Juegos del Hambre", "Percy Jackson y el Ladrón del Rayo"};
            case "Investigación":
                return new String[]{"El ADN y la Ciencia", "La Ciencia de la Vida", "La Teoría del Todo"};
            default:
                return new String[]{};
        }
    }
}