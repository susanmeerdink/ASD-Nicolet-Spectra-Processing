
public class SpectraFileList {
	private String sampleID;
	private int begin;
	private int end;
	private String[] listFiles;
	
	public SpectraFileList(String sampleID, int begin, int end){
		this.sampleID = sampleID;
		this.begin = begin;
		this.end = end;
		this.listFiles = null;
	}
	
	public SpectraFileList(String sampleID, String[] inFiles){
		this.sampleID = sampleID;
		this.setListFiles(inFiles);
		this.begin = -1;
		this.end = -1;
	}

	public String getSampleID() {
		return sampleID;
	}

	public void setSampleID(String sampleID) {
		this.sampleID = sampleID;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String[] getListFiles() {
		return listFiles;
	}

	public void setListFiles(String[] listFiles) {
		this.listFiles = listFiles;
	}
	
	
}
