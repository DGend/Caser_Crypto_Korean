import java.text.Normalizer;
import java.util.ArrayList;

/**
 * @author Dend
 *
 */
public class KoCrypto {
	final int shift_num = 3;
	ArrayList<KoStruct> arrHex = new ArrayList<KoStruct>();

	/**
	 * @param message 유니코드 평문
	 * @return shift_num양만큼 이동한 문장 출력
	 */
	String Encrypt(String message) {
		arrHex.clear();

		// hex값으로 변환
		change_int(message, arrHex);
		
		shift_row(arrHex, shift_num);

		System.out.print("암호화	: ");
		return restruct(arrHex);
	}

	/**
	 * @param crypto 암호화된 유니코드문
	 * @return shift_num양만큼 이동한 문장 출력
	 */
	String Decrypt(String crypto) {
		arrHex.clear();

		// hex값으로 변환
		change_int(crypto, arrHex);

		Deshift_row(arrHex, shift_num);
		System.out.print("복호화	: ");
		return restruct(arrHex);
	}

	/**
	 * @param arrHex 글자별 Hex값 배열
	 * @return 글자로 나누어진 문자들을 문장 재조합
	 */
	private String restruct(ArrayList<KoStruct> arrHex) {
		// TODO Auto-generated method stub
		KoStruct k;
		StringBuilder result = new StringBuilder();
		String complet = "";
		for (int i = 0; i < arrHex.size(); ++i) {
			k = arrHex.get(i);
			result.setLength(0);
			if(k.getOtherHex()==0){
				result.append(String.format("%c", 0x1100 + k.getFirstHex()));
				result.append(String.format("%c", 0x1161 + k.getSecundHex()));
				result.append(String.format("%c", 0x11A7 + k.getThirdHex()));
				complet += Normalizer.normalize(result, Normalizer.Form.NFC);
			} else{
				result.append(String.format("%c", k.getFirstHex()));
				complet += Normalizer.normalize(result, Normalizer.Form.NFC);
			}
		}

		System.out.println(complet);

		return complet;
	}

	/**
	 * @param str 유니코드 문자열
	 * @param Hex 유니코드 문자열을 글자별 Hex값으로 분리하여 저장
	 */
	void change_int(String str, ArrayList Hex) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		int a = 0;
		boolean flag = false, flag2 = false;
		KoStruct k = new KoStruct();

		for (int i = 0; i < str.length(); ++i) {
//			System.out.print(str.charAt(i) + " ");
//			System.out.println(String.format("%4x ", str.codePointAt(i)));
			a = str.codePointAt(i);
//			System.out.println(a);
			if (4351 < a && a < 4449) {
				if (flag) {
					Hex.add(k);
					k = new KoStruct();
				}
				k.setFirstHex(a - 4352);
				flag = true;
				flag2 = false;
			} else if (4448 < a && a < 4519) {
				k.setSecundHex(a - 4449);
				if (i == str.length()-1) {
					Hex.add(k);
				}
			} else if (4518 < a && a < 4608) {
				k.setThirdHex(a - 4519);
				if (i == str.length()-1) {
					Hex.add(k);
				}
			} else {
				if(i!=0&&!flag2){
					Hex.add(k);
					k = new KoStruct();
				}
				k.setOtherHex(1);
				flag2=true;
				k.setFirstHex(a);
				Hex.add(k);
				k = new KoStruct();
				flag=false;
			}
		}
	}

	/**
	 * @param arr 글자별배열
	 * @param shift 암호화 이동량
	 */
	void shift_row(ArrayList<KoStruct> arr, int shift) {
		for (int i = 0; i < arr.size(); ++i) {
			KoStruct k = arr.get(i);
			for (int j = 0; j < k.size(); ++j) {
				k.changeNum(j, shift);
				if(k.getOtherHex()!=0){
					break;
				}	
			}
		}
	}

	/**
	 * @param arr 글자별배열
	 * @param shift 복호화 이동량
	 */
	void Deshift_row(ArrayList<KoStruct> arr, int shift) {
		for (int i = 0; i < arr.size(); ++i) {
			KoStruct k = arr.get(i);
			for (int j = 0; j < k.size(); ++j) {
				k.DechangeNum(j, shift);
				if(k.getOtherHex()!=0){
					break;
				}
			}
		}
	}
}