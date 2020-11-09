package com.silikonm.common.interfaces;

/**
 * @author harsha
 */
public interface Command {

    public Object execute(com.silikonm.common.factory.DAOFactory factory);
}
