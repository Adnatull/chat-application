package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;

public class Server extends JFrame {

	private JPanel contentPane;
	private JTextField host;
	private JTextField port;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		host = new JTextField();
		host.setFont(new Font("Tahoma", Font.PLAIN, 18));
		host.setText("localhost");
		host.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IP/Host Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		port = new JTextField();
		port.setText("1234");
		port.setFont(new Font("Tahoma", Font.PLAIN, 18));
		port.setColumns(10);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton startServer = new JButton("Start Server");
		startServer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton stopServer = new JButton("Stop Server");
		stopServer.setEnabled(false);
		stopServer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(startServer)
								.addComponent(lblPort, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(port, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
								.addComponent(host, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(215)
							.addComponent(stopServer, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(host, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(port, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPort, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(stopServer, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(startServer))
					.addContainerGap(81, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
