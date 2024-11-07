import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLogin {
	
	JFrame frame;
	JLabel header,user,pass,error;
	JTextField username;
	JPasswordField password;
	JButton submit,clear;
	
	public AdminLogin() {
		frame = new JFrame("DevElet Institute - Admin Login");
		header = new JLabel("DevElet Institute (ADMIN)");
		user = new JLabel("Enter userName :-");
		username = new JTextField(20);
		pass = new JLabel("Enter Password :-");
		password = new JPasswordField(20);
		submit = new JButton("Login");
		clear = new JButton("Clear");
		
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());
		container.add(header);
		container.add(user);
		container.add(username);
		container.add(pass);
		container.add(password);
		container.add(submit);
		container.add(clear);
		
		frame.setVisible(true);
		frame.setSize(500,500);
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String u=username.getText();
				char[] dp=password.getPassword();
				String p= new String(dp);
				
				if(u.equals("admin") && p.equals("admin@123")) {
					frame.dispose();
					new AdminHome();
				}else {
					error = new JLabel("Invalid userName or password");
					container.add(error);
				}
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});	
	}
	
	public static void main(String[] args) {
		new AdminLogin();
	}
}
