package mytown.entities;

import java.util.ArrayList;
import java.util.List;

import mytown.interfaces.ITownFlag;
import mytown.interfaces.ITownPlot;


public class TownPlot implements ITownPlot {
	
	protected int x1, z1, y1, x2, z2, y2;
	protected int dim;
	
	protected String key;
	
	protected Town town;
	protected Resident owner;
	
	protected int chunkX1, chunkZ1, chunkX2, chunkZ2;
	
	protected List<TownBlock> townBlocks;
	
	protected List<ITownFlag> plotFlags; 
	
	
	public TownPlot(int dim, int x1, int y1, int z1, int x2, int y2, int z2, Town town, Resident owner)
	{
		if(x1 > x2) {
			int aux = x2;
			x2 = x1;
			x1 = aux;
		}
		
		if(z1 > z2) {
			int aux = z2;
			z2 = z1;
			z1 = aux;
		}
		
		if(y1 > y2) {
			int aux = y2;
			y2 = y1;
			y2 = aux;
		}
		
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
		this.dim = dim;
		
		this.town = town;
		this.owner = owner;
		
		this.initializeFlags();
		this.updateKey();
	}
	
	/**
	 * Gets the unique key for the plot
	 * 
	 * @return
	 */
	@Override
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Updates the key. Only called in the constructor and when updating in database. DO NOT CALL ELSEWHERE!
	 */
	@Override
	public void updateKey() {
		key = String.format("%s;%s;%s;%s;%s;%s;%s", dim, x1, y1, z1, x2, y2, z2);
	}
	
	private void initializeFlags() {
		if(town.getFlags() != null) // just in case ;)
			this.plotFlags = town.getFlags();
		else
			this.plotFlags = new ArrayList<ITownFlag>();
		
	}
	
	//temp
	public void verify()
	{
		if(x1 > x2)
		{
			int aux = x2;
			x2 = x1;
			x1 = aux;
		}
		if(z1 > z2)
		{
			int aux = z2;
			z2 = z1;
			z1 = aux;
		}
		
		
		int chunkX = x1 / 16;
		int chunkZ = z1 / 16;
		
		for(int X = chunkX; X <= x2/16; X++)
			for(int Z = chunkZ; Z <= z2/16; Z++)
			{
				//if(!DatasourceProxy.getDatasource().hasTownBlock(String.format(TownBlock.keyFormat, chunkX, chunkZ, dim)))
					
				
			}
	}

	@Override
	public Town getTown() {
		return this.town;
	}

	@Override
	public Resident getOwner() {
		return this.owner;
	}

	@Override
	public int getStartX() {
		return x1;
	}

	@Override
	public int getStartZ() {
		return z1;
	}

	@Override
	public int getStartY() {
		return y1;
	}

	@Override
	public int getEndX() {
		return x2;
	}

	@Override
	public int getEndZ() {
		return z2;
	}

	@Override
	public int getEndY() {
		return y2;
	}

	@Override
	public int getStartChunkX() {
		return x1 / 16;
	}

	@Override
	public int getStartChunkZ() {
		return z1 / 16;
	}

	@Override
	public int getEndChunkX() {
		return x1 / 16;
	}

	@Override
	public int getEndChunkZ() {
		return z1 / 16;
	}

	@Override
	public List<ITownFlag> getFlags() {
		return this.plotFlags;
	}

	@Override
	public List<TownBlock> getEncompasingBlocks() {
		return this.townBlocks;
	}
	
	@Override
	public boolean isBlockInsidePlot(int x, int y, int z) {
		return x2 >= x && x <= x1 && y2 >= y && y <= y1 && z2 >= z && z <= z1;
	}
	
	@Override
	public ITownFlag getFlag(String flagName) {
		for(ITownFlag flag : plotFlags) {
			if(flag.getName().equals(flagName)) {
				return flag;
			}
		}
		return null;
	}
	
	@Override
	public int getDim() {
		return this.dim;
	}
}
