package com.vos.gui.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import com.vos.components.javabeans.BeanNumberToLetter;

public class InputImport {

	private JFrame frmConvierteUnImporte;
	private JTextField tfImporte;
	private JLabel lblImporteXletra;
	
	private BeanNumberToLetter conver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputImport window = new InputImport();
					window.frmConvierteUnImporte.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InputImport() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConvierteUnImporte = new JFrame();
		frmConvierteUnImporte.setTitle("Convierte un Importe a Letra");
		frmConvierteUnImporte.setBounds(100, 100, 450, 300);
		frmConvierteUnImporte.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConvierteUnImporte.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(74, 249, 350, -234);
		frmConvierteUnImporte.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblImporte = new JLabel("Importe $");
		lblImporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblImporte.setBounds(22, 37, 65, 14);
		frmConvierteUnImporte.getContentPane().add(lblImporte);
		
		tfImporte = new JTextField();
		tfImporte.setBounds(101, 34, 125, 20);
		frmConvierteUnImporte.getContentPane().add(tfImporte);
		tfImporte.setColumns(10);
		
		JButton btnConvierte = new JButton("Convierte");
		btnConvierte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				conver.setNumber(tfImporte.getText());
				
				lblImporteXletra.setText( conver.getNumberToLetter() );				
			}
		});
		btnConvierte.setBounds(236, 33, 89, 23);
		frmConvierteUnImporte.getContentPane().add(btnConvierte);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblImporteXletra.setText("");
				tfImporte.setText("");
			}
		});
		btnLimpiar.setBounds(335, 33, 89, 23);
		frmConvierteUnImporte.getContentPane().add(btnLimpiar);
			
		lblImporteXletra = new JLabel("");
		lblImporteXletra.setVerticalAlignment(SwingConstants.TOP);
		lblImporteXletra.setHorizontalAlignment(SwingConstants.LEFT);
		lblImporteXletra.setBounds(10, 100, 414, 30);
		frmConvierteUnImporte.getContentPane().add(lblImporteXletra);
		
		conver = new BeanNumberToLetter();
	}
}
