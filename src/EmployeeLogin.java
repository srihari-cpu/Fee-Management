import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EmployeeLogin {

	JFrame frame;
	JLabel header,segment,user,pass,error;
	JTextField username;
	JPasswordField password;
	JButton submit,clear;
		
	public EmployeeLogin() {
		frame = new JFrame("DevElet Institute - Employee Login");
		segment = new JLabel("DevElet Institute (EMPLOYEE)");
		header =new JLabel("Employee Login");
		
		user = new JLabel("Enter userName :-");
		username = new JTextField(20);
		pass = new JLabel("Enter Password :-");
		password = new JPasswordField(20);
		submit = new JButton("Login");
		clear = new JButton("Clear");
			
		Container container = frame.getContentPane();
		container.setLayout(new FlowLayout());
		container.add(segment);
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
				String un=username.getText();
				char[] ch=password.getPassword();
				String p=new String(ch);
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","123456");
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery("select password from employee where username='"+un+"'");
					while(rs.next()) {
						if(p.equals(rs.getString(1))) {
							frame.dispose();
							new EmployeeHome();
						}
					}
					if(!p.equals(rs.getString(1))) {
						error=new JLabel("Invalid userName or Password");
						container.add(error);
					}
					
				}catch(Exception ex) {
					System.out.println(ex.toString());
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
		new EmployeeLogin();
	}
}
