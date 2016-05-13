package resistance;
/*
 * This call is used to handle matrix cells
 * */
public class Cell {
	private int i;
	private int j;

	public Cell(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public String toString() {
		return "Cell [i=" + i + ", j=" + j + "]";
	}

	@Override
	public boolean equals(Object o) {
		Cell ce = (Cell) o;
		return this.i == ce.getI() && this.j == ce.getJ();
	}
}
