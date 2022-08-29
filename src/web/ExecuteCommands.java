package web;

import com.qualitia.constants.ExecutionResult;
import com.qualitia.execution.ActionResponse;
import com.qualitia.execution.ApplicationLogger;
import com.webdriverfw.Wrappers.General;
import java.io.File;
import java.io.IOException;

public class ExecuteCommands extends General {

    /**
     * This actions executes the commands that can be run in cmd prompt.
     * It also executes batch files.
     *
     * @param command executable command
     * @param dir folder location at which we want to run command, it can be empty also
     * @return
     * @throws IOException
     */
    public ActionResponse ExecuteInCommandPrompt(String command, String dir) throws IOException {
        ActionResponse actionResponse = getActionResponse();
        ApplicationLogger applicationLogger = actionResponse.getApplicationLogger();

        String commandToExecute;
        if (command.contains(".bat")) {
            commandToExecute = "cmd.exe /c start \"\" " + "\"" + command + "\"";
        } else {
            commandToExecute = "cmd.exe /c " + command;
        }
        applicationLogger.writeToInfoLog(commandToExecute);

        if (!dir.isEmpty()) {
            // Open command prompt in specified directory and then run commands.
            // (Here to run any executable/batch you will have to provide the respective folder path as 2nd parameter)
            Runtime.getRuntime().exec(commandToExecute, null, new File(dir));
        } else {
            // Open command prompt in default directory and run commands.
            // (here to run any executable/ batch file you will have to provide its complete path)
            Runtime.getRuntime().exec(commandToExecute);
        }
        actionResponse.setMessage("Command executed successfully.");
        actionResponse.setExecutionResult(ExecutionResult.PASSED);

        return actionResponse;
    }
}
