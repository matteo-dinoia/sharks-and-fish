import javax.swing.JFrame;
import javax.swing.Timer;

public class Finestra extends JFrame  {
	private static final long serialVersionUID = 2L;
	
	
	public static void main(String[] args) {
		Finestra f=new Finestra();
		f.setVisible(true);
	}
	
	public Finestra() {
	
		//pannello e gestione listener
		Pannello p=new Pannello();
		this.add(p);
		
		GestioneDati gd=new GestioneDati(p);
		this.addKeyListener(gd);
		this.addMouseListener(gd);
		this.addMouseMotionListener(gd);
		Timer t=new Timer(Repaintable.TEMPO_REPAINT, gd);
		
		//Chiusura e dimensioni
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setMinimumSize(this.getSize());
		
		
		//START CICLO
		t.start();
	}
}
