package com.example.demo.repository;

import java.awt.EventQueue;
import java.awt.Color;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Interfaz_InicioSesion {

	private static final String URL_LOGIN = "http://localhost:8080/Usuarios/login";

	private JFrame frame;
	protected JTextField t1;
	protected JPasswordField t2;
	protected JLabel l;
	protected JLabel l1;
	protected JLabel l2;
	protected JLabel l3;

	private final HttpClient cliente = HttpClient.newHttpClient();
	private final ObjectMapper mapper = new ObjectMapper();
	private final Preferences prefs = Preferences.userNodeForPackage(Interfaz_InicioSesion.class);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_InicioSesion window = new Interfaz_InicioSesion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interfaz_InicioSesion() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Inicio de sesión");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		l = new JLabel("Usuario:");
		l.setBounds(80, 63, 74, 14);
		frame.getContentPane().add(l);

		t1 = new JTextField();
		t1.setBounds(180, 60, 199, 20);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		t1.setText(prefs.get("usuarioRecordado", "")); // carga el usuario recordado

		l1 = new JLabel("Contraseña:");
		l1.setBounds(60, 118, 94, 14);
		frame.getContentPane().add(l1);

		t2 = new JPasswordField();
		t2.setBounds(180, 115, 199, 20);
		frame.getContentPane().add(t2);

		l2 = new JLabel("");
		l2.setBounds(180, 91, 199, 14);
		l2.setForeground(Color.RED);
		frame.getContentPane().add(l2);

		l3 = new JLabel("");
		l3.setBounds(180, 146, 199, 14);
		l3.setForeground(Color.RED);
		frame.getContentPane().add(l3);

		JButton bt1 = new JButton("Limpiar");
		bt1.setBounds(46, 176, 89, 23);
		frame.getContentPane().add(bt1);
		bt1.addActionListener(e -> {
			t1.setText("");
			t2.setText("");
			l2.setText("");
			l3.setText("");
		});

		JButton bt2 = new JButton("Comprobar");
		bt2.setBounds(190, 176, 89, 23);
		frame.getContentPane().add(bt2);
		bt2.addActionListener(e -> iniciarSesion());

		JButton bt3 = new JButton("Recordar");
		bt3.setBounds(322, 176, 89, 23);
		frame.getContentPane().add(bt3);
		bt3.addActionListener(e -> {
			prefs.put("usuarioRecordado", t1.getText().trim());
			JOptionPane.showMessageDialog(frame, "Usuario recordado");
		});
	}

	private void iniciarSesion() {
		l2.setText("");
		l3.setText("");

		String usuario = t1.getText().trim();
		String password = new String(t2.getPassword());

		// Validación básica
		boolean ok = true;
		if (usuario.isEmpty()) {
			l2.setText("Introduce el usuario");
			ok = false;
		}
		if (password.isEmpty()) {
			l3.setText("Introduce la contraseña");
			ok = false;
		}
		if (!ok) return;

		try {
			String json = mapper.writeValueAsString(
					Map.of("username", usuario, "password", password));

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(URL_LOGIN))
					.header("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.build();

			HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() == 200) {
				JsonNode user = mapper.readTree(response.body());
				String rango = user.path("rango").asText();
				JOptionPane.showMessageDialog(frame,
						"Bienvenido " + usuario + " (" + rango + ")");
				// Aquí abrirías tu siguiente ventana, por ejemplo:
				// frame.dispose(); new VentanaPrincipal(rango).setVisible(true);
			} else if (response.statusCode() == 401) {
				l3.setText("Usuario o contraseña incorrectos");
			} else {
				l3.setText("Error del servidor: " + response.statusCode());
			}

		} catch (java.net.ConnectException ex) {
			l3.setText("No se puede conectar al servidor");
		} catch (Exception ex) {
			ex.printStackTrace();
			l3.setText("Error: " + ex.getMessage());
		}
	}
}
