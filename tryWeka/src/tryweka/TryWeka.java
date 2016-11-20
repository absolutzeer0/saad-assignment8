/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryweka;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import weka.associations.Apriori;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author matt
 */
public class TryWeka {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, Exception {
        String sourceFileName = "/home/matt/Documents/SoftwareArchitectureDesign/Assignment6/testFiles/test_case_example/courses.csv";
        String targetFileName = "/home/matt/Documents/SoftwareArchitectureDesign/Assignment6/testFiles/test_case_example/courses.arff";
        String tempFileName = "/home/matt/Documents/SoftwareArchitectureDesign/Assignment6/testFiles/test_case_example/tempCourses.csv";
        File source = new File(sourceFileName);
        File target = new File(targetFileName);
        File temp = new File(tempFileName);
        PrintWriter writer = new PrintWriter(temp);
        
        //Pre-process the CSV file and re-save it to the temporary CSV file location 
        BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));
        String line = "";
        String[] readLine;
        writer.println("courseID,courseName,semester1,semester2,semester3"); // writer header to csv file
        while ((line = reader.readLine()) != null){
            readLine = line.split(",");
            // if the line that was read is not the same size, create an array 
            // that is the same size and write to the csv file instead
            if (readLine.length != 5){
                String[] newReadLine = new String[5];
                System.arraycopy(readLine,0,newReadLine,0,readLine.length);
                writer.println(Arrays.toString(newReadLine));
            }else{
                writer.println(Arrays.toString(readLine));
            }
            
        }
        writer.close();
        
        // load the temporary csv file
        CSVLoader loader = new CSVLoader();
        temp = new File(tempFileName);
        loader.setSource(temp);
        Instances data = loader.getDataSet(); // instances object
        
        // save ARFF
        ArffSaver saver = new ArffSaver();
        saver.setInstances(data);
        saver.setFile(target);
        saver.writeBatch();
        
        //ARFF Data has been written. Now we do the Apriori algorithm
        ///////////////////////////////////////////////////////////////////////
        // Load ARFF data
        Instances algoData = DataSource.read(targetFileName);
        algoData.setClassIndex(algoData.numAttributes() - 1);
        
        // build associator
        Apriori apriori = new Apriori();
        apriori.setClassIndex(algoData.classIndex());
        apriori.buildAssociations(algoData);

        // output associator
        System.out.println(apriori);
    }
    
}
