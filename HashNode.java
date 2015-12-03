package hashtables;


public class HashNode <T> {
	private T info;
	private byte state;
	
	final static byte DELETED = -1;
	final static byte EMPTY = 0;
	final static byte FULL = 1;
	
	public HashNode(){
		info = null;
		state=EMPTY;
	}

	public T getInfo(){
		return info;
	}

	public void delete(){
		state=DELETED;
	}

	public void setInfo(T info){
		this.info = info;
		this.state=FULL;
	}

	public byte getState() {
		return state;
	}
	
	public String toString (){
		String string="[";
		switch (getState()){
		case FULL:
			string+=info;
			break;
		case EMPTY:
			string+="_E_";
			break;
		case DELETED:
			string+="_D_";
		}
		string+="]";
		return string;
	}
}
