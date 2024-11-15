import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
public class biblioteca {

    private static HashMap<String, String> usuariosRegistrados = new HashMap<>();
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro e Inicio de Sesión");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1, 0, 15));
        JLabel imageLabel = new JLabel(new ImageIcon("logo.png"));
        JLabel welcomeLabel = new JLabel("¡Bienvenido!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        JButton registerButton = new JButton("Regístrate");
        registerButton.setBackground(new Color(0, 128, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setPreferredSize(new Dimension(150, 30));
        JButton loginButton = new JButton("Inicia sesión");
        loginButton.setBackground(new Color(0, 128, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 30));
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaRegistro();
                frame.dispose(); 
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInicioSesion();
                frame.dispose(); 
            }
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
        submitButton.setBackground(new Color(0, 100, 0));
        submitButton.setForeground(Color.WHITE);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String matricula = matriculaField.getText();
                String contraseña = new String(contraseñaField.getPassword());
                if (!matricula.isEmpty() && !contraseña.isEmpty()) {
                    usuariosRegistrados.put(matricula, contraseña);
                    JOptionPane.showMessageDialog(registroFrame, "Registro exitoso para " + nombre);
                } else {
                    JOptionPane.showMessageDialog(registroFrame, "Por favor, ingrese todos los campos.");
                }
            }
        });

        JButton loginButton = new JButton("Inicia sesión");
        loginButton.setBackground(new Color(0, 128, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInicioSesion();
                registroFrame.dispose();
            }
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
        loginButton.setBackground(new Color(0, 100, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String matricula = matriculaField.getText();
                String contraseña = new String(contraseñaField.getPassword());
                if (usuariosRegistrados.containsKey(matricula) && usuariosRegistrados.get(matricula).equals(contraseña)) {
                    JOptionPane.showMessageDialog(inicioSesionFrame, "Inicio de sesión exitoso.");
                    inicioSesionFrame.dispose();  
                    abrirVentanaBiblioteca();  // Abrir ventana de la biblioteca
                } else {
                    JOptionPane.showMessageDialog(inicioSesionFrame, "Matrícula o contraseña incorrecta.");
                }
            }
        });

        JButton registerButton = new JButton("Regístrate");
        registerButton.setBackground(new Color(0, 128, 0));
        registerButton.setForeground(Color.WHITE);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaRegistro();
                inicioSesionFrame.dispose();
            }
        });

        panel.add(loginButton);
        panel.add(registerButton);
        inicioSesionFrame.add(panel);
        inicioSesionFrame.setVisible(true);
    }

    public static void abrirVentanaBiblioteca() {
        JFrame bibliotecaFrame = new JFrame("Categorías de la Biblioteca");
        bibliotecaFrame.setSize(400, 300);
        bibliotecaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));  
        String[] categorias = {"Drama", "Terror", "Romance", "Aventura", "Histórico", "Ciencia", "Juvenil", "Investigación"};
        for (String categoria : categorias) {
            JButton categoryButton = new JButton(categoria);
            categoryButton.setBackground(new Color(0, 128, 0));
            categoryButton.setForeground(Color.WHITE);
            categoryButton.setOpaque(true);
            categoryButton.setBorderPainted(false);
            categoryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirVentanaCategoria(categoria); 
                }
            });
            panel.add(categoryButton);
        }

        bibliotecaFrame.add(panel);
        bibliotecaFrame.setVisible(true);
    }

    public static void abrirVentanaCategoria(String categoria) {
        JFrame categoriaFrame = new JFrame(categoria);
        categoriaFrame.setSize(600, 400);
        categoriaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        String[] libros;
        String[] imagenes;
        switch (categoria) {
            case "Drama":
                libros = new String[]{"Hamlet", "La Casa de Bernarda Alba", "Medea", "El Rey Lear"};
                imagenes = new String[]{"hamlet.jpg", "lacasa.jpeg", "medea.jpg", "elrey.jpeg"};
                break;
            case "Terror":
                libros = new String[]{"Drácula", "Frankenstein", "El Resplandor", "El Exorcista"};
                imagenes = new String[]{"dracula.jpg", "frankenstein.jpg", "resplandor.jpg", "exorcista.jpg"};
                break;
            case "Romance":
                libros = new String[]{"Orgullo y Prejuicio", "Cumbres Borrascosas", "El Gran Gatsby", "Romeo y Julieta"};
                imagenes = new String[]{"orgullo.jpg", "cumbres.jpg", "gatsby.jpg", "romeo.jpg"};
                break;
            case "Aventura":
                libros = new String[]{"La Isla del Tesoro", "Moby Dick", "Robinson Crusoe", "El Hobbit"};
                imagenes = new String[]{"isla.jpg", "moby.jpg", "robinson.jpg", "hobbit.jpg"};
                break;
            default:
                libros = new String[]{};
                imagenes = new String[]{};
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 10, 10));  
        for (int i = 0; i < libros.length; i++) {
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
            ImageIcon bookImage = new ImageIcon(imagenes[i]);
            JLabel imageLabel = new JLabel(bookImage);
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    solicitarLibro();  
                }
            });
            bookPanel.add(imageLabel);
            JLabel bookLabel = new JLabel(libros[i], SwingConstants.CENTER);
            bookLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    solicitarLibro();  
                }
            });
            bookPanel.add(bookLabel);
            panel.add(bookPanel);
        }

        categoriaFrame.add(new JScrollPane(panel));
        categoriaFrame.setSize(600, 500);
        categoriaFrame.setVisible(true);
    }

    public static void solicitarLibro() {
        JFrame solicitarFrame = new JFrame("Solicitar Libro");
        solicitarFrame.setSize(300, 200);
        solicitarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        JLabel solicitarLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(solicitarLabel);
        JLabel fechaInicioLabel = new JLabel("Fecha de inicio:");
        JTextField fechaInicioField = new JTextField(20);
        panel.add(fechaInicioLabel);
        panel.add(fechaInicioField);
        JLabel fechaTerminoLabel = new JLabel("Fecha de término:");
        JTextField fechaTerminoField = new JTextField(20);
        panel.add(fechaTerminoLabel);
        panel.add(fechaTerminoField);
        JLabel contrasenaLabel = new JLabel("Contraseña:");
        JPasswordField contrasenaField = new JPasswordField(20);
        panel.add(contrasenaLabel);
        panel.add(contrasenaField);
        JButton solicitarButton = new JButton("Solicitar");
        solicitarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contrasena = new String(contrasenaField.getPassword());
                if (usuariosRegistrados.containsValue(contrasena)) {
                    JOptionPane.showMessageDialog(solicitarFrame, "Solicitud de libro aceptada.");
                } else {
                    JOptionPane.showMessageDialog(solicitarFrame, "Solicitud rechazada: Contraseña incorrecta.");
                }
            }
        });

        panel.add(solicitarButton);
        solicitarFrame.add(panel);
        solicitarFrame.setVisible(true);
    }
}
