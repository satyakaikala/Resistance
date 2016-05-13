package resistance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PathOfLeastResistance {

	public final int NO_ROWS;
	public final int NO_COL;
	public int[][] array;
	public List<Data> data = new ArrayList<Data>();

	PathOfLeastResistance(int[][] array) {
		this.array = array;
		NO_ROWS = this.array.length;
		NO_COL = this.array[0].length;
	}

	public Data getLeastResistancePath() throws Exception {
		int totalValue = 0;
		for (int i = 0; i < NO_ROWS; i++) {
			List<Cell> cellList = new ArrayList<Cell>();
			fun(new Cell(i, Constant.ZERO), totalValue, cellList);
		}
		Collections.sort(data);
		return data.get(0);
	}

	public void fun(Cell cell, int totalValue, List<Cell> cellList) throws Exception {
		cellList.add(cell);
		totalValue += array[cell.getI()][cell.getJ()];
		if (!hasNextColumn(cell.getJ())) {
			populateData(totalValue, cellList);
			return;
		}
		List<Cell> cells = getNextCells(cell);
		for (Cell cell2 : cells) {
			fun(cell2, totalValue, cellList);
			cellList.remove(cell2);
		}
	}

	public void populateData(int totalValue, List<Cell> cellList) {
		String key = "";
		for (Cell cell2 : cellList) {
			key = key + array[cell2.getI()][cell2.getJ()] + Constant.DELIMITER;
		}
		data.add(new Data(key, totalValue));
	}

	public List<Cell> getNextCells(Cell cell) throws Exception {
		List<Cell> list = new ArrayList<Cell>();
		if (NO_COL < cell.getJ() || NO_ROWS < cell.getI()) {
			throw new Exception("The data is incorrect");
		}
		int iValue = cell.getI();
		int jValue = cell.getJ();
		int nextJValue = jValue + 1;
		List<Integer> nextIValues = getNextIValues(iValue);
		nextIValues.forEach((value) -> list.add(new Cell(value, nextJValue)));
		return removeDoubleForCell(list);
	}

	public List<Cell> removeDoubleForCell(List<Cell> list) {
		List<Cell> list1 = new ArrayList<Cell>();
		Set<Cell> hs = new TreeSet<Cell>(new Comparator<Cell>() {
			public int compare(Cell c1, Cell c2) {
				if (c1.getI() == c2.getI() && c1.getJ() == c2.getJ()) {
					return 0;
				}
				return 1;
			}
		});
		hs.addAll(list);
		list1.addAll(hs);
		return list1;
	}

	public void removeDoubleForData() {
		Set<Data> hs = new TreeSet<Data>(new Comparator<Data>() {
			public int compare(Data d1, Data d2) {
				if (d1.getPathValues().equalsIgnoreCase(d2.getPathValues())) {
					return 0;
				}
				return 1;
			}
		});
		hs.addAll(data);
		data.clear();
		data.addAll(hs);
	}

	public List<Integer> getNextIValues(int i) {
		List<Integer> list = new ArrayList<Integer>();
		if ((i - 1) < 0) {
			if ((NO_ROWS - 1) != i) {
				list.add(NO_ROWS - 1);
			}
		} else {
			list.add(i - 1);
		}
		list.add(i);
		if ((NO_ROWS - 1) == i) {
			if (i > 0) {
				list.add(0);
			}
		} else {
			list.add(++i);
		}
		return list;
	}

	public Boolean hasNextColumn(int j) {
		return NO_COL > (j + 1);
	}

}
