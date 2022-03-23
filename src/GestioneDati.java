import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;


public class GestioneDati implements KeyListener, MouseListener, MouseMotionListener, ActionListener {
	private Repaintable componente;
	private boolean running=true;
	private int selezionato=Animale.SQUALO;
	private Animale mappa[][]=new Animale[Repaintable.CELLE][Repaintable.CELLE];
	private ArrayList<Integer> xA;
	private ArrayList<Integer> yA;
	
	
	public GestioneDati(Repaintable p) {
		this.componente=p;
		xA=new ArrayList<Integer>();
		yA=new ArrayList<Integer>();
		for(int i=0; i<Repaintable.CELLE;i++) {
			xA.add(i);
			yA.add(i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			eseguiCiclo();
			componente.repaintWithData(mappa);
		}
	}	
	
	
	
	private void eseguiCiclo() {
		Collections.shuffle(xA);
		Collections.shuffle(yA);
		
		for(int xi=0; xi<Repaintable.CELLE; xi++) {
			for(int yi=0; yi<Repaintable.CELLE; yi++) {
				int x=xA.get(xi);
				int y=yA.get(yi);
				
				
				Animale a=getCellValue(x, y);
				if(a!=null) {
					
					ottieniDir(x, y,a);
					if(a.direzionato!=null) {
						sposta(x, y, a); //CORD VECCHIE
						x=a.dirCord.x;
						y=a.dirCord.y;
					}
					
					
					a.doThings();
					if(a.isDead) setCellValue(x, y, null);
					else if(a.isReproducing) {
						ottieniDir(x, y, a);
						if(a.direzionato!=null) setCellValue(a.dirCord, Animale.get(a.getValore()));
						a.azzeraRiprod();
					}
				}
			}
		}
		
	}
	private void sposta(int xi, int yi, Animale a) {
		Animale temp=a.dirAnimale;
		Animale me=a;
		if(temp==null) {
			setCellValue(a.dirCord, me);
			setCellValue(xi,yi, temp);
		}
		else if(temp.getValore()==Animale.PESCE&&me.getValore()==Animale.SQUALO) {
			setCellValue(a.dirCord, me);
			setCellValue(xi, yi, null);
			
			me.addEnergy();
		}
		
		
	}

	private void getFromDir(int x, int y, Animale a,  int dir) {
		a.direzionato=dir;
		a.dirCord=getFromDirCoord(x,y, dir);
		a.dirAnimale=getCellValue(a.dirCord.x, a.dirCord.y);
		
	}
	
	private Cordinate getFromDirCoord(int x, int y, int dir) {
		switch(dir%4) {
			case Cordinate.SU:
				return new Cordinate(x, y-1);
			case Cordinate.DX:
				return new Cordinate(x+1, y);
			case Cordinate.GIU:
				return new Cordinate(x, y+1);
			case Cordinate.SX:
				return new Cordinate(x-1, y);
		}
		return null;
		
	}

	private void ottieniDir(int x, int y, Animale a) {
		
		int inizio=((int) (Math.random()*100000))%4;
		for(int i=0; i<4; i++) {
			getFromDir(x, y, a,  inizio+i);
			
			
			if(a.isPossible()) {
				
				return;
			}
		}
		
		
		a.direzionato=null;
		
	}

	private void setCellValue(int x, int y, Animale value) {
		int c=Repaintable.CELLE;
		x=(x%c+c)%c;
		y=(y%c+c)%c;
		 mappa[x][y]=value;
		 componente.repaintWithData(mappa);
	}
	private void setCellValue(Cordinate c, Animale value) {
		 setCellValue(c.x, c.y, value);
	}
	private Animale getCellValue(int x, int y) {
		int c=Repaintable.CELLE;
		x=(x%c+c)%c;
		y=(y%c+c)%c;
		return mappa[x][y];
	}	
	private Animale getCellValue(Cordinate c) {
		return getCellValue(c.x,c.y);
	}	
	
	//TODO GRAFICA NON QUI
	private void inversePointByMouse(int x, int y) {
		//OFFSET BORDO
		x-=10;
		y-=40;
		//CONTROLLO SE IN PANEL
		if(x>=0&&y>=0&&x<Repaintable.DIM&&y<Repaintable.DIM) {
			x/=Repaintable.DIM_CELLA;
			y/=Repaintable.DIM_CELLA;
			
			setCellValue(x,y, Animale.get(selezionato));
			
		}
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_S) {
			running=!running;
			System.out.println(""+running);
		}
		else if(e.getKeyCode()==KeyEvent.VK_Q) {
			selezionato=Animale.SQUALO;
		}
		else if(e.getKeyCode()==KeyEvent.VK_W) {
			selezionato=0;
		}
		else if(e.getKeyCode()==KeyEvent.VK_E) {
			selezionato=Animale.PESCE;
		}
		
		// TODO 
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		inversePointByMouse(e.getX(), e.getY());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		inversePointByMouse(e.getX(), e.getY());
	}
	
	@Override public void mouseMoved(MouseEvent e) {}	
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
	@Override public void keyReleased(KeyEvent e) {}

}
