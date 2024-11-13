import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EmployeeHome {
	
	JFrame frame;
	JLabel header;
	JButton add,delete,update,view,logout;
	
	public EmployeeHome() {
		frame= new JFrame("DevElet institute - Employee Home");
		header = new JLabel("DevElet Institute (EMPLOYEE)");
		logout=new JButton("Logout");
		
		add=new JButton("Add Student");
		delete= new JButton("Delete Student");
		update=new JButton("Edit fees");
		view =new JButton("View Student Record");
		
		Container container=frame.getContentPane();
		container.setLayout(new FlowLayout());
		container.add(header);
		container.add(add);
		container.add(delete);
		container.add(update);
		container.add(view);
		container.add(logout);
		
		frame.setVisible(true);
		frame.setSize(500,500);
		
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EmployeeLogin();
			}
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new AddStudent();	
			}
		});
		
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new DeleteStudent();
			}
		});
		
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new SearchStudent();
			}
		});
		
		view.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ViewStudent();
			}
		});
	}
	
   public static void main(String[] args) {
	   new EmployeeHome();
   }
}
