package leitorCSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeitorCSV {

	public static void main(String[] args) throws NumberFormatException, IOException {
		String pathCSV = "/home/felix/Desktop/estoque.csv";
		BufferedReader bodyCSV = null;
		String row = "";
		String csvSplit = ";";
		int contProd = 0;
		double totProd = 0;
		boolean jump = true;
		ArrayList<Object> stock = new ArrayList<Object>();
		
		try {
			bodyCSV = new BufferedReader(new FileReader(pathCSV));
			double maxValue1 = 0;
			double maxValue2 = 0;
			while((row = bodyCSV.readLine()) != null) {				
				String[]temp = row.split(csvSplit);
				if(jump!=true) {					
					contProd = contProd + Integer.parseInt(temp[3]);
					totProd = totProd + (Double.parseDouble(temp[2])*Double.parseDouble(temp[3]));
					if(Double.parseDouble(temp[2])>maxValue1 || Double.parseDouble(temp[2])>maxValue2) {
						stock.add(temp[0]+" "+temp[1]+" "+temp[2]);
						if(Double.parseDouble(temp[2])>maxValue2) {
							maxValue2 = maxValue1;
							maxValue1 = Double.parseDouble(temp[2]);							
						}
					}
				}
				jump =false;
			}
			
			while(stock.size()>2) {
				stock.remove(0);
			}
			System.out.println("Produtos mais caros:");
			for(int i = 0; i < stock.size(); i++) {				
				System.out.println(stock.get(i));								
			}
			System.out.println("");
			System.out.println("");
			System.out.printf("Preço médio de produtos: %.2f %n", totProd/contProd);
			System.out.println("Quantidade de items em estoque: "+contProd);
		}
		catch (FileNotFoundException e){
			System.out.println("Arquivo não encontrado \n"+e.getMessage());
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("IndexOutOfBounds \n"+e.getMessage());
		}
		finally{
			if(bodyCSV!=null) {
				try {
					bodyCSV.close();
				}
				catch (IOException e) {
					System.out.println("IO Erro "+ e.getMessage());
				}
			}
		}
	}

}
