package main;

import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SettingForm extends JFrame{
	public JPanel jPanel=new JPanel();
	
	class PropertyItem extends JPanel{
		JLabel label;
		JTextField textField;
		public PropertyItem(String name,String defaultValue) {
			label=new JLabel();
			label.setText(name);
			textField=new JTextField(10);
			textField.setText("10");
			this.add(label);
			this.add(textField);
			this.setSize(250,35);
		}
		public String getValue() {
			return textField.getText();
		}
	}
	PropertyItem widthPro=new PropertyItem("雷区长度", "10");
	PropertyItem heightPro=new PropertyItem("雷区宽度", "10");
	PropertyItem mineNumPro=new PropertyItem("雷数量", "10");
	public SettingForm(){
		this.setTitle("扫雷");
		this.setBounds(0, 0, 250,350);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		this.setResizable(false);
		
		this.add(widthPro);
		this.add(heightPro);
		this.add(mineNumPro);
		
		JButton okButton=new JButton("开始");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int width=Integer.valueOf(widthPro.getValue());
				int height=Integer.valueOf(heightPro.getValue());
				int mineSum=Integer.valueOf(mineNumPro.getValue());
				MineForm mineForm=new MineForm(width, height, mineSum);
				mineForm.show();
			}
		});
		this.add(okButton);
	}
	
}
