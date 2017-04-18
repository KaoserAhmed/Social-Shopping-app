import java.util.*;
import java.io.*;
class sell{
	
	void bikroy()throws IOException {
    // This section is used to collect and store data on some txt file
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

		System.out.print("Do you want to calculate your product Price?(Y/N): ");
		String getResell= br.readLine().toUpperCase();
		if (getResell.equals("Y")) {
			this.reSellPrice();
		}

		System.out.print("Enter your location(Home Town): ");
		
		String location= br.readLine().toUpperCase();
		BufferedWriter fbwl= new BufferedWriter(new FileWriter("DB/"+location+".txt",true));
		BufferedWriter fbw= new BufferedWriter(new FileWriter("DB/db.txt",true));
		fbw.write(location+"\n");


		System.out.print("Enter The Product name you want to sell: ");
		String pName= br.readLine().toUpperCase();
		fbwl.write(pName+"\n");



		System.out.print("Enter Product Description(Brand+Model): ");
		String pDescription= br.readLine();


		System.out.print("Enter Product price: ");
		String pPrice= br.readLine();


		System.out.print("Enter Contact Information(Email/Phone/FB): ");
		String pContact=br.readLine();

		BufferedWriter productbw = new BufferedWriter(new FileWriter("DB/"+location+"-Product.txt",true));
		System.out.println("Entry Done. Wait for Buyer");
		productbw.write(pDescription+"   Price: "+pPrice+" Contact: "+pContact+"\n");


		fbw.close();
		fbwl.close();
		productbw.close();
	}




	void search(String location, String pName) throws IOException{
		// this section is used to get data from the files and search data from them
		try{
				BufferedReader br= new BufferedReader(new FileReader("DB/db.txt"));
				LinkedList<String>dbList= new LinkedList<String>();
				String a;
				while((a = br.readLine())!=null){
					dbList.add(a);
				}


				BufferedReader pbr= new BufferedReader(new FileReader("DB/"+location+".txt"));
				LinkedList<String>pdbList= new LinkedList<String>();
				while((a = pbr.readLine())!=null){
					pdbList.add(a);
				}


				BufferedReader productbw= new BufferedReader(new FileReader("DB/"+location+"-Product.txt"));
				LinkedList<String>pdbDescription= new LinkedList<String>();
				while((a = productbw.readLine())!=null){
					pdbDescription.add(a);
				}

				


				int i=0;
				int j=0;
				int serial=0;
				System.out.println("--------------------------------");
				System.out.println(pName+" Available in "+location);
				System.out.println("--------------------------------");
				for (String s : dbList ) {
					j++;
					if (location.equals(s) ) {
						if (pName.equals(pdbList.get(i))) {
							serial++;
							System.out.println(serial+" - "+ pdbDescription.get(i));
							System.out.println(" ");
							
						}else if(j == dbList.size()){
							System.out.println("Item not found in "+location);
						}

						i++;
					}
				}
				this.orderProduct();

		}catch(FileNotFoundException e){
			System.out.println("Item is not available in your area");
		}
	}



	void kroy() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter your location: ");
		String location= br.readLine().toUpperCase();
		System.out.print("Enter The Product name you want to buy(Laptop,Mobile etc): ");
		String buyInput= br.readLine().toUpperCase();

		this.search(location,buyInput);
	}



	void orderProduct() throws IOException {
		System.out.print("Which one you want to buy?(Enter Order no.): ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String order= br.readLine();
		int i = Integer.parseInt(order);

		System.out.println("Your Order is confirmed. You order id: "+i);
	}




	void reSellPrice() throws IOException{

		int resell=0;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("What is you products original price?: ");
		int newPrice= Integer.parseInt(br.readLine());
		System.out.print("How old is your product?(Month): ");
		int old= Integer.parseInt(br.readLine());
		System.out.print("Scratch or fault(true/false): ");
		String b= br.readLine().toUpperCase();
		if (b.equals("TRUE")) {
			double temp=newPrice;

			for (int i =0;i<old ;i++ ) {
				temp= temp-((newPrice*3)/100);
			}
			resell=(int)(temp-((newPrice*30)/100));
		}else{
			for (int i =0;i<old ;i++ ) {
				newPrice= newPrice-((newPrice*3)/100);
			}
			resell=newPrice;

		}

		System.out.print("Your Product Price Is Approximately: "+resell);
		System.out.println();

	}

}



class SocialShopping{
	public static void main(String args[]) throws IOException{

		sell s= new sell();

		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		System.out.print("What do you want?(Buy/Sell): ");
		String decide = br.readLine().toUpperCase();


		switch (decide){
			case "BUY":
			 s.kroy();
			 break;

			 case "SELL":
			  s.bikroy();
			  break;

			  default :
			  	System.out.println("Please restart and enter (Buy/sell)");
			  	break;
		}
	}
}
