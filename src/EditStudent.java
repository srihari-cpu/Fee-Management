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

public class EditStudent {

	JFrame frame;
	JLabel header,segment,name,course,totalfees,amountpaid,amountbalance;
	JTextField n,tf,ap,ab;
	JButton logout,submit,clear;
	JComboBox<String> c;
	String courselist[]= {"Select Course","java","python","c","php","angular",".net"};

	public EditStudent(int id,String na,String co,int ta,int pa,int ba) {
		frame= new JFrame("DevElet institute - Edit Student");
		segment=new JLabel("DevElet Institute");
		header = new JLabel("Edit feees");
		logout=new JButton("Logout");
		
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
		
		n.setText(na);
		c.setSelectedItem(co);
		tf.setText(Integer.toString(ta));;
		ap.setText(Integer.toString(pa));
		ab.setText(Integer.toString(ba));
		
		submit=new JButton("Modify");
		clear=new JButton("Clear");
	
		Container container=frame.getContentPane();
		container.setLayout(new FlowLayout());
		container.add(header);
		container.add(logout);
		container.add(segment);
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
				String name=n.getText();
				String course=(String)c.getSelectedItem();
				int totalamount=Integer.parseInt(tf.getText());
				int paidamt=Integer.parseInt(ap.getText());
				int balamt=Integer.parseInt(ab.getText());
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/fees","root","123456");
					String sql="update student set name=?,course=?,totalfees=?,amountpaid=?,amountbalance=? where id=?";
					PreparedStatement pst=con.prepareStatement(sql);
					pst.setString(1,name);
					pst.setString(2,course);
					pst.setInt(3,totalamount);
					pst.setInt(4,paidamt);
					pst.setInt(5,balamt);
					pst.setInt(6,id);
					pst.executeUpdate();
					
					JDialog d= new JDialog();
					JLabel l=new JLabel("Student with ID : "+id+" is Updated successfully");
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
				n.setText("");
				c.setSelectedIndex(0);
				tf.setText("");
				ap.setText("");
				ab.setText("");
			}
		});
	}
}
