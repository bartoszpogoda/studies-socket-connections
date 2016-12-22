package view.impl;

import javax.swing.JFrame;

import view.MainView;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.MainViewController;
import controller.SensorOutputController;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Font;

public class MainViewImpl extends JFrame implements MainView {
	private JTextField tfHost;
	private JTextField tfPort;
	private JTextField tfMessage;
	
	private JButton btnSendMessage;
	private JTextArea textMonitor;
	private JPanel panelBlink;
	
	private boolean blinkThreadActive = false;

	public MainViewImpl() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panelControl = new JPanel();
		panelControl
				.setBorder(new TitledBorder(null, "Sterowanie", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelControl.setBounds(12, 13, 300, 204);
		getContentPane().add(panelControl);
		panelControl.setLayout(null);

		JPanel panelConnection = new JPanel();
		panelConnection.setBackground(new Color(245, 255, 250));
		panelConnection.setBounds(12, 30, 276, 74);
		panelControl.add(panelConnection);
		panelConnection.setLayout(null);

		JLabel lblHost = new JLabel("host");
		lblHost.setBounds(12, 29, 36, 16);
		panelConnection.add(lblHost);

		tfHost = new JTextField();
		tfHost.setBounds(48, 26, 97, 22);
		panelConnection.add(tfHost);
		tfHost.setColumns(10);

		JLabel lblPort = new JLabel("port");
		lblPort.setBounds(167, 29, 56, 16);
		panelConnection.add(lblPort);

		tfPort = new JTextField();
		tfPort.setBounds(196, 26, 56, 22);
		panelConnection.add(tfPort);
		tfPort.setColumns(10);

		tfMessage = new JTextField();
		tfMessage.setBounds(12, 129, 276, 22);
		panelControl.add(tfMessage);
		tfMessage.setColumns(10);

		btnSendMessage = new JButton("Wy\u015Blij polecenie");
		btnSendMessage.setBounds(12, 164, 276, 25);
		panelControl.add(btnSendMessage);

		JPanel panelMonitor = new JPanel();
		panelMonitor.setForeground(new Color(255, 255, 255));
		panelMonitor.setBackground(new Color(0, 0, 0));
		panelMonitor.setBounds(324, 23, 278, 389);
		getContentPane().add(panelMonitor);
		panelMonitor.setLayout(null);

		textMonitor = new JTextArea();
		textMonitor.setFont(new Font("Monospaced", Font.BOLD, 24));
		textMonitor.setForeground(new Color(255, 255, 255));
		textMonitor.setBackground(new Color(0, 0, 255));
		textMonitor.setBounds(12, 13, 254, 333);
		panelMonitor.add(textMonitor);
		
		panelBlink = new JPanel();
		panelBlink.setBounds(12, 359, 22, 20);
		panelBlink.setBackground(Color.WHITE);
		panelMonitor.add(panelBlink);
		
		setSize(633,473);
	}

	
	@Override
	public void setOutput(String[] data) {
		textMonitor.setText("");
		
		for(String strData : data){
			textMonitor.append(strData + "\n");
		}

		panelBlink.setBackground(Color.GREEN);
		
		if(!blinkThreadActive){
			Thread blinkThread = new Thread(){
				@Override
				public void run() {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					panelBlink.setBackground(Color.WHITE);
					blinkThreadActive = false;
				}
			};
			blinkThread.start();
		}
	
	
	}

	@Override
	public void setMainViewController(MainViewController mainViewController) {
		btnSendMessage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainViewController.sendMessage(tfHost.getText(), tfPort.getText(), tfMessage.getText());
				
			}
		});
		
	}
	
	@Override
	public void setSensorOutputController(SensorOutputController sensorOutputController) {
		//terminate threads on window close
				this.addWindowListener(new WindowListener(){
					@Override
					public void windowActivated(WindowEvent arg0) {}
					
					@Override
					public void windowClosed(WindowEvent arg0) { 
						sensorOutputController.stop();
					}
					
					@Override
					public void windowClosing(WindowEvent arg0) {}

					@Override
					public void windowDeactivated(WindowEvent arg0) {}

					@Override
					public void windowDeiconified(WindowEvent arg0) {}

					@Override
					public void windowIconified(WindowEvent arg0) {	}

					@Override
					public void windowOpened(WindowEvent arg0) { }
					
				});
	}

	@Override
	public void reportError(String message) {
		JOptionPane.showMessageDialog(this, message, "B³¹d", JOptionPane.ERROR_MESSAGE);
	}
}
