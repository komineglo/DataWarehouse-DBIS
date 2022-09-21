package dw.payment_methode.modul;

public class DimPayment_method {
	private String Payment_method_id;
	private String Payment_description;
	
	public DimPayment_method(String Payment_method_id, String Payment_description) {
		this.Payment_method_id= Payment_method_id;
		this.Payment_description= Payment_description;
		
	}
	
	public String getPayment_method_id() {
		
		return Payment_method_id;
	}
	
	public String getPayment_description() {
		
		return Payment_description;
	}
	
	public String toString() {
	    if (Payment_method_id != null)
	        return Payment_method_id
	        		+ (Payment_description != null ? " (" + Payment_description + " )" : "" );
	       else
	         return super.toString();
	     }
	  
	

}
