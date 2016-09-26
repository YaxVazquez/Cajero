package maincajero;

/**
 *
 * @author Yax
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Cajero extends JFrame {

	JTextField pantalla;
	double resultado;
	String operacion;
	JPanel panelNumeros, panelOperaciones;
	boolean nuevaOperacion = true;
        
	public Cajero() {
		super();
		setSize(600, 600);
		setTitle("Cajero");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel panel = (JPanel)this.getContentPane();
		panel.setLayout(new BorderLayout());

		pantalla = new JTextField("0", 200);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 45));
                pantalla.setBackground(Color.WHITE);
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		panel.add("North", pantalla);

		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));
                panelNumeros.setBackground(Color.WHITE);
		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
		}
		nuevoBotonNumerico(".");
		panel.add("Center", panelNumeros);

		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new GridLayout(5, 6));
		panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
                panelOperaciones.setBackground(Color.WHITE);
                
		nuevoBotonOperacion("Crear cuenta");
		nuevoBotonOperacion("Abonar");
		nuevoBotonOperacion("Retirar");
		nuevoBotonOperacion("Consultar");
		nuevoBotonOperacion("CE");

		panel.add("East", panelOperaciones);

		validate();
	}
	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
                btn.setForeground(Color.black);
                btn.setFont(new Font("Arial", 0, 35));
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                Border cool = new LineBorder(Color.BLACK, 1);
                btn.setBorder(cool);
    
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});
		panelNumeros.add(btn);
	}
	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
                btn.setForeground(Color.black);
                btn.setFont(new Font("Arial", 0, 30));
                btn.setOpaque(false);
                btn.setContentAreaFilled(false);
                Border cool = new LineBorder(Color.BLACK, 1);
                btn.setBorder(cool);
                
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperaciones.add(btn);
	}
	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}
	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("CE")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}
	private void calcularResultado() {
		if (operacion.equals("+")) {
			resultado += new Double(pantalla.getText());
		} else if (operacion.equals("-")) {
			resultado -= new Double(pantalla.getText());
		} else if (operacion.equals("/")) {
			resultado /= new Double(pantalla.getText());
		} else if (operacion.equals("*")) {
			resultado *= new Double(pantalla.getText());
		}

		pantalla.setText("" + resultado);
		operacion = "";
	}
      /*  @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== salir)
        {
            try{
                dispose();
                
                usuario.setSesion(false);
            }catch(Exception a){
                menu_principal men= new menu_principal();
            }
            
        }
        else if(e.getSource()== ejercicios)
        {
            dispose();
            menu_ejercicios mmenu = new menu_ejercicios();
        }
        else if(e.getSource()==calculadora)
        {
            dispose();
            calculadora calc= new calculadora();
        } */
}