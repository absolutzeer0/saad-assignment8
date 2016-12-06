package backend;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.associations.Apriori;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;

// TODO: Test any of this code.  I couldn't figure out which WEKA packages to
// install, so none of this has even been compiled.  It probably has a dozen
// errors in it.
public class WekaConverter 
{
    // Constants
    private final String coursesHeader = "courseID,courseName,semester1,semester2,semester3";
    private final String recordsHeader = "studentID,courseID,instructorID,comments,grade";

    // Instance vars
    private String tempCSV = "tempCSV.csv";
    private String targetARFF = "targetARFF.arff";

    // Empty constructor is empty
    public WekaConverter(){}


    public String aprioriReadout(File sourceFile) throws IOException, Exception
    {
        // Convert input source into appropriate format
        String header = generateHeader(sourceFile);
        int points = calculateNumOfDatapoints(header);
        convertCSVtoARFF(sourceFile, header, points);

        // Load ARFF data
        NumericToNominal convert = new NumericToNominal();
        String[] options= new String[2];
        options[0]="-R";
        options[1]="1-3"; 
        convert.setOptions(options);
       
        Instances algoData = DataSource.read(targetARFF);
        convert.setInputFormat(algoData);
        Instances newAlgoData = Filter.useFilter(algoData, convert);
        newAlgoData.setClassIndex(newAlgoData.numAttributes() - 1);

        // build associator
        Apriori apriori = new Apriori();
        apriori.setClassIndex(newAlgoData.classIndex());
        apriori.buildAssociations(newAlgoData);

        return apriori.toString();
    }

    private String generateHeader(File sourceFile)
    {
        String filename = sourceFile.getName();
        if(filename.contains("records")) { 
            return recordsHeader; 
        }
        else if (filename.contains("courses")) { 
            return coursesHeader; 
        }
        else
        {
            System.err.println("Error generating header: Unknown source meaning");
            return "";
        }
    }

    private int calculateNumOfDatapoints(String header)
    {
        if(header != null && !header.isEmpty()){
            String[] readLine = header.split(",");
            return readLine.length;
        }
        else{
            return -1;
        }
    }

    private void convertCSVtoARFF(File sourceFile, String header, int datapoints) throws IOException
    {
        // Add header
        File tempFile, targetFile= null;
        try
        {
            tempFile = addHeaderToCSV(sourceFile, header, datapoints);
            targetFile = new File(targetARFF);

            // Load temp CSV into an Instances object
            CSVLoader loader = new CSVLoader();
            loader.setSource(tempFile);
            Instances data = loader.getDataSet();

            // Convert Instances object into a ARFF file
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(targetFile);
            saver.writeBatch();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


    }

    private File addHeaderToCSV(File sourceFile, String header, int datapoints) throws IOException
    {
        // Create a temporary file and a Writer to write to it
        File temp = new File(tempCSV);
        PrintWriter writer = new PrintWriter(temp);

        // Pre-process the CSV file and re-save it to the temporary CSV file location 
        BufferedReader reader = new BufferedReader(new FileReader(sourceFile));
        String line = "";
        String[] readLine;
        writer.println(header); // writer header to csv file

        // Copy source CSV into temporary file
        while ((line = reader.readLine()) != null){

            readLine = line.split(",");
            StringBuilder sb = new StringBuilder();
            // if the line that was read is not the same size, create an array 
            // that is the same size and write to the csv file instead
            if (readLine.length != datapoints){
                String[] newReadLine = new String[datapoints];
                System.arraycopy(readLine,0,newReadLine,0,readLine.length);
                for (int i=0; i<newReadLine.length ; ++i){
                    sb.append(newReadLine[i]);
                    if (i != newReadLine.length-1){
                        sb.append(",");
                    }
                }
            }else{
                for (int i=0; i<readLine.length ; ++i){
                    sb.append(readLine[i]);
                    if (i != readLine.length-1){
                        sb.append(",");
                    }
                }
            }
            
            writer.println(sb.toString());
        }

        // Close it down and return
        writer.close(); reader.close();
        return temp;
    }

    public String getTempCSV() {
        return tempCSV;
    }

    public void setTempCSV(String tempCSV) {
        this.tempCSV = tempCSV;
    }

    public String getTargetARFF() {
        return targetARFF;
    }

    public void setTargetARFF(String targetARFF) {
        this.targetARFF = targetARFF;
    }
}
