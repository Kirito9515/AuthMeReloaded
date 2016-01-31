package fr.xephi.authme.command.executable.authme;

import fr.xephi.authme.AuthMe;
import fr.xephi.authme.ConsoleLogger;
import fr.xephi.authme.command.CommandService;
import fr.xephi.authme.command.ExecutableCommand;
import fr.xephi.authme.output.MessageKey;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * The reload command.
 */
public class ReloadCommand implements ExecutableCommand {

    @Override
    public void executeCommand(CommandSender sender, List<String> arguments, CommandService commandService) {
        AuthMe plugin = commandService.getAuthMe();
        try {
            commandService.getSettings().reload();
            commandService.reloadMessages(commandService.getSettings().getMessagesFile());
            plugin.setupDatabase();
            commandService.send(sender, MessageKey.CONFIG_RELOAD_SUCCESS);
        } catch (Exception e) {
            sender.sendMessage("Error occurred during reload of AuthMe: aborting");
            ConsoleLogger.showError("Fatal error occurred! AuthMe instance ABORTED!");
            ConsoleLogger.writeStackTrace(e);
            plugin.stopOrUnload();
        }
    }
}
