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
def daticalDBProjectName = props['daticalDBProjectName'];
def daticalDBProvisioningDir = getAbsPath(props['daticalDBProvisioningDir']);
def daticalDBReplace = props['daticalDBReplace'];
def daticalDBZipProject = props['daticalDBZipProject'];
def daticalDBTestConnections = props['daticalDBTestConnections']
def daticalDBvm = props['daticalDBvm'];
def daticalDBvmargs = props['daticalDBvmargs'];

def daticalDBHammerPath = new File(daticalDBCmd).getParent();
def daticalDBProjectCreatorScript = 'file:/' + daticalDBHammerPath + File.separator + "scripts" + File.separator + 'project_creator.groovy';

def cmdArgs = [daticalDBCmd, 'groovy', daticalDBProjectCreatorScript, daticalDBDriversDir, daticalDBProvisioningDir, daticalDBProjectDir, daticalDBProjectName];

if (daticalDBReplace == "true") {
	cmdArgs << "replace";
}

if (daticalDBTestConnections == "true") {
	cmdArgs << "testConnections";
}

if (daticalDBvm) {
	cmdArgs << "--vm";
	cmdArgs << daticalDBvm;
}

if (daticalDBvmargs) {
	cmdArgs << "--vmargs";
	String[] myArray = daticalDBvmargs.split();
	for ( x in myArray ) {
		cmdArgs << x;
	}
}

int exitCode = cmdHelper.runCommand("Executing Datical DB", cmdArgs);

System.exit(exitCode);
