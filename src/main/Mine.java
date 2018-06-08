package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Mine extends JButton {
	int MINEWIDTH=50;
	//public int id;
	public int x,y;
	public boolean explodable;
	private EventListener actionListener;
	public int surrounding;//周边的雷数
	public boolean clicked=false;//有没有被点击探测过
	
	private int state=0;
	final String[] stateStr={"","!","?"};
	final Color[] stateColor={Color.black,Color.red,Color.BLUE};
	
	
	public Mine(boolean explodable,int x,int y,int width,Container container){
		this.x=x;
		this.y=y;
		this.MINEWIDTH=width;
		this.explodable=explodable;
		this.setBounds(x*MINEWIDTH, y*MINEWIDTH, MINEWIDTH, MINEWIDTH);
		this.setFont(new Font("微软雅黑", 0, width/3));
		
		container.add(this);
//		this.setFont(new Font("微软雅黑",0, 10));
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==e.BUTTON1&&!clicked){
					showMine();
					actionListener.OnMouseClick(Mine.this, e);
					clicked=true;
				}
				else if(e.getButton()==e.BUTTON3&&!clicked){
					state=(state+1)%3;
					updateState();
				}
				
			}
		});
	}
	public void addListener(EventListener al){
		actionListener=al;
	}
	public void showMine(){//显示是否有雷
		this.setForeground(Color.black);
		if(!explodable)this.setText(String.valueOf(surrounding));
		else this.setMineBackground();
	}

	public void setMineBackground(){
		ImageIcon icon=new ImageIcon(Resourse.mineImg);
		this.setIcon(icon);
	}
	public void updateState(){
		this.setText(stateStr[state]);
		this.setForeground(stateColor[state]);
	}
	public void Click(){
		showMine();
		//actionListener.OnMouseClick(Mine.this, null);
		clicked=true;
	}
}
