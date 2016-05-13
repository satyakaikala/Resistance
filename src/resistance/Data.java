package resistance;

public class Data implements Comparable<Data> {
	private String pathValues;
	private int totalValue;

	public Data(String pathValues, int totalValue) {
		super();
		this.pathValues = pathValues.substring(Constant.ZERO, pathValues.length() - 1);
		this.totalValue = totalValue;
	}

	public String getPathValues() {
		return pathValues;
	}

	public void setPathValues(String pathValues) {
		this.pathValues = pathValues;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	@Override
	public String toString() {
		return "Data [pathValues=" + pathValues + ", totalValue=" + totalValue + "]";
	}

	@Override
	public int compareTo(Data d) {
		if (this.totalValue == d.getTotalValue()) {
			return 0;
		} else if (this.totalValue > d.getTotalValue()) {
			return 1;
		} else {
			return -1;
		}
	}

}
