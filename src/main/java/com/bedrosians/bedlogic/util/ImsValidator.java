package com.bedrosians.bedlogic.util;


import java.util.Date;

import javax.ws.rs.core.MultivaluedMap;

import org.codehaus.jettison.json.JSONObject;

import com.bedrosians.bedlogic.domain.item.Item;
import com.bedrosians.bedlogic.exception.BedDAOBadParamException;


public class ImsValidator {

	public static void validateQueryParams(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		validateForNullParams(queryParams);	
	}
	
	public static void validateInsertUpdateParams( MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		validateForNullParams(queryParams);		
	}
	
	public static void validateNewItem(Item item) throws BedDAOBadParamException{
		if(item == null)
		   throw new BedDAOBadParamException("Item should not be null.");
		validateItemCode(item);
		validateFieldLength(item);
		//validateNewItemUnit(item);
		if(item.getPrice() != null){
		   validateDates(item.getPrice().getTempdatefrom(), item.getPrice().getTempdatethru());
		}
	}

	public static void validateItemCode(Item item) throws BedDAOBadParamException{
		if(item.getItemcode() == null || item.getItemcode().length() < 1)
		   throw new BedDAOBadParamException("Item code cannot be empty.");
		if(item.getItemcode().length() > 18)
		   throw new BedDAOBadParamException("Item code cannot be longer that 18 characters.");
	}
	
	private static void validateForNullParams(MultivaluedMap<String, String> queryParams) throws BedDAOBadParamException{
		if(queryParams == null || queryParams.isEmpty())
		   throw new BedDAOBadParamException("Please enter valid query parameters!");	
	}
	
	public static void validateFieldLength(Item item) throws BedDAOBadParamException{
		if(item.getItemdesc().getItemdesc1() != null && item.getItemdesc().getItemdesc1().length() > 35)
		   throw new BedDAOBadParamException("Description should not be longer that 35 characters.");
		if(item.getPurchasers().getPurchaser() != null && item.getPurchasers().getPurchaser().length() > 10)
		   throw new BedDAOBadParamException("Purchaser should not be longer that 10 characters.");
		if(item.getPurchasers().getPurchaser2() != null && item.getPurchasers().getPurchaser2().length() > 10)
		   throw new BedDAOBadParamException("Purchaser2 should not be longer that 10 characters.");
		if(item.getIconsystem() != null && item.getIconsystem().length() > 20)
		   throw new BedDAOBadParamException("Icons should not be longer than 20 charactors.");
		if(item.getPrice().getPricegroup() != null && item.getPrice().getPricegroup().length() > 2)
		   throw new BedDAOBadParamException("Pricegroup length should be two charactors or less.");
		if(item.getItemgroupnbr() != null && item.getItemgroupnbr() > 99)
		   throw new BedDAOBadParamException("Itemgroupnbr length should be two digits or less.");
		validateCharacter("Lottype", item.getLottype()); 
		validateCharacter("showonwebsite", item.getLottype()); 
		validateCharacter("showonalysedwards", item.getLottype()); 
		validateCharacter("offshade", item.getLottype());			   
		validateCharacter("frostresistance", item.getTestSpecification() == null ? null : item.getTestSpecification().getFrostresistance());
		//validateCharacter("directship", item.getDirectship());
		
	}
	
	public static void validateCharacter(String name, String value) throws BedDAOBadParamException {
		if(value != null && value.length() > 1)
		   throw new BedDAOBadParamException(name + " is " + value + ". It should be one charactor.");
	}	

	public static void validateDates(Date startDate, Date endDate) throws BedDAOBadParamException{
		if (startDate != null && endDate != null && startDate.after(endDate))
			throw new BedDAOBadParamException("End date should not be earlier than start date.");
	}
	
