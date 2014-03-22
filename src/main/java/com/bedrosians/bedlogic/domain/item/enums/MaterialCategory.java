package com.bedrosians.bedlogic.domain.item.enums;

public enum MaterialCategory {

	Medallion("Medallion"),
	Deco("Deco"),
	Tool("Tool"),
	Catalog("Catalog"),
	SettingMat("SettingMat"),
	Tear_Sheet("Tear Sheet"),
	Board("Board"),
	Label("Label"),
	Listello("Listello"),
	Trim("Trim"),
	SWA("SWA"),
	Tile("Tile"),
	Allied("Allied"),
	Paver("Paver"),
	Mosaic("Mosaic"),
	Slab("Slab"),
	Pack_Out("Pack_Out"),
	Display("Display"),
	ARC("ARC"),
	Ledger("Ledger");
		

	
	private String description;
		 
	private MaterialCategory(String description){
		 this.description = description;
	}
	
	public String getDescription(){
		 return description;
	}
		 
	public static MaterialCategory instanceOf(String key){
		for(MaterialCategory instance : values()){
		    if( instance.getDescription().equalsIgnoreCase(key))
	            return instance;				        
		}
	    return null;
	}

}
