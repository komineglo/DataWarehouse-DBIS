package dw.shipping_method.modul;

public class DimShipping_method {
	
	private String Shipping_method_id;
	private String Shipping_method_description;
	private double Shipping_fee;
	
	public DimShipping_method(String Shipping_method_id, 
							String Shipping_method_description, 
							double Shipping_fee) {
		
		this.Shipping_method_id = Shipping_method_id;
		this.Shipping_method_description = Shipping_method_description;
		this.Shipping_fee = Shipping_fee;
	}
	
	public String getShipping_method_id() {
		return Shipping_method_id;
	}
	
	public String getShipping_method_description() {
		return Shipping_method_description;
	}
	
	public double getShipping_fee() {
		return Shipping_fee;
	}
	
	public String toString() {
	    if (Shipping_method_id != null)
	        return Shipping_method_id
	        		+ (Shipping_method_description != null ? " (" + Shipping_method_description + " )" : "" );
	       else
	         return super.toString();
	     }
	

}
