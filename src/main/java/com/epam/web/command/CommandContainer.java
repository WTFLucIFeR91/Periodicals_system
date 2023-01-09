package com.epam.web.command;

import com.epam.web.command.admin.*;
import com.epam.web.command.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.HashMap;
import java.util.Map;

public class CommandContainer {

    private static final Logger log = LogManager.getLogger(CommandContainer.class);
    private static final Map<String,Command> commands = new HashMap<>();
    static {

        commands.put("mainPage", new MainPageCommand());
        commands.put("signup", new SignupCommand());
        commands.put("login",new LoginCommand());

        commands.put("clientPage", new ClientPageCommand());
        commands.put("clientProfile", new ClientProfileCommand());
        commands.put("showSubscriptions", new ShowSubscriptionsCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("updateUser",new UpdateUserCommand());
        commands.put("payment", new PaymentCommand());
        commands.put("paymentForm", new PaymentFormCommand());
        commands.put("subscribe", new SubscribeCommand());

        commands.put("deletePeriodical", new DeletePeriodical());
        commands.put("editPeriodical", new EditPeriodical());
        commands.put("addPeriodical", new AddPeriodicalCommand());

        commands.put("adminPage" , new AdminPageCommand());
        commands.put("adminProfile", new AdminProfileCommand());
        commands.put("showUsers", new ShowUsersCommand());


        commands.put("block", new BlockUserCommand());
        commands.put("unblock", new UnblockUserCommand());




        commands.put("topUpBalance", new TopUpBalanceCommand());
        commands.put("noCommand", new NoCommand());




        log.debug("Command container was successfully initialized");
        log.trace("Number of commands --> " + commands.size());

    }

    public static Command getCommand (String commandName){
        if (commandName == null || !commands.containsKey(commandName)){
            log.warn("Command not found, commandName =>" + commandName);
            return commands.get("noCommand");
        }
        return commands.get(commandName);
    }
}
