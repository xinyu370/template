package com.example.demo.service.impl;

import java.io.Serializable;
import java.util.Set;

public interface ClientDetails extends Serializable {

    String getClientId();

    String getClientSecret();

    Set<String> getScope();

    Set<String> getAuthorizedGrantTypes();

}
