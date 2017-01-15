package view.impl;

import javax.swing.JFrame;

import view.MainView;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.MainViewController;
import controller.SensorListener;

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
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class MainViewImpl extends JFrame implements MainView {
	private JTextField tfHost;
	private JTextField tfPort;
	private JTextField tfMessage;
	
	private JButton btnSendMessage;
	private JTextArea textMonitor;
	private JPanel panelBlink;
	
	private JLabel lblMonitorPort;
	
	private boolean blinkThreadActive = false;

	public MainViewImpl() {
		getContentPane().setBackground(UIManager.getColor("Button.background"));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JPanel panelControl = new JPanel();
		panelControl.setBackground(UIManager.getColor("Button.background"));
		panelControl
				.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Panel Sterowania", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelControl.setBounds(29, 13, 300, 201);
		getContentPane().add(panelControl);
		panelControl.setLayout(null);

		JPanel panelConnection = new JPanel();
		panelConnection.setBackground(UIManager.getColor("Button.background"));
		panelConnection.setBounds(12, 30, 276, 74);
		panelControl.add(panelConnection);
		panelConnection.setLayout(null);

		JLabel lblHost = new JLabel("host");
		lblHost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHost.setBounds(12, 29, 36, 16);
		panelConnection.add(lblHost);

		tfHost = new JTextField();
		tfHost.setBounds(48, 26, 97, 22);
		panelConnection.add(tfHost);
		tfHost.setColumns(10);

		JLabel lblPort = new JLabel("port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPort.setBounds(167, 29, 56, 16);
		panelConnection.add(lblPort);

		tfPort = new JTextField();
		tfPort.setBounds(208, 27, 56, 22);
		panelConnection.add(tfPort);
		tfPort.setColumns(10);

		tfMessage = new JTextField();
		tfMessage.setBounds(12, 129, 276, 22);
		panelControl.add(tfMessage);
		tfMessage.setColumns(10);

		btnSendMessage = new JButton("Wy\u015Blij polecenie");
		btnSendMessage.setBackground(new Color(255, 250, 205));
		btnSendMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSendMessage.setBounds(12, 164, 276, 25);
		panelControl.add(btnSendMessage);

		JPanel panelMonitor = new JPanel();
		panelMonitor.setForeground(new Color(255, 255, 255));
		panelMonitor.setBackground(new Color(0, 0, 0));
		panelMonitor.setBounds(348, 13, 434, 237);
		getContentPane().add(panelMonitor);
		panelMonitor.setLayout(null);

		textMonitor = new JTextArea();
		textMonitor.setEditable(false);
		textMonitor.setFont(new Font("Monospaced", Font.BOLD, 24));
		textMonitor.setForeground(new Color(255, 255, 255));
		textMonitor.setBackground(new Color(0, 0, 255));
		textMonitor.setBounds(12, 13, 410, 202);
		panelMonitor.add(textMonitor);
		
		panelBlink = new JPanel();
		panelBlink.setBounds(365, 221, 22, 10);
		panelBlink.setBackground(Color.WHITE);
		panelMonitor.add(panelBlink);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Informacje", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(29, 227, 300, 59);
		getContentPane().add(panel);
		
		JLabel lblPortNaKtrym = new JLabel("Port:");
		lblPortNaKtrym.setBounds(91, 18, 46, 28);
		panel.add(lblPortNaKtrym);
		lblPortNaKtrym.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lblMonitorPort = new JLabel("MONITOR_PORT");
		lblMonitorPort.setBounds(149, 15, 171, 35);
		panel.add(lblMonitorPort);
		lblMonitorPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(542, 243, 35, 28);
		getContentPane().add(panel_1);
		
		setSize(801,346);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	
	@Override
	public void setOutput(String[] data) {
		textMonitor.setText("");
		
		for(String strData : data){
			textMonitor.append("   " + strData + "\n");
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
	public void setSensorListener(SensorListener sensorOutputController) {
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


	@Override
	public void setPort(int port) {
		lblMonitorPort.setText(Integer.toString(port));
	}
}
