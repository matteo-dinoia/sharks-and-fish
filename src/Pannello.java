import java.awt.*;
import javax.swing.*;

public class Pannello extends JPanel implements Repaintable {
	private static final long serialVersionUID = 3L;
	private Valore[][] valoriAnimali;

	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Repaintable.DIM, Repaintable.DIM);
		
		
		
		if(valoriAnimali!=null) {
			int dimC=Repaintable.DIM_CELLA;
			
			g.setColor(Color.white);
			for(int x=0; x<Repaintable.CELLE; x++) {
				for(int y=0; y<Repaintable.CELLE; y++) {
					g.setColor(getColorBasedOnType(x, y));
					g.fillRect(x*dimC, y*dimC, dimC, dimC);	
				}
			}
		}
	}

	private Color getColorBasedOnType(int x, int y) {
		Color ris=Color.black;
		Valore v=new Pesce();
		try {
			v=this.valoriAnimali[x][y];
			if(v.getValore()==Animale.PESCE)ris=Color.green;
			else if(v.getValore()==Animale.SQUALO)ris=Color.red;
		}catch(Exception e) {	
		}
			

		
		return ris;
	}

	@Override
	public void repaintWithData(Valore[][] v) {
		valoriAnimali=v;
		repaint();
	}
	
	@Override
	public Dimension getSize() {
		return Repaintable.getDim();
	}
	@Override
	public Dimension getPreferredSize() {
		return Repaintable.getDim();
	}
	@Override
	public Dimension getMinimumSize() {
		return Repaintable.getDim();
	}

}
