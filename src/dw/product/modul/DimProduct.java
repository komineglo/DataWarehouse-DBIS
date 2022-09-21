package dw.product.modul;

public class DimProduct {
	private int Product_id;
	private String Product_name;
	private String Product_description;
	private String Manufacturer;
	private float Product_length;
	private float Product_width;
	private float Product_height;
	private float Product_weight;
	private double Produkt_Brutto_Preis;
	private double Produkt_Netto_Preis;
	private double Erzielte_Gewinn_Pro_Produkt;
	
	public DimProduct (int Product_id , String Product_name , String Product_description,
			String Manufacturer, float Product_length, float Product_width, 
			float Product_height, float Product_weight, 
			double Produkt_Brutto_Preis, double Produkt_Netto_Preis, 
			double Erzielte_Gewinn_Pro_Produkt) 
	{
		this.Product_id = Product_id;
		this.Product_name = Product_name;
		this.Product_description = Product_description;
		this.Manufacturer = Manufacturer ;
		this.Product_length =  Product_length;
		this.Product_width = Product_width ; 
		this.Product_height = Product_height ;
		this.Product_weight = Product_weight ;
		this.Produkt_Brutto_Preis = Produkt_Brutto_Preis ;
		this.Produkt_Netto_Preis  =Produkt_Netto_Preis;
		this.Erzielte_Gewinn_Pro_Produkt = Erzielte_Gewinn_Pro_Produkt;
		
		
	}
	
	public int getProduct_id  (){
		
		return Product_id ;
	}
	
	public String getProduct_name  () {
	return Product_name ;	
	}
	
	public String getProduct_description  () {
		return Product_description ;	
		}
	
	public String getManufacturer  () {
		return Manufacturer ;	
		}
	
	public float getProduct_length  () {
		return Product_length ;	
		}
	
	public float getProduct_width  () {
		return Product_width ;	
		}
	
	public float getProduct_height  () {
		return  Product_height;	
		}
	
	public float getProduct_weight  () {
		return  Product_weight;	
		}
	
	public double getProdukt_Brutto_Preis  () {
		return Produkt_Brutto_Preis ;	
		}
	
	public double getProdukt_Netto_Preis  () {
		return  Produkt_Netto_Preis;	
		}
	
	public double getErzielte_Gewinn_Pro_Produkt  () {
		return Erzielte_Gewinn_Pro_Produkt ;	
		}
	
	
	public String toString() {
	    if (Product_name != null)
	        return Product_name;
	       else
	         return super.toString();
	     }
	
	
	

}

