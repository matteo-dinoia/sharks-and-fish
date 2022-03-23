import java.awt.Dimension;

public interface Repaintable {
	public final static int TEMPO_REPAINT=0;
	public final static int DIM=1000;
	public final static int DIM_CELLA = 5;
	public final static int CELLE = DIM/DIM_CELLA;
	
	public static Dimension getDim() {
		return new Dimension(DIM, DIM);
	}
	
	public void repaintWithData(Valore[][] celle);
}
