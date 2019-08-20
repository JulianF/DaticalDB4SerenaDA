DaticalDB4SerenaDA
=================

This plugin brings Datical DB functionality to Serena Deployment Automation.

Two properties in the plugin step, Datical DB Install Directory and Datical DB Drivers Directory, read a default property at the resource (Agent) level. For each agent that is going to execute Datical DB, create a Resource Property called daticalDBCmd and daticalDBDriversDir.  

Release Notes:

### Version 1.162 - March 10, 2015

	- Added support for arbitrary Groovy Script execution.
	- NOTE: need to create target directory prior to running "Create Datical DB Project"

### Version 1.160 - March 4, 2015

	- Added support for creating new projects using our project_creator.groovy script. New Step Name is "Create Datical DB Project".
	- Added support for baselining existing projects using our project_baseline.groovy script. New Step Name is "Register and Baseline Datical DB Project"
	- Added support for "show version"
	
### Version 1.158 - January 27, 2015

 - Added support for Labels in Forecast.

### Version 1.157 - January 12, 2015

 - Added support for Labels in Deploy and Diff Change Log.

### Version 1.156 - December 1, 2014

 - Fixed issue with "Datical DB JVM Arguments" in Groovy scripts. NOTE: not bumping internal version number.

### Version 1.155 - November 19, 2014

- Fixed bug with Deploy Threshold values in plugin.xml.
- Fixed bug in deploy.groovy script that prevented the Deploy step from working.

### Version 1.154 - November 18, 2014

To support customers that wish to dynamically choose the JVM when Datical DB is executed, the command line now supports two new arguments:

 - --vm Path to a JDK install
 - --vmargs JVM arguments

The JVM arguments must be the LAST thing on the command line.

Examples:
> $ hammer show version --vm /usr/lib/jvm/java-7-openjdk-amd64 --vmargs -Xmx1024M

> C:\Users\wesley\product\repl>.\hammer.bat show version --vm "C:\Program Files\Java\jdk1.7.0_17" --vmargs -Dmx1024M

Thus, we've added new new text boxes to each Datical DB Step, Datical DB JVM and Datical DB JVM Arguments. Neither are required.

There is a known issue with placing multiple arguments in the "Datical DB JVM Arguments" text box. Serema Deployment Automation passes all properties as quoted strings. Thus, *--vmargs -Xms512M -Dmx512M* becomes *--vmargs "-Xms512M -Dmx512M"*. We are working with Serena on a resolution to this issue.

