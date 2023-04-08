import java.io. * ;
import java.util.List;


public class Generator {
	public static final String delimiter = ",";
	public static void read(String csvFile) {
		try {
			File file = new File(csvFile);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = " ";

			String[] tempArr;

			FileWriter fw = new FileWriter(csvFile.substring(0, csvFile.lastIndexOf(".")) + ".lua");
			BufferedWriter bw = new BufferedWriter(fw);

			while ((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);

				bw.write("Shop.Items[\"" + tempArr[1] + "\"] = {");
				bw.newLine();
				bw.write("	tab = " + tempArr[0] + ", price = " + tempArr[2] + ",");
				bw.newLine();
				bw.write("}");
				bw.newLine();
				bw.newLine();
			}
			br.close();
			bw.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		File dir = new File(".");
		File[] files = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".csv");
			}
		});

		for (File file : files) {
			if (!file.getName().equals("Shop_ForSell.csv")) {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);

				FileWriter fw = new FileWriter(file.getName().substring(0, file.getName().lastIndexOf(".")) + ".lua");
				BufferedWriter bw = new BufferedWriter(fw);

				String line = " ";
				String[] tempArr;
				
				System.out.print(file.getName());
				System.out.println();
				
				while ((line = br.readLine()) != null) {
					tempArr = line.split(delimiter);

					bw.write("Shop.Items[\"" + tempArr[1] + "\"] = {");
					bw.newLine();
					bw.write("	tab = " + tempArr[0] + ", price = " + tempArr[2] + ",");
					bw.newLine();
					bw.write("}");
					bw.newLine();
					bw.newLine();
				}
				br.close();
				bw.close();
			}
			else {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);

				FileWriter fw = new FileWriter(file.getName().substring(0, file.getName().lastIndexOf(".")) + ".lua");
				BufferedWriter bw = new BufferedWriter(fw);

				String line = " ";
				String[] tempArr;

				bw.write("Shop.defaultPrice = 2");
				bw.newLine();
				bw.write("Shop.defaultPriceBroken = 1");
				bw.newLine();
				bw.write("Shop.SellisBlacklist = false");
				bw.newLine();
				bw.write("Shop.SellisWhitelist = true");
				bw.newLine();
				bw.write("Shop.Sell = {");
				bw.newLine();

				while ((line = br.readLine()) != null) {
					tempArr = line.split(delimiter);
					
					bw.write("	[\"" + tempArr[0] + "\"] = { price = " + tempArr[1] + " },");
					bw.newLine();
				}
				bw.write("}");
				br.close();
				bw.close();
			}
		}
	}
}