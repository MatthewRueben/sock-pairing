package matthew_rueben.pairing.performance;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Matt Rueben
 */
public class ExportingToCSV {


    private static void writeRowToCSV(FileWriter writer, Object[] row)
            throws IOException
    {   for (Object element : row) {
            if (element != null) writer.write(element.toString());
            writer.write(",");
        }
        writer.write("\n");
    }

    public static void exportToCSV(String filePath, String[] headers, Object[][] table)
            throws IOException
    {   try (FileWriter writer = new FileWriter(filePath))
        {   writeRowToCSV(writer, headers);
            for (Object[] row : table) writeRowToCSV(writer, row);
        }
    }

    /**
     * Example usage.
     */
    public static void main(String[] args)
    {
        String[] headers = {"Group","Participant","Success?"};

        Object[][] table = new Object[3][3];

        int rowIndex;

        rowIndex = 0;
        table[rowIndex][0] = "Control";
        table[rowIndex][1] = 1;
        table[rowIndex][2] = false;

        rowIndex = 1;
        table[rowIndex][0] = "Treatment";
        table[rowIndex][1] = 2;
        // Blanks are handled.

        String pathName = "/Users/matth/Downloads/";

        String dateAndTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        dateAndTime = dateAndTime.replace('T','_').replace(':',' ').replace(".","__");
        //dateAndTime = dateAndTime.substring(0, dateAndTime.indexOf('.'));

        String filePath = pathName + "test" + "@" + dateAndTime + ".csv";

        try {
            ExportingToCSV.exportToCSV(filePath, headers, table);
        } catch (IOException e) {
            System.err.println("Error creating CSV file: " + e.getMessage());
        }
    }
}
