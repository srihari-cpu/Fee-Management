import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddStudent {
	
	JFrame frame;
	JLabel header,segment,id,name,course,totalfees,amountpaid,amountbalance;
	JTextField i,n,tf,ap,ab;
	JButton logout,submit,clear;
	JComboBox<String> c;
	String courselist[]= {"Select Course","java","python","c","php","angular",".net"};

	public AddStudent() {
		frame= new JFrame("DevElet institute - Add Student");
		header = new JLabel("DevElet Institute");
		logout=new JButton("Logout");
		segment=new JLabel("Add New Student");
		
		id=new JLabel("Student ID");
		i=new JTextField(30);
		name=new JLabel("Student Name");
		n=new JTextField(30);
		course = new JLabel("Course");
		c = new JComboBox<String>();
		for(String s:courselist) {
			c.addItem(s);
		}
		
		totalfees=new JLabel("Total Fees");
		tf=new JTextField(20);
		amountpaid=new JLabel("Amount Paid");
		ap=new JTextField(20);
		amountbalance=new JLabel("Amount Balance");
		ab=new JTextField(20);
		submit=new JButton("Add Student");
		clear=new JButton("Clear");
		
		Container container=frame.getContentPane();
		container.setLayout(new FlowLayout());
		container.add(header);
		container.add(logout);
		container.add(segment);
		container.add(id);
		container.add(i);
		container.add(name);
		container.add(n);
		container.add(course);
		container.add(c);
		container.add(totalfees);
		container.add(tf);
		container.add(amountpaid);
		container.add(ap);
		container.add(amountbalance);
		container.add(ab);
		container.add(submit);
		container.add(clear);
		
		frame.setVisible(true);
		frame.setSize(500,500);
		
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EmployeeLogin();
			}
		});
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int id=Integer.parseInt(i.getText());
				String name=n.getText();
				String course=(String)c.getSelectedItem();
				int totalamount=Integer.parseInt(tf.getText());
				int paidamt=Integer.parseInt(ap.getText());
				int balamt=Integer.parseInt(ab.getText());
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","123456");
					String sql="insert into student values(?,?,?,?,?,?)";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setInt(1,id);
					pst.setString(2,name);
					pst.setString(3,course);
					pst.setInt(4,totalamount);
					pst.setInt(5,paidamt);
					pst.setInt(6,balamt);
					pst.executeUpdate();
					
					JDialog d= new JDialog();
					JLabel l=new JLabel("Student with ID : "+id+" is saved successfully");
					JButton ok=new JButton("Ok");
					d.setLayout(new FlowLayout());
					d.add(l);
					d.add(ok);
					d.setVisible(true);
					d.setSize(500,100);
					
					ok.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							d.dispose();
							frame.dispose();
							new EmployeeHome();
						}
					});
				}catch(Exception ex) {
					System.out.println(ex.toString());
				}
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				i.setText("");
				c.setSelectedIndex(0);
				n.setText("");
				tf.setText("");
				ap.setText("");
				ab.setText("");
			}
		});
	}
	
	public static void main(String[] args) {
		new AddStudent();
	}
}
