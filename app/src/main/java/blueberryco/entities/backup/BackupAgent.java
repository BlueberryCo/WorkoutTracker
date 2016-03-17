package blueberryco.entities.backup;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

import blueberryco.database.DatabaseHelper;

/**
 * Created by boiko on 25-Feb-16.
 */
public class BackupAgent {

    private static final String BACKUP_FILE_NAME = "backupeddatabase.db";
    private static final String BACKUP_FILE_FOLDER = "backupfiles/";
    private static final String DATABASE_FILE_FOLDER = "databases/";

    private final File databaseFile;
    private final String databaseName;
    private final String fitInFilePath;

    private final String backupFolderPath;
    private final String backupFile;

    private final String databaseFolderPath;


    public BackupAgent(Context context){

        fitInFilePath = context.getFilesDir().getParent() + File.separator;
        databaseName = DatabaseHelper.getMainDatabaseName();
        databaseFile = context.getDatabasePath(databaseName);

        backupFolderPath = fitInFilePath + BACKUP_FILE_FOLDER;
        backupFile = backupFolderPath + BACKUP_FILE_NAME;

        databaseFolderPath = fitInFilePath + DATABASE_FILE_FOLDER;
    }

    public void copyDbFileForBackup(){

        File sourceFile = databaseFile;
        File destinationFile = new File(backupFile);

        File destinationFileDir = new File(backupFolderPath);

        if(!destinationFileDir.exists()){
            destinationFileDir.mkdir();
        }

        if(destinationFile.exists()){
            destinationFile.delete();
        }

        try {
            FileChannel src = new FileInputStream(sourceFile).getChannel();
            FileChannel dst = new FileOutputStream(destinationFile).getChannel();

            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();
        } catch (IOException e) {
            Log.e("-- BACKUP FILE --", Log.getStackTraceString(e));
        }
    }

    public void restoreDbFileFromBackup(){

        File sourceFile = new File(backupFile);
        File destinationFile = databaseFile;

        File destinationFileDir = new File(databaseFolderPath);

        if(!destinationFileDir.exists()){
            destinationFileDir.mkdir();
        }

        if(destinationFile.exists()){
            destinationFile.delete();
        }

        try {
            FileChannel src = new FileInputStream(sourceFile).getChannel();
            FileChannel dst = new FileOutputStream(destinationFile).getChannel();

            dst.transferFrom(src, 0, src.size());
            src.close();
            dst.close();
        } catch (IOException e) {
            Log.e("-- RESTORE FILE --", Log.getStackTraceString(e));
        }
    }

    public void writeToOutputStream(OutputStream outputStream){
        copyDbFileForBackup();

        File file = new File(backupFile);

        try {
            FileInputStream inputStream = new FileInputStream(file);

            byte[] buf = new byte[8192];

            int c = 0;
            while ((c = inputStream.read(buf, 0, buf.length)) > 0) {
                outputStream.write(buf, 0, c);
                outputStream.flush();
            }

            inputStream.close();
        }catch (IOException e){
            Log.e("-- Output stream --", Log.getStackTraceString(e));
        }
    }

    public void readFromInputStream(InputStream inputStream){
        File destinationFolderLocal = new File(backupFolderPath);
        File destinationFile = new File(backupFile);

        if(!destinationFolderLocal.exists()){
            destinationFolderLocal.mkdir();
        }

        if(destinationFile.exists()){
            destinationFile.delete();
        }

        try {
            OutputStream fileOutputStream = new FileOutputStream(destinationFile);

            int read = 0;
            byte[] bytes = new byte[8192];

            while ((read = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }

            fileOutputStream.close();
        } catch (IOException e) {
            Log.e("-- Output stream --", Log.getStackTraceString(e));
        }

        restoreDbFileFromBackup();
    }
}
