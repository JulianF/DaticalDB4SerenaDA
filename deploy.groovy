import com.urbancode.air.CommandHelper;

final def inputPropsFile = new File(args[0])
final def outputPropsFile = new File(args[1])

final def props = new Properties()
try {
    props.load(new FileInputStream(inputPropsFile))
}
catch (IOException e) {
    throw new RuntimeException(e)
}

final def cwd = new File('.');
final def cmdHelper = new CommandHelper(cwd);



//--------------------------------------------------------------------------------------------------
def getAbsPath(def file) {
    def tempFile = null;
    if (file != null && file != "") {
        File temporaryFile = new File(file);
        tempFile = temporaryFile.getAbsolutePath();
    }
    return tempFile;
}
//path properties
def daticalDBCmd = getAbsPath(props['daticalDBCmd']);
def daticalDBDriversDir = getAbsPath(props['daticalDBDriversDir']);
def daticalDBProjectDir = getAbsPath(props['daticalDBProjectDir']);
def daticalDBAction = "deploy";
def daticalDBServer = props['daticalDBServer'];
def daticalDBContext = props['daticalDBContext'];
def daticalDBRollback = props['daticalDBRollback']
def daticalDBExportSQL = props['daticalDBExportSQL'];
def daticalDBExportRollbackSQL = props['daticalDBExportRollbackSQL'];

if (daticalDBRollback == "false") {
    daticalDBAction = "deploy";
} else {
	daticalDBAction = "deploy-autoRollback";
}

def cmdArgs = ""; 

if (daticalDBExportSQL == "true") {
	
	if (daticalDBExportRollbackSQL == "true") {
		
		cmdArgs = [daticalDBCmd, '-drivers', daticalDBDriversDir, '--project', daticalDBProjectDir, "--genSQL", "--genRollbackSQL"];
		
	} else {
	
		cmdArgs = [daticalDBCmd, '-drivers', daticalDBDriversDir, '--project', daticalDBProjectDir, "--genSQL"];

	}
	
} else if (daticalDBExportRollbackSQL == "true") {

	cmdArgs = [daticalDBCmd, '-drivers', daticalDBDriversDir, '--project', daticalDBProjectDir, "--genRollbackSQL"];

} else {

	cmdArgs = [daticalDBCmd, '-drivers', daticalDBDriversDir, '--project', daticalDBProjectDir];

}

if (daticalDBContext) {
	cmdArgs << "--context";
	cmdArgs << daticalDBContext;
}

cmdArgs << daticalDBAction;
cmdArgs << daticalDBServer;

int exitCode = cmdHelper.runCommand("Executing Datical DB", cmdArgs);

System.exit(exitCode);
