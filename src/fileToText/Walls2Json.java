package fileToText;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Walls2Json {
    private static List<File> images;
    private static File[] subfolderImages;
    private static List<File[]> subfolderIndex;
    private static int subfolderNumber = 0;
    private static StringBuilder walls;

    public static void main(String args[]) {
        // Get .jar directory
        String dir = new File(Walls2Json.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
        // Replace %20 with real spaces
        dir = dir.replace("%20", " ");

        // Show progress & Scan
        System.out.println("\nDirectory: " + dir + "\nScanning...");
        images = new ArrayList<File>(Arrays.asList(Scan4Images(dir)));

        // Scan for subfolders
        // Add images in subfolder to list of arrays
        // Also add those images to the master list
        File[] subfolders = Scan4Subfolders(dir);
        subfolderIndex = new ArrayList<File[]>();
        for (File f : subfolders) {
            String subfolderDir = f.getAbsolutePath();
            subfolderImages = Scan4Images(subfolderDir);
            Arrays.sort(subfolderImages);
            subfolderIndex.add(subfolderImages);
            subfolderNumber++;
        }

        // Write images if found, else return error message
        if (images.size() > 0) {
            System.out.println("Found " + images.size() + " images");

            writeJson();
            saveFiles(dir);
            System.out.print("Finished!");
        } else
            System.out.print("Could not find any .png image files. :(");
    }

    private static File[] Scan4Images(String dir) {
        File directory = new File(dir);

        return directory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".png") || filename.endsWith(".PNG");
            }
        });
    }

    private static File[] Scan4Subfolders(String dir) {
        File directory = new File(dir);

        return directory.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
    }

    private static void writeJson() {
        // Initialize String
        walls = new StringBuilder();

        // Write headers
        walls.append("{\n");
        walls.append("\t\"Frames\": {\n");
        walls.append("\t\t\"categories\": [\n");

        // Loop through all the subfolders that need to have their own string-array
        for (File[] subfolder : subfolderIndex) {
        	for (int i = 0; i < subfolderNumber; i++) {
	            if (subfolder.length > 0) {
	
	                // Get name of subfolder
	                String subfolderName = subfolder[0].getParentFile().getName();
	
	
	                // Starting writing this subfolder's array
	                walls.append("\t\t\t{\n");
	                walls.append("\t\t\t\t\"name: \": \"" + subfolderName + "\",\n");
	                walls.append("\t\t\t\t\"wallpapers\": [\n");
	
	                // Loop through all the images within this subfolder and assign a value for each
	                String urlPrefix = "https://raw.githubusercontent.com/asdfasdfvful/Pitched-Wallpapers/master/Material_Glass/";
	                
	                for (File img : subfolder) {
	                	for (int i2 = 0; i2 < subfolderImages.length; i2++)
	                    {
	                    	walls.append("\t\t\t\t\t{\n");
	                    	walls.append("\t\t\t\t\t\t\"author\": \"Pitched Apps\",\n");
//	                    	walls.append("\t\t\t\t\t\t\"url\": \"" + urlPrefix + subfolderImages[i].getName() + "\",\n");
		                    walls.append("\t\t\t\t\t\t\"url\": \"" + urlPrefix + img.getName() + "\",\n");
//		                    walls.append("\t\t\t\t\t\t\"name\": \"" + subfolderImages[i].getName().replace(".png", "").replace(".PNG", "") + "\"\n");
		                    walls.append("\t\t\t\t\t\t\"name\": \"" + img.getName().replace(".png", "").replace(".PNG", "") + "\"\n");
		                    walls.append("\t\t\t\t\t\t\"dimensions\": \"3200x2560\"\n");
		                    walls.append("\t\t\t\t\t\t\"copyright\": \"CreativeCommons Attribution-NoDerivatives-NoCommercial\"\n");
		                    if (i == subfolderImages.length - 1) {
		                    	walls.append("\t\t\t\t\t}\n");
		                    } else {
		                    	walls.append("\t\t\t\t\t},\n");
		                    }
		                    walls.append("\t\t\t\t\t{\n");
		                }	
	                }
	
	                // Close this subfolder's array
	                walls.append("\t\t\t\t]\n");
	                if (i == subfolderNumber - 1) {
	                	walls.append("\t\t\t}\n");
                	} else {
                		walls.append("\t\t\t},\n");
	                }
	
	            }
        	}
        }

        // Write footers
        walls.append("\t\t]\n");
        walls.append("\t}\n");
        walls.append("}\n");
    }

    private static void saveFiles(String dir) {
        // Save file
    	String fileName = "0wallpaper.json";
        try {
            File wallsFile = new File(dir, fileName);
            FileOutputStream f = new FileOutputStream(wallsFile);
            f.write(walls.toString().getBytes());
            f.close();
        } catch (Exception e) {
            System.out.println("Oops, an error occured while writing " + fileName);
        }
    }
}