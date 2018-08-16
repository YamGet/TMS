package org.fidel.tms.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class DatabaseBackup extends TimerTask {
	
	public static boolean backupDataWithOutDatabase(String dumpExePath, String host, String port, String user, String password, String database, String backupPath){
		
		boolean status = false;
		
		try{
			Process p = null;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
			Date date = new Date();
			String filepath = "backup(without_DB)-" + database + "-" + host + "-(" + dateFormat.format(date) + ").sql";
			
			Runtime runtime = Runtime.getRuntime();
			
			p = runtime.exec(new String[]{dumpExePath, "-uroot", "-ppassword", database, "-r\"" + backupPath + "" + filepath + "\"" });
			
			int processComplete = p.waitFor();
			
			if(processComplete == 0){
				status = true;				
				//Log.info("Backup created successfully for with out DB " + database + " in " + host + ":" + port);
			} else {
				status = false;
				//Log.info("Couldn't create the backup successfully for with out DB " + database + " in " + host + ":" + port);
			}
			
		} catch(IOException ioe){
			//Log.error(ioe, ioe);
		} catch(Exception ex){
			//Log.error(ex, ex);
		}		
		
		return status;
	}

	public static boolean backupDataWithDatabase(String dumpExePath, String host, String port, String user, String password, String database, String backupPath){
		
		boolean status = false;
		
		try{
			Process p = null;
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
			Date date = new Date();
			
			String filepath = "backup(with_DB)-" + database + "-" + host + "-(" + dateFormat.format(date) + ").sql";
			
			Runtime runtime = Runtime.getRuntime();
			
			p = runtime.exec(new String[]{dumpExePath, "-uroot", "-ppassword", database, "-r\"" + backupPath + "" + filepath + "\"" });
			
			int processComplete = p.waitFor();
			
			if(processComplete == 0){
				status = true;
				//Log.info("Backup created successfully for with DB " + database + " in " + host + ":" + port);
			} else {
				status = false;
				//Log.info("Couldn't create the backup successfully for with DB " + database + " in " + host + ":" + port);
			}
			
		} catch(IOException ioe){
			//Log.error(ioe, ioe);
		} catch (Exception e) {
			//Log.error(e, e);
		}
				
		return status;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		backupDataWithDatabase(SysConstant.DUMP_EXE_PATH, SysConstant.HOST, SysConstant.PORT, SysConstant.DATABASE_USERNAME, SysConstant.DATABASE_PASSWORD, SysConstant.SYS_NAME, SysConstant.DATABASE_BACKUP_FOLDER);
	}

}
