package main;

import java.awt.Button;
import java.awt.Container;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MineForm extends JFrame {
	public int WIDTH=10;
	public int HEIGHT=10;
	public int MINESUM=10;
	int seekCount=0;
	private Mine[][] mines;
	
	EventListener eventListener=new EventListener() {
		@Override 
		public void OnMouseClick(Mine m, java.awt.event.MouseEvent e) {
			/*System.out.println(m.x+m.y*10);
			if(e.getButton()==e.BUTTON3){
				System.out.println("Right");
			}
			*/
			if(m.surrounding==0&&!m.explodable){
				autoSeek(m.x, m.y);
			}
			else if(!m.explodable){
				seekCount++;
			}
			else if(m.explodable){
				JOptionPane.showMessageDialog(null,  "炸了","提示", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			//System.out.println(seekCount);
			if(seekCount==WIDTH*HEIGHT-MINESUM){
				JOptionPane.showMessageDialog(null,  "赢了","提示", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		};
	};
	
	private void autoSeek(int x,int y){
		if(!mines[y][x].clicked){
			//System.out.println("Seek");
			mines[y][x].Click();
			seekCount++;
			if(mines[y][x].surrounding==0){
				for(int j=-1;j<=1;j++){
					for(int i=-1;i<=1;i++){
						if(j+y>=0&&j+y<HEIGHT&&x+i>=0&&x+i<WIDTH){
							autoSeek(x+i, y+j);
						}
					}
				}
			}
		}
	}
	public MineForm(int width,int height,int mineSum){
		WIDTH=width;
		HEIGHT=height;
		MINESUM=mineSum;
		mines=new Mine[HEIGHT][WIDTH];
		Resourse.load();
		this.setTitle("扫雷");
		layerInit();
	}
	private void setMine(){
		int mineCount=0;
		Random random=new Random();
		while(mineCount<MINESUM){
			int x=random.nextInt(WIDTH);
			int y=random.nextInt(HEIGHT);
			if(!mines[x][y].explodable){
				mineCount++;
				mines[x][y].explodable=true;
			}
		}
		
		for(int j=0;j<HEIGHT;j++){
			for(int i=0;i<WIDTH;i++){
				int count=0;
				for(int x=-1;x<=1;x++){
					for(int y=-1;y<=1;y++){
						if(j+y>=0&&j+y<HEIGHT&&x+i>=0&&x+i<WIDTH){
							if(mines[j+y][x+i].explodable)
							count++;
						}
					}
					mines[j][i].surrounding=count;	
				}
			}
		}
	}
	private void layerInit(){
		
		int maxWindowWidth=1000;
		int maxWindowHeight=600;
		int mineWidth=50;
		if(mineWidth*WIDTH>maxWindowWidth){
			mineWidth=maxWindowWidth/WIDTH;
		}
		if(mineWidth*HEIGHT>maxWindowHeight){
			mineWidth=maxWindowHeight/HEIGHT;
		}
		Container container=new Container();
		this.setBounds(0, 0,mineWidth*WIDTH, mineWidth*HEIGHT+28);
		//container.add(button1);
		for(int j=0;j<HEIGHT;j++){
			for(int i=0;i<WIDTH;i++){
				mines[j][i]=new Mine(false, i, j,mineWidth, container);
				mines[j][i].addListener(eventListener);
				
			}
		}
		setMine();
		Resourse.resizeMineImg(mineWidth, mineWidth);
		this.setContentPane(container);
		
		
	}
}
