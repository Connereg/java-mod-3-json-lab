import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FileIORunner {
    public static void main(String[] args) throws Exception {
        // your code here

        Person conner = new Person("conner", "gleason", 1992,3,3);
        Person jake = new Person("Jake", "Morbius", 1991, 4, 10 );
        Person yugioh = new Person( "Yugi", "Ho", 1290, 9, 12);
        Person pegasus = new Person("Pegasus", "Maximillion", 1900, 2, 18);


        //Create the list
        List<Person> personList = new ArrayList<Person>();

        personList.add(conner);
        personList.add(jake);
        personList.add(yugioh);
        personList.add(pegasus);

        StringBuffer personStringBuffer = new StringBuffer();

        writeToFile("simple.json", personList);


        //READING JSON FROM SIMPLE.DATA FILE
        List<Person> restoredPersons = Arrays.asList(new ObjectMapper().readValue(new File("simple.json"), Person[].class));
        for ( Person person : restoredPersons) {
            Logger.getInstance().log(person.getFirstName() + " " + person.getLastName());
        }

        // READER WRITER LAB v

//        writeToFile("simple.data", personStringBuffer.toString());
//        Logger.getInstance().log(readFromFile("simple.data", true));

    }


    public static Person readJson() throws IOException {
        Path filePath = Path.of("simple.data");

        String content = Files.readString(filePath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Person.class);
    }

    // FILE WRITER
    static void writeToFile(String fileName, List<Person> personList) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fileName);
            String json = new ObjectMapper().writeValueAsString(personList);
            fileWriter.write(json);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            if (fileWriter != null)
                fileWriter.close();
        }

    }

}