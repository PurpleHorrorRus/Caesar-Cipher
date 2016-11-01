public abstract class Information {
	protected String source = "", exp_encr = "", exp_decr = "";
	
	String ABS_PATH_TO_FILE = "", PATH_TO_FILE = "";
	
	int[] count, modelCount;
	
	int last_deg;
	
	int founded = 0;
	int x = 0, y = 0;
	
	
	char[] alphabet = {
			'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж',
			'З', 'И','Й', 'К', 'Л', 'М', 'Н', 'О',
			'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 
			'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 
			'Я'
	};
}
