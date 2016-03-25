package blueberryco.workouttracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;

import blueberryco.entities.backup.BackupAgent;
import blueberryco.entities.backup.ServiceCallResult;

/**
 * Created by boiko on 07-Mar-16.
 */
public abstract class BaseDriveActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "BaseDriveActivity";

    private static final String FILE_ON_DRIVE_TITLE = "9701532A-80B7-4059-B86C-E6D18D0E7343";
    private static final String FOLDER_ON_DRIVE_TITLE = "FitInBackupFolder";
    private static final String FOLDER_ON_DRIVE_DESCRIPTION =
            "Папка, създадена от приложението FitIn \n" +
                    "с цел бекъп на данни. Ако изтриете папката и \n" +
                    "прилежащите й файлове, приложението няма да може да \n" +
                    "синхронизира данни!";

    ProgressDialog progressDialog;

    /**
     * Request code for auto Google Play Services error resolution.
     */
    protected static final int REQUEST_CODE_RESOLUTION = 1;

    /**
     * Google API client.
     */
    private GoogleApiClient mGoogleApiClient;

    /**
     * Called when activity gets visible. A connection to Drive services need to
     * be initiated as soon as the activity is visible. Registers
     * {@code ConnectionCallbacks} and {@code OnConnectionFailedListener} on the
     * activities itself.
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(this);
    }

    /**
     * Handles resolution callbacks.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESOLUTION && resultCode == RESULT_OK) {
            mGoogleApiClient.connect();
        }
    }

    /**
     * Called when activity gets invisible. Connection to Drive service needs to
     * be disconnected as soon as an activity is invisible.
     */
    @Override
    protected void onPause() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }

    /**
     * Called when {@code mGoogleApiClient} is connected.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "GoogleApiClient connected");
    }

    /**
     * Called when {@code mGoogleApiClient} is disconnected.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "GoogleApiClient connection suspended");
    }

    /**
     * Called when {@code mGoogleApiClient} is trying to connect but failed.
     * Handle {@code result.getResolution()} if there is a resolution is
     * available.
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "GoogleApiClient connection failed: " + result.toString());
        if (!result.hasResolution()) {
            // show the localized error dialog.
            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show();
            return;
        }
        try {
            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);
        } catch (IntentSender.SendIntentException e) {
            Log.e(TAG, "Exception while starting resolution activity", e);
        }
    }

    /**
     * Shows a toast message.
     */
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Getter for the {@code GoogleApiClient}.
     */
    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    protected void connectToGoogleDrive(){
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Drive.API)
                    .addScope(Drive.SCOPE_FILE)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    protected void disconnectFromGoogleDrive(){
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    final ResultCallback<DriveApi.MetadataBufferResult> queryMetadataResult = new ResultCallback<DriveApi.MetadataBufferResult>() {
        @Override
        public void onResult(DriveApi.MetadataBufferResult result) {
            if (!result.getStatus().isSuccess()) {
                showMessage("Problem while retrieving results");
                return;
            }

            BackupDataAsyncTask task = new BackupDataAsyncTask();
            if(result.getMetadataBuffer().getCount() > 0){
                DriveFolder folder = result.getMetadataBuffer().get(0).getDriveId().asDriveFolder();
                task.execute(folder);
            } else {
                task.execute((DriveFolder)null);
            }
        }
    };

    final ResultCallback<DriveApi.MetadataBufferResult> restoreMetadataResult = new ResultCallback<DriveApi.MetadataBufferResult>() {
        @Override
        public void onResult(DriveApi.MetadataBufferResult result) {
            if (!result.getStatus().isSuccess()) {
                showMessage("Problem while retrieving results");
                return;
            }

            if(result.getMetadataBuffer().getCount() > 0) {
                final DriveFile file = result.getMetadataBuffer().get(0).getDriveId().asDriveFile();
                RestoreDataAsyncTask task = new RestoreDataAsyncTask();
                task.execute(file);
            }else {
                showDialogMessage("Backup file not found on drive!");
            }
        }
    };

    private class BackupDataAsyncTask extends AsyncTask<DriveFolder, Void, ServiceCallResult<DriveFolder.DriveFileResult>> {

        @Override
        protected ServiceCallResult<DriveFolder.DriveFileResult> doInBackground(DriveFolder... params) {
            DriveFolder folder = params[0];

            ServiceCallResult<DriveFolder.DriveFileResult> result = new ServiceCallResult();

            if(folder != null){
                Query query = new Query.Builder()
                        .addFilter(Filters.eq(SearchableField.TITLE, FILE_ON_DRIVE_TITLE))
                        .build();

                DriveApi.MetadataBufferResult fileMetadataResult = Drive.DriveApi.query(getGoogleApiClient(), query).await();
                if(!fileMetadataResult.getStatus().isSuccess()){
                    result.setMessage("Problem finding existing backup file!");
                    result.setResult(null);
                    return result;
                }

                if(fileMetadataResult.getMetadataBuffer().getCount() > 0){
                    DriveFile file = fileMetadataResult.getMetadataBuffer().get(0).getDriveId().asDriveFile();
                    if(!file.delete(getGoogleApiClient()).await().isSuccess()){
                        result.setMessage("Error deleting old backup file!");
                        result.setResult(null);
                        return result;
                    }


                    DriveApi.DriveContentsResult driveContentsResult = Drive.DriveApi.newDriveContents(getGoogleApiClient()).await();
                    if(!driveContentsResult.getStatus().isSuccess()){
                        result.setMessage("Error while trying to create new backup file contents!");
                        result.setResult(null);
                        return result;
                    }

                    BackupAgent backupAgent = new BackupAgent(BaseDriveActivity.this);
                    backupAgent.writeToOutputStream(driveContentsResult.getDriveContents().getOutputStream());

                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle(FILE_ON_DRIVE_TITLE)
                            .build();

                    DriveFolder.DriveFileResult driveFileResult = folder.createFile(getGoogleApiClient(), changeSet, driveContentsResult.getDriveContents()).await();
                    result.setResult(driveFileResult);
                    return result;

                } else {
                    result.setMessage("Existing backup file not found!");
                    result.setResult(null);
                    return result;
                }
            }else {
                MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                        .setTitle(FOLDER_ON_DRIVE_TITLE)
                        .setDescription(FOLDER_ON_DRIVE_DESCRIPTION)
                        .build();
                DriveFolder.DriveFolderResult newFolderResult = Drive.DriveApi.getRootFolder(getGoogleApiClient()).createFolder(getGoogleApiClient(), changeSet).await();
                if(!newFolderResult.getStatus().isSuccess()){
                    result.setMessage("Error creating backup folder!");
                    result.setResult(null);
                    return result;
                }

                DriveApi.DriveContentsResult driveContentsResult = Drive.DriveApi.newDriveContents(getGoogleApiClient()).await();
                if(!driveContentsResult.getStatus().isSuccess()){
                    result.setMessage("Error while trying to create new backup file contents!");
                    result.setResult(null);
                    return result;
                }

                BackupAgent backupAgent = new BackupAgent(BaseDriveActivity.this);
                backupAgent.writeToOutputStream(driveContentsResult.getDriveContents().getOutputStream());

                MetadataChangeSet fileChangeSet = new MetadataChangeSet.Builder()
                        .setTitle(FILE_ON_DRIVE_TITLE)
                        .build();

                DriveFolder.DriveFileResult driveFileResult = newFolderResult.getDriveFolder().createFile(getGoogleApiClient(), fileChangeSet, driveContentsResult.getDriveContents()).await();
                result.setResult(driveFileResult);
                return result;
            }
        }

        @Override
        protected void onPostExecute(ServiceCallResult<DriveFolder.DriveFileResult> result){
            showProgress(false, null);

            if(!result.isSuccess()){
                Log.e("-- ERROR BACKUP --", result.getMessage());
                showDialogMessage(getResources().getString(R.string.sync_error));
            }else {
                showDialogMessage(getResources().getString(R.string.backup_completed));
            }
        }
    }

    private class RestoreDataAsyncTask extends AsyncTask<DriveFile, Void, ServiceCallResult<DriveApi.DriveContentsResult>>{

        @Override
        protected ServiceCallResult<DriveApi.DriveContentsResult> doInBackground(DriveFile... params) {
            ServiceCallResult<DriveApi.DriveContentsResult> result = new ServiceCallResult<>();

            DriveApi.DriveContentsResult contentsResult = params[0].open(getGoogleApiClient(), DriveFile.MODE_READ_ONLY, null).await();
            result.setResult(contentsResult);
            return result;
        }

        @Override
        protected void onPostExecute(ServiceCallResult<DriveApi.DriveContentsResult> result) {
            showProgress(false, null);

            if(!result.isSuccess()){
                Log.e("-- ERROR RESTORE --", result.getMessage());
                showMessage(getResources().getString(R.string.sync_error));
                return;
            }

            DriveContents contents = result.getResult().getDriveContents();
            BackupAgent agent = new BackupAgent(BaseDriveActivity.this);
            agent.readFromInputStream(contents.getInputStream());

            showDialogMessage(getResources().getString(R.string.restore_completed));
        }
    }

    protected void backup(){
        if(!isNetworkAvailable()){
            showDialogMessage(this.getResources().getString(R.string.network_off_message));
            return;
        }

        connectToGoogleDrive();

        showProgress(true, getResources().getString(R.string.backup_in_progress));

        Query query = new Query.Builder()
                .addFilter(Filters.eq(SearchableField.TITLE, FOLDER_ON_DRIVE_TITLE))
                .build();
        Drive.DriveApi.query(getGoogleApiClient(), query).setResultCallback(queryMetadataResult);

        disconnectFromGoogleDrive();
    }

    protected void restore(){
        if(!isNetworkAvailable()){
            showDialogMessage(this.getResources().getString(R.string.network_off_message));
            return;
        }

        connectToGoogleDrive();

        showProgress(true, getResources().getString(R.string.restore_in_progress));

        Query restoreQuery = new Query.Builder()
                .addFilter(Filters.eq(SearchableField.TITLE, FILE_ON_DRIVE_TITLE))
                .build();
        Drive.DriveApi.query(getGoogleApiClient(), restoreQuery).setResultCallback(restoreMetadataResult);
        disconnectFromGoogleDrive();
    }

    protected void showProgress(boolean show, String message){
        if(!TextUtils.isEmpty(message)){
            progressDialog.setMessage(message);
        }

        if(show){
            progressDialog.show();
        }else {
            progressDialog.dismiss();
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void showDialogMessage(String message){
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getResources().getString(R.string.information));
        alertDialog.setMessage(message);
        DialogInterface.OnClickListener listener =  new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", listener);
        alertDialog.show();
    }
}
