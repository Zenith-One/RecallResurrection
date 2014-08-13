package zenithmods.recall.util;

public enum RecallSubtype {
	BLANK 		("blank"), 
	BLACK 		("black"),
	BLUE  		("blue"),
	BROWN  		("brown"),
	CYAN      	("cyan"),
	DARK_GRAY 	("darkGray"),
	GREEN		("green"),
	LIGHT_BLUE	("lightBlue"),
	LIGHT_GRAY	("lightGray"),
	LIME		("lime"),
	MAGENTA		("magenta"),
	ORANGE		("orange"),
	PINK		("pink"),
	PURPLE		("purple"),
	RED			("red"),
	WHITE		("white"),
	YELLOW		("yellow");
	
	private String title;
	
	private RecallSubtype(String title){
		this.title = title;
	}
	
	public static RecallSubtype getFromMeta(int meta){
		return RecallSubtype.values()[meta];
	}
	
	public static RecallSubtype getFromTitle(String title){
		for (RecallSubtype t : RecallSubtype.values()){
			if (t.title().equals(title)){
				return t;
			}
		}
		return null;
	}
	
	public static int getMeta(RecallSubtype type){
		return type.ordinal();
	}
	
	public String title(){
		return title;
	}
}
