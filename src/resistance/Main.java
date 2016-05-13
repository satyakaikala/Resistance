package resistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int[][] array01;
		int[][] array02;
		int[][] array03;
		List<Data> datas = new ArrayList<Data>();
		List<String> lines = new ArrayList<String>();
		try {
			array01 = getArrayFromFile("file01.txt");
			array02 = getArrayFromFile("file02.txt");
			array03 = getArrayFromFile("file03.txt");
			PathOfLeastResistance p01 = new PathOfLeastResistance(array01);
			PathOfLeastResistance p02 = new PathOfLeastResistance(array02);
			PathOfLeastResistance p03 = new PathOfLeastResistance(array03);
			datas.add(p01.getLeastResistancePath());
			datas.add(p02.getLeastResistancePath());
			datas.add(p03.getLeastResistancePath());
			datas.forEach((d) -> { // lambda function
				if (d.getTotalValue() < 50) {
					lines.add("YES");
					lines.add(String.valueOf(d.getTotalValue()));
					lines.add(d.getPathValues());
					lines.add("=============================");
				} else {
					String[] p = d.getPathValues().split(Constant.DELIMITER);
					String path = p[0];
					int total = Integer.parseInt(p[0]);
					for (int i = 1; i < p.length; i++) {
						if ((total + Integer.parseInt(p[i])) > 50) {
							break;
						}
						path += Constant.DELIMITER + p[i];
						total += Integer.parseInt(p[i]);
					}
					lines.add("No");
					lines.add(String.valueOf(total));
					lines.add(path);
					lines.add("=============================");
				}
			});
			Path file = Paths.get("output.txt");
			Files.write(file, lines, Charset.forName("UTF-8"));
			System.out.println("The program is Completed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int[][] getArrayFromFile(String fileName) throws FileNotFoundException {
		int[][] array = null;
		List<String> list = new ArrayList<String>();
		Scanner input = new Scanner(new File(fileName));
		while (input.hasNextLine()) {
			list.add(input.nextLine());
		}
		String[] noColumn = list.get(0).split(Constant.DELIMITER);
		array = new int[list.size()][noColumn.length];

		for (int i = 0; i < list.size(); i++) {
			String[] col = list.get(i).split(",");
			for (int j = 0; j < col.length; j++) {
				array[i][j] = Integer.parseInt(col[j]);
			}
		}
		input.close();
		return array;
	}
}
