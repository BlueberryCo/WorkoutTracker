package blueberryco.entities.backup;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import blueberryco.database.DatabaseHelper;

/**
 * Created by boiko on 25-Feb-16.
 */
public class BackupAgent {

    private static final String BACKUP_FILE_NAME = "backupeddatabase";
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
}
