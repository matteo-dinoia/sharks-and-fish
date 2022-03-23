
public abstract class Animale extends Valore{
	static final int PESCE=1;
	static final int SQUALO=2;
	boolean isDead=false;
	boolean isReproducing=false;
	Integer direzionato=null;
	Animale dirAnimale=null;
	Cordinate dirCord=null;
	
	public Animale(int value) {
		setValore(value);
	}

	static Animale get(int selezionato) {
		Animale ris=null;
		
		if(selezionato==PESCE) ris=new Pesce();
		if(selezionato==SQUALO) ris=new Squalo();
		
		return ris;
	}

	protected abstract boolean isPossible();

	protected abstract void doThings();

	protected abstract void azzeraRiprod();

	protected abstract void addEnergy();


}
