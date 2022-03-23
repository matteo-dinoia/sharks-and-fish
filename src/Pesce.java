
public class Pesce extends Animale {
	private static final int REPROD_MINT = 8;
	private int time;

	public Pesce() {
		super(PESCE);
	}

	@Override
	protected boolean isPossible() {
		if(dirAnimale==null)return true; 
		
		return false;
	}

	@Override
	protected void doThings() {
		time++;
		if(time>REPROD_MINT) isReproducing=true;
		
	}

	@Override
	protected void azzeraRiprod() {
		time=0;
		
	}

	@Override
	protected void addEnergy() {
		// NULLA
		
	}
	
	
}
