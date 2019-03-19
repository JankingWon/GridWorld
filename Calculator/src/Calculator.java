import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Calculator extends JFrame{
	public Calculator() {
	StringBuffer operation = new StringBuffer();
	Container c = getContentPane(); //定义一个顶级容器c
        JPanel jp = new JPanel();   //新建JPanel面板--jp
        
        //这是计算器的上面几个显示的东西，最开始使用lable后来改用textfield所以命名有点奇怪
        JTextField lable1 = new JTextField("12");
        JTextField lable2 = new JTextField("");
        lable2.setEditable(false);
        JTextField lable3 = new JTextField("2");
        JTextField lable4 = new JTextField("=");
        lable4.setEditable(false);
        JTextField lable5 = new JTextField(" ");
        lable5.setEditable(false);
        
	//这是下面的几个按钮，控制加减乘除和计算结果
        JButton button1 = new JButton("+");
        JButton button2 = new JButton("-");
        JButton button3 = new JButton("*");
        JButton button4 = new JButton("/");
        JButton button5 = new JButton("OK");
        jp.setLayout(new GridLayout(2,5,5,5));//新建网格布局管理器（行数，列数，组件间的水平垂直间距）
        jp.add(lable1);
        jp.add(lable2);
        jp.add(lable3);
        jp.add(lable4);
        jp.add(lable5);
        jp.add(button1);
        jp.add(button2);
        jp.add(button3);
        jp.add(button4);
        jp.add(button5);
        c.add(jp);//将JPanel面板jp添加到顶级容器c中
        
        setSize(400,200);//窗体大小  
        setTitle("Calculator");//窗体标签  
        setVisible(true);//显示窗体  
        setResizable(false);//锁定窗体 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//这个用于控制点击窗口按钮让程序退出
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
            	lable2.setText("+");
            	operation.append("+");
            }
        });
        
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
            	lable2.setText("-");
            	operation.append("-");
            }
        });
        
        button3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
            	lable2.setText("*");
            	operation.append("*");
            }
        });
        
        button4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
            	lable2.setText("/");
            	operation.append("/");
            }
        });
        //“显示结果”按钮的点击响应事件
        button5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0){
            	if(operation.length() == 0) {
            		//因为是通过operation的最后一个字符判断运符号，所以要排除operation为空的情况
            	}else if(operation.charAt(operation.length() - 1) == '+') {
//处理string到double不成功的异常
            		try {
            			lable5.setText("" + (Double.parseDouble(lable1.getText()) + Double.parseDouble(lable3.getText())));
            		}catch(Exception exception) {
            			System.out.println("Input error! " + exception);
            		}
            	}else if(operation.charAt(operation.length() - 1) == '-'){
//处理string到double不成功的异常
            		try {
            			lable5.setText("" + (Double.parseDouble(lable1.getText()) - Double.parseDouble(lable3.getText())));
            		}catch(Exception exception) {
            			System.out.println("Input erroro! " + exception);
            		}
            	}else if(operation.charAt(operation.length() - 1) == '*'){
//处理string到double不成功的异常
            		try {
            			lable5.setText("" + (Double.parseDouble(lable1.getText()) * Double.parseDouble(lable3.getText())));
            		}catch(Exception exception) {
            			System.out.println("Input error! " + exception);
            		}
            	}else if(operation.charAt(operation.length() - 1) == '/'){
//处理string到double不成功的异常或者除数为零的情况
            		try {
            			lable5.setText("" + (Double.parseDouble(lable1.getText()) / Double.parseDouble(lable3.getText())));
            		}catch(Exception exception) {
            			System.out.println("Divisor can't be zero! || Input invalid!" + exception);
            		}
            		
				}
            }
        });
	}
	//主函数
	public static void main(String[] args) {
//一个用来驱动程序运行的实例
		Calculator instance = new Calculator();
	}
	
	
}
