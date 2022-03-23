
public class Squalo extends Animale{
	private static final int REPROD_COST = 1;
	private static final int REPROD_MINC = 5;
	private static final int ENERGIA_PER_PESCE = 5;
	int energy=REPROD_COST;

	public Squalo() {
		super(SQUALO);
	}

	@Override
	protected boolean isPossible() {
		if(dirAnimale==null)return true; 
		if(dirAnimale.getValore()==PESCE)return true;
		
		return false;
	}

	@Override
	protected void doThings() {
		energy-=1;
		if(energy<=0) isDead=true;
		if(energy>=REPROD_MINC) isReproducing=true;
	}

	@Override
	protected void azzeraRiprod() {
		energy-=REPROD_COST;
		
	}

	@Override
	protected void addEnergy() {
		energy+=ENERGIA_PER_PESCE;
		
	}
}