	//Validate the new item against the ims CHECK constraints
	public static void validateNewItemUnit(Item item) throws BedDAOBadParamException{
	  if(item.getUnits() != null) {	
		if(item.getUnits().getBaseunit() == null || item.getUnits().getBaseunit().length() < 1)
		   throw new BedDAOBadParamException("BaseUnit cannot be null.");
	
		if((item.getUnits().getBaseunit() != null && item.getUnits().getBaseunit().length() > 4) || 
		   (item.getUnits().getUnit1unit() != null && item.getUnits().getUnit1unit().length() > 4) ||
		   (item.getUnits().getUnit2unit() != null && item.getUnits().getUnit2unit().length() > 4) ||
		   (item.getUnits().getUnit3unit() != null && item.getUnits().getUnit3unit().length() > 4) ||
		   (item.getUnits().getUnit4unit() != null && item.getUnits().getUnit4unit().length() > 4))
			throw new BedDAOBadParamException("Unit cannot be longer than four characters.");
		//db constraint ims_check 1
		if((item.getUnits().getUnit1unit() == null || item.getUnits().getUnit1unit().length() == 0) && item.getUnits().getUnit1ratio() > 0)
			throw new BedDAOBadParamException("Unit1 ratio should be 0, since unit1 unit is empty.");
		if((item.getUnits().getUnit1unit() != null && item.getUnits().getUnit1unit().length() > 0) && item.getUnits().getUnit1ratio() < 0)
			throw new BedDAOBadParamException("Please privide Unit1 ratio.");
		
		//db constraint ims_check 2
		if((item.getUnits().getUnit2unit() == null || item.getUnits().getUnit2unit().length() == 0) && item.getUnits().getUnit2ratio() > 0)
			throw new BedDAOBadParamException("Unit2 ratio should be 0, since unit2 unit is empty.");
		if((item.getUnits().getUnit2unit() != null && item.getUnits().getUnit2unit().length() > 0) && item.getUnits().getUnit2ratio() < 0)
			throw new BedDAOBadParamException("Please privide Unit2 ratio.");
	
		//db constraint ims_check 3
		if((item.getUnits().getUnit3unit() == null || item.getUnits().getUnit3unit().length() == 0) && item.getUnits().getUnit3ratio() > 0)
			throw new BedDAOBadParamException("Unit3 ratio should be 0, since unit3 unit is empty.");
		if((item.getUnits().getUnit3unit() != null && item.getUnits().getUnit3unit().length() > 0) && item.getUnits().getUnit3ratio() < 0)
			throw new BedDAOBadParamException("Please privide Unit3 ratio.");
				
		//db constraint ims_check 4
		if((item.getUnits().getUnit4unit() == null || item.getUnits().getUnit4unit().length() == 0) && item.getUnits().getUnit4ratio() > 0)
			throw new BedDAOBadParamException("Unit4 ratio should be 0, since unit4 unit is empty.");
		if((item.getUnits().getUnit4unit() != null && item.getUnits().getUnit4unit().length() > 0) && item.getUnits().getUnit4ratio() < 0)
			throw new BedDAOBadParamException("Please privide Unit1 ratio.");
		 
	 	//db constraint ims_check 5
		  /*CONSTRAINT ims_check5 CHECK (((((((((((((((((((
		        (vendorpriceunit IS NOT NULL) AND 
		        ((((
		           ((vendorpriceunit <= '    '::bpchar) OR 
		        	(vendorpriceunit = baseunit)) OR (vendorpriceunit = unit1unit)) OR (vendorpriceunit = unit2unit)) OR (vendorpriceunit = unit3unit)) OR (vendorpriceunit = unit4unit))) AND 
		        	(vendorlistprice IS NOT NULL)) AND 
		        	(vendordiscpct1 IS NOT NULL)) AND 
		        	(vendordiscpct2 IS NOT NULL)) AND 
		        	(vendordiscpct3 IS NOT NULL)) AND 
		        	(vendorroundaccuracy IS NOT NULL)) AND 
		        	(vendorroundaccuracy >= (0)::numeric)) AND 
		        	(vendorroundaccuracy <= (4)::numeric)) AND 
		        	(vendornetprice IS NOT NULL)) AND 
		        	(vendormarkuppct IS NOT NULL)) AND 
		        	(vendorfreightratecwt IS NOT NULL)) AND 
		        	(vendorlandedbasecost IS NOT NULL)) AND 
		        	(sellpricemarginpct IS NOT NULL)) AND 
		        	(sellpricemarginpct <> (100)::numeric)) AND 
		        	(sellpriceroundaccuracy IS NOT NULL)) AND 
		        	(sellpriceroundaccuracy >= (0)::numeric)) AND 
		        	(sellpriceroundaccuracy <= (4)::numeric)) AND 
		        	(listpricemarginpct IS NOT NULL)) AND 
		        	(listpricemarginpct <> (100)::numeric))
		  */
		/*
		if(!item.getNewNoteSystem()
				.getVendorpriceunit().equalsIgnoreCase(item.getUnits().getBaseunit()) && 
	        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit1unit()) &&
	        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit2unit()) &&
	        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit3unit()) &&
	        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit4unit())) {
	        	 System.out.println("Vendorpriceunit = " + item.getVendors().getVendorpriceunit());
	        	 System.out.println("Base unit = " + item.getUnits().getBaseunit());
	        	 System.out.println("Unit 1 unit = " + item.getUnits().getUnit1unit());
	        	 System.out.println("Unit 2 unit = " + item.getUnits().getUnit2unit());
	        	 System.out.println("Unit 3 unit = " + item.getUnits().getUnit3unit());
	        	 System.out.println("Unit 4 unit = " + item.getUnits().getUnit4unit());
	           throw new BedDAOBadParamException("According to ims table requirments, vendor price unit should match one of the unit's packaging unit."); 	 
	         }
	         if(item.getVendors().getVendorlistprice() == null){
	           	throw new BedDAOBadParamException("According to ims table requirments, Vendor list price should not be null.");  
	         }
	         if(item.getVendors().getVendordiscpct() == null || item.getVendors().getVendordiscpct2() == null || item.getVendors().getVendordiscpct3() == null){
	        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor discount percent (1--3) should not be null.");  
	         }
	         if(item.getVendors().getVendorroundaccuracy() == null || item.getVendors().getVendorroundaccuracy() < 0 || item.getVendors().getVendorroundaccuracy() > 100){
	        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor round accuracy should be 0 to 100.");   
	         }
	         if(item.getVendors().getVendornetprice() == null){
	        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor net price should not be null.");  
	         }
	         if(item.getVendors().getVendormarkuppct() == null){
	        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor markup percent should not be null.");  
	         }
	         if(item.getVendors().getVendorfreightratecwt() == null){
	        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor freight rate cwt should not be null.");   
	         }
	         if(item.getVendors().getVendorlandedbasecost() == null){
	        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor landed base cost should not be null.");
	         }
	         if(item.getPrice().getSellpricemarginpct() == null || item.getPrice().getSellpricemarginpct().longValue() < 0 || item.getPrice().getSellpricemarginpct().longValue() > 100){
	        	throw new BedDAOBadParamException("According to ims table requirments, Sell price margine percent should not be null and shoud be 0 to 100..");
	         }
	         if(item.getPrice().getListpricemarginpct() == null || item.getPrice().getListpricemarginpct().longValue() < 0 || item.getPrice().getListpricemarginpct().longValue() > 100){
	        	 throw new BedDAOBadParamException("According to ims table requirments, List price margine percent should not be null and shoud be 0 to 100.");
	         }
	         if(item.getPrice().getListpricemarginpct().longValue() >= 100) {
	        	 throw new BedDAOBadParamException("According to ims table requirments, List price margine percent should be 0 to 100.");
	         }		
	         if((item.getPrice().getSellpriceroundaccuracy() == null) || item.getPrice().getSellpriceroundaccuracy() < 0 || item.getPrice().getSellpriceroundaccuracy() > 100){
	        	throw new BedDAOBadParamException("According to ims table requirments, List price round accuracy should be 0 to 100.");
	         }
	      */
		/*
         if(item.getVendors().getVendorpriceunit() == null || item.getVendors().getVendorpriceunit().length() == 0 || item.getVendors().getVendorpriceunit().length() > 4){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor price unit shoud not be null and its length shoud be 4 or less characters.");
         }
         if(!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getBaseunit()) && 
        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit1unit()) &&
        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit2unit()) &&
        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit3unit()) &&
        	!item.getVendors().getVendorpriceunit().equalsIgnoreCase(item.getUnits().getUnit4unit())) {
        	 System.out.println("Vendorpriceunit = " + item.getVendors().getVendorpriceunit());
        	 System.out.println("Base unit = " + item.getUnits().getBaseunit());
        	 System.out.println("Unit 1 unit = " + item.getUnits().getUnit1unit());
        	 System.out.println("Unit 2 unit = " + item.getUnits().getUnit2unit());
        	 System.out.println("Unit 3 unit = " + item.getUnits().getUnit3unit());
        	 System.out.println("Unit 4 unit = " + item.getUnits().getUnit4unit());
           throw new BedDAOBadParamException("According to ims table requirments, vendor price unit should match one of the unit's packaging unit."); 	 
         }
         if(item.getVendors().getVendorlistprice() == null){
           	throw new BedDAOBadParamException("According to ims table requirments, Vendor list price should not be null.");  
         }
         if(item.getVendors().getVendordiscpct() == null || item.getVendors().getVendordiscpct2() == null || item.getVendors().getVendordiscpct3() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor discount percent (1--3) should not be null.");  
         }
         if(item.getVendors().getVendorroundaccuracy() == null || item.getVendors().getVendorroundaccuracy() < 0 || item.getVendors().getVendorroundaccuracy() > 100){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor round accuracy should be 0 to 100.");   
         }
         if(item.getVendors().getVendornetprice() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor net price should not be null.");  
         }
         if(item.getVendors().getVendormarkuppct() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor markup percent should not be null.");  
         }
         if(item.getVendors().getVendorfreightratecwt() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor freight rate cwt should not be null.");   
         }
         if(item.getVendors().getVendorlandedbasecost() == null){
        	 throw new BedDAOBadParamException("According to ims table requirments, Vendor landed base cost should not be null.");
         }
         if(item.getPrice().getSellpricemarginpct() == null || item.getPrice().getSellpricemarginpct().longValue() < 0 || item.getPrice().getSellpricemarginpct().longValue() > 100){
        	throw new BedDAOBadParamException("According to ims table requirments, Sell price margine percent should not be null and shoud be 0 to 100..");
         }
         if(item.getPrice().getListpricemarginpct() == null || item.getPrice().getListpricemarginpct().longValue() < 0 || item.getPrice().getListpricemarginpct().longValue() > 100){
        	 throw new BedDAOBadParamException("According to ims table requirments, List price margine percent should not be null and shoud be 0 to 100.");
         }
         if(item.getPrice().getListpricemarginpct().longValue() >= 100) {
        	 throw new BedDAOBadParamException("According to ims table requirments, List price margine percent should be 0 to 100.");
         }		
         if((item.getPrice().getSellpriceroundaccuracy() == null) || item.getPrice().getSellpriceroundaccuracy() < 0 || item.getPrice().getSellpriceroundaccuracy() > 100){
        	throw new BedDAOBadParamException("According to ims table requirments, List price round accuracy should be 0 to 100.");
         }
          */
		//ims check 6
		if (item.getUnits().getBaseunit() != null && checkIllegalValue(item.getUnits().getBaseunit()) ||
	       (item.getUnits().getUnit1unit() != null && checkIllegalValue(item.getUnits().getUnit1unit())) ||
	       (item.getUnits().getUnit2unit() != null && checkIllegalValue(item.getUnits().getUnit2unit())) ||
	       (item.getUnits().getUnit3unit() != null && checkIllegalValue(item.getUnits().getUnit3unit())) ||
	       (item.getUnits().getUnit4unit() != null && checkIllegalValue(item.getUnits().getUnit4unit())))
	    	 throw new IllegalArgumentException("Unit or unit ratio is wrong.");
	  }	
	}
	
	private static boolean checkIllegalValue(String originalString){
		String[] illegalValues = new String[]{"EA", "EACH", "PC", "LF", "SF", "CT", "CTM", "CTNH", "PK"};
		for(String s : illegalValues){
			if(originalString.equalsIgnoreCase(s))
			   return true;	
		}
		return false;
	}
	
	public boolean validateUnitUPC(Item item){
		return true;
	}	
}
