/**
 * @author Dend
 *
 */
public class KoStruct {
	private int firstHex = 0;	/// 초성
	private int secundHex = 0;	/// 중성
	private int thirdHex = 0;	///	종성
	private int otherHex = 0;	/// 한글여부

	public void setFirstHex(int hex) {
		this.firstHex = hex;
	}

	public void setSecundHex(int hex) {
		this.secundHex = hex;
	}

	public void setThirdHex(int hex) {
		this.thirdHex = hex;
	}

	public void setOtherHex(int hex) {
		this.otherHex = hex;
	}

	public int getFirstHex() {
		return firstHex;
	}

	public int getSecundHex() {
		return secundHex;
	}

	public int getThirdHex() {
		return thirdHex;
	}

	public int getOtherHex() {
		return otherHex;
	}

	/**
	 * @param location 초성=0, 중성=1, 종성=2 
	 * @param number Shift 시킬 값
	 * @return 정상실행=true, 비정상종료=false
	 */
	public boolean changeNum(int location, int number) {
		if (location > size()) {
			return false;
		} else {
			switch (location) {
			case 0:
				firstHex += number;
				if (otherHex==0&&firstHex > 18)
					firstHex -= 19;
				break;
			case 1:
				secundHex += number;
				if (secundHex > 20)
					secundHex -= 21;
				break;
			case 2:
				thirdHex += number;
				if (thirdHex > 27)
					thirdHex -= 28;
				break;
			}
		}
		return true;
	}

	/**
	 * @param location 초성=0, 중성=1, 종성=2 
	 * @param number Shift 시킬 값
	 * @return 정상실행=true, 비정상종료=false
	 */
	public boolean DechangeNum(int location, int number) {
		if (location > size()) {
			return false;
		} else {
			switch (location) {
			case 0:
				firstHex -= number;
				if (otherHex==0&&firstHex < 0)
					firstHex += 19;
				break;
			case 1:
				secundHex -= number;
				if (secundHex < 0)
					secundHex += 21;
				break;
			case 2:
				thirdHex -= number;
				if (thirdHex < 0)
					thirdHex += 28;
				break;
			}
		}
		return true;
	}

	/// 자음 + 모음 = 2
	public int size() {
		if (thirdHex == 0) {
			return 2;
		} else {
			return 3;
		}
	}
}