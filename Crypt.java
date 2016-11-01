public class Crypt extends Information {

	int findIndexInAlphabet(char letter){
		int num = -1;
		for(int i = 0; i < alphabet.length; i++)
			if(letter == alphabet[i]) num = i;
		return num;
	}
	
	void encrypt(String exp, int deg){
		exp = exp.toUpperCase();
		for(int i = 0, b = 0; i < exp.length(); i++, b++){
			if(i > 0 && b < exp.length() && exp.charAt(i) == ' ') exp_encr += " "; //Пробел между словами
			else { //Шифровка
				if(Character.isLetter(exp.charAt(i))){
					y = (findIndexInAlphabet(exp.charAt(i)) + deg) % alphabet.length;
					exp_encr += alphabet[y];
				} else exp_encr += exp.charAt(i);
			}
			
		}
	}
	
	void decrypt(String exp, int deg){
		exp = exp.toUpperCase();
		for(int i = 0, b = 0; i < exp.length(); i++, b++){
			if(i > 0 && b < exp.length() && exp.charAt(i) == ' ') exp_decr += " "; //Пробел между словами
			else { //Дешифровка
				if(Character.isLetter(exp.charAt(i))){
					x = (findIndexInAlphabet(exp.charAt(i)) - deg + alphabet.length) % alphabet.length;
					exp_decr += alphabet[x];
				} else exp_decr += exp.charAt(i);
			}
		}
	}
}
