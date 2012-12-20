package jp.co.geo.logviewer.model;

final public class LogItemType{
	private int index;
	private String name;
	private int original;
	private String description;
	
	private LogItemType(String name, int original, int index, String description) {
		this.index = index;
		this.name = name;
		this.original = original;
		this.description = description;
	}
	
	/**
	 * 時間
	 */
	public static LogItemType TIME = new LogItemType("TIME", 0, 3, "時間");
	
	/**
	 * HTTPステータスコード
	 */
	public static LogItemType STATUS = new LogItemType("STATUS", 1, 5, "ステータス");
	
	/**
	 * ログレベル
	 */
	public static LogItemType LOGLEVEL = new LogItemType("LOGLEVEL", 2, 3, "ログレベル");

	/**
	 * HTTPのリクエストURL
	 */
	public static LogItemType URL = new LogItemType("URL", 3, 4, "URL");

	/**
	 * サイズ
	 */
	public static LogItemType SIZE = new LogItemType("SIZE", 4, 5, "サイズ");
	
	/**
	 * 処理時間
	 */
	public static LogItemType PROCESSING_TIME = new LogItemType("PROCESSING_TIME", 5, 6, "処理時間");
	
	/**
	 * 表の列番号となる Index を設定する
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * 表の列名となる文字列を設定する
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Indexを返す
	 * @return
	 */
	public int values() {
		return index;
	}
	
	/**
	 * 表で表示される列番号
	 * @return
	 */
	public int index() {
		return index;
	}
	
	/**
	 * 列名
	 * @return
	 */
	public String description() {
		return description;
	}
	
	/**
	 * タイプ名をそのまま文字列で返す
	 */
	public String toString() {
		return name;
	}
	
	public static LogItemType getLogItemType(String type) {
		if(type.equals(TIME.toString())) {
			return TIME;
		} else if(type.equals(STATUS.toString())) {
			return STATUS;
		} else if(type.equals(LOGLEVEL.toString())) {
			return LOGLEVEL;
		} else if(type.equals(URL.toString())) {
			return URL;
		} else if(type.equals(PROCESSING_TIME.toString())){
			return PROCESSING_TIME;
		} else if(type.equals(SIZE.toString())){
			return SIZE;
		}else {
			return null;
		}
	}
}
