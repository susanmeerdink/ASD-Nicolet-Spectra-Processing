This JAVA project reads in ASD spectra and averages into one spectra.

Step 1: Upload ASD Spectra
The input ASD file must be created in ViewSpecPro available at www.asdi.com. When creating the file in ASCII Export make sure to set these settings:
Data Format: Reflectance,  Derivative: None,  Data Organization: Row,  Field Seperator: Comma with Output to a Single File
No Header Information,  Do not Print X-Axis,  No Description or Note,  Print Row Title with FileName(s) to Left of Row
		
Step 2: Upload Spectra File List
This file must be a .csv file with the first column containing the Sample ID, second column containing the first ASD filenumber associated
with that spectra, and the third column containing the last ASD file number associated with that spectra. For example: VH001, 314, 320.