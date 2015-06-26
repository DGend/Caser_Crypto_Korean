public class main {
	public static void main(String[] args){
		KoCrypto caser_Ko = new KoCrypto();
		String message = "dongseo 오늘의 날씨 맑음 흐림 밝음!!!!";
		
		System.out.println("원문	: "+message);
		
		message = caser_Ko.Encrypt(message);
		
		caser_Ko.Decrypt(message);
		
	}
}