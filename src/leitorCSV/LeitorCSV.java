package leitorCSV;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LeitorCSV {

	public static void main(String[] args) throws NumberFormatException, IOException {
		String pathCSV = "/home/felix/Área de Trabalho/csv/estoque.csv";
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
			
			FileWriter arq = new FileWriter("/home/felix/Área de Trabalho/csv/estoque.html");
			
			PrintWriter gravarArq = new PrintWriter(arq);
			 
		    gravarArq.printf("<!DOCTYPE html>%n");
		    gravarArq.printf("<html>%n");
		    gravarArq.printf("<head>%n");
		    		gravarArq.printf("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>%n");
		    		gravarArq.printf("<title>Produtos</title>%n");
		 			gravarArq.printf("<style>%n");
		 				gravarArq.printf("html {%n");
		 					gravarArq.printf("font-family: Georgia, Cambria, serif;%n");
		 					gravarArq.printf("font-size: .875em;%n");
		 					gravarArq.printf("background: #fff;%n");
		 					gravarArq.printf("color: #373D49;%n");
		    			gravarArq.printf("}%n");
		    			gravarArq.printf("body { padding: 15px; }%n");
		    			gravarArq.printf("table {%n");
		    				gravarArq.printf("width: 100%%;%n");
		    				gravarArq.printf("border: 1px solid #ddd;%n");
		    				gravarArq.printf("border-collapse: collapse;%n");
		    				gravarArq.printf("border-spacing: 0;%n");
		    			gravarArq.printf("}%n");
		    			gravarArq.printf("table>thead>tr>th {%n");
		    				gravarArq.printf("text-align: left;%n");
		    				gravarArq.printf("border-bottom: 2px solid #ddd%n");
		    			gravarArq.printf("}%n");
		    			gravarArq.printf("th, td { padding: 8px; line-height: 1.4285714; }%n");
		    			gravarArq.printf("table>tbody>tr:nth-child(odd)>td { background-color: #f9f9f9 }%n");
		    		gravarArq.printf("</style>%n");
		    gravarArq.printf("</head>%n");
		    
		    gravarArq.printf("<body>%n");
		    	gravarArq.printf("<h1>Lista de Produtos</h1>%n");
		    	gravarArq.printf("<hr>%n");
		    	gravarArq.printf("<p>Lista gerada em 21/10/2016 às 12:00:00</p>%n");
		    
		    	gravarArq.printf("<table>%n");
		    		gravarArq.printf("<thead>%n");
		    			gravarArq.printf("<tr>%n");
		    				gravarArq.printf("<th>Produto</th>%n");
	    					gravarArq.printf("<th>Marca</th>%n");
	    					gravarArq.printf("<th>Preço</th>%n");
	    					gravarArq.printf("<th>Estoque</th>%n");
	    				gravarArq.printf("</tr>%n");
	    			gravarArq.printf("</thead>%n");
	    			gravarArq.printf("<tbody>%n");
	    			gravarArq.printf("<!-- Lista de produtos -->%n");
	    				
	    				bodyCSV = new BufferedReader(new FileReader(pathCSV));
	    				jump = true;
	    				
	    				while((row = bodyCSV.readLine()) != null) {				
	    					String[]temp = row.split(csvSplit);
	    					if(jump!=true) {
	    						String produto = temp[0];
	    							
	    						gravarArq.printf("<tr>%n");
    							gravarArq.printf("<td>"+temp[0]+"</td>%n");
    							gravarArq.printf("<td>"+temp[1]+"</td>%n");
    							gravarArq.printf("<td>"+temp[2]+"</td>%n");
    							gravarArq.printf("<td>"+temp[3]+"</td>%n");
    							gravarArq.printf("</tr>%n");
	    					}
	    					jump =false;
	    				}
	    				
	    				
	    				
	    			
	    			gravarArq.printf("</tbody>%n");
	    		gravarArq.printf("</table>%n");

	    	gravarArq.printf("</body>%n");
		    
		    gravarArq.printf("</html>%n");
		    
		    arq.close();
			
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
