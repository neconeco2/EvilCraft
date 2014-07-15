package evilcraft.api.algorithms;


/**
 * Location class.
 * @author rubensworks
 *
 */
public class Location implements ILocation {

    private int dimensions;
    private int[] coordinates;
    
    /**
     * Make a new instance.
     * @param coordinates The coordinates.
     */
    public Location(int[] coordinates) {
        this.dimensions = coordinates.length;
        this.coordinates = coordinates;
    }
    
    @Override
    public int getDimensions() {
        return this.dimensions;
    }

    @Override
    public int[] getCoordinates() {
        return coordinates;
    }

    @Override
    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }
    
    @Override
	public Size getDifference(ILocation location) {
    	if(location.getDimensions() != this.getDimensions()) {
    		throw new IllegalArgumentException(
    				"The dimensions of this and the given dimension are not the same.");
    	}
    	int[] diffs = new int[getDimensions()];
    	for(int i = 0; i < diffs.length; i++) {
    		diffs[i] = Math.abs(location.getCoordinates()[i] - this.getCoordinates()[i]);
    	}
		return new Size(diffs);
	}
    
    @Override
    public int getDistance(ILocation location) {
    	Size difference = getDifference(location);
    	int distance = 0;
    	for(int i = 0; i < difference.getDimensions(); i++) {
    		distance += difference.getSizes()[i];
    	}
    	return (int) Math.pow(distance, 1 / difference.getDimensions());
    }

    @Override
    public ILocation copy() {
        return new Location(coordinates.clone());
    }
    
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("[");
    	for(int coord : getCoordinates()) {
    		builder.append(coord);
    		builder.append(';');
    	}
    	builder.append("]");
    	return builder.toString();
    }

    @Override
    public boolean equals(Object object) {
    	if(object instanceof ILocation && object != null) {
    		return ((ILocation) object).getCoordinates().equals(getCoordinates());
    	}
    	return false;
    }

}