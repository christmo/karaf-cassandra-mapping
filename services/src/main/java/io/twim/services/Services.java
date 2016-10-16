package io.twim.services;

import io.twim.model.Client;

/**
 * Created by christmo on 16/10/16.
 */
public interface Services {

    void saveClient(Client client, Class<Client> clientClass);
}
