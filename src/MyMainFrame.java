 /*
 * JAVAС��Ϸ��ƴͼ �����ĵ�һ��С��Ϸ
 * Cell���Ǽ̳еİ�ť�࣬��������Ӧͼ�Σ��γɷ���
 *MyCanvas��һ����壬����Cell��Ķ��󣨷��񣩣������������еĺ���
 *
 *2007��10��18�գ�19��
 *liumingtao1023@163.com
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MyMainFrame extends JFrame implements ActionListener {
	MyCanvas myCanvas;
	JPanel panelNorth,panelPreview;//�����Ϸ�����壬��Ԥ����������
	Button start,preview,set;//���忪ʼ��Ԥ�����趨��ť
	Container container;//�������õ��������
	
	public MyMainFrame() {//��ʹ��
		container=this.getContentPane();
		start=new Button("��ʼ");
		start.addActionListener(this);
		preview=new Button("Ԥ��");
		preview.addActionListener(this);
		set = new Button("����");
		set.addActionListener(this);
		panelPreview=new JPanel();
		panelPreview.setLayout(null);
		Icon icon=new ImageIcon("pictrue/pic_"+MyCanvas.pictureID+".jpg");
		JLabel label=new JLabel(icon);
		label.setBounds(0,0,300,300);
		panelPreview.add(label);
		
		
		panelNorth=new JPanel();
		panelNorth.setBackground(Color.red);
		panelNorth.add(start);
		panelNorth.add(preview);
		panelNorth.add(set);
		myCanvas=new MyCanvas();
		container.add(myCanvas,BorderLayout.CENTER);
		container.add(panelNorth,BorderLayout.NORTH);
		this.setTitle("ƴͼС��Ϸ-��");
		this.setLocation(300,200);
		this.setSize(308,365);
		this.setResizable(false);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(3);
	}
	public static void main(String[] args) {
		// TODO �Զ����ɷ������
		new MyMainFrame();
		
	}
	public void actionPerformed(ActionEvent arg0) {//��������ť�¼��Ĵ���
		// TODO �Զ����ɷ������
		Button button=(Button)arg0.getSource();
		if(button==start){
			myCanvas.Start();
			
		}else if(button==preview){
			if(button.getLabel()=="Ԥ��"){
				container.remove(myCanvas);
				container.add(panelPreview);
				panelPreview.updateUI();
				container.repaint();
				
				button.setLabel("����");
			}else{
				container.remove(panelPreview);
				container.add(myCanvas);
				container.repaint();
				button.setLabel("Ԥ��");
			}
		}else if(button==set){//�޸���ѡͼƬ
			Choice pic = new Choice();
			pic.add("Сè");
			pic.add("С��");
			pic.add("��");
			pic.add("QQ");
			pic.add("��ͨ");
			pic.add("��");

			int i=JOptionPane.showConfirmDialog(this,	pic, "ѡ��ͼƬ", JOptionPane.OK_CANCEL_OPTION);
			if(i==JOptionPane.YES_OPTION){
				MyCanvas.pictureID=pic.getSelectedIndex()+1;
				myCanvas.reLoadPictrue();
				Icon icon=new ImageIcon("pictrue/pic_"+MyCanvas.pictureID+".jpg");
				JLabel label=new JLabel(icon);
				label.setBounds(0,0,300,300);
				panelPreview.removeAll();
				panelPreview.add(label);
				panelPreview.repaint();
			}
		}
	}

}
