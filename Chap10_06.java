import java.awt.event.*;
import java.applet.AudioClip;
import java.awt.*;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;
public class Chap10_06 extends JApplet implements ActionListener {
	AudioClip sound1;
	Boolean Trigger=false;
	String i1,i2,i3;
	JTextField display,strI;
	Timer time;
	Button Edit,Clear,P5min,Stop;
	public void paint(Graphics g) {
		super.paint(g);
		Date now = new Date();
		g.setColor(Color.DARK_GRAY);
		g.drawString(now.toString().substring(11,19), 14, 85);
		g.setColor(Color.BLUE);
		g.drawString(now.toString().substring(11,19), 16, 87);
	}
	public void actionPerformed(ActionEvent e) {
		Date now = new Date();
		int H,M,S,SH,SM,SS;
		H=Integer.parseInt(now.toString().substring(11,13));
		M=Integer.parseInt(now.toString().substring(14,16));
		S=Integer.parseInt(now.toString().substring(17,19));
		if (e.getSource()==Edit) {
			i1=JOptionPane.showInputDialog("Enter Time Hour:");
			i2=JOptionPane.showInputDialog("Enter Time Minute:");
			if(i2.length()==1)i2="0"+i2;
			i3=JOptionPane.showInputDialog("Enter Time Second:");
			if(i3.length()==1)i3="0"+i3;
			strI.setText("Set Time "+i1+":"+i2+":"+i3);
			Trigger=true;
		}else if(e.getSource()==Clear){
			i1=null;
			i2=null;
			i3=null;
			strI.setText("");
			Trigger=false;
		}else if(Trigger) {
			SH=Integer.parseInt(i1);
			SM=Integer.parseInt(i2);
			SS=Integer.parseInt(i3);
			if((SH==H)&&(SM==M)&&(SS==S)) {
				sound1.play();
				Triggest();
			}else if(e.getSource()==P5min) {
				sound1.stop();
				SM+=5;
				if(SM>=60) {
					SM-=60;
					SH+=1;
					if(SH>=24)SH=0;
					i1=String.valueOf(SH);
					i2="0"+String.valueOf(SM);
				}else if(SM<=10){
					i2="0"+String.valueOf(SM);
				}else {
					i2=String.valueOf(SM);
				}
				strI.setText("Set Time "+i1+":"+i2+":"+i3);
				JOptionPane.showMessageDialog( null,"Go Next time "+i1+":"+i2+":"+SS,"Go Sleep +5 minute",JOptionPane.INFORMATION_MESSAGE );
				Reset();
			}else if(e.getSource()==Stop) {
				sound1.stop();
				i1=null;
				i2=null;
				i3=null;
				strI.setText("");
				Reset();
				Trigger=false;
			}
		}
		repaint();
	}
	private void Reset() {
		P5min.setEnabled(false);
		Stop.setEnabled(false);
		Edit.setEnabled(true);
		Clear.setEnabled(true);
	}
	private void Triggest() {
		Edit.setEnabled(false);
		Clear.setEnabled(false);
		P5min.setEnabled(true);
		Stop.setEnabled(true);
	}
	public void init() {
		sound1 = getAudioClip( getDocumentBase(),"Daydream_cafe.au" );
		time = new Timer(1000, this);
		time.start();
		setForeground(Color.BLUE);
		setFont(new Font("Century Gothic",Font.BOLD, 40));
		Container c = getContentPane();
		c.setLayout( new FlowLayout());
		Edit = new Button("Edit");
		Edit.addActionListener(this);
		c.add(Edit);
		Clear = new Button("Clear");
		Clear.addActionListener(this);
		c.add(Clear);
		P5min = new Button("P5min");
		P5min.addActionListener(this);
		c.add(P5min);
		Stop = new Button("Stop");
		Stop.addActionListener(this);
		c.add(Stop);
		P5min.setEnabled(false);
		Stop.setEnabled(false);
		strI = new JTextField(15);
		c.add(strI);
		strI.setHorizontalAlignment(JTextField.CENTER);
		strI.setEnabled(false);
	}
}