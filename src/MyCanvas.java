import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MyCanvas extends JPanel implements MouseListener {
	boolean hasAddActionListener=false;//���÷���Ķ����������ı�־λ��TRUEΪ�Ѿ�����϶����¼���FALSE����δ��Ӷ����¼�
	Cell cell[];//���巽��
	Rectangle cellNull;//����շ�������
	public static int pictureID=1;//��ǰѡ���ͼƬ����
	public MyCanvas() {
		this.setLayout(null);
		this.setSize(400,400);
		cellNull=new Rectangle(200,200,100,100);//�շ��������ڵ�����ÿ����
		cell=new Cell[9];
		Icon icon;
		for (int i = 0; i < 3; i++) {//Ϊ9���������ͼƬ������ʹ�����꣬�γ���������
			for(int j=0;j<3;j++){
				icon=new ImageIcon("pictrue/pic_"+pictureID+"_"+(i*3+j+1)+".jpg");
				cell[i*3+j]=new Cell(icon);
				cell[i*3+j].setLocation(j*100,i*100);
				this.add(cell[i*3+j]);
			}
		}
		this.remove(cell[8]);//�Ƴ����һ������ķ���
	}
	
	public void reLoadPictrue(){//��ѡ������ͼ�ν���ƴͼʱ�������¼�����ͼƬ
		Icon icon;
		for (int i = 0; i < 3; i++) {
			for(int j=0;j<3;j++){
				icon=new ImageIcon("pictrue/pic_"+pictureID+"_"+(i*3+j+1)+".jpg");
				cell[i*3+j].setIcon(icon);
			}
		}
	}
	public boolean isFinish(){//�ж��Ƿ�ƴ�ϳɹ�
		for(int i=0;i<8;i++){
			int x=cell[i].getBounds().x;
			int y=cell[i].getBounds().y;
			if(y/100*3+x/100!=i)
				return false;
		}
		return true;
	}
	
	public void Start(){//�Է�������������У�����˳��
		
		while(cell[0].getBounds().x<=100&&cell[0].getBounds().y<=100){//����һ����������ϽǽϽ�ʱ
			int x=cellNull.getBounds().x;
			int y=cellNull.getBounds().y;
			int direction=(int)(Math.random()*4);//����0-4����Ӧ�շ�������������ƶ�
			if(direction==0){//�շ������ƶ�������෽�񻥻�λ�ã���෽�����ƶ�
				x-=100;
				if(test(x,y)){
					for(int j=0;j<8;j++){
						if((cell[j].getBounds().x==x)&&(cell[j].getBounds().y==y)){//����Ѱ�����İ�ť
							cell[j].move("RIGHT",100);
							cellNull.setLocation(x,y);
							break;//�ҵ�������forѭ��
						}
					}
				}
			}else if(direction==1){//RIGHT
				x+=100;
				if(test(x,y)){
					for(int j=0;j<8;j++){
						if((cell[j].getBounds().x==x)&&(cell[j].getBounds().y==y)){
							cell[j].move("LEFT",100);
							cellNull.setLocation(x,y);
							break;
						}
					}
				}
			}else if(direction==2){//UP
				y-=100;
				if(test(x,y)){
					for(int j=0;j<8;j++){
						if((cell[j].getBounds().x==x)&&(cell[j].getBounds().y==y)){
							cell[j].move("DOWN",100);
							cellNull.setLocation(x,y);
							break;
						}
					}
				}
			}else{//DOWN
				y+=100;
				if(test(x,y)){
					for(int j=0;j<8;j++){
						if((cell[j].getBounds().x==x)&&(cell[j].getBounds().y==y)){
							cell[j].move("UP",100);
							cellNull.setLocation(x,y);
							break;
						}
					}
				}
			}
				
		}
		
			if(!hasAddActionListener)//�����δ��Ӷ����¼��������
				for(int i=0;i<8;i++)//Ϊ�ڸ�������Ӷ����¼�������������ť�����ƶ���
					cell[i].addMouseListener(this);
		hasAddActionListener=true;
	}
	private boolean test(int x,int y){
		if((x>=0&&x<=200)||(y>=0&&y<=200))
			return true;
		else
			return false;
	}
//	public void paint(Graphics g){
//		
//		for(int i=0;i<=300;i+=100)
//			g.drawLine(0, i, 300, i);
//		for(int i=0;i<=300;i+=100)
//			g.drawLine(i, 0, i, 300);
//		for(int i=0;i<8;i++)
//			cell[i].repaint();
//	}

	public void mouseClicked(MouseEvent arg0) {	}
	public void mouseEntered(MouseEvent arg0) {	}
	public void mouseExited(MouseEvent arg0) 	{	}
	public void mouseReleased(MouseEvent arg0) {	}
	public void mousePressed(MouseEvent arg0) {//���������¼�����Ϊ�õ���MyCanvas�е�һЩ���������û����Cell���д�������¼�
		Cell button=(Cell)arg0.getSource();
		int x1=button.getBounds().x;//�õ����������������
		int y1=button.getBounds().y;
		
		int x2=cellNull.getBounds().x;//�õ��շ��������
		int y2=cellNull.getBounds().y;
		
		if(x1==x2&&y1-y2==100)//���бȽϣ����������������н���
			button.move("UP",100);
		else if(x1==x2&&y1-y2==-100)
			button.move("DOWN",100);
		else if(x1-x2==100&y1==y2)
			button.move("LEFT",100);
		else if(x1-x2==-100&&y1==y2)
			button.move("RIGHT",100);
		else
			return;//������Ͳ������κδ���
		
		cellNull.setLocation(x1,y1);
		this.repaint();
		if(this.isFinish()){//�����Ƿ���ɵ��ж�
			JOptionPane.showMessageDialog(this,"��ϲ�����ƴͼ,���ͣ�");
			for(int i=0;i<8;i++)
				cell[i].removeMouseListener(this);//�������ɣ���������¼�����굥��������������
			hasAddActionListener=false;
		}
	}

	
}
