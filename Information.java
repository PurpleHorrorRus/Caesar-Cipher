public abstract class Information {
	protected String source = "", exp_encr = "", exp_decr = "";
	
	String ABS_PATH_TO_FILE = "", PATH_TO_FILE = "";
	
	int[] count, modelCount;
	
	int last_deg;
	
	int founded = 0;
	int x = 0, y = 0;
	
	
	char[] alphabet = {
			'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', '¨', 'Æ',
			'Ç', 'È','É', 'Ê', 'Ë', 'Ì', 'Í', 'Î',
			'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 
			'×', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'Þ', 
			'ß'
	};
}
