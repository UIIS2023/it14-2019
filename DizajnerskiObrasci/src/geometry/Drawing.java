package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Drawing extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Drawing");
		frame.setSize(800, 600);
		Drawing drawing = new Drawing();
		frame.getContentPane().add(drawing);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public  void paint(Graphics g){
		
		Point p=new Point(50,50,true, Color.BLUE);
		//p.draw(g);
		
		Line l=new Line(new Point(70,50),new Point(70,140),true,Color.RED);
		//l.draw(g);
		
		Circle c=new Circle(new Point(150,200),50,true,Color.LIGHT_GRAY,Color.PINK);
		//c.draw(g);
		
		Rectangle r= new Rectangle(new Point(230,100),60,120,true,Color.RED,Color.MAGENTA);
		//r.draw(g);
		
		Donut d= new Donut(new Point(300,330),70,30,true,Color.BLUE,Color.YELLOW);
		//d.draw(g);
		
		
		ArrayList<Shape> shapes= new ArrayList<Shape>();
		shapes.add(p);
		shapes.add(l);
		shapes.add(c);
		shapes.add(r);
		shapes.add(d);
		
		
		
		
		//iscrtati 3. element iz liste shapes
		//shapes.get(2).draw(g);
		
		//iscrtati poslednji element iz liste shapes
		//shapes.get(shapes.size()-1).draw(g);
		
		 //kreirati i dodati u listu, a potom i iscrtati novu liniju l1 tako da ona bude
			// 4. element u listi
		
		Line l1= new Line( new Point(450,200),new Point(550,200),true);
		shapes.add(3, l1);

	     for (Shape s: shapes){
		s.draw(g);
	         }
	     Iterator<Shape> it1=shapes.iterator();
			while(it1.hasNext()){
				System.out.println("Selected: "+it1.next().isSelected());
			}
	
	}
	
}

