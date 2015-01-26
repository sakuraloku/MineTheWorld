package code;

public class Code {
	
	public static byte[] encode(byte[] data){
		byte[] encodeData = null;
		if(data != null){
			encodeData = new byte[data.length];
			
			int count = data.length;
			for(int i = 0; i < count; i++){
				encodeData[i] = (byte) (256 - (int)data[i]);
			}
		}
		return encodeData;
	}
}
