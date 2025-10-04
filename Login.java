import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
class Login implements MouseListener{
	JPanel p1,p2,p3,p4;
	JTextField username,ip;
	JButton send;
	JFrame fr;
	public static String user,ipAddress;
	public Login(){
		fr=new JFrame();
		fr.setPreferredSize(new Dimension(400,300));
		fr.setLayout(new FlowLayout());
		fr.setLocation(350, 150);
		fr.getContentPane().setBackground(new Color(0,0,0,200));
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialize component 
		JLabel l1=new JLabel("Welcome Login Panel");
		l1.setFont(new Font("sans-serif",Font.BOLD,25));
		l1.setForeground(Color.RED);
		p2=new JPanel(new FlowLayout());
		p1=new JPanel(new FlowLayout());
		p3=new JPanel(new FlowLayout());
		p4=new JPanel(new FlowLayout());
		
		//set background color of panel
		p1.setBackground(new Color(0,0,0,0));
		p2.setBackground(new Color(0,0,0,0));
		p3.setBackground(new Color(0,0,0,0));
		p4.setBackground(new Color(0,0,0,0));
		
		//ip box setup
		ip=new JTextField(20);
		ip.setText("plese Enter Ip Address");
		ip.setPreferredSize(new Dimension(50,50));
		ip.setFont(new Font("calibri",Font.PLAIN,22));
		
		//username setup
		username=new JTextField(20);
		username.setText("please Enter UserName");
		username.setPreferredSize(new Dimension(50,50));
		username.setFont(new Font("calibri",Font.PLAIN,22));
		
		//button setup
		send=new JButton("Send");
		send.setPreferredSize(new Dimension(150,50));
		send.setBackground(Color.DARK_GRAY);
		send.setForeground(Color.WHITE);
		//add values
		p1.add(username);
		p3.add(ip);
		p2.add(l1);
		p4.add(send);
		
		fr.add(p2);
		fr.add(p1);
		fr.add(p3);
		fr.add(send);
		fr.pack();
		
		username.addMouseListener(this);
		ip.addMouseListener(this);
		send.addMouseListener(this);
	}
//	public static void main(String args[]) {
//		new Login();
//	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==username) {
			username.selectAll();
			username.setSelectedTextColor(Color.CYAN);
			username.setSelectionColor(Color.BLACK);
		}
		if(e.getSource()==ip) {
			ip.selectAll();
			ip.setSelectedTextColor(Color.CYAN);
			ip.setSelectionColor(Color.BLACK);
		}
		if(e.getSource()==send) {
			user=username.getText();
			ipAddress=ip.getText();
			fr.dispose();
			new UIdesign().uiFrame();
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==send) {
			send.setBackground(Color.RED);
			send.setForeground(Color.WHITE);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==send) {
			send.setBackground(Color.DARK_GRAY);
			send.setForeground(Color.WHITE);
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
