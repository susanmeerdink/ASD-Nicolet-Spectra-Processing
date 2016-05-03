

public class Spectra {
	
	private double[] rawASDvalues; //This will hold raw ASD spectral values
	private String sampleID; //This will hold sample ID or name
		
	//Constructor
	public Spectra(String inputID, double[] inputSpectra){
		rawASDvalues = inputSpectra;
		sampleID = inputID;
	}

	public double[] getRawASDvalues() {
		return rawASDvalues;
	}

	public void setRawASDvalues(double[] rawASDvalues) {
		this.rawASDvalues = rawASDvalues;
	}

	public String getSampleID() {
		return sampleID;
	}

	public void setSampleID(String sampleID) {
		this.sampleID = sampleID;
	}
	
}
